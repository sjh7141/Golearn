package com.golearn.client;

import com.golearn.model.BoardCommentResponse;
import com.golearn.model.BoardResponse;
import com.golearn.model.MemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-api", url = "${feign.account-api.url}")
public interface AccountRestClient {
    @GetMapping("/{mbr_no}")
    MemberResponse getMember(@PathVariable("mbr_no") int mbrNo);
}
