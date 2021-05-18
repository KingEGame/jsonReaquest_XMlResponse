package com.cbk.kg.excersice.model.request;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Check {

    private  Request request;

}
