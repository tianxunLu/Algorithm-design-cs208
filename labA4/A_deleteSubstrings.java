package labA4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
sustechcs208television
4
cs
television
cs208
sustech
//3
<<<====================@=====>~
3
<<
~
===
//9
 */
public class A_deleteSubstrings {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String main = in.nextLine(); //overall str
        int n = in.nextInt(); //#substr
        node_A[] strs = new node_A[n];
        int len = main.length();
        ArrayList<node_A>[] pakage = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            ArrayList<node_A> temp = new ArrayList<>();
            pakage[i] = temp;
        }
        for (int i = 0; i < n; i++) {
            node_A temp = new node_A();
            strs[i] = temp;
        }
        //put all elements found into package
        for (int i = 0; i < n; i++) {
            String inputStr = in.nextLine();
            strs[i].substr = inputStr;
            ArrayList<Integer> temp = new ArrayList<>();
            temp = kmpSearch(main, inputStr);
            int stri_len = inputStr.length();
            for (int j = 0; j < temp.size(); j++) {
                int end = temp.get(j) + stri_len - 1;
                node_A newnode = new node_A();
                newnode.substr = strs[i].substr;
                newnode.begin = temp.get(j);
                newnode.length = stri_len;
                pakage[end].add(newnode);
            }
        }
        for (int i = 0; i < len; i++) {
            if (pakage[i].size() > 1) {
                Collections.sort(pakage[i], new StringLengthComparator());
            }
        }
        int result = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < pakage[i].size(); j++) {
                node_A pre_node = pakage[i].get(j);
                result++;
                int begin = pre_node.begin;
                for (int k = i; k >= begin; k--) {
                    for (int l = 0; l < pakage[k].size(); l++) {
                        if (pakage[k].get(l).begin <= begin) {
                            pakage[k].remove(pakage[k].get(l));
                            l--;
                        }
                    }
                }
            }
        }
        out.println(result);
        out.close();

    }

    public static ArrayList<Integer> kmpSearch(String string, String pattern) {
        int[] next = buildNext(pattern);
        ArrayList<Integer> allfound = new ArrayList<>();
        int i = 0, j = 0;
        while (i < string.length()) {
            if (j < pattern.length()) {
                if (string.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                } else if (j > 0) j = next[j - 1];
                else i++;
                if (j == pattern.length()) {
                    allfound.add(i - j);
                    j = next[next.length - 1];
                }
            } else {
                i++;
            }
        }
        return allfound;
    }

    public static int[] buildNext(String pattern) {
        int[] next = new int[pattern.length()];
        int prefix_len = 0;
        next[0] = 0;
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(prefix_len) == pattern.charAt(i)) {
                prefix_len++;
                next[i++] = prefix_len;
            } else {
                if (prefix_len == 0) next[i++] = 0;
                else prefix_len = next[prefix_len - 1];
            }
        }
        return next;
    }
}

class StringLengthComparator implements Comparator<node_A> {
    @Override
    public int compare(node_A s1, node_A s2) {
        return Integer.compare(s2.begin, s1.begin);
    }
}

class node_A {
    String substr;
    int begin;
    int length;
}
