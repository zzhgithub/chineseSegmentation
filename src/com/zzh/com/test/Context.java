package com.zzh.com.test;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 上下文
 * Created by zhouzihao on 2018/6/8.
 */
public interface Context {

    //这里还可以加入需要的数据参数
    ByteBuffer buffer();
    State state();
    void state(State state);
    Map<Character,Long> index();
}
