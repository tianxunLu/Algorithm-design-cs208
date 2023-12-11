package labA11;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A_mod {
    static long mod = (long) (1e9 + 7);
    static long x = 0, y = 0;
    static int[] child;
    static long[][] arr;

    static void gcd(long a, long b) {
        if (b == 0) {
            x = 1;
            y = 0;
        } else {
            gcd(b, a % b);
            long temp = x;
            x = y;
            y = temp - a / b * y;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int k = in.nextInt(); //the final
        nodeA[] tree = new nodeA[n + 1];//
        child = new int[n + 1];
        arr = new long[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {//
            nodeA temp = new nodeA();
            temp.val = i;
            tree[i] = temp;
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();//
            int b = in.nextInt();//
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }
        long sum = 0;
//        arr = new fraction[n][n+1];
        for (int i = 1; i < n + 1; i++) {//
            tree[i].weight = in.nextInt();
            sum = sum + (long) (tree[i].weight);
//            fraction inarr = new fraction(a,b);
            tree[i].nominator = in.nextInt();
            tree[i].denominator = in.nextInt();
        }
        long result = 0;
        for (int i = 1; i < n + 1; i++) {
//            clear
            for (int j = 0; j < n+1; j++) {
                child[j] = 0;
                for (int l = 0; l < n+1; l++) {
                    arr[j][l] = 0;
                }
            }

            gcd(sum, mod);
            x = (x + mod) % mod;
            arr[i][1] = ((tree[i].weight % mod) * (x % mod)) % mod;
            x = y = 0;
            dfs(tree[i], 0);
            result = (result % mod + arr[i][k] % mod) % mod;
        }
        out.print(result);
        out.close();
    }

    static void dfs(nodeA root, int father) {
        int val = root.val;
        child[val] = 1;
        for (int i = 0; i < root.child.size(); i++) {
            nodeA children = root.child.get(i);
            int valchild = children.val;
            if (valchild != father) {
                gcd(children.denominator, mod);
                x = (x + mod) % mod;
                arr[valchild][0] = ((children.denominator - children.nominator + mod) % mod) * (x % mod) % mod;
                arr[valchild][1] = (children.nominator % mod) * (x % mod) % mod;
                x = y = 0;
                dfs(children, val);
                child[val] += child[valchild];
                for (int j = child[val]; j >= 1; j--) {
                    arr[val][j] = (arr[val][j] % mod * arr[valchild][0] % mod) % mod;
                    for (int k = 1; k <= child[valchild] && k < j; k++) {
                        arr[val][j] = (arr[val][j] % mod + (arr[val][j - k] % mod * arr[valchild][k] % mod) % mod) % mod;
                    }
                }
            }
        }
    }

}

class nodeA {
    int val;
    int weight;
    int nominator;
    int denominator;
    ArrayList<nodeA> child = new ArrayList<>();
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