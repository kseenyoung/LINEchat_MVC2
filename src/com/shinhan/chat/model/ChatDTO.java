package com.shinhan.chat.model;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ChatDTO {
    private int chat_id;
    private int room_id;
    private String member_id;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
