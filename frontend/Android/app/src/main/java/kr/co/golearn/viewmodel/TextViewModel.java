package kr.co.golearn.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextViewModel extends ViewModel {
    public MutableLiveData<String> replyText = new MutableLiveData<>();
    public MutableLiveData<String> updateReply = new MutableLiveData<>();

    public MutableLiveData<String> getReplyText() {
        return replyText;
    }
    public MutableLiveData<String> getUpdateReply() {
        return updateReply;
    }

    public void setReplyText(String text){
        replyText.postValue(text);
    }
    public void setUpdateReply(String text){
        updateReply.postValue(text);
    }
}
