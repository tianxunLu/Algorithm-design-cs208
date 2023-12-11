package labA3;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
6 3
2 1 3
1 2 4 5 6 8
//26
6 3
1 2 2
1 4 4 5 6 8
//22
6 4
1 1 1 4
1 2 3 4 5 6
//18
*/
//6 3 1 4 4 1 2 3 4 5 6
public class A_collectApples {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        //initial
        int n = in.nextInt();//apples
        int m = in.nextInt();//baskets
        int[] volume = new int[m];
        int[] position = new int[n];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            volume[i] = in.nextInt();
            sum+=volume[i];
        }
        for (int i = 0; i < n; i++) {
            position[i] = in.nextInt();
        }
        //operate
        volume = merge_sort(volume,m);
        long[] appendix = new long[m];
        appendix[m-1] = volume[m-1];
        for (int i = 1; i < m; i++) {
            appendix[m-i-1] = appendix[m-i]+volume[m-1-i];
        }
        long length = 0;
        if (sum>n){
            int left = 0,right = m-1;
            while (left <= right){
                int mid = (left+right)/2;
                if (appendix[mid] < n){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }
            length+=position[n-1];
            int previous = n-1;
            for (int i = m-1; i > right; i--) {
                previous-=volume[i];
                length+=position[previous];

            }
        }else {
            int previousPosition = 0;
            for (int i = 0; i < m; i++) {
                long temp = position[volume[i] + previousPosition - 1];
                length += temp;
                previousPosition += volume[i];
            }
        }
        out.println(length*2);
        out.close();
    }


    public static int[] merge_sort(int[] A,int n){
        if (n>1) {
            int p = n / 2;
            int[] B = Arrays.copyOfRange(A, 0, p);//bqd
            int[] C = Arrays.copyOfRange(A, p, A.length);
            B = merge_sort(B, p);
            C = merge_sort(C, n - p);
            A = merge(B, p, C, n - p);
        }
        return A;
    }

    public static int[] merge(int[] L, int nl, int[] R, int nr){
        int n = nl+nr;
        int i = 0,j = 0;
        int[] A = new int[n];
        for (int k = 0; k < n; k++) {
            if (i<nl && (j>=nr || L[i]<=R[j])){
                A[k] = L[i];i++;
            }else {
                A[k] = R[j];j++;
            }
        }
        return A;
    }
}



