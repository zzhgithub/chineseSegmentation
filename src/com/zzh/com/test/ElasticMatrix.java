package com.zzh.com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 可伸缩二维 的 稀松矩阵
 * Created by zhouzihao on 2018/6/13.
 */
public class ElasticMatrix implements Matrix{

    private Map<Integer,Map<Integer,Long>> data = new HashMap<>();

    @Override
    public void inc(Integer x, Integer y) {
        if(Objects.isNull(getValue(x,y))){
            setValue(x,y,1L);
            return;
        }else {
            setValue(x,y,getValue(x,y) + 1L);
        }
    }

    @Override
    public Long getValue(Integer x, Integer y) {
        Map<Integer,Long> yy = data.get(x);
        if (Objects.isNull(yy)){
            return null;
        }

        if (Objects.isNull(yy.get(y))){
            return null;
        }

        return yy.get(y);
    }

    @Override
    public void setValue(Integer x, Integer y, Long value) {
        Map<Integer,Long> yy = data.get(x);
        if (Objects.isNull(yy)){
            yy = new HashMap<Integer,Long>();
            yy.put(y,value);
            data.put(x,yy);
            return;
        }
        data.get(x).put(y,value);
    }
}
