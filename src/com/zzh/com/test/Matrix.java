package com.zzh.com.test;

import java.io.Serializable;

/**
 * 管理矩阵的类 2元组
 * Created by zhouzihao on 2018/6/12.
 */
public interface Matrix extends Serializable{

    /**
     * 给固定位置添加一
     * @param x
     * @param y
     */
    void inc(Integer x,Integer y);

    /**
     * 获取固定位置的值
     * @param x
     * @param y
     * @return
     */
    Long getValue(Integer x,Integer y);


    /**
     * 设置固定位置的值
     * @param x
     * @param y
     */
    void setValue(Integer x,Integer y,Long value);

}
