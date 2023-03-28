package com.example.smsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    //Initialise the list item here
    private ArrayList<ContactsModel> arrayListContacts = new ArrayList<>();

    //Creating context for toast
    private Context context;

    public ContactsAdapter(Context context) {
        this.context = context;

    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //set text view
        holder.textViewContactFirstName.setText(arrayListContacts.get(position).getFirstName());
        holder.textViewContactLastName.setText(arrayListContacts.get(position).getLastName());

        //On click listener for the recycler view
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactInfoActivity.class);
                intent.putExtra("contact firstName",arrayListContacts.get(position).getFirstName());
                intent.putExtra("contact lastName",arrayListContacts.get(position).getLastName());
                intent.putExtra("contact phoneNumber",arrayListContacts.get(position).getPhoneNumber());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListContacts.size();
    }

    public void setArrayListAllContacts(ArrayList<ContactsModel> arrayListContacts) {
        this.arrayListContacts = arrayListContacts;
        notifyDataSetChanged();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Declaration
        private LinearLayout parent;
        private TextView textViewContactFirstName, textViewContactLastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //The container
            parent = itemView.findViewById(R.id.single_contact);

            textViewContactFirstName = itemView.findViewById(R.id.text_view_contact_first_name);
            textViewContactLastName = itemView.findViewById(R.id.text_view_contact_last_name);
        }
    }

}
