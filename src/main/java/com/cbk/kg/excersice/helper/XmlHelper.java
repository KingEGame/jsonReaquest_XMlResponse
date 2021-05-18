package com.cbk.kg.excersice.helper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class XmlHelper {

    public static String serialize(Object object) throws JAXBException, UnsupportedEncodingException {

        JAXBContext ctx = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = ctx.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshaller.marshal(object, sw);
        return sw.toString();
    }
}
