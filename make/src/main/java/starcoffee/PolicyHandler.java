package starcoffee;

import starcoffee.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired MakeRepository makeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApporved_AcceptOrder(@Payload PaymentApporved paymentApporved){

        if(!paymentApporved.validate()) return;

        System.out.println("\n\n##### listener AcceptOrder : " + paymentApporved.toJson() + "\n\n");



        // Sample Logic //
        Make make = new Make();
        make.setOrderId(paymentApporved.getOrderId());
        make.setStatus("making");
        
        makeRepository.save(make);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_CancleOrder(@Payload OrderCanceled orderCanceled){

        if(!orderCanceled.validate()) return;

        System.out.println("\n\n##### listener CancleOrder : " + orderCanceled.toJson() + "\n\n");



        // Sample Logic //
        Make make = new Make();
        make.setOrderId(orderCanceled.getOrderId());
        make.setStatus("make cancle");
        
        makeRepository.save(make);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
