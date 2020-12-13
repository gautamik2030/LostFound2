package com.example.lostfound2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {

    //the firebase options from main activity will be recieved here
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        // setter has fed the info about variables and in this method we retrieve the data using getter
        holder.ItemName.setText(model.getItemName());
        holder.Location.setText(model.getLocation());
        holder.Date.setText(model.getDate());
        holder.Time.setText(model.getTime());



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //converting xml file into view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    //holds the reference of the single row xml
    class myviewholder extends RecyclerView.ViewHolder{

        TextView ItemName, Location, Date, Time;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            ItemName=(TextView)itemView.findViewById(R.id.itemtext);
            Location=(TextView)itemView.findViewById(R.id.loctext);
            Date=(TextView)itemView.findViewById(R.id.date);
            Time=(TextView)itemView.findViewById(R.id.time);
        }
    }

}
