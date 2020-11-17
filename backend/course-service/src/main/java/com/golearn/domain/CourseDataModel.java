package com.golearn.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.AbstractDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import java.util.Collection;

@Slf4j

public class CourseDataModel extends AbstractDataModel {

    @Override
    public LongPrimitiveIterator getUserIDs() throws TasteException {
        return null;
    }

    @Override
    public PreferenceArray getPreferencesFromUser(long userID) throws TasteException {
        return null;
    }

    @Override
    public FastIDSet getItemIDsFromUser(long userID) throws TasteException {
        return null;
    }

    @Override
    public LongPrimitiveIterator getItemIDs() throws TasteException {
        return null;
    }

    @Override
    public PreferenceArray getPreferencesForItem(long itemID) throws TasteException {
        return null;
    }

    @Override
    public Float getPreferenceValue(long userID, long itemID) throws TasteException {
        return null;
    }

    @Override
    public Long getPreferenceTime(long userID, long itemID) throws TasteException {
        return null;
    }

    @Override
    public int getNumItems() throws TasteException {
        return 0;
    }

    @Override
    public int getNumUsers() throws TasteException {
        return 0;
    }

    @Override
    public int getNumUsersWithPreferenceFor(long itemID) throws TasteException {
        return 0;
    }

    @Override
    public int getNumUsersWithPreferenceFor(long itemID1, long itemID2) throws TasteException {
        return 0;
    }

    @Override
    public void setPreference(long userID, long itemID, float value) throws TasteException {

    }

    @Override
    public void removePreference(long userID, long itemID) throws TasteException {

    }

    @Override
    public boolean hasPreferenceValues() {
        return false;
    }

    @Override
    public void refresh(Collection<Refreshable> alreadyRefreshed) {

    }
}
