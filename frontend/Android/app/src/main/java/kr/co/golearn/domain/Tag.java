package kr.co.golearn.domain;

public class Tag {
    private long tagNo;
    private String tag_name;

    public Tag() {

    }

    public long getTagNo() {
        return tagNo;
    }

    public void setTagNo(long tagNo) {
        this.tagNo = tagNo;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagNo=" + tagNo +
                ", tag_name='" + tag_name + '\'' +
                '}';
    }
}
