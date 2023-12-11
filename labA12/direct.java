package labA12;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class direct {
    static int k, ans;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        k = input.nextInt();
        node_B[] map = new node_B[n];
        //initialize
        for (int i = 0; i < n; i++) {
            node_B temp = new node_B();
            temp.index = i;
            map[i] = temp;
        }
        for (int i = 0; i < n - 1; ++i) {
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            map[u].children.add(map[v]);
            map[v].children.add(map[u]);
            map[u].degree++;
            map[v].degree++;
        }
        for (int i = 0; i < n; ++i) {
            if (map[i].degree > 1) { //when not a leave
                int addition= dfs(map[i], null) == 0 ? 0 : 1;
                out.println(ans + addition);
                out.close();
                return;
            }
        }
        out.println(ans + 1); //add its leave
        out.close();
    }

    static int dfs(node_B x, node_B fa) {
        if (x.degree == 1) {
            return 1;
        }
        int dep = 0;
        for (int i = 0; i < x.children.size(); i++) {
            node_B child = x.children.get(i);
            if (child != fa) {
                int d = dfs(child, x);
                if (d + dep > k) {
                    dep = Math.min(dep, d);
                    ++ans;
                    continue;
                }
                dep = Math.max(dep, d);
            }
        }
        return dep > 0 ? dep + 1 : 0;
    }
}

class node_B {
    int index;
    int degree = 0;
    node_B next;
    ArrayList<node_B> children = new ArrayList<>();
}
