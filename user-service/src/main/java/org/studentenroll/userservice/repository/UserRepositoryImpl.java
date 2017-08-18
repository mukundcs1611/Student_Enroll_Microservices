package org.studentenroll.userservice.repository;

import org.springframework.stereotype.Repository;

import org.studentenroll.userservice.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public User findOne(String id) {
        return em.find(User.class,id);

    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.findAll", User.class);

        return namedQuery.getResultList();
    }

    @Override
    public User create(User user) {

       // em.persist(user.getAddress());
        em.persist(user);
        return user;
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User update(User user) {
        //System.out.println(em.find(Address.class,user.getAddress().getId()));
        //em.merge(em.find(Address.class,user.getAddress().getId()));


        return em.merge(user);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> namedQuery=em.createNamedQuery("User.findByEmail",User.class);
        namedQuery.setParameter("pEmail",email);
        List<User> list=namedQuery.getResultList();
        if(list!=null && list.size()==1) return list.get(0);

        else return null;
    }

    @Override
    public User findByUserId(String userId) {
        TypedQuery<User> namedQuery=em.createNamedQuery("User.findByUserId",User.class);
        namedQuery.setParameter("userId",userId);

        List<User> list=namedQuery.getResultList();
        if(list!=null && list.size()==1) return list.get(0);

        else return null;
    }

}
