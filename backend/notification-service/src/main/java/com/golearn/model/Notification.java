package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private int notiNo;
    private int mbrNo;
    private int notiType;
    private String notiPath;
    private String notiMsg;
    @CreationTimestamp
    private Date regDt;
    private int notiRead;

}
