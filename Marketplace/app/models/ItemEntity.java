package models;

import javax.persistence.*;
import com.avaje.ebean.Model;

@Entity
@Table(name = "itementity")
public class ItemEntity extends Model{

    public static Finder<Long,ItemEntity> FINDER = new Finder<>(ItemEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Item")
    private Long id;
    private ProductEntity product;
    private WishListEntity wishlist;
    private int quantity;


    public ItemEntity() {
        this.id=null;
        this.product = null;
        this.wishlist = null;
        quantity = -1;

    }

    public ItemEntity(Long id) {
        this();
        this.id = id;
    }

    public ItemEntity(Long id, ProductEntity product, WishListEntity wishlist, int quantity) {
        this.id = id;
        this.product = product;
        this.wishlist = wishlist;
        this.quantity = quantity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public WishListEntity getWishlist() {
        return wishlist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setWishlist(WishListEntity wishlist) {
        this.wishlist = wishlist;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", wishlist=" + wishlist +
                ", quantity=" + quantity +
                '}';
    }
}
