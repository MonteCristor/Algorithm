package com.demo.prim;

import com.sun.org.apache.xml.internal.security.keys.content.MgmtData;

import java.util.Arrays;

public class PrimAlgorithm {

    public static void main(String[] args) {

        //测试图是否创建成功
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示 10000这个大数 表示两个点不连通
        int [][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        //创建MGraph对象
        MGraph mGraph = new MGraph(verxs);
        //创建一个MinTree
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);

        //输出
        minTree.showGraph(mGraph);

        //测试普利姆算法
        minTree.prim(mGraph, 1);

    }
}
//创建最小生成树
class MinTree{
    //创建图的邻接矩阵

    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight){
        int i, j;
        for (i = 0; i < verxs; i++) {//顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    //显示图的方法
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法 得到最小生成树

    /**
     *
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成 'A'->0 'B'->1
     */
    public void prim (MGraph graph, int v){
        //标记顶点是否被访问过
        int[] visited = new int[graph.verxs];
        //visited默认元素的值都是0 表示没有访问过

        //标记当前节点已访问
        visited[v] = 1;
        //用h1 和h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;

        int minWeight = 10000;//将minWeight初始成一个大数 后面遍历过程中 会被替换
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs顶点 prim算法结束后 有graph.verxs-1边

            //确定每一次生成的子图 和哪两个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) {//i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) {//j意图表示还没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        //替换minWeight 寻找已经访问过的节点和为访问过的节点间的权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边最小
            System.out.println("边<" +graph.data[h1] + "," + graph.data[h2] + "> 权值:" +minWeight);
            //将当前找到的节点标记为已经访问
            visited[h2] = 1;
            //重置minWeight 为最大值 10000
            minWeight = 10000;
        }
    }
}

class MGraph{
    int verxs; //表示图的节点个数
    char[] data;//保存节点的数据
    int [][] weight; //存放边 就是邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }


}