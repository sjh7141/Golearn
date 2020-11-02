package kr.co.golearn.domain;

public class Tag {
    private long no;
    private String name;
    private long targetNo;

    public Tag() {

    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTargetNo() {
        return targetNo;
    }

    public void setTargetNo(long targetNo) {
        this.targetNo = targetNo;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", targetNo=" + targetNo +
                '}';
    }
}
