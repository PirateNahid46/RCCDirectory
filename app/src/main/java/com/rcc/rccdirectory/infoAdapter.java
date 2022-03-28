package com.rcc.rccdirectory;


import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class infoAdapter extends FirebaseRecyclerAdapter<
        Info, infoAdapter.personsViewholder> {
    public infoAdapter(@NonNull FirebaseRecyclerOptions<Info> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull Info model) {
        holder.name.setText(model.getName());

    }

    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new infoAdapter.personsViewholder(view);
    }


    public class personsViewholder extends RecyclerView.ViewHolder {
        TextView name;
        public personsViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recName);
        }
    }
}
