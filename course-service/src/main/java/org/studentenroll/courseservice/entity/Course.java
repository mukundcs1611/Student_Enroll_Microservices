package org.studentenroll.courseservice.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@NamedEntityGraph(name = "Courses.noEnrollment")
@NamedQueries(@NamedQuery(name="Course.findByCourseNo",
        query="SELECT c from Course c WHERE c.course_no=:courseNo"))
public class Course {
    @Id
    @Column(columnDefinition = "varchar(36)",name="id")
    private String course_id;

    @Column(nullable=false)
    private String subject;


    private int semester_id;
    @Column(nullable=false)
    private int course_no;
    @Column(nullable=false)
    private int section;
    @Column(nullable=false)
    private String course_title;
    @Column(nullable=false)
    private String instructor;
    @Column(nullable=false)
    private String days;
    @Column(nullable=false)
    private String start_time;
    @Column(nullable=false)
    private String end_time;
    @Column(nullable=false)
    private int max;

    private int enrolled;

    @OneToMany

    private Set<Enrollment> enrollment;


    public Course(){
        this.course_id= UUID.randomUUID().toString();
    }

    public String getCourseId() {
        return course_id;
    }

    public void setCourseId(String course_id) {
        this.course_id = course_id;
    }

    public int getSemesterId() {
        return semester_id;
    }

    public void setSemesterId(int semester_id) {
        this.semester_id = semester_id;
    }

    public int getCourseNo() {
        return course_no;
    }

    public void setCourseNo(int course_no) {
        this.course_no = course_no;

    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
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

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public Set<Enrollment> getEnrollment() {
        return enrollment;
    }


    public void setEnrollment(Set<Enrollment> enrollment) {
        this.enrollment = enrollment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}

