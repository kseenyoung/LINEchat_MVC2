package com.shinhan.room.model;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class RoomDTO {
    private int roomId;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int countMember;  // 방 인원
}