package starcoffee;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long price;
    private Long orderId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        PaymentApporved paymentApporved = new PaymentApporved();
        paymentApporved.setStatus("Pay OK");
        BeanUtils.copyProperties(this, paymentApporved);
        paymentApporved.publishAfterCommit();

        try { // 피호출 서비스(결제:pay) 의 임의 부하 처리 - 400 밀리에서 증감 220 밀리 정도 왔다갔다 하게
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    @PostUpdate
    public void onPostUpdate(){
        PaymentCancled paymentCancled = new PaymentCancled();
        BeanUtils.copyProperties(this, paymentCancled);
        paymentCancled.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}