package labA3;

import java.io.*;
import java.util.*;

public class lab5_oj2 {
    static node[] nodes;

    public static void main(String[] args) {
        while (true){
            QReader in = new QReader();

        QWriter out = new QWriter();
        int testcases = in.nextInt();
        for (int z = 0; z < testcases; z++) {
            int n = in.nextInt();
            int m = in.nextInt();
            nodes = new node[n + 1];
            for (int i = 0; i < n + 1; i++) {
                nodes[i] = new node();
                nodes[i].ance = i;
            }
            for (int i = 0; i < m; i++) {
                int a = in.nextInt();//a-->b
                int b = in.nextInt();
                nodes[b].indg.add(a);
                nodes[a].outdg.add(b);
            }
            for (int i = 1; i < n + 1; i++) {
                nodes[i].indegree = nodes[i].indg.size();
            }
            int tot = n;
            for (int i = 2; i < n + 1; i++) {
                int ance = srcance(nodes[i].indg.get(0));
                if (nodes[i].indg.size() >= 2) {    //入度大于2的合成一个点
                    for (int j = 0; j < nodes[i].indg.size(); j++) {
                        int a = srcance(nodes[i].indg.get(j));  //a是该入点的祖宗
                        node ne = nodes[a];
                        if (ne.ance != ance) {
                            tot--;
                            ne.ance = ance;
                            nodes[ance].indegree += ne.indegree;
                            nodes[ance].outdg.addAll(ne.outdg);
                        }
                    }
                }

            }
            Queue<Integer> queue = new LinkedList<>();
            if (nodes[srcance(1)].indegree == 0) {
                queue.add(1);
            }
            int count = 1;
            while (!queue.isEmpty()) {//求拓扑序
                int a = srcance(queue.poll());
                nodes[a].isvisited = true;
                for (int i = 0; i < nodes[a].outdg.size(); i++) {
                    int b = nodes[a].outdg.get(i);
                    int c = srcance(b);
                    node anc = nodes[c];
                    anc.indegree--;
                    if (!anc.isvisited) {
                        if (anc.dist < nodes[a].dist + 1) {
                            anc.dist = nodes[a].dist + 1;
                        }
                        if (anc.indegree == 0) {
                            queue.add(c);
                            count++;
                        }
                    }
                }
            }

            if (count != tot) {//输出
                out.println("No");
            } else {
                out.println("Yes");
                for (int i = 1; i < n + 1; i++) {
                    node nd = nodes[i];
                    if (i == 1) {
                        out.print("1 ");
                    } else if (i == n) {
                        int anc = srcance(i);
                        int fdanc = srcance(nd.indg.get(0));
                        node ancnd = nodes[anc];
                        node fdancnd = nodes[fdanc];
                        int dist = ancnd.dist - fdancnd.dist;
                        out.print(dist);
                    } else {
                        int anc = srcance(i);
                        int fdanc = srcance(nd.indg.get(0));
                        node ancnd = nodes[anc];
                        node fdancnd = nodes[fdanc];
                        int dist = ancnd.dist - fdancnd.dist;
                        out.print(dist + " ");
                    }
                }
                out.println("");
            }
        }
        out.close();
    }}

    public static int srcance(int index) {
        int ance = nodes[index].ance;
        if (index != ance) {
            node anc = nodes[ance];
            ance = anc.ance;
            ance = srcance(ance);
        }
        nodes[index].ance = ance;
        return ance;
    }
}

class node {
    int indegree = 0;
    boolean isvisited = false;
    int weight = 0;
    int dist = 0;
    int ance = -1;
    ArrayList<Integer> indg = new ArrayList<>();
    ArrayList<Integer> outdg = new ArrayList<>();
    ArrayList<Integer> newindg = new ArrayList<>();
}

