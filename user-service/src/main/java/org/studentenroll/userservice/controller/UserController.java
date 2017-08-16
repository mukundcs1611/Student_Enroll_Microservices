package org.studentenroll.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;
import org.studentenroll.userservice.service.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


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
    Set<UserCourse> getCoursesByUserId(@PathVariable String userId){

        return null;
    }

}

@RestController
@RequestMapping(value="internal")
class UserInternalController{
    final UserService userService;

    @Autowired
    UserInternalController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value="addCourse")
    @ResponseBody boolean addCourses(@RequestBody Map<String,List<String>> param){
        String userId=param.get("userId").get(0);
        List<String> courses=param.get("cartItems");
        User user=this.userService.findOne(userId);
        if(user==null){
         return false;
        }
        List<UserCourse> currentcourses=user.getUserCourses();
        List<UserCourse> newcourses=new ArrayList<UserCourse>();
        for(String course:courses){
            UserCourse u=new UserCourse(course);
            newcourses.add(u);
        }
        currentcourses.addAll(newcourses);

        return false;
    }



}