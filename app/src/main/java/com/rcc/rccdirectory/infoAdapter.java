package com.rcc.rccdirectory;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.ResourceUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;


public class infoAdapter extends FirebaseRecyclerAdapter<
        Info, infoAdapter.infoViewHolder> {
    Context context;

    public infoAdapter(@NonNull FirebaseRecyclerOptions<Info> options, Context context) {
        super(options);
    }

    public infoAdapter(@NonNull FirebaseRecyclerOptions<Info> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull infoViewHolder holder, int position, @NonNull Info model) {
        holder.name.setText(model.getName());
        holder.cn.setText(model.getCn());
        String cn = model.getCn();
        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference(cn+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytesPrm -> {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytesPrm, 0, bytesPrm.length);
            holder.image.setImageBitmap(bmp);
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("cn", cn);
                intent.putExtra("name", model.getName());
                intent.putExtra("batch", model.getBatch());
                intent.putExtra("house",model.getHouse());
                intent.putExtra("home", model.getHome());
                intent.putExtra("district", model.getDistrict());
                intent.putExtra("mobile", model.getContact());
                intent.putExtra("work", model.getWork());
                intent.putExtra("email", model.getEmail());
                intent.putExtra("misc", model.getMisc());
                view.getContext().startActivity(intent);

            }
        });


    }


    @NonNull
    @Override
    public infoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new infoAdapter.infoViewHolder(view);
    }



    class infoViewHolder extends RecyclerView.ViewHolder {
        TextView name, cn;
        CircleImageView image;
        LinearLayout linearLayout;
        public infoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recName);
            cn = itemView.findViewById(R.id.cadetNoL);
            image = itemView.findViewById(R.id.imageRec);
            linearLayout = itemView.findViewById(R.id.itemLay);



        }


    }

}
