package org.studentenroll.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;
import org.studentenroll.userservice.exception.BadRequestException;
import org.studentenroll.userservice.exception.NotFoundException;
import org.studentenroll.userservice.messaging.HttpMessaging;
import org.studentenroll.userservice.repository.UserCourseRepository;
import org.studentenroll.userservice.repository.UserRepository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserCourseRepository userCourseRepository;
    private final HttpMessaging httpMessaging;
    @Autowired
    public UserServiceImpl(UserRepository repository, UserCourseRepository userCourseRepository, HttpMessaging httpMessaging) {
        this.repository = repository; this.userCourseRepository=userCourseRepository;
        this.httpMessaging = httpMessaging;
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String id) {
        User user=repository.findOne(id);
        if(user==null){
            throw new NotFoundException("User with id"+id+" not found");//return 404
        }
        return user;
    }

    @Override
    @Transactional(readOnly=true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public User create(User user) {
        User userExists=repository.findByEmail(user.getEmail());
        if(userExists!=null){
            throw new BadRequestException("User with email="+userExists.getEmail()+" already exists");
        }

        return repository.create(user);
    }

    @Override
    @Transactional
    public void delete(String id) {
        if(repository.findOne(id)==null){
            throw new BadRequestException("User with id="+id+" doesn't exist");
        }
        repository.delete(repository.findOne(id));
    }

    @Override
    @Transactional
    public User update(String id,User user) {
        User usr=repository.findOne(id);
        if(usr==null){
            throw new NotFoundException("User with id ="+id+" not found");
        }
        return repository.update(user);
    }
//TODO : OPTIMIZE
    @Override
    public List<UserCourse> getCoursesByUserId(String uuid) {
        User user=findOne(uuid);
        List<UserCourse> courses=user.getUserCourses();
        List<UserCourse> coursesToUpdate=null;
        List<String> fetchCourses=new ArrayList<String>();
        for(UserCourse course:courses){
            if(course.getCourse_title()==null){
                //prepare a packet and send a request
                fetchCourses.add(course.getCourseId());

            }

        }
        if(fetchCourses.size()>0){
            coursesToUpdate=this.httpMessaging.getCourses("http://course-service/internal/getCoursesByCourseId",fetchCourses);
            for(UserCourse course:coursesToUpdate){
                UserCourse uc=this.userCourseRepository.findByCourseId(course.getCourseId());
                uc.setCourse_title(course.getCourse_title());
                uc.setDays(course.getDays());
                uc.setEnd_time(course.getEnd_time());
                uc.setInstructor(course.getInstructor());
                if(course.getLocation()==null){
                    uc.setLocation("");
                }
                else
                    uc.setLocation(course.getLocation());
                uc.setStart_time(course.getStart_time());
                uc.setCourseNo(course.getCourseNo());
                uc.setSemesterId(course.getSemesterId());
                this.userCourseRepository.update(uc);
            }
        }


        return courses;

    }

    @Override
    public User authenticate(String param,String password) {
        User user;
        if(param.contains("@")){//TODO Front end donot accept username with special characters
            user= repository.findByEmail(param);
        }
        else
            user=repository.findByUserId(param);

        if(user.getPassword().equals(password)) //TODO Encrypt Password
            return user;
        else
            return null;
    }
}
