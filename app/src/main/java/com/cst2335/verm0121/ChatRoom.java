package com.cst2335.verm0121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    ArrayList<ChatMessage> messages = new ArrayList<>();
    ChatMessage chat = new ChatMessage("", "", false);

    ActivityChatRoomBinding binding;

    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
    String currentDateandTime;
    private RecyclerView.Adapter<MyRowHolder> myAdapter;

    private ChatMessageDAO mDAO;

    public void addContent(Boolean isTrue ){
        currentDateandTime = sdf.format(new Date());
        chat = new ChatMessage(binding.textInput.getText().toString(), currentDateandTime, isTrue);
        messages.add(chat);
        myAdapter.notifyItemInserted(messages.size() - 1);
        binding.textInput.setText("");
    };

    private void insertMessage(ChatMessage newMessage){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDAO.insertMessage(newMessage);
            }
        }).start();
    }

    private void loadMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ChatMessage> loadedMessages = (ArrayList<ChatMessage>) mDAO.getAllMessages();
                messages.addAll( mDAO.getAllMessages() );
                chatModel.messages.postValue(loadedMessages);
                myAdapter.notifyDataSetChanged();
            }
        }).start();
    }
    
    private void deleteMessages(ChatMessage messageToDelete){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDAO.deleteMessage(messageToDelete);
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MessageDatabase db = MessageDatabase.getInstance(this);
        mDAO = db.chatMessageDAO();
        super.onCreate(savedInstanceState);

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        messages = chatModel.messages.getValue();
        if (messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.sendButton.setOnClickListener(click -> {
            addContent(true);

        });
        binding.receiveButton.setOnClickListener(click -> {
            addContent(false);
        });
        loadMessages();

        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if (viewType == 0) {
                    SentMessageBinding sendBinding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(sendBinding.getRoot(),viewType);
                } else {
                    ReceiveMessageBinding receiveBinding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(receiveBinding.getRoot(),viewType);
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
                ChatMessage message = messages.get(position);
                if (messages.get(position).isSentButton() == true) {
                    return 0;
                } else {
                    return 1;
                }

            }

        });
        binding.recyclerView.setAdapter(myAdapter);


        loadMessages();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView, int position) {
            super(itemView);

            itemView.setOnClickListener(clk ->{
                int positionFinal = getAdapterPosition();
                if (positionFinal != RecyclerView.NO_POSITION) {
                    ChatMessage messageToDelete = messages.get(positionFinal);
                    AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );
                    builder.setMessage("Do you want to delete the message: " + messageText.getText())
                            .setTitle("Question")
                            .setNegativeButton("No",(dialog,cl)->{})
                            .setPositiveButton("Yes",(dialog,cl)->{
                                deleteMessages(messageToDelete);
                                messages.remove(positionFinal);
                                myAdapter.notifyItemRemoved(positionFinal);
                            }).show();
                }
            });


            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}
