package com.zzh.com.test;

import java.io.IOException;

/**
 * 文件字频统计
 * Created by zhouzihao on 2018/6/8.
 */
public class Zzh {

    private static final String file = "/Users/zhouzihao/mytmp/statetest/all.txt";

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        System.out.println("开始测试");
        ContextImpl context = new ContextImpl();
        context.state(States.START);
        try {
            context.buffer(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        while (context.state().process(context));
        System.out.println("结束测试");
        long end = System.currentTimeMillis();
        System.out.println(String.format("===>读取并打印文件耗时：%s毫秒", end - start));
    }
}
