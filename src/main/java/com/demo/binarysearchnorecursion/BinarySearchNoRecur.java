package com.demo.binarysearchnorecursion;

public class BinarySearchNoRecur {

    public static void main(String[] args) {

        //测试
        int [] arr = {1,3,8,10,11,67,100};

        int index = binarySearch(arr, -8);

        System.out.println("index =" + index);

    }


    //二分查找的非递归实现
    /**
     *
     * @param arr 待查找的数组 arr是升序排列
     * @param targtet 需要查找的数
     * @return 返回对应的下标 -1 表示没有找到
     */
    public static int binarySearch(int []  arr, int targtet){

        int left = 0;
        int right = arr.length -1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == targtet){
                return mid;
            } else if (arr[mid] > targtet){
                right = mid -1;//需要向左边查找
            } else {
                left = mid + 1;//需要想右边查找
            }
        }
        return  -1;
    }

}
