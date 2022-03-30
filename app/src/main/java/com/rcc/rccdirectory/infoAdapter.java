package com.rcc.rccdirectory;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class infoAdapter extends FirebaseRecyclerAdapter<
        Info, infoAdapter.infoViewHolder> {
    public infoAdapter(@NonNull FirebaseRecyclerOptions<Info> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull infoViewHolder holder, int position, @NonNull Info model) {
        holder.name.setText(model.getName());
        String cn = model.getCn();
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
        TextView name;
        ImageView image;
        LinearLayout linearLayout;
        public infoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recName);
            image = itemView.findViewById(R.id.listview_images);
            linearLayout = itemView.findViewById(R.id.itemLay);



        }


    }

}
