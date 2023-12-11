package labA3;

import java.util.Random;

public class data {
    public static void main(String[] args) {
        int count = 1000;
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int a = r.nextInt(100);
            int b = r.nextInt(100);
            System.out.print(a+" "+b+" ");
            for (int j = 0; j < b; j++) {
                int x = r.nextInt(10000);
                System.out.print(x+" ");
            }
            for (int j = 0; j < a; j++) {
                int y = r.nextInt(10000);
                System.out.print(y+" ");
            }
        }
    }
}
