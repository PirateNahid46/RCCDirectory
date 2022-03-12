package com.rcc.rccdirectory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<String> list , cadetNo, nameList, batchList, houseList, homeList, disList, conList, workList, emailList, miscList;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("info");
        ListView listView = findViewById(R.id.listView);
        list = new ArrayList<String>();
        cadetNo = new ArrayList<String>();
        nameList = new ArrayList<String>();
        batchList = new ArrayList<String>();
        houseList = new ArrayList<String>();
        homeList = new ArrayList<>();
        disList = new ArrayList<String>();
        conList = new ArrayList<String>();
        workList = new ArrayList<String>();
        emailList = new ArrayList<String>();
        miscList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.itemTextView, list );




        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String cn = dataSnapshot.child("cn").getValue(String.class);
                String batch = dataSnapshot.child("batch").getValue(String.class);
                String contact = dataSnapshot.child("contact").getValue(String.class);
                String district = dataSnapshot.child("district").getValue(String.class);
                String home = dataSnapshot.child("home").getValue(String.class);
                String house = dataSnapshot.child("house").getValue(String.class);
                String work = dataSnapshot.child("work").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String misc = dataSnapshot.child("misc").getValue(String.class);




                list.add(name +"\n"+ cn + "\n" + house);
                nameList.add(name);
                cadetNo.add(cn);
                batchList.add(batch);
                houseList.add(house);
                homeList.add(home);
                disList.add(district);
                conList.add(contact);
                workList.add(work);
                emailList.add(email);
                miscList.add(misc);

                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("cn", cadetNo.get(i));
                intent.putExtra("name", nameList.get(i));
                intent.putExtra("batch", batchList.get(i));
                intent.putExtra("house",houseList.get(i));
                intent.putExtra("home", homeList.get(i));
                intent.putExtra("district", disList.get(i));
                intent.putExtra("mobile", conList.get(i));
                intent.putExtra("work", workList.get(i));
                intent.putExtra("email", emailList.get(i));
                intent.putExtra("misc", miscList.get(i));

                startActivity(intent);

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

    private void search() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    private void filter() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

    }



}