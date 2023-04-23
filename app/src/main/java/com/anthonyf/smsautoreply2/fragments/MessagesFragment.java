package com.anthonyf.smsautoreply2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anthonyf.smsautoreply2.R;
import com.anthonyf.smsautoreply2.adapters.ContactsListAdapter;
import com.anthonyf.smsautoreply2.adapters.MessagesListAdapter;
import com.anthonyf.smsautoreply2.model.Message;

import java.util.ArrayList;
import java.util.List;


public class MessagesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MessagesListAdapter mMessagesListAdapter;
    private List<Message> messagesList = new ArrayList<>();
    private Button button;

    public MessagesFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        // Retrieve Button from the Fragment's layout
        Button button = view.findViewById(R.id.messages_fragment_btn_add_new);
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
        mRecyclerView = view.findViewById(R.id.messages_fragment_list_view);

        prepareMessages();

        return view;
    }

    private void prepareMessages() {

        //getMessagesFromPhone();

        messagesList.add(new Message("Lorem ipsum dolor sit amet"));
        messagesList.add(new Message("consectetur adipiscing elit, sed do eiusmod tempor"));
        messagesList.add(new Message("incididunt ut labore et dolore magna aliqua."));
        messagesList.add(new Message("Ut enim ad minim veniam"));
        messagesList.add(new Message("quis nostrud exercitation ullamco laboris "));
        messagesList.add(new Message("nisi ut aliquip ex ea commodo consequat"));
        messagesList.add(new Message("Duis aute irure dolor in reprehenderit "));
        messagesList.add(new Message("in voluptate velit esse cillum dolore eu fugiat nulla pariatur."));
        messagesList.add(new Message(" Excepteur sint occaecat cupidatat non proident"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init the RecyclerView's Adapter
        mMessagesListAdapter = new MessagesListAdapter(messagesList);

        // Bind the RecyclerView to its parent's layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Bind the Adapter to the RecyclerView
        mRecyclerView.setAdapter(mMessagesListAdapter);

    }


}