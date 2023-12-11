//package labA11;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class A_mod {
//    static long mod = (long) (1e9+7);
//    static long x = 0,y = 0;
//    static int[] child;
//    static long[][] FL;
//    static long inv(long i)
//    {
//        if(i==1)return 1;
//        return (mod-mod/i)*inv(mod%i)%mod;
//    }
//    static void exgcd(long a,long b){
//        if (b==0){
//            x=1;y=0;
//        }else {
//            exgcd(b,a%b);
//            long tem=x;
//            x=y;
//            y=tem-a/b*y;
//        }
//    }
//    static long gcd(long a,long b){
//        if(b==0)
//            return a;
//        return gcd(b,a%b);
//    }
//    static long lcm(long a, long b) {
//        return a*b/gcd(a, b);
//    }
//    static fraction plus(fraction a,fraction b){
//        long dlcm = lcm(a.denominator,b.denominator);
//        long plus1 = dlcm/a.denominator,plus2 = dlcm/b.denominator;
//        fraction re = new fraction(plus1*a.numerator+plus2*b.numerator, dlcm);
//        return re;
//    }
//    static fraction multiple(fraction a,fraction b){
//        long gcd1 = gcd(a.numerator,b.denominator);
//        long gcd2 = gcd(a.denominator,b.numerator);
//        long fenzi = (a.numerator/gcd1)*(b.numerator/gcd2);
//        long fenmu = (a.denominator/gcd2)*(b.denominator/gcd1);
//        return new fraction(fenzi,fenmu);
//    }
//    static fraction[][] arr;
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int n = in.nextInt();
//        int k = in.nextInt(); //the final
//        node[] tree = new node[n+1];//
//        child = new int[n];
//        FL = new long[n][n];
//        for (int i = 1; i < n+1; i++) {//
//            node temp = new node();
//            temp.val = i;
//            tree[i] = temp;
//        }
//        for (int i = 0; i < n-1; i++) {
//            int a = in.nextInt();//
//            int b = in.nextInt();//
//            tree[a].child.add(tree[b]);
//            tree[b].child.add(tree[a]);
//        }
//        long sum = 0;
////        arr = new fraction[n][n+1];
//        for (int i = 1; i < n+1; i++) {//
//            tree[i].weight = in.nextInt();
//            sum = sum+(long)(tree[i].weight);
//            int a = in.nextInt(),b = in.nextInt();
////            fraction inarr = new fraction(a,b);
//            tree[i].nominator = a;tree[i].denominator = b;
////            arr[i][1] = inarr;
////            arr[i][0] = new fraction(inarr.denominator-inarr.numerator,inarr.denominator);
////            tree[i].initial = inarr;
//        }
////        fraction re = new fraction(0,1);
//        long result = 0;
//        for (int i = 1; i < n+1; i++) {
////            clear
//            for (int j = 0; j < n; j++) {
//                child[j] = 0;
//                for (int l = 0; l < n; l++) {
//                    FL[j][l] = 0;
//                }
//            }
//
//            exgcd(sum,mod);
//
//            x=(x+mod)%mod;
//            FL[i][1]=((tree[i].weight%mod)*(x%mod))%mod;
//            x=y=0;
////            for (int j = 0; j < n; j++) {
////                arr[j][1] = new fraction(arr[j][0].denominator-arr[j][0].numerator,arr[j][0].denominator);
////                for (int l = 2; l <= n; l++) {
////                    fraction rearr = new fraction(1,1);
////                    arr[j][l] = rearr;
////                }
////            }
////            for (int j = 0; j < n; j++) {
////                tree[j].isVisited = false;
////                tree[j].childnum  = 1;
////            }
////            tree[i].isVisited = true;
//            dfss(tree[i],0);
////            dfs(tree[i]);
////            long gcd = gcd(tree[i].weight,sum);
////            long fenmu = sum,fenzi = tree[i].weight;
////            if (gcd>1){
////                fenmu/=gcd;fenzi/=gcd;
////            }
////            fraction w = new fraction(fenzi,fenmu);
////            re = plus(re,multiple(w,arr[i][k]));
////            res=(res%mod+FP[t][k]%mod)%mod;
////            re.numerator = (re.numerator+mod)%mod;
////            re.denominator = (re.denominator+mod)%mod;
////            long invert = inv(re.denominator);
////            result =  (((re.numerator+mod)%mod)*((invert+mod)%mod))%mod;
//            result=(result%mod+FL[i][k]%mod)%mod;
//        }
//        out.print(result);
//        out.close();
//    }
//
//    static void dfss(node root,int father){
//        int val = root.val;
//        child[val] = 1;
//        for (int i = 0; i < root.child.size(); i++) {
//            node children = root.child.get(i);
//            int valchild = children.val;
//            if (valchild!=father){
//                exgcd(children.denominator,mod);
//                x = (x+mod)%mod;
//                FL[valchild][1]=(children.nominator%mod)*(x%mod)%mod;
//                FL[valchild][0]=((children.denominator-children.nominator+mod)%mod)*(x%mod)%mod;
//                x = 0;
//                y = 0;
//                dfss(children, val);
//                child[val]+=child[valchild];
//                for(int j=child[val];j>=1;j--){
//                    FL[val][j]=(FL[val][j]%mod*FL[valchild][0]%mod)%mod;
//                    for(int k=1;k<=child[valchild]&&k<j;k++){
//                        FL[val][j]= (FL[val][j]%mod+(FL[val][j-k]%mod*FL[valchild][k]%mod)%mod)%mod;
//                    }
//                }
//            }
//        }
//    }
//    static void dfs(node root){
//        for (int i = 0; i < root.child.size(); i++) {
//            if (root.child.get(i).isVisited == false){
//                root.child.get(i).isVisited = true;
//                dfs(root.child.get(i));
//                node children = root.child.get(i);
//                root.childnum += children.childnum;
//                //construct all updates
//                int child1 = root.childnum;
//                int child2 = children.childnum;
//                fraction[] update = new fraction[child1+1];
//                for (int j = 1; j <= child1; j++) {
//                    fraction temp = new fraction(0,1);
//                    for (int k = j; k >= 1 & j-k<=child2 & j-k>=0; k--) {
//                        fraction a = arr[root.val][k];
//                        fraction b =  arr[children.val][j-k];
//                        if (a!=null && b!=null & (a.denominator!=1 & b.denominator!=1)){
//                            temp = plus(temp,multiple(a,b));
//                        }
//                    }
//                    update[j] = temp;
//                }
//                //update
//                for (int j = 1; j <= child1; j++) {
//                    arr[root.val][j] = update[j];
//                }
//            }
//
//        }
//    }
//}
//class fraction{
//    public fraction(long numerator, long denominator) {
//        this.numerator = numerator;
//        this.denominator = denominator;
//    }
//
//    long numerator = 1;
//    long denominator = 1;
//}
//class node{
//    int val;
//    int weight;
//    int nominator;
//    int denominator;
//    ArrayList<node> child = new ArrayList<>();
//    node father;
//    fraction initial;
//    boolean isVisited = false;
//    int childnum = 1;
//}
//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//}
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException e) {
//            return;
//        }
//    }
//}