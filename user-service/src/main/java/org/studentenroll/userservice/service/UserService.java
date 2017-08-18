package org.studentenroll.userservice.service;


import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;

import java.util.List;
import java.util.Set;

public interface UserService {
     User findOne(String id);
     List<User> findAll();
     User create(User user);
     void delete(String id);
     User update(String id, User user);
     List<UserCourse> getCoursesByUserId(String userId);
     User authenticate(String param, String password);

}
