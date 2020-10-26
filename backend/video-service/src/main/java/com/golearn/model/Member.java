package com.golearn.model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "gl_member")
@Data
public class Member {

    @Id
    private int mbrNo;

    private String mbrId;
    private String mbrPwd;
    private String mbrEmail;
    private String mbrNickname;
    private String mbrProfile;
    private Date regDt;
    private Date chgDt;
    private boolean ckEmail;
    private boolean ckActive;

}
