package org.studentenroll.cartservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart{
    @Id
    @GeneratedValue
    private Long cartId;

    @Column(nullable=false)
    private String userId;

    @ElementCollection
    private List<String> items;

    Cart() {
    }
    public Cart(String userId){
        this.userId=userId;
    }

    public Long getCartid(){
        return cartId;
    }

    public String getUserId() {
        return userId;
    }


    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items){
        this.items=items;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId='" + userId + '\'' +
                '}';
    }

}