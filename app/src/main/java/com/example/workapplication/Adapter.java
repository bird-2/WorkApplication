package com.example.workapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholder> {
    private List<Photo> list;
    private View inflater;

   private Context context;


    public Adapter(List<Photo> peakyList) {
        list = peakyList;
    }

    @Override
    public Adapter.myviewholder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        inflater = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        myviewholder myviewholder = new myviewholder(inflater);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Photo photo = list.get(position);
        holder.photoImage.setImageResource(photo.getImageid());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        View photoView;
        ImageView photoImage;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView;
            photoImage = itemView.findViewById(R.id.imageView);
        }
    }
}
