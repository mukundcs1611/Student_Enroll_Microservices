package org.studentenroll.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;
import org.studentenroll.userservice.exception.BadRequestException;
import org.studentenroll.userservice.exception.NotFoundException;
import org.studentenroll.userservice.repository.UserRepository;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
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

    @Override
    public Set<UserCourse> getCoursesByUserId(String userId) {
        return null;
    }

    @Override
    public User authenticate(String param,String password) {
        User user;
        if(param.contains("@")){//TODO Front end donot accept username with special characters
            user= repository.findByEmail(param);
        }
        else
            user=repository.findByUserId(param);

        if(user.getPassword().equals(password)) //TODO Encrypt Password in front and back
            return user;
        else
            return null;
    }
}
