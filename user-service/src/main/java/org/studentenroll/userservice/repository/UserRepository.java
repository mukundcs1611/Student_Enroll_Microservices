package org.studentenroll.userservice.repository;


import org.studentenroll.userservice.entity.User;

import java.util.List;

public interface UserRepository {
     User findOne(String id);
     List<User> findAll();
     User create(User user);
     void delete(User user);
     User update(User user);
     User findByEmail(String email);
     User findByUserId(String userId);

}
