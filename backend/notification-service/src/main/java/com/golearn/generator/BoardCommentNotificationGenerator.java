package com.golearn.generator;

import com.golearn.client.BoardRestClient;
import com.golearn.model.BoardCommentResponse;
import com.golearn.model.BoardResponse;
import com.golearn.model.Notification;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Data
public class BoardCommentNotificationGenerator implements NotificationGenerator{
    private int type=1;
    private final BoardRestClient boardRestClient;
    BoardCommentNotificationGenerator(BoardRestClient boardRestClient){
        this.boardRestClient = boardRestClient;
    }
    @Override
    public List<Integer> generate(Notification notification) {
        BoardResponse boardResponse = boardRestClient.getBoard(notification.getDest());
        List<Integer> list = new LinkedList<>();
        list.add(boardResponse.getMbrNo());
        return list;
    }
}
