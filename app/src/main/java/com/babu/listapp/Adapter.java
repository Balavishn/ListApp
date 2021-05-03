package com.babu.listapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babu.listapp.ui.home.HomeFragment;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.myview> {
    List<ApplicationInfo> listapp;
    PackageManager packageManager;
    View root;
    HomeFragment home;

    public Adapter(List<ApplicationInfo> listapp, PackageManager packageManager,View root,HomeFragment home) {
        this.listapp=listapp;
        this.packageManager=packageManager;
        this.root=root;
        this.home=home;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_app_name,parent,false);
        myview myview=new myview(v);
        return myview;
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        holder.t1.setText(listapp.get(position).loadLabel(packageManager));
        Drawable back=listapp.get(position).loadIcon(packageManager);

        //Glide.with(root.getContext()).load(back).circleCrop().into(holder.i1);
        
        holder.i1.setBackground(back);
     /*   holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.startActivity(new Intent(root.getContext(),Bug_report.class));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listapp.size();
    }

    public class myview extends RecyclerView.ViewHolder {
        TextView t1;
        ImageView i1;
        LinearLayout l;
        public myview(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView);
            i1=itemView.findViewById(R.id.textView1);
            l=itemView.findViewById(R.id.singleapp);
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(),Bug_report.class).putExtra("app_name",t1.getText().toString()));
                }
            });
        }
    }
}
