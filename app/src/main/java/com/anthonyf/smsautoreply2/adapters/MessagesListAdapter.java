package com.anthonyf.smsautoreply2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonyf.smsautoreply2.R;
import com.anthonyf.smsautoreply2.model.Message;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.ViewHolder> {


    private List<Message> messagesList;
    private int lastCheckedRadioButtonPosition = -1;

    public MessagesListAdapter(List<Message> messages) { this.messagesList = messages; }

    @NonNull
    @Override
    public MessagesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.messages_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesListAdapter.ViewHolder holder, int position) {
        Message message = messagesList.get(holder.getAdapterPosition()); // getAdapterPosition = the 'position' arg
        // Log.d("MessagesListAdapter", "Message: " + message.getContent());

        holder.radioButton.setText(message.getContent());
        holder.radioButton.setChecked(lastCheckedRadioButtonPosition == holder.getAdapterPosition());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastCheckedRadioButtonPosition = holder.getAdapterPosition();
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Defining the views we want to display in each list's element
        public RadioButton radioButton;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Retrieving the views that we want to display in each list's element
            this.radioButton = (RadioButton) itemView.findViewById(R.id.message_radio_btn);
            //this.contentView = (TextView) itemView.findViewById(R.id.message_content);

            // Binding the ViewHolder's layout
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.message_layout);

        }

    }
}
