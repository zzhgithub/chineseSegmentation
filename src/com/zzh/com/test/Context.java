package com.zzh.com.test;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * 上下文
 * Created by zhouzihao on 2018/6/8.
 */
public interface Context {

    /**
     * get the buffer of file
     * @return
     */
    ByteBuffer buffer();

    /**
     * get current State
     * @return
     */
    State state();

    /**
     * set current State
     * @return
     */
    void state(State state);


    /**
     * get the Index of Character
     * @return
     */
    Map<Character,Long> index();

    State preState();

    void preState(State state);

    List<Character> indexList();

    void addIndex(Character character);
}
