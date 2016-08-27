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
    @OneToOne(cascade = CascadeType.ALL)
    private ProductEntity product;


    private Long idP;
    @ManyToOne(cascade = CascadeType.ALL)
    private WishListEntity wishlist;
    private int quantity;

    public ItemEntity() {
        this.id = null;
        this.product = null;
        this.wishlist = null;
        this.quantity = 0;
    }

    public ItemEntity(Long id) {
        this();
        this.id = id;
    }

    public ItemEntity(Long id, ProductEntity product, WishListEntity wishlist, int quantity, Long idP)
    {
        this.id = id;
        this.product = product;
        this.wishlist = wishlist;
        this.quantity = quantity;
        this.idP = idP;
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

    public void setProduct(ProductEntity product) {
        this.product= product;
    }

    public WishListEntity getWishlist() {
        return wishlist;
    }

    public void setWishlist(WishListEntity wishlist) {
        this.wishlist = wishlist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
        this.idP = idP;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", product_id='" + product + '\'' +
                ", wishlist_id=" + wishlist +
                ", quantity=" + quantity +
                '}';
    }
}
