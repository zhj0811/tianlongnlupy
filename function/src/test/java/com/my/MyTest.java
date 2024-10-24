package com.my;

import org.junit.Test;

public class MyTest {

    @Test
    public void getProperty() {
        System.out.println(System.getProperty("java.library.path"));
    }
}
