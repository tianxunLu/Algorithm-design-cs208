package labA3;

import java.util.Random;

public class data2 {
    public static void main(String[] args) {
        int count = 100;
        Random r = new Random();
        System.out.println(100);
        for (int i = 0; i < count; i++) {
            int a = r.nextInt(100)+5;
            int b = r.nextInt(100)+5;
            System.out.print(a+" "+b+" ");
            for (int j = 0; j < b; j++) {
                for (int k = 0; k < b; k++) {
                    int x = r.nextInt(a-1)+1;
                    int y = r.nextInt(a-1)+1;
                    System.out.print(x+" "+y+" ");
                }
            }
        }
    }
}
