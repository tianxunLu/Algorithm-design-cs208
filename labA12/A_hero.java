package labA12;

import java.io.*;
import java.util.StringTokenizer;

public class A_hero {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int m = in.nextInt();
        int n = in.nextInt();
        int sum = 0;
        for (int i = 0; i < m; i++) {
            int reward = in.nextInt();
            sum+=reward;


        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        out.close();
    }
}
class node_A{
    int val;
    public node_A(int val) {
        this.val = val;
    }
}
