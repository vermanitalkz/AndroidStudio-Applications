package com.cst2335.verm0121;

import java.util.ArrayList;

public class ChatMessage {
    public  String message;
    ArrayList<ChatMessage> messages;
    String timeSent;
    boolean isSentButton;
    ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }
}
