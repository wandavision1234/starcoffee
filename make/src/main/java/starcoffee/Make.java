package starcoffee;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Make_table")
public class Make {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long menuId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        CoffeeMade coffeeMade = new CoffeeMade();
        BeanUtils.copyProperties(this, coffeeMade);
        coffeeMade.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        CoffeeCancled coffeeCancled = new CoffeeCancled();
        BeanUtils.copyProperties(this, coffeeCancled);
        coffeeCancled.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}