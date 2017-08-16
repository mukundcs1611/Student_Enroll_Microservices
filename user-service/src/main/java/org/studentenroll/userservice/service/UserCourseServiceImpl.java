package org.studentenroll.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studentenroll.userservice.entity.UserCourse;
import org.studentenroll.userservice.repository.UserServiceRepository;

/**
 * @author chavali
 **/
@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    UserServiceRepository userServiceRepository;
    @Override
    public UserCourse findOne(String id) {
        return null;
    }

    @Override
    public UserCourse findOneByCourseId(String id) {

        return null;
    }
}
