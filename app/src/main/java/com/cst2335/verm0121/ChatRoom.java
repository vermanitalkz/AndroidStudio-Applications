package com.cst2335.verm0121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyRowHolder> {
        @NonNull
        @Override
        public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
            return new MyRowHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

        }

        @Override
        public int getItemCount() {

            return 0;
        }
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
