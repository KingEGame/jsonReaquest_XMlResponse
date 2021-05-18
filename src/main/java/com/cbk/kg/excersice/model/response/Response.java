package com.cbk.kg.excersice.model.response;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response implements Serializable {

    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String dts;

    @XmlElement
    private Long p_id;

    @XmlElement
    private Integer status;

    @XmlElement
    private String message;

}
