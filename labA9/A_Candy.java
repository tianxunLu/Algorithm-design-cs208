package labA9;
import java.io.*;
import java.util.StringTokenizer;

public class A_Candy {
    static node[] tree;
    static int[] candies;
    static int[] positions;
    static int sum;
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
//        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        candies = new int[n];
        positions = new int[n];
        for (int i = 0; i < n; i++) {
            candies[i] = input.nextInt();
        }
        // preprocess
        tree = new node[2*n];
        tree[1] = new node(1,n);
        buildTree(1,1,n);
        for (int i = 0; i < m; i++) {
            int type = input.nextInt();
            if (type == 1){
                sum = 0;
                int left = input.nextInt();
                int right = input.nextInt();
                findSum(1,left,right);
                out.println(sum);
            }else {
                int pos = input.nextInt();
                int num = input.nextInt();
                modify(pos,num);
            }
        }
        out.close();
    }

    public static void buildTree(int index, int l, int r) {
        tree[index] = new node(l, r);
        if (l != r) {
            int m = (l + r)/2;
            buildTree(index * 2, l, m);
            buildTree(index * 2 + 1, m + 1, r);
            tree[index].sum = tree[2*index].sum + tree[2*index+1].sum;
        }
        else {
            tree[index].sum = candies[l-1];
            positions[l-1] = index;
        }
    }
    public static void buildTree(int index){
        node current = tree[index];
        int left = current.left, right = current.right;
        if (left != right) {
            int mid = (left+right)/2;
            tree[2*index] = new node(left,mid);
            buildTree(2*index);
            tree[2*index+1] = new node(mid+1,right);
            buildTree(2*index+1);
            tree[index].sum = tree[2*index].sum + tree[2*index+1].sum;
        }
        else {
            tree[index].sum = candies[left-1];
            positions[left-1] = index;
        }
    }

    public static void findSum(int index,int left,int right){
        if (tree[index].left>right || tree[index].right<left) return;
        if (tree[index].left>=left && tree[index].right<=right){
            sum+=tree[index].sum;
        }else {
            findSum(index*2,left,right);
            findSum(index*2+1,left,right);
        }
    }

    public static void modify(int pos,int num) {
        int index = positions[pos-1], diff = num - candies[pos-1];
        while (index >= 1) {
            tree[index].sum += diff;
            index /= 2;
        }
    }
}
class node {
    int left;
    int right;
    int sum;

    public node(int left, int right) {
        this.left = left;
        this.right = right;
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