package com.golearn.generator;

import com.golearn.client.BoardRestClient;
import com.golearn.model.BoardCommentResponse;
import com.golearn.model.Notification;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class BoardReplyNotificationGenerator implements NotificationGenerator {
    private int type = 2;
    private final BoardRestClient boardRestClient;

    BoardReplyNotificationGenerator(BoardRestClient boardRestClient) {
        this.boardRestClient = boardRestClient;
    }

    @Override
    public List<Integer> generate(Notification notification) {
        BoardCommentResponse boardCommentResponse = boardRestClient.getBoardComment(notification.getDest());
        List<Integer> list = new LinkedList<>();
        list.add(boardCommentResponse.getMbrNo());
        return list;
    }
}
