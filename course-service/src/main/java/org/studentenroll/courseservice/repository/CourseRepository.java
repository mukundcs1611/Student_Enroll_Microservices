package org.studentenroll.courseservice.repository;

import org.studentenroll.courseservice.entity.Course;
import org.studentenroll.courseservice.entity.Enrollment;

import java.util.List;

public interface CourseRepository {
     List<Course> findAll();
     Course findOne(String id);
     Course create(Course course);
     List<Course> findFiltered(String query);
     Course updateCourse(Course course);
     void deleteCourse(String id);
     Enrollment enroll(Course course);
     Course findByCourseNo(String courseNo);
}
