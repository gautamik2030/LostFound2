package com.example.lostfound2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {

    Context context;
    //the firebase options from main activity will be recieved here
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options, Context context) {

        super(options);
        this.context=context;

    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model Model) {


        // setter has fed the info about variables and in this method we retrieve the data using getter
        holder.ItemName.setText(Model.getItemName());
        holder.Location.setText(Model.getLocation());
        holder.Date.setText(Model.getDate());
        holder.category.setText(Model.getCategory());
        holder.username.setText(Model.getUsername());
        holder.contact.setText(Model.getContact());
        holder.question.setText(Model.getQuestion());

        String id = getRef(position).getKey();
       DatabaseReference iref = getRef(position);
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("objects");
        holder.v.setOnClickListener(v -> {

                    getRef(position).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String det_n = snapshot.child("name").getValue().toString();
                            String det_u = snapshot.child("username").getValue().toString();
                            String det_d = snapshot.child("date").getValue().toString();
                            String det_q = snapshot.child("question").getValue().toString();
                            String det_l = snapshot.child("location").getValue().toString();
                            String det_c = snapshot.child("contact").getValue().toString();
                            holder.ItemName.setText(det_n);
                            holder.Location.setText(det_l);
                            holder.Date.setText(det_d);
                            holder.contact.setText(det_c);
                            holder.username.setText(det_u);
                            holder.question.setText(det_q);
                            Intent intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("Itemname", det_n);
                            intent.putExtra("Location", det_l);
                            intent.putExtra("Date", det_d);
                            intent.putExtra("question", det_q);
                            intent.putExtra("usname", det_u);
                            intent.putExtra("contact", det_c);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                    });




                });

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

        TextView ItemName, Location, Date,category,username,contact,question;
        View v;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            ItemName=(TextView)itemView.findViewById(R.id.name);
            Location=(TextView)itemView.findViewById(R.id.location);
            Date=(TextView)itemView.findViewById(R.id.date);
            category=(TextView)itemView.findViewById(R.id.cat);
            username=(TextView)itemView.findViewById(R.id.username);
            contact=(TextView)itemView.findViewById(R.id.contact);
            question=(TextView)itemView.findViewById(R.id.question);

            v=itemView;

        }


        }
    }


