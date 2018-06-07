package com.sbt.javaschool.ignite.benchmark;

import java.util.Map;
import java.util.Random;

public class ExampleServiceRandomStrBenchmark extends ExampleServiceAbstractBenchmark {
    private final Random rnd = new Random();
    private final char[] chars = "1234567890qwertyuiopasdfghjklzxcvbnm".toCharArray();

    @Override public boolean test(Map<Object, Object> ctx) throws Exception {
        service().toUpperCase(randomStr());

        return true;
    }

    protected String randomStr() {
        char[] strChars = new char[rnd.nextInt(100) + 1];

        for (int i = 0; i < strChars.length; i++) {
            strChars[i] = chars[rnd.nextInt(chars.length)];
        }

        return new String(strChars);
    }
}
