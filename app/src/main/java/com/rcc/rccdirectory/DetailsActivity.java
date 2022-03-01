package com.rcc.rccdirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailsActivity extends AppCompatActivity {
    TextView cadetNo,nameView, batchView, houseView, addressView, mobileView, workView, emailView;
    ImageView imageView;
    DatabaseReference reference;
    StorageReference storageReference;
    String name, cn , batch, house, home, district, mobile, work, email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        reference = FirebaseDatabase.getInstance().getReference("list");
        cn = getIntent().getStringExtra("cn");
        storageReference = FirebaseStorage.getInstance().getReference("profile.png");
        name = getIntent().getStringExtra("name");
        batch = getIntent().getStringExtra("batch");
        house = getIntent().getStringExtra("house");
        home = getIntent().getStringExtra("home");
        district = getIntent().getStringExtra("district");
        mobile = getIntent().getStringExtra("mobile");
        work = getIntent().getStringExtra("work");
        email = getIntent().getStringExtra("email");



        cadetNo = findViewById(R.id.cadetNo);
        nameView = findViewById(R.id.nameView);
        imageView = findViewById(R.id.profileImage);
        batchView = findViewById(R.id.batchView);
        houseView = findViewById(R.id.houseView);
        addressView = findViewById(R.id.addressV);
        mobileView = findViewById(R.id.mobileV);
        workView = findViewById(R.id.workV);
        emailView = findViewById(R.id.emailV);


        cadetNo.setText("Cadet no: " + cn);
        nameView.setText("Name: " + name);
        batchView.setText("Batch: " + batch);
        houseView.setText("House: "+ house);
        addressView.setText("Address: " + home + ", "+ district);
        mobileView.setText("Mobile: "+ mobile);
        workView.setText("Work: "+ work);
        emailView.setText("Email: "+ email);

        final long ONE_MEGABYTE = 1024 * 1024;
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytesPrm -> {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytesPrm, 0, bytesPrm.length);
            imageView.setImageBitmap(bmp);
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });












    }
}