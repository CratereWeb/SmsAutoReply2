package com.anthonyf.smsautoreply2.fragments;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import com.anthonyf.smsautoreply2.R;
import com.anthonyf.smsautoreply2.adapters.ContactsListAdapter;
import com.anthonyf.smsautoreply2.model.Contact;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ContactsListAdapter mContactsListAdapter;
    private List<Contact> mContactsList = new ArrayList<>();
    private Button button;
    private TextView contactsResumeTextView;
    private ActivityResultLauncher<String> requestPermissionLauncher;



    public ContactsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Retrieve the resume TextView from fragment's layout
        contactsResumeTextView = view.findViewById(R.id.contacts_fragment_resume);

        // Retrieve Button from the Fragment's layout
        //Button button = view.findViewById(R.id.contacts_fragment_btn);
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a bundle to be sent to the next fragment
                Bundle bundle = new Bundle();
                bundle.putString("");
            }
        });
        */

        // Retrieve RecyclerView from the Fragment's layout
        mRecyclerView = view.findViewById(R.id.contacts_fragment_list_view);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        // If permission is allowed, we can access contacts and add them to the fragment's contact list
                        getContactsFromPhone();
                    } else {
                        // If permission is not allowed, we inform the user through the existing text view
                        contactsResumeTextView.setText(R.string.contacts_permission_denied);
                    }
                });

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // If permission is not allowed, we request it to the user
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        } else {
            // If permission is already allowed, we can access contacts and add them to the fragment's contact list
            getContactsFromPhone();
        }

        //prepareContacts();


        return view;
    }

    private void loadFakeContacts() {

        /*
        contactsList.add(new Contact(1, "Anthony Fieve", "+33628920175"));
        contactsList.add(new Contact(2, "Gandalf Le Gris", "+33678912345"));
        contactsList.add(new Contact(3, "John Wick", "+33123456789"));
        contactsList.add(new Contact(5, "James Bond", "+33700700700"));
        contactsList.add(new Contact(6, "Charlotte Legrand", "+33700700701"));
        contactsList.add(new Contact(7, "Madison Ambrose", "+33700700702"));
        contactsList.add(new Contact(8, "Joel Cohen", "+33700700703"));
        contactsList.add(new Contact(9, "Jacques Brel", "+33700700704"));
        contactsList.add(new Contact(10, "Georges Brassens", "+33700700705"));
        contactsList.add(new Contact(11, "Mikhail Bakounine", "+33700700706"));
        contactsList.add(new Contact(12, "Fedor Dostoievski", "+33700700707"));
        contactsList.add(new Contact(13, "Karl Marx", "+33700700708"));
        contactsList.add(new Contact(14, "Louise Michel", "+33700700709"));
        contactsList.add(new Contact(15, "Pierre Kropotkine", "+33700700710"));
        */
    }

    @SuppressLint("Range") // Suppress the warnings due to the use of cursor.getColumnIndex() in the method below
    private void getContactsFromPhone() {

        /*
            Give ContactsFragment an access to the application's data by using a ContentResolver
            Since the AndroidManifest defines that this app can ask the user a permission to access the phone's contacts,
            the ContentResolver gives the application the ability to interact with the phone's contacts database
        */
        ContentResolver cr = requireActivity().getContentResolver(); // the application now has access to all the data that the application has access to

        /*
            To query the contacts database, we can use a Cursor.
            Cursor is an interface allowing to browse through a response to a database request.
            Using the ContactsContract class (a content provider) and some optional params,
            the ContentResolver.query() method returns a Cursor able to browse the phone's contacts database.
        */

        // Set a projection to define which fields of each contact entry we want to get
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // Retrieve contacts using a Cursor, passing the projection defined above as its 'projection' arg
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null);

        // If the cursor gathered some data (contact) in the response
        if(cursor != null && cursor.getCount() > 0) {
            // Iter through the cursor entries
            while(cursor.moveToNext()) {

                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.d("FOUND_CONTACT", id + " : " + displayName + " : " + phoneNumber);

                // Instantiate and add the found contact to the contacts list to display
                Contact contact = new Contact(id, displayName, phoneNumber, false);
                mContactsList.add(contact);
            }
            cursor.close();
        }

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init the RecyclerView's Adapter
        mContactsListAdapter = new ContactsListAdapter(mContactsList);

        // Bind the RecyclerView to its parent's layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Bind the Adapter to the RecyclerView
        mRecyclerView.setAdapter(mContactsListAdapter);

    }

    public List<Contact> getSelectedContacts() {
        List<Contact> selectedContacts = new ArrayList<>();

        for(int i = 0; i < mContactsList.size(); i++) {

            if (mContactsList.get(i).isSelected()) {
                selectedContacts.add(mContactsList.get(i));
            }
        }

        return selectedContacts;
    }
}