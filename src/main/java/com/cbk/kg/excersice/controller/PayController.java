package com.cbk.kg.excersice.controller;

import com.cbk.kg.excersice.entity.Payment;
import com.cbk.kg.excersice.enums.PaymentStatusesEnum;
import com.cbk.kg.excersice.helper.XmlHelper;
import com.cbk.kg.excersice.model.request.Check;
import com.cbk.kg.excersice.model.request.Pay;
import com.cbk.kg.excersice.model.response.Response;
import com.cbk.kg.excersice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@RestController
public class PayController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/hello")
    public String gateway() {
        return "Hello World";
    }

    @PostMapping(value = "/check", produces = {MediaType.APPLICATION_XML_VALUE})
    public String check( @RequestBody(required = true) Check check) throws JAXBException, UnsupportedEncodingException {
        Timestamp currentDateTime = Timestamp.valueOf(LocalDateTime.now());
        if(paymentService.getByRequestId(check.getRequest().getId()) == null){
            paymentService.save(getPaymant(check, currentDateTime, PaymentStatusesEnum.ERROR.getId()));
            return response(check, currentDateTime,PaymentStatusesEnum.ERROR.getId(), "Account doesn't exist");
        }else if(!paymentService.getByRequestId(check.getRequest().getId()).getAccount().equals(check.getRequest().getAccount())){
            paymentService.save(getPaymant(check, currentDateTime, PaymentStatusesEnum.ERROR.getId()));
            return response(check, currentDateTime,PaymentStatusesEnum.ERROR.getId(), "Account doesn't exist");
        }

        paymentService.save(getPaymant(check, currentDateTime, PaymentStatusesEnum.CHECKED.getId()));

        return response(check, currentDateTime, PaymentStatusesEnum.CHECKED.getId(),"Account Exists");
    }

    private Payment getPaymant(Check check, Timestamp timestamp, Integer status){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         Payment payment = Payment
                .builder()
                .account(check.getRequest().getAccount())
                .amount(check.getRequest().getAmount())
                .command(check.getRequest().getCommand())
                .date(Timestamp.valueOf(format.format(timestamp)))
                .supplierId(check.getRequest().getSupplierId())
                .requestId(check.getRequest().getId())
                .paymentId(timestamp.getTime())
                .status(status)
                .build();
         return payment;
    }

    private String response(Check check, Timestamp timestamp,Integer status, String message) throws JAXBException, UnsupportedEncodingException {
        Response response = Response
                .builder()
                .id(check.getRequest().getId())
                .dts(check.getRequest().getDate())
                .p_id(timestamp.getTime())
                .status(status)
                .message(message)
                .build();


        XmlHelper xmlHelper = new XmlHelper();
        return  xmlHelper.serialize(response);
    }

    @PostMapping(value = "/pay", produces = {MediaType.APPLICATION_XML_VALUE} )
    public String pay(@RequestBody(required = true) Pay pay) throws JAXBException, UnsupportedEncodingException {
        Timestamp currentDateTime = Timestamp.valueOf(LocalDateTime.now());
        XmlHelper xmlHelper = new XmlHelper();

        Payment payment = Payment
                .builder()
                    .account(pay.getRequest().getAccount())
                    .amount(pay.getRequest().getAmount())
                    .command(pay.getRequest().getCommand())
                    .date(Timestamp.valueOf(pay.getRequest().getDate()))
                    .supplierId(pay.getRequest().getSupplierId())
                    .requestId(pay.getRequest().getId())
                    .paymentId(currentDateTime.getTime())
                    .status(PaymentStatusesEnum.CONFIRMED.getId())
                .build();
        paymentService.save(payment);

        Response response = Response
            .builder()
            .id(pay.getRequest().getId())
            .dts(pay.getRequest().getDate())
            .p_id(payment.getPaymentId())
            .status(PaymentStatusesEnum.CONFIRMED.getId())
            .message("Payment confirmed")
            .build();
        return  xmlHelper.serialize(response);

    }

    @GetMapping("/")
    public String greeting(Authentication authentication) {
        return "Spring Security In-memory Authentication Example";
    }
}