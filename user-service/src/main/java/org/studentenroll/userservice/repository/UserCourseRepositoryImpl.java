package org.studentenroll.userservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.studentenroll.userservice.entity.User;
import org.studentenroll.userservice.entity.UserCourse;
import org.studentenroll.userservice.messaging.HttpMessaging;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chavali
 **/
@Repository
public class UserCourseRepositoryImpl implements UserCourseRepository{

    @PersistenceContext
    private EntityManager em;
    private HttpMessaging httpMessaging;

    @Autowired
    public UserCourseRepositoryImpl(HttpMessaging httpMessaging) {
        this.httpMessaging = httpMessaging;
    }

    @Override
    public UserCourse findOne(String userCourseId) {
        return em.find(UserCourse.class,userCourseId);
    }

    @Override
    @Transactional
    public void delete(UserCourse userCourse) {
        em.detach(userCourse);
    }

    @Override
    @Transactional
    public UserCourse create(UserCourse userCourse) {
        em.persist(userCourse);
        return userCourse;
    }

    @Override
    public UserCourse findByCourseId(String courseId) {
        TypedQuery<UserCourse> namedQuery=em.createNamedQuery("UserCourse.findByCourseId",UserCourse.class);
        namedQuery.setParameter("courseId",courseId);

        List<UserCourse> list=namedQuery.getResultList();
        if(list!=null && list.size()==1) return list.get(0);

        else return null;
    }

    @Override
    @Transactional
    public UserCourse update(UserCourse userCourse){
        return em.merge(userCourse);
    }

}
