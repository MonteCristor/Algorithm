package com.demo.dac;

public class Hanoitower {

    public static void main(String[] args) {

        hanoiTower(5, 'A','B','C');

    }

    //汉诺塔移动的方法
    //使用分治算法
    public static  void hanoiTower(int num, char a, char b, char c){
        //如果只有一个盘
        if (num == 1){
            System.out.println("第1个盘从 " + a + "->" + c);
        } else  {
            //如果 n>2 看做是两个盘 1. 最下边的一个盘 2. 上面所有的盘
            //1. 先把上面的所有盘 A->B  移动过程会使用C
            hanoiTower(num -1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3. 把B塔的所有盘 从 B->C 移动过程使用A塔
            hanoiTower(num -1, b, a, c);
        }

    }
}
