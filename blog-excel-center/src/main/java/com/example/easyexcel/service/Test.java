package com.example.easyexcel.service;

import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-04-28
 */
@Service
public class Test {

    public Integer add (String a,int b) {

        return Integer.parseInt(a) + b;
    }

    public Integer divide (String a,int b) {
        return Integer.parseInt(a)/b;
    }

}
