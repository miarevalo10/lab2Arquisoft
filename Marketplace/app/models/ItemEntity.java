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
    private String name;
    private Integer stock;
    private Float price;
    private Boolean available;

    public ItemEntity() {
        this.id=null;
        this.name ="NO NAME";
        this.stock = -1;
        this.price = -1.00f;
        this.available = false;
    }

    public ItemEntity(Long id) {
        this();
        this.id = id;
    }

    public ItemEntity(Long id, String name, Integer stock, Float price, Boolean available) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
