package com.rcc.rccdirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
    TextView cadetNo,nameView, batchView, houseView, addressView, mobileView, workView, emailView, miscView;
    ImageView imageView;
    DatabaseReference reference;
    StorageReference storageReference;
    String name, cn , batch, house, home, district, mobile, work, email, misc;
    ImageView callBtn, emailBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        reference = FirebaseDatabase.getInstance().getReference("list");
        cn = getIntent().getStringExtra("cn");
        storageReference = FirebaseStorage.getInstance().getReference(cn+".jpg");
        name = getIntent().getStringExtra("name");
        batch = getIntent().getStringExtra("batch");
        house = getIntent().getStringExtra("house");
        home = getIntent().getStringExtra("home");
        district = getIntent().getStringExtra("district");
        mobile = getIntent().getStringExtra("mobile");
        work = getIntent().getStringExtra("work");
        email = getIntent().getStringExtra("email");
        misc = getIntent().getStringExtra("misc");



        cadetNo = findViewById(R.id.cadetNo);
        nameView = findViewById(R.id.nameView);
        imageView = findViewById(R.id.profileImage);
        batchView = findViewById(R.id.batchView);
        houseView = findViewById(R.id.houseView);
        addressView = findViewById(R.id.addressV);
        mobileView = findViewById(R.id.mobileV);
        workView = findViewById(R.id.workV);
        emailView = findViewById(R.id.emailV);
        miscView = findViewById(R.id.miscV);

        emailBtn = findViewById(R.id.emailBtn);
        callBtn = findViewById(R.id.callBtn);


        cadetNo.setText("Cadet no: " + cn);
        nameView.setText("Name: " + name);
        batchView.setText("Batch: " + batch);
        houseView.setText("House: "+ house);
        addressView.setText("Address: " + home + ", "+ district);
        mobileView.setText("Mobile: "+ mobile);
        workView.setText("Work: "+ work);
        emailView.setText("Email: "+ email);
        miscView.setText("Misc: "+misc);

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

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+mobile));
                startActivity(intent);

            }
        });
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                startActivity(Intent.createChooser(intent, "Mail using"));
            }
        });











    }
}