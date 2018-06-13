package com.zzh.com.test;

import java.io.IOException;
import java.util.List;

/**
 * 文件字频统计
 * Created by zhouzihao on 2018/6/8.
 */
public class Zzh {

    //数据持久化
    private static Storage<List<Character>> storage = new Storage<>();
    private static Storage<ElasticMatrix> storagemat = new Storage<>();
    private static final String file = "/Users/zhouzihao/mytmp/chineseSegmentation/all.txt";

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        System.out.println("开始测试");
        ContextImpl context = new ContextImpl(storage.read("indexlist"),new ElasticMatrix());
        context.state(States.START);
        try {
            context.buffer(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        while (context.state().process(context));
        System.out.println("结束测试");
        long end = System.currentTimeMillis();

        try {
            storage.write("indexlist", context.indexList());
            storagemat.write("matrix",(ElasticMatrix)context.matrix());
            System.out.print("save result over;");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(String.format("===>读取并打印文件耗时：%s毫秒", end - start));
    }
}
