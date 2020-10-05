package com.demo.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private int edgeNum; //记录边的个数
    private char[] vertexs; //顶点数组
    private int [][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//使用INF 表示两个顶点不能联通

    public static void main(String[] args) {

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int [][] matrix  = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };
        //创建KruskalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        //输出构建的
        kruskalCase.print();


        EData[] edges = kruskalCase.getEdges();
        //未排序
        for (EData edge : edges) {
            System.out.println(edge);
        }
        System.out.println("排序后");
        kruskalCase.sortEdges(edges);
        for (EData edge : edges) {
            System.out.println(edge);
        }

    }

    //构造器
    public KruskalCase(char[] vertexs, int[][] matrix){
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点 复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i <vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边 使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {// 0 -> i+ 1
                if (this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }

    }

    //打印邻接举证
    public void print(){
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {

                System.out.printf("%12d\t", matrix[i][j]);

            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理 冒泡
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight >edges[j+1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch 传入的顶点的值 比如 'A' 'B'
     * @return 返回ch对应的下标 如果找不到 返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        //找不到返回-1
        return -1;
    }

    /**
     *  获取图中的边 放到EData[] 数组中 后面需要遍历该数组
     *  通过matrix 邻接矩阵获取
     *  EData[] 形式 [['A', 'B', 12], ['B', 'F', 7],... ]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];

        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     *  获取下标为i的顶点的终点 用于判断两个顶点的终点是否相同
     * @param ends  记录了各个顶点对应的终点是哪个  ends数组是在遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

//创建一个类 EData  它的对象实例就表示一条边
class EData{
    char start; //边的一个点
    char end; //边的另外一个点
    int weight; //边的权值
    //构造器
    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    //重写toString方法便于输出这条边
    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}