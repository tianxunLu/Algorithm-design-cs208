package labA3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_sameWay {
    public static void main(String[] args) {
        while (true){
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt(); //cases
        for (int i = 0; i < T; i++) {
            int n = in.nextInt(); //nodes
            int m = in.nextInt(); //edges
            node_B[] nodes = new node_B[n];
            for (int j = 0; j < n; j++) {
                node_B temp = new node_B();
                temp.ancestor = temp; //ancestor is itself
                nodes[j] = temp;
            }
            for (int j = 0; j < m; j++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                nodes[b].father.add(nodes[a]);
                nodes[a].child.add(nodes[b]);
                nodes[b].indegree++;
                nodes[a].val = a;
                nodes[b].val = b;
            }
            for (int j = 0; j < n; j++) {
                if (nodes[j].indegree > 1) {
                    node_B chosen = nodes[j].father.get(0);
                    chosen.ancestor = find(chosen);
                    for (int k = 1; k < nodes[j].father.size(); k++) { //k=1?
                        node_B pre_father = nodes[j].father.get(k);
                        node_B ance_pre_father = find(pre_father);
                        if (ance_pre_father!=chosen.ancestor){
                            chosen.indegree += ance_pre_father.indegree;
                            chosen.child.addAll(ance_pre_father.child);
                            ance_pre_father.ancestor = chosen.ancestor;
                        }
                    }
                }
            }
            node_B[] queue = new node_B[n];
            int front = 0, rear = 0;
            node_B root = nodes[0];
            queue[rear++] = root; //put the root in
            root.visited = true;
            boolean flag = true;
            if (nodes[0].indegree != 0) {
                flag = false;
            }
            //when the queue is empty the traverse ends
            int value = 1;
            while (rear != front) {
                node_B curr = queue[front++].ancestor;
                if (!curr.visited) {
                    curr.visited = true;
                    curr.dist = value++;
                }
                for (int j = 0; j < curr.child.size(); j++) {
                    node_B pre_child = curr.child.get(j);
                    pre_child.ancestor.indegree--;
                    if (pre_child.ancestor.indegree==0){
                        queue[rear++] = pre_child.ancestor;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                find(nodes[j]).iscondensed = true;
            }
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (nodes[j].iscondensed) sum++;
            }
            if (rear != sum || !flag) {
                out.println("No");
            } else {
                //find value
                out.println("Yes");
                out.print(1 + " ");
                for (int j = 1; j < n-1; j++) {
                    int dist1 = nodes[j].ancestor.dist;
                    int dist2 = 0;
                    for (int k = 0; k < nodes[j].father.size(); k++) {
                        dist2 = Math.max(nodes[j].father.get(0).ancestor.dist,dist2);
                    }
                    out.print((dist1 - dist2) + " ");
                }
                out.print("\n");
            }
        }
        out.close();
        }
    }

    static node_B find(node_B x) {
        if (x.ancestor == x) {
            return x;
        }
        x.ancestor = find(x.ancestor);
        return x.ancestor;
    }
}

class node_B {
    int val;
    int dist;
    node_B ancestor;
    ArrayList<node_B> father = new ArrayList<>(); //this do not change
    ArrayList<node_B> child = new ArrayList<>();
    int indegree = 0;
    boolean visited = false;
    boolean iscondensed = false;
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}