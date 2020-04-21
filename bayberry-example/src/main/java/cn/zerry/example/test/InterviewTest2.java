package cn.zerry.example.test;

import java.util.LinkedList;
import java.util.List;

/**
 * 把1，2，3，4，…，2020这2020个数均匀排成一个大圆圈，
 * 从1开始数，第1个留着，接下来3个（2，3,4）划掉，
 * 第5个留着，接下来3个（6，7，8）划掉， 一直处理下去。
 * 问：最后剩下哪个数？
 * 答：1329
 */
public class InterviewTest2 {
    public static Integer NUM = 2020;

    public static List<Integer> getList(){
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= NUM; i++) {
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        int step = 4;
        List<Integer> list = getList();
        int index = 0;
        for(int i = 0; list.size() > 1; i++) {
            System.out.println(list.toString());
            if((i+1) % step != 1) {
                list.remove(index);
            } else {
                index++;
            }
            if(index == list.size()){
                index = 0;
            }
        }
        // 1329
        System.out.println(list.get(0));
    }
}
