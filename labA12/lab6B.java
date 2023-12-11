package labA12;

import java.io.*;
import java.util.StringTokenizer;

public class lab6B {
    static final int N = 300001;
    static int[] head = new int[N];
    static int[] target = new int[N];
    static int[] nexp = new int[N];
    static int[] degree = new int[N];
    static int  k, ans;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        k = input.nextInt()-1;
        int p = 0;
        for(int i=0;i<n-1;++i){
            int u=input.nextInt();
            int v=input.nextInt();
            nexp[p]= head[u];
            head[u]=p;
            target[p]=v;
            nexp[++p]= head[v];
            head[v]=p;
            target[p]=u;
            ++degree[u];
            ++degree[v];
            ++p;
        }
        for(int i=1;i<=n;++i){
            if(degree[i]>1){
                int re = dfs(i,0)==0?0:1;
                out.print(ans+re);
                out.close();
            }
        }
        out.print(ans+1);
        out.close();
    }


    public static int dfs(int x, int fa) {
        if (degree[x] == 1)
            return 1;
        int dep = 0;
        for (int u = head[x]; u!=0; u = nexp[u])
            if (target[u] != fa) {
                int d = dfs(target[u], x);
                if (d + dep > k) {
                    dep = dep < d ? dep : d;
                    ++ans;
                    continue;
                }
                dep = d > dep ? d : dep;
            }
        return dep != 0 ? dep + 1 : 0;
    }
}

