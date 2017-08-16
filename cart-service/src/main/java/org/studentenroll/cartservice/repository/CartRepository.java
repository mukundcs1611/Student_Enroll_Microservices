package org.studentenroll.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.studentenroll.cartservice.entity.Cart;

import java.util.Collection;

@RepositoryRestResource
public interface CartRepository extends JpaRepository<Cart,Long> {
    @RestResource(path="by-id")
    Cart findCartByUserId(@Param("userId") String userId);//Wizardry, how does it even work!


}