package org.studentenroll.userservice.service;

import org.studentenroll.userservice.entity.UserCourse;

/**
 * @author mukund chavali
 **/
public interface UserCourseService {
 UserCourse findOne(String id);
 UserCourse findOneByCourseId(String id);
}
