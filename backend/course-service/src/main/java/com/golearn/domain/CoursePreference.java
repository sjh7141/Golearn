package com.golearn.domain;



import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.model.Preference;

import static lombok.AccessLevel.PACKAGE;
@Slf4j
@Data
@Getter
@ToString(of = {"userId", "itemId"})
@EqualsAndHashCode(of = {"userId", "itemId"}, callSuper = false)
public class CoursePreference implements Preference {

    private long userId;
    private long itemId;
    private float value=10;
    public CoursePreference(long userId, long itemId){
        this.userId = userId;
        this.itemId = itemId;
    }

    @Override
    public long getUserID() {
        return userId;
    }

    @Override
    public long getItemID() {
        return itemId;
    }

    @Override
    public float getValue(){
        return value;
    }
}