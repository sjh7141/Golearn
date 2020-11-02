package kr.co.golearn.domain.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kr.co.golearn.domain.Course;
import kr.co.golearn.domain.Tag;

public class SearchCourseResponse {
    @SerializedName("course")
    private List<Course> course;

    @SerializedName("tag")
    private List<Tag> tag;

    public SearchCourseResponse() {

    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "SearchCourseResponse{" +
                "course=" + course +
                ", tag=" + tag +
                '}';
    }
}
