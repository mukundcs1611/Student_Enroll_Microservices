package org.studentenroll.userservice.entity;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author chavali
 **/
@Entity
@NamedQueries(@NamedQuery(name="UserCourse.findByCourseId",
        query="SELECT c from UserCourse c WHERE c.course_id=:courseId"))
public class UserCourse {

    @Id
    String id;

    @Column(nullable=false)
    private String course_id;//Course ID

    private String course_no;

    private String course_title;

    private String days;


    private String start_time;

    private String end_time;

    private String semester_id;

    private String location;

    private String instructor;

    private String section;

    public UserCourse(String courseId)
    {
        this.id=UUID.randomUUID().toString();
        this.course_id = courseId;
    }
    public UserCourse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return course_id;
    }

    public void setCourseId(String course_id) {
        this.course_id = course_id;
    }

    public String getCourseNo() {
        return course_no;
    }

    public void setCourseNo(String course_no) {
        this.course_no = course_no;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSemesterId() {
        return semester_id;
    }

    public void setSemesterId(String semester_id) {
        this.semester_id = semester_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "id(uuid)='" + id + '\'' +
                ", courseId='" + course_id + '\'' +
                ", courseName='" + course_title + '\'' +
                ", days='" + days + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", semester_id='" + semester_id + '\'' +
                ", location='" + location + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }



}
