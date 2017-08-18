package org.studentenroll.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;
import org.studentenroll.userservice.exception.BadRequestException;
import org.studentenroll.userservice.exception.NotFoundException;
import org.studentenroll.userservice.repository.UserCourseRepository;
import org.studentenroll.userservice.service.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@CrossOrigin
@RequestMapping(value="user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.GET,value="")
    public List<User> findAll(){//get

        return userService.findAll();
    }

    @RequestMapping(method=RequestMethod.GET,value="{id}")
    public User findOne(@PathVariable("id") String userId){//get
    return userService.findOne(userId);
    }

    @RequestMapping(method=RequestMethod.POST,value="authenticate",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody User authenticate(@RequestBody Map<String,String> params ){
        String param =params.get("param");
        String password=params.get("password");
        return userService.authenticate(param,password);
    }

    @RequestMapping(method=RequestMethod.POST,
                    consumes= MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User create(@RequestBody User user){//post
    return userService.create(user);
    }

    @RequestMapping(method=RequestMethod.PUT,value="{id}",
                    consumes= MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User update(@PathVariable("id")String id, @RequestBody User user){//put
    return userService.update(id,user);
    }

    @RequestMapping(method=RequestMethod.DELETE,value="{id}")
    public void delete(@PathVariable String id){
        userService.delete(id);

    }

    @GetMapping(value="/viewmycourses/{userId}")
    List<UserCourse> getCoursesByUserId(@PathVariable String userId){

        return userService.getCoursesByUserId(userId);
    }

}

@RestController
@RequestMapping(value="internal")
class UserInternalController{
    final UserService userService;
    final UserCourseRepository userCourseRepository;
    @Autowired
    UserInternalController(UserService userService, UserCourseRepository userCourseRepository) {
        this.userService = userService;
        this.userCourseRepository = userCourseRepository;
    }

    @PutMapping(value="addCourse")
    @Transactional
    @ResponseBody boolean addCourses(@RequestBody Map<String,List<String>> param){

        String userId=param.get("userId").get(0);
        List<String> courses=param.get("cartItems");
        List<UserCourse> currentcourses;
        List<UserCourse> newcourses;
        try{
            User user=this.userService.findOne(userId);

            if(user==null){
                throw new NotFoundException("User with id "+userId+" is currently not registered" );
            }
            currentcourses=user.getUserCourses();
            newcourses=new ArrayList<UserCourse>();
            for(String course:courses){
                if(userCourseRepository.findOne(course)!=null){
                    throw new BadRequestException("User already enrolled to course "+course);
                }
                UserCourse u=new UserCourse(course);
                newcourses.add(u);
                userCourseRepository.create(u);
            }
            currentcourses.addAll(newcourses);
            this.userService.update(userId,user);


        }
        catch(BadRequestException|NotFoundException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }



}