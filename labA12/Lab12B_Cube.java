package labA12;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lab12B_Cube {
    static node source = new node();
    static node drain = new node();
    static int infinity = 40*40*1000+1;
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
//            while(true){
        int a = input.nextInt(), b = input.nextInt(), h = input.nextInt();
            System.out.println(a);
        int D = input.nextInt();
        int minCut = 0;
        if (h == 1) {
            for (int j = 0; j < a; j++) {
                for (int k = 0; k < b; k++) {
                    minCut += input.nextInt();
                }
            }
        }
        else {
            node[][][] cube = new node[h-1][a][b];
            // first level
            for (int j = 0; j < a; j++) {
                for (int k = 0; k < b; k++) {
                    cube[0][j][k] = new node();
                    int weight = input.nextInt();
                    source.children.add(cube[0][j][k]);
                    source.weight.add(weight);
                }
            }
            // except first and final
            for (int i = 1; i < h-1; i++) {
                for (int j = 0; j < a; j++) {
                    for (int k = 0; k < b; k++) {
                        cube[i][j][k] = new node();
                        int weight = input.nextInt();
                        cube[i-1][j][k].children.add(cube[i][j][k]);
                        cube[i-1][j][k].weight.add(weight);
                    }
                }
            }
            // final level
            for (int j = 0; j < a; j++) {
                for (int k = 0; k < b; k++) {
                    int weight = input.nextInt();
                    cube[h-2][j][k].children.add(drain);
                    cube[h-2][j][k].weight.add(weight);
                }
            }

            // add constraint
            for (int i = h-2; i >= D ; i--) {
                for (int j = 0; j < a; j++) {
                    for (int k = 0; k < b; k++) {
                        if (j+1 < a){
                            cube[i][j][k].children.add(cube[i-D][j+1][k]);
                            cube[i][j][k].weight.add(infinity);
                        }
                        if (j-1 >= 0){
                            cube[i][j][k].children.add(cube[i-D][j-1][k]);
                            cube[i][j][k].weight.add(infinity);
                        }
                        if (k+1 < b){
                            cube[i][j][k].children.add(cube[i-D][j][k+1]);
                            cube[i][j][k].weight.add(infinity);
                        }
                        if (k-1 >= 0){
                            cube[i][j][k].children.add(cube[i-D][j][k-1]);
                            cube[i][j][k].weight.add(infinity);
                        }
                    }
                }
            }


            while (createLevel(source,a*b*h+3)){
                while (true) {
                    int maxFlow = findArgument(source,infinity);
                    if (maxFlow != 0) minCut += maxFlow;
                    else break;
                }
                source.current = 0;
                drain.current = 0;
                source.Level = -1;
                drain.Level = -1;
                for (int i = 0; i < h-1; i++) {
                    for (int j = 0; j < a; j++) {
                        for (int k = 0; k < b; k++) {
                            cube[i][j][k].Level = -1;
                            cube[i][j][k].current = 0;

                        }
                    }
                }
            }
        }
        output.println(minCut);
//    }
        output.close();
    }
    public static boolean createLevel(node root,int n){
        node[] queue = new node[n];
        int front = 0, rear = 0;
        queue[rear++] = root;
        root.Level = 0;
        while (front != rear){
            node temp = queue[front++];
            for (int i = 0; i < temp.children.size(); i++) {
                node child = temp.children.get(i);
                int weight = temp.weight.get(i);
                if (child.Level == -1 && weight != 0){
                    queue[rear++] = child;
                    child.Level = temp.Level + 1;
                }
            }
        }
        return drain.Level != -1;
    }
    public static int findArgument(node root, int flow){
        if (root == drain) return flow;
        for (int i = root.current; i < root.children.size(); i++) {
            root.current = i;
            node child = root.children.get(i);
            int weight = root.weight.get(i);
            if (child.Level == root.Level+1 && weight != 0){
                int maxFlow = findArgument(child,Math.min(flow,weight));
                if (maxFlow > 0){
                    root.weight.set(i, weight-maxFlow);
                    int index = child.children.indexOf(root);
                    if (index == -1) {
                        child.children.add(root);
                        child.weight.add(maxFlow);
                    }
                    else {
                        child.weight.set(index,child.weight.get(index)+maxFlow);
                    }
                    return maxFlow;
                }
            }
        }
        return 0;
    }
}

class node{
    int Level = -1;
    int current = 0;
    ArrayList<node> children = new ArrayList<>();
    ArrayList<Integer> weight = new ArrayList<>();
}
