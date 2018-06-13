package com.zzh.com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 数据持久化类
 * Created by zhouzihao on 2018/6/13.
 */
public class Storage<T> {

    public void write(String path,T t) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
        out.writeObject(t);
        out.close();
    }

    public T read(String path) {
        try {

            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
            Object obj = in.readObject();
            if (null != obj) {
                return (T) obj;
            }
            return null;
        }catch (Exception e) {
            return null;
        }
    }
}
