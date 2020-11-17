package com.golearn.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "gl_member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @PostLoad
    public void setPrivate(){
        this.mbrPwd=null;
        this.mbrEmail=null;
        this.regDt=null;
        this.chgDt=null;
        this.ckEmail=false;
        this.ckActive=false;
    }
    public String toString(){
        return "";
    }
}
//
