package com.example.smsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    //Declaration
    View view;
    private RecyclerView recyclerViewListItem;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        recyclerViewListItem = view.findViewById(R.id.recyclerview_all_contacts);

        //Dummy Data
        ArrayList<ContactsModel> arrayListAllContacts = new ArrayList<>();

        arrayListAllContacts.add(new ContactsModel("test","1","+0000"));
        arrayListAllContacts.add(new ContactsModel("Test","2","0000"));
        arrayListAllContacts.add(new ContactsModel("test","3","0000"));

        //recycler view initializing and setting
        ContactsAdapter adapter = new ContactsAdapter(getContext());
        adapter.setArrayListAllContacts(arrayListAllContacts);
        recyclerViewListItem.setAdapter(adapter);
        recyclerViewListItem.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }
}