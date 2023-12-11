package labA9;

import java.io.*;
import java.util.StringTokenizer;

public class B {
    static class node {
        int l, r, lmax, rmax, mmax;
        int lazy = -1;

        public node(int l, int r) {
            this.l = l;
            this.r = r;
            this.lmax = rmax = mmax = r - l + 1;
        }
    }

    static node[] list;

    public static void build(int k, int l, int r) {
        list[k] = new node(l, r);
        if (l != r) {
            int m = (l + r) >> 1;
            build(k * 2, l, m);
            build(k * 2 + 1, m + 1, r);
        }
    }


    public static void change(int k, int l, int r, int v) {
        if (l <= list[k].l && list[k].r <= r) {
            list[k].lmax = list[k].rmax = list[k].mmax = v * (list[k].r - list[k].l + 1);
            list[k].lazy = v;
        } else {
            //lazy down
            if (list[k].lazy != -1) {
                list[k * 2].mmax = list[k * 2].lmax = list[k * 2].rmax = list[k].lazy * (list[k * 2].r - list[k * 2].l + 1);
                list[k * 2 + 1].mmax = list[k * 2 + 1].lmax = list[k * 2 + 1].rmax = list[k].lazy * (list[k * 2 + 1].r - list[k * 2 + 1].l + 1);
                list[k * 2].lazy = list[k * 2 + 1].lazy = list[k].lazy;
                list[k].lazy = -1;
            }

            int m = (list[k].l + list[k].r) >> 1;
            if (l > m) {
                change(k * 2 + 1, l, r, v);
            } else if (r <= m) {
                change(k * 2, l, r, v);
            } else {
                change(k * 2, l, m, v);
                change(k * 2 + 1, m + 1, r, v);
            }
            if (list[k * 2].mmax == list[k * 2].r - list[k * 2].l + 1)
                list[k].lmax = list[k * 2].r - list[k * 2].l + 1 + list[k * 2 + 1].lmax;
            else{
                list[k].lmax = list[k * 2].lmax;
            }
            if (list[k * 2 + 1].mmax == list[k * 2 + 1].r - list[k * 2 + 1].l + 1)
                list[k].rmax = list[k * 2 + 1].r - list[k * 2 + 1].l + 1 + list[k * 2].rmax;
            else{
                list[k].rmax = list[k * 2 + 1].rmax;
            }
            list[k].mmax = Math.max(Math.max(list[k * 2].mmax, list[k * 2 + 1].mmax), list[k * 2].rmax + list[k * 2 + 1].lmax);
        }
    }

    public static int get(int k, int len) {
        if (list[k].l == list[k].r) {
            return list[k].l;
        } else {
            //lazy down
            if (list[k].lazy != -1) {
                list[k * 2].mmax = list[k * 2].lmax = list[k * 2].rmax = list[k].lazy * (list[k * 2].r - list[k * 2].l + 1);
                list[k * 2 + 1].mmax = list[k * 2 + 1].lmax = list[k * 2 + 1].rmax = list[k].lazy * (list[k * 2 + 1].r - list[k * 2 + 1].l + 1);
                list[k * 2].lazy = list[k * 2 + 1].lazy = list[k].lazy;
                list[k].lazy = -1;
            }

            if (list[k * 2].mmax >= len) {
                return get(k * 2, len);
            } else if (list[k * 2].rmax + list[k * 2 + 1].lmax >= len) {
                return list[k * 2 + 1].l - list[k * 2].rmax;
            } else if (list[k * 2 + 1].mmax >= len) {
                return get(k * 2 + 1, len);
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        QReader sc = new QReader();
        QWriter out = new QWriter();
        int n = sc.nextInt();
        int m = sc.nextInt();
        list = new node[n << 2];
        build(1, 1, n);
        for (int i = 0; i < m; i++) {
            int w = sc.nextInt();
            if (w == 1) {
                int len = sc.nextInt();
                int ml = get(1, len);
                out.println(ml);
                if (ml != 0) {
                    change(1, ml, ml + len - 1, 0);
                }
            } else {
                int x = sc.nextInt();
                int y = sc.nextInt();
                change(1, x, y, 1);
            }
        }
        out.close();
    }

}

