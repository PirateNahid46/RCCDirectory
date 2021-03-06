package com.rcc.rccdirectory;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class infoAdapter extends RecyclerView.Adapter<infoAdapter.ViewHolder> {
    Context mContext;
    List<Info> mInfo;

    public infoAdapter(Context mContext, List<Info> mInfo) {
        this.mInfo = mInfo;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, cn, work, house;
        CircleImageView image;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recName);
            cn = itemView.findViewById(R.id.cadetNoL);
            image = itemView.findViewById(R.id.imageRec);
            linearLayout = itemView.findViewById(R.id.itemLay);
            work = itemView.findViewById(R.id.workL);
            house = itemView.findViewById(R.id.houseL);



        }


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new infoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Info info = mInfo.get(position);
        holder.name.setText("Cadet "+info.getName());
        holder.cn.setText(info.getCn());
        holder.work.setText(info.getWork());
        switch (info.getHouse()){
            case "Qasim":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.house.setBackground(mContext.getResources().getDrawable(R.drawable.qhdot));
                }
                break;
            case "Khalid":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.house.setBackground(mContext.getResources().getDrawable(R.drawable.khdot));
                }
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.house.setBackground(mContext.getResources().getDrawable(R.drawable.thdot));
                }
                break;

        }


        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference(info.getCn()+".jpg");
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(mContext)
                        .load(uri)
                        .into(holder.image);
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("cn", info.getCn());
                mContext.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return mInfo.size();
    }
}







