package com.zzh.com.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhouzihao on 2018/6/8.
 */
public class ContextImpl implements Context {

    State state;
    State preState;
    ByteBuffer buffer;
    Map<Character,Long> indexMap = new ConcurrentHashMap<>();
    List<Character> list = new ArrayList<>();
    Matrix matrix = new ElasticMatrix();

    @Override
    public Map<Character, Long> index() {
        return this.indexMap;
    }

    public void buffer(String file) throws IOException{
        RandomAccessFile fis = new RandomAccessFile(new File(file), "rw");
        FileChannel channel = fis.getChannel();
        long size = channel.size();
        buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
    }

    @Override
    public ByteBuffer buffer() {
        return buffer;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public void state(State state) {
        this.state = state;
    }

    @Override
    public State preState() {
        return preState;
    }

    @Override
    public void preState(State state) {
        preState = state;
    }

    @Override
    public List<Character> indexList() {
        return list;
    }

    @Override
    public void addIndex(Character character) {
        list.add(character);
    }

    @Override
    public Matrix matrix() {
        return matrix;
    }


    //todo 结果的持久化
}
