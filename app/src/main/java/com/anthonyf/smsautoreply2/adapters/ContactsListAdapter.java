package com.anthonyf.smsautoreply2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonyf.smsautoreply2.R;
import com.anthonyf.smsautoreply2.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {

    // Creating an array of Contact (model.Contact)
    private List<Contact> contactsList;

    public ContactsListAdapter (List<Contact> contacts) {
        this.contactsList = contacts;
    }
    /*
    public ContactsListAdapter (Context context, List<Contact> contacts) {
        this.context = context;
        this.contactsList = contacts;
    }
    */

    @NonNull
    @Override
    public ContactsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.contacts_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsListAdapter.ViewHolder holder, int position) {
        Contact contact = contactsList.get(position);
        //Log.d("ContactsListAdapter", "Contact: " + contact.getDisplayName() + " - " + contact.getPhoneNumber());

        holder.displayNameView.setText(contact.getDisplayName());

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Defining the views we want to display in each list's element
        public TextView displayNameView;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Retrieving the views that we want to display in each list's element
            this.displayNameView = (TextView) itemView.findViewById(R.id.contact_display_name);
            // Retrieving the contacts list item's layout
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.contact_layout);

        }

    }
}
