package models;

import javax.persistence.*;
import com.avaje.ebean.Model;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlistentity")
public class WishListEntity extends Model{

    public static Finder<Long,WishListEntity> FINDER = new Finder<>(WishListEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Wishlist")
    private Long id;
    private String username;

////    @OneToMany
//    private List<ItemEntity> items;


    public WishListEntity() {
        this.id=null;
        this.username ="NO NAME";

    }

    public WishListEntity(Long id) {
        this();
        this.id = id;
    }

    public WishListEntity(Long id, String username) {
        this.id = id;
        this.username = username;
//        items = new ArrayList<ItemEntity>() ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    public List<ItemEntity> getItems() {
//        return items;
//    }



    @Override
    public String toString() {
        return "WishListEntity{" +
                "id=" + id +
                ", name='" + username +
                '}';
    }
}
