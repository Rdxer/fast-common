package com.rdxer.common.ex;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ToStringEx {
    @SneakyThrows
    public static String toStringWithPretty(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        return s;
    }

    public static void toStringWithPrettyPrint(Object o) {
        System.out.println(toStringWithPretty(o));
    }




}
