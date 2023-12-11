package labA4;

import java.io.*;
import java.util.*;

public class B_sellFruit {
    static HashMap<Integer,Fruit> map;
//    static ArrayList<Integer> active;
    static HashMap<Integer,Fruit> max;
    static Fruit[] pos;
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//       int count = in.nextInt();
//        while(true){
//            long startTime = System.nanoTime();
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(); //overall count
        List<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int l = in.nextInt()-1;
            int r = in.nextInt()-1;
            int v = in.nextInt();
            fruits.add(new Fruit(l,r,v));
            fruits.get(i).val = i;
        }
        //find active timeslot
        Collections.sort(fruits, Comparator.comparingInt(Fruit::getL));
        int x = 0;
//        active = new ArrayList<>();
        map = new HashMap<>();
        max = new HashMap<>();
        pos = new Fruit[n];
        //build hashmap
        for (int i = 0; i < n; i++) {
            Fruit pre_f = fruits.get(i);
            x = Math.max(x+1,pre_f.l);
//            active.add(x);
            max.put(x,pre_f);
            pre_f.max_of_two = x;
            pre_f.val_in_left = i;
            pos[i] = pre_f;
        }
        //find right position
        Collections.sort(fruits);
        ArrayList<Fruit> fruit_set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Fruit pre_f = fruits.get(i);
            if (matched(pre_f,pre_f.l)){
                fruit_set.add(pre_f);
            }
        }
        //max profit
        long result = 0;
        for (int i = 0; i < fruit_set.size(); i++) {
            result+=fruit_set.get(i).v;
        }
//            String a = in.nextLine();
//        System.out.println(result);
//            long endTime = System.nanoTime();
//            System.out.println(endTime-startTime);

        out.println(result);
        out.close();
    }
//}

    static boolean matched(Fruit a,int x){
        if (x>a.r) return false;
        if (map.get(x) == null){
            map.put(x,a);
            return true;
        }
        Fruit b = map.get(x);
//        int next_active = active.get(active.indexOf(x)+1);
        int next;
        if (max.containsKey(x)){
            next = pos[max.get(x).val_in_left+1].max_of_two;
        } else {
            next = x+1;
        }
//        System.out.println(next_active+" "+next);
        if (a.r>b.r){
            return matched(a,next);
        }else {
            if (matched(b, next)){
                map.put(x,a);
                return true;
            }
        }
        return false;
    }
        //}
    static class Fruit implements Comparable<Fruit> {
        int val;
        int l;
        int r;
        int v;
        int val_in_left;
        int max_of_two;
        public Fruit(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }
        static int getL(Fruit a){
            return a.l;
        }
        @Override
        public int compareTo(Fruit o) {
            return Integer.compare(o.v, v);
        }
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