package com.cctegitc.swcpt.function.feign.encode;

import java.lang.reflect.Type;

import feign.form.spring.SpringFormEncoder;
import org.springframework.web.multipart.MultipartFile;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;

/**
 * @author jiangyang
 * @date 2022-01-12 13:41:36
 */
public class CustomEncoder implements Encoder {

    private SpringFormEncoder springFormEncoder = new SpringFormEncoder();

    private JacksonEncoder jacksonEncoder = new JacksonEncoder();

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        if (type.equals(MultipartFile.class)) {
            springFormEncoder.encode(o, type, requestTemplate);
        } else {
            jacksonEncoder.encode(o, type, requestTemplate);
        }

    }
}
