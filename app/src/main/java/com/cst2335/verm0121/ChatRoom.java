package com.cst2335.verm0121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cst2335.verm0121.databinding.ActivityChatRoomBinding;
import com.cst2335.verm0121.databinding.ReceiveMessageBinding;
import com.cst2335.verm0121.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {
    ChatRoomViewModel chatModel ;
    ArrayList<ChatMessage> messages;
    ActivityChatRoomBinding binding;
    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        if(messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
        }
        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
                    @NonNull
                    @Override
                    public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        if (viewType == 0) {
                            SentMessageBinding sendBinding = SentMessageBinding.inflate(getLayoutInflater());
                            return new MyRowHolder(sendBinding.getRoot());
                        } else {
                            ReceiveMessageBinding receiveBinding = ReceiveMessageBinding.inflate(getLayoutInflater());
                            return new MyRowHolder(receiveBinding.getRoot());
                        }
                    }

                    @Override
                    public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                        holder.messageText.setText("");
                        holder.timeText.setText("");

                        ChatMessage obj = messages.get(position);
                        holder.messageText.setText(obj.getMessage());
                        holder.timeText.setText(obj.getTimeSent());
                    }

                    @Override
                    public int getItemCount() {
                        return messages.size();
                    }
            public int getItemViewType(int position) {
                if (messages.get(position).isSentButton() == true) {
                    return 0;
                } else {
                    return 1;
                }

            }

        });
             binding.sendButton.setOnClickListener(click ->

                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyy hh-mm-ss a");
                        String currentDateandTIme = sdf.format(new Date());
                        messages.add(new ChatMessage(binding.textInput.getText().toString(), currentDateandTIme, true));
                        myAdapter.notifyItemInserted(messages.size() - 1);
                        binding.textInput.setText("");
                    });

             binding.receiveButton.setOnClickListener(click ->

                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyy hh-mm-ss a");
                        String currentDateandTIme = sdf.format(new Date());
                        messages.add(new ChatMessage(binding.textInput.getText().toString(), currentDateandTIme, false));
                        myAdapter.notifyItemInserted(messages.size() - 1);
                        binding.textInput.setText("");
                    });
        binding.recyclerView.setLayoutManager(new

                    LinearLayoutManager(this));
                }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}
