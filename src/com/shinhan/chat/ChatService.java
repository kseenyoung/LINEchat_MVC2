package com.shinhan.chat;

import com.shinhan.chat.model.ChatDAO;
import com.shinhan.chat.model.ChatDTO;

public class ChatService {
    ChatDAO chatDAO = new ChatDAO();
    public boolean insertMessage(ChatDTO chatDTO){return chatDAO.insertMessage(chatDTO);}
}
