package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.getsetVote;

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
    }


    public int getItemCount() {

        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtid, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            txtid = itemView.findViewById(R.id.txtid);
            name = (TextView) itemView.findViewById(R.id.txtname);


        }
    }
}
