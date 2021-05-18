package com.cbk.kg.excersice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "supplier_id")
    private int supplierId;

    @JsonProperty(value = "account")
    private String account;

    @JsonProperty(value = "amount")
    private Double amount;

    @JsonProperty(value = "command")
    private String command;

    @JsonProperty(value = "date")
    private String date;

}
