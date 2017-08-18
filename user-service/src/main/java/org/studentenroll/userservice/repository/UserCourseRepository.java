package org.studentenroll.userservice.repository;

import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;

import java.util.List;

/**
 * @author chavali
 **/
public interface UserCourseRepository {
    UserCourse findOne(String id);
    void delete(UserCourse userCourse);
    UserCourse create(UserCourse enrollment);
    UserCourse findByCourseId(String courseId);
    UserCourse update(UserCourse userCourse);
}
