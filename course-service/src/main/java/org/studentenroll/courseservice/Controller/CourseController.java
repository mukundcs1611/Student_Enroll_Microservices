package org.studentenroll.courseservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.studentenroll.courseservice.Exception.BadRequestException;
import org.studentenroll.courseservice.entity.Course;
import org.studentenroll.courseservice.entity.Enrollment;
import org.studentenroll.courseservice.repository.CourseRepository;
import org.studentenroll.courseservice.service.CourseService;

import java.util.*;

/**
 * @author chavali mukund
 */

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @CrossOrigin
    @GetMapping(value="donotAccess")
    public @ResponseBody List<Course> findAll(){
       Course c1=new Course();
       c1.setCourseNo(1234);
       c1.setEnrolled(12);
       Course c2=new Course();
       c2.setCourseNo(12345);
       c2.setEnrolled(25);
        //return "hello";
        return Arrays.asList(c1,c2);
    }

    @CrossOrigin
    @PostMapping(value="view")
    public @ResponseBody List<Course> findFiltered(@RequestBody Map<String,String> params){
        return courseService.findFiltered(params);
    }

    @PutMapping(value="{id}")
    public @ResponseBody Course update(@PathVariable String id,@RequestBody Course course){
        return courseService.updateCourse(id,course);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Course create(@RequestBody Course course){
        return courseService.create(course);
    }

    @PutMapping()
    public @ResponseBody
    Enrollment preenroll(@RequestBody Map<String,String> params){
        return courseService.enroll(params.get("courseId"),params.get("userId"));
    }

    @DeleteMapping()
    public @ResponseBody boolean drop(@RequestBody String uuid,@RequestBody String enrollmentId){
        return courseService.drop(uuid,enrollmentId);
    }

}

@RestController
@RequestMapping(value="/internal")
@Transactional
class CourseInternalController {
    final
    CourseService courseService;
    final CourseRepository courseRepository;

    @Autowired
    public CourseInternalController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @RequestMapping(value = "enrollCart")
    public @ResponseBody Boolean enrollCart(@RequestBody Map<String,List<String>> param){

        String userId=param.get("userId").get(0);
        List<String> courses=param.get("cartItems");
        try{
            for(String courseId: courses ){
                if(this.courseService.enroll(courseId,userId)==null){
                    throw new BadRequestException("Course with id "+courseId+" Not available now");
                }
            }
        }

        catch(BadRequestException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value="getCoursesByCourseId")
    public @ResponseBody List<Course> getCoursesByCourseID(@RequestBody List<String> courseIds){
        List<Course> courses=new ArrayList<Course>();
        courseIds.forEach(
                n->{
                    Course c=courseRepository.findOne(n);
                    courses.add(c);
                }
        );

        return courses;
    }
}

