package com.rcc.rccdirectory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailsActivity extends AppCompatActivity {
    TextView cadetNo,nameView, batchView, houseView, addressView, mobileView, workView, emailView, miscView, fullNameView, ribbon;
    ImageView imageView;
    StorageReference storageReference;
    DatabaseReference reference;
    String cn ,mobile, email;
    ImageView callBtn, emailBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        cn = getIntent().getStringExtra("cn");
        storageReference = FirebaseStorage.getInstance().getReference(cn+".jpg");
        reference = FirebaseDatabase.getInstance().getReference("info").child(cn);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Info info = dataSnapshot.getValue(Info.class);
                assert info != null;
                nameView.setText(info.getName());
                batchView.setText("Batch: " + info.getBatch() + " Intake: " + info.getIntake());
                houseView.setText(info.getHouse());
                addressView.setText(info.getHome() + ", "+ info.getDistrict());
                mobileView.setText(info.getContact());
                workView.setText(info.getWork());
                emailView.setText(info.getEmail());
                fullNameView.setText(info.getFlName());
                miscView.setText("Birthdate: " + info.getBdate()+
                        "\n \n"+ info.getMisc());
                mobile = info.getContact();
                email = info.getEmail();

                switch (info.getHouse()){
                    case "Qasim":
                        ribbon.setBackground(getResources().getDrawable(R.drawable.ribbonqh));
                        break;
                    case "Khalid":
                        ribbon.setBackground(getResources().getDrawable(R.drawable.ribbonkh));
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        cadetNo = findViewById(R.id.cadetNo);
        cadetNo.setText(cn);
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
        fullNameView = findViewById(R.id.flNameV);
        ribbon = findViewById(R.id.hBookmark);




        final long ONE_MEGABYTE = 1024 * 1024;
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytesPrm -> {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytesPrm, 0, bytesPrm.length);
            imageView.setImageBitmap(bmp);
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

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