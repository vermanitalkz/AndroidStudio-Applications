package com.cst2335.verm0121;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatMessageDAO {

    @Insert
    public void insertMessage(ChatMessage m);

    @Query("Select * from ChatMessage")
    public List<ChatMessage> getAllMessages();

    @Delete
    void deleteMessage(ChatMessage m);
}
