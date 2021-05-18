package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.getsetVote;
import com.example.myapplication.ui.createVote;

import java.util.ArrayList;

public class voteAdapter extends RecyclerView.Adapter<voteAdapter.MyViewHolder> {

    ArrayList<getsetVote> arrayList = new ArrayList<getsetVote>();
    Context context;

    public voteAdapter(ArrayList<getsetVote> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote, parent, false);
        final MyViewHolder myviewHoldernew = new MyViewHolder(view);
        return myviewHoldernew;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(arrayList.get(position).getName());
        holder.txtid.setText(arrayList.get(position).getId());

        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), arrayList.get(position).getName().toString() , Toast.LENGTH_SHORT).show();

                ((createVote)context).customer=arrayList.get(position).getName().toString();
                ((createVote)context).customerId=arrayList.get(position).getId().toString();
                ((createVote)context).getValue();
            }
        });

    }


    public int getItemCount() {

        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtid, name;
        ImageButton txtName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           txtid = itemView.findViewById(R.id.txtid);
            name = (TextView) itemView.findViewById(R.id.txtname);
            txtName =(ImageButton) itemView.findViewById(R.id.txtName);


        }
    }
}
