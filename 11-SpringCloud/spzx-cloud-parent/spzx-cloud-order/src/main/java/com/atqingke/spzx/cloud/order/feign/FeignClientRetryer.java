package com.atqingke.spzx.cloud.order.feign;

import feign.RetryableException;
import feign.Retryer;

public class FeignClientRetryer implements Retryer {

    // 定义两个成员变量来决定重试次数
    private int start = 1 ;
    private int end = 3 ;

    @Override
    public void continueOrPropagate(RetryableException e) {     // 是否需要进行重试取决于该方法是否抛出异常，如果抛出异常重试结束
        if(start >= end) {
            throw new RuntimeException(e) ;
        }
        start++ ;
    }

    @Override
    public Retryer clone() {    // 框架底层调用该方法得到一个重试器
        return new FeignClientRetryer();
    }
}