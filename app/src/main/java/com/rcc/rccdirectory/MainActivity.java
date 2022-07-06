package com.rcc.rccdirectory;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    infoAdapter infoAdapter;
    List<Info> mInfo;
    ImageButton search;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        search = findViewById(R.id.searchBtn);
        setSupportActionBar(toolbar);
        mInfo = new ArrayList<Info>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("info");
        recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mInfo.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Info info = dataSnapshot.getValue(Info.class);
                    mInfo.add(info);
                }
                infoAdapter = new infoAdapter(getBaseContext(), mInfo);
                recyclerView.setAdapter(infoAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                search();
                return true;
            case R.id.filter:
                filter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    int i = 0;

    private void search() {
        LinearLayout searchB = findViewById(R.id.searchBar);
        if(i == 0){
            i = 1;
            searchB.setVisibility(View.VISIBLE);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            i = 0;
            searchB.setVisibility(View.GONE);
        }

    }

    private void filter() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

    }





}