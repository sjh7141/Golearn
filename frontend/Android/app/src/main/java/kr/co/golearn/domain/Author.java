package kr.co.golearn.domain;

public class Author {
    private long mbrNo;
    private String mbrNickName;
    private String mbrProfile;

    public long getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(long mbrNo) {
        this.mbrNo = mbrNo;
    }

    public String getMbrNickName() {
        return mbrNickName;
    }

    public void setMbrNickName(String mbrNickName) {
        this.mbrNickName = mbrNickName;
    }

    public String getMbrProfile() {
        return mbrProfile;
    }

    public void setMbrProfile(String mbrProfile) {
        this.mbrProfile = mbrProfile;
    }

    @Override
    public String toString() {
        return "Author{" +
                "mbrNo=" + mbrNo +
                ", mbrNickName='" + mbrNickName + '\'' +
                ", mbrProfile='" + mbrProfile + '\'' +
                '}';
    }
}
