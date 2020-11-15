package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*

{
  "no": 1,
  "username": "test",
  "password": null,
  "email": "test@test.com",
  "nickname": "개발냄새",
  "profile": "https://go-learn.s3.ap-northeast-2.amazonaws.com/member/profile/profile_default1.png",
  "banner": "https://go-learn.s3.ap-northeast-2.amazonaws.com/member/banner/c9fe0e1c-98ef-4b62-8833-6b3edfd6be57.png",
  "reg_date": "2020-11-02T20:16:22.000+00:00",
  "update_date": "2020-10-26T11:16:22.000+00:00",
  "check_email": true,
  "check_active": true,
  "subscribe_count": 4
}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    int no;
    String nickname;
    String profile;
}
