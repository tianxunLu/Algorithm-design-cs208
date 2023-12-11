package Helpper.Lab9;

import java.io.*;
import java.util.StringTokenizer;

public class Lab9B_occupy {
    static nodeB[] tree;
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] requests = new int[m][3];
        for (int i = 0; i < m; i++) {
            requests[i][0] = input.nextInt();
            if (requests[i][0] == 1) requests[i][1] = input.nextInt();
            else if (requests[i][0] == 2) {
                requests[i][1] = input.nextInt();
                requests[i][2] = input.nextInt();
            }
        }
        // preprocess
        tree = new nodeB[2*n];
        buildTree(1,1,n);

        // requests
        for (int i = 0; i < m; i++) {
            int type = requests[i][0];
            if (type == 1){
                int num = requests[i][1];
                int index = searchRoom(1,num);
                // "Ture" indicate check-in
                changeOccupy(1,index,index+num-1,true);
                out.println(index);
            }
            else if (type == 2){
                int left = requests[i][1], right = requests[i][2];
                // "False" indicate check-out
                changeOccupy(1,left,right,false);
            }
        }
        out.close();
    }

    public static void buildTree(int index, int l, int r) {
        tree[index] = new nodeB(l, r);
        if (l != r) {
            int m = (l + r)/2;
            buildTree(index*2, l, m);
            buildTree(index*2+1, m+1, r);
        }
    }

    public static int searchRoom(int index, int num) {
        nodeB root = tree[index];
        // leaf node
        if (root.left == root.right){
            if (root.maxLen < num) return 0;
            else return root.left;
        }
        nodeB leftChild = tree[2*index], rightChild = tree[2*index+1];
        if (root.maxLen < num) return 0;
        if (leftChild.maxLen >= num) return searchRoom(2*index,num);
        else if (rightChild.maxLen < root.maxLen) return leftChild.right-leftChild.rLen+1;
        else return searchRoom(2*index+1,num);
    }

    public static void changeOccupy(int index,int left,int right,boolean flag) {
        nodeB root = tree[index];
        // update lazy before update node
        checkLazy(index);

        // leaf node
        if (root.left == root.right){
            if (root.left >= left && root.right <= right) {
                // check-in
                if (flag) {
                    // set zero to root
                    root.lLen = root.rLen = root.maxLen = 0;
                }
                // check-out
                else {
                    // set full to root
                    root.lLen = root.rLen = root.maxLen = 1;
                }
                // update parent
                updateParent(index);
            }
            return;
        }

        nodeB leftChild = tree[2*index], rightChild = tree[2*index+1];
        // Case02 Fully Inside
        if (root.left >= left && root.right <= right) {
            // check-in
            if (flag) {
                // set zero to root
                root.lLen = root.rLen = root.maxLen = 0;
                // update lazy before mark node
                checkLazy(2*index);
                checkLazy(2*index+1);
                // mark occupyLazy
                leftChild.OLazy = rightChild.OLazy = true;
            }
            // check-out
            else {
                // set full to root
                root.lLen = root.rLen = root.maxLen = root.right-root.left+1;
                // update lazy before mark node
                checkLazy(2*index);
                checkLazy(2*index+1);
                // mark occupyLazy
                leftChild.FLazy = rightChild.FLazy = true;
            }
            // update parent
            updateParent(index);
            return;
        }
        // Case03 Intersect partially
        if (leftChild.left <= right && leftChild.right >= left) changeOccupy(2*index,left,right,flag);
        if (rightChild.left <= right && rightChild.right >= left) changeOccupy(2*index+1,left,right,flag);
    }

    public static void checkLazy(int index){
        nodeB root = tree[index];
        if (root.OLazy){
            root.lLen = root.rLen = root.maxLen = 0;
            root.OLazy = false;
        }
        if (root.FLazy){
            root.lLen = root.rLen = root.maxLen = root.right-root.left+1;
            root.FLazy = false;
        }
    }

    public static void updateParent(int index){
        if (index == 1) return;
        index = index/2;
        nodeB root = tree[index], leftChild = tree[2*index], rightChild = tree[2*index+1];
        // update lazy before update parent
        checkLazy(2*index);
        checkLazy(2*index+1);
        // update three max
        root.lLen = leftChild.lLen;
        root.rLen = rightChild.rLen;
        root.maxLen = Math.max(leftChild.rLen+rightChild.lLen,Math.max(leftChild.maxLen,rightChild.maxLen));
        updateParent(index);
    }
}
class nodeB {
    int left, right;
    int lLen,rLen,maxLen;
    boolean OLazy,FLazy;
    public nodeB(int l, int r) {
        this.left = l;
        this.right = r;
        this.lLen = this.rLen = this.maxLen = r-l+1;
        this.OLazy = this.FLazy = false;
    }
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
