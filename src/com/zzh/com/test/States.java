package com.zzh.com.test;

import static java.lang.Character.UnicodeBlock.*;

/**
 * Created by zhouzihao on 2018/6/8.
 */
public enum States implements State {
    START{
        @Override
        public boolean process(Context context) {
            /**
             * 判断中文英文还是结束
             */
            if (context.buffer().position() == context.buffer().capacity()){
                context.state(END);
                return true;
            }
            context.buffer().mark();//标记
            if(context.buffer().capacity()-context.buffer().position()>=3){
                byte[] test = new byte[3];
                context.buffer().get(test);
                char[] list = new String(test).toCharArray();
                if(Until.isChinese(list[0])) {
                    context.state(CN);
                }else {
                    context.state(EN);
                }
            }else {
                context.state(EN);
            }
            return true;
        }
    },
    CN{
        @Override
        public boolean process(Context context) {
            context.buffer().reset();//恢复
            byte[] test = new byte[3];//中文要读3个字符
            context.buffer().get(test);
            char[] list = new String(test).toCharArray();
            indexOf(context,list[0]);
            // 计算字间互信息矩阵
            if (context.preState() == CN){
                matrix(context,preChar,list[0]);
            }
            context.state(START);
            context.preState(CN);
            preChar = list[0];
            return true;
        }
    },
    EN{
        @Override
        public boolean process(Context context) {
            context.buffer().reset();//恢复
            //处理英文
            byte[] test = new byte[1];//英文只要读1个字符
            context.buffer().get(test);
            char[] list = new String(test).toCharArray();
            //FIXME 这里不处理英文单词
            context.state(START);
            context.preState(EN);
            return true;
        }
    },
    END{
        @Override
        public boolean process(Context context) {
            System.out.println("end");
            return false;
        }
    };


    public void indexOf(Context context,char i){
        //这里不能存中文的标点符号
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(i);
        if (ub == CJK_SYMBOLS_AND_PUNCTUATION
                || ub == CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B){
            return;
        }
        Long hit = context.index().get(i);
        if (hit ==null){
            context.index().put(i,0L);
            context.addIndex(i);
        }else {
            context.index().put(i,hit+1);
        }
    }

    public static char preChar;

    //todo 记录一个词后出现另外一个词
    // 这里使用的汉字个数是 3500到4000那么使用
    public void matrix(Context context,char pre,char next){
        //index list
        Integer x = context.indexList().indexOf(pre);
        Integer y = context.indexList().indexOf(next);
        //这里跳过没有收录的字段
        if(x<0 || y<0){
            return;
        }
        //决定了使用链表吧！反正是稀松矩阵嘞
        context.matrix().inc(x,y);
    }

}
