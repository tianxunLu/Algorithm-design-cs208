package labA11;

public class B_chessBoard {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(); //nxn
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }


        out.print(111);
        out.close();
    }
}
