package com.dg.ssm.controller.convert;

import org.springframework.core.convert.converter.Converter;

public class StringTrimconvert implements Converter<String, String> {
    @Override
    public String convert(String s) {
        try {
            if (s != null) {
                s = s.trim();
                if (s.equals("")) {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
}
