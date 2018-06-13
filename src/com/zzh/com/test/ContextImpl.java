package com.zzh.com.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhouzihao on 2018/6/8.
 */
public class ContextImpl implements Context {

    private State state;
    private State preState;
    private ByteBuffer buffer;
    private Map<Character,Long> indexMap = new ConcurrentHashMap<>();
    private List<Character> list = new ArrayList<>();
    private Matrix matrix = new ElasticMatrix();

    public ContextImpl(List<Character> list, Matrix matrix) {
        if (Objects.nonNull(list))
            this.list = list;
        if (!Objects.nonNull(matrix))
            this.matrix = matrix;
    }

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
