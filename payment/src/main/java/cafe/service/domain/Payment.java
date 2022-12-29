package cafe.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import cafe.service.PaymentApplication;
import cafe.service.external.PGDemo;
import lombok.Data;


@Entity
@Table(name="Payment_table")
@Data

public class Payment  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long paymentId;
    
    private Long customerId;
    
    private Long cafeId;
    
    private Long menuId;
    
    private Integer qty;
    
    private Long totalPrice;
    
    private Date orderDate;
    
    private Long approvalCode;

    private String status;
    
    private Long orderId;

    @PostPersist
    public void onPostPersist(){
        pg().startTransaction();
        Paid paid = new Paid(this);
        paid.publishAfterCommit();

    }
    @PreUpdate
    public void onPreUpdate(){


        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();

    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }

    public static PGDemo pg(){
        PGDemo pg = PaymentApplication.applicationContext.getBean(PGDemo.class);
        return pg;
    }




    public static void cancelPayment(CustomerOrderCanceled customerOrderCanceled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(customerOrderCanceled.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        
    }
    public static void cancelPayment(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        
    }


}
