package com.golearn.client;

import com.golearn.model.BoardCommentResponse;
import com.golearn.model.BoardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "board-api", url = "${feign.board-api.url}")
public interface BoardRestClient {
    @GetMapping("/{brd_no}")
    BoardResponse getBoard(@PathVariable("brd_no") int brdNo);
    @GetMapping("/comment/details/{cmt_no}")
    BoardCommentResponse getBoardComment(@PathVariable("cmt_no") int cmtNo);

}
