package labA12;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Challenge {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        for(int t=0;t<100;t++) {
            int n = in.nextInt();
            long m = in.nextLong();
            int k = in.nextInt();
//        long[][] items = new long[n][3];
            Object[][] items1 = new Object[n][2];

            for (int i = 0; i < n; i++) {
                long p = in.nextLong();
                long q = in.nextLong();
                Item item = new Item(p, q);
                items1[i][0] = item;
                items1[i][1] = q;
            }

            Arrays.sort(items1, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] a, Object[] b) {
                    return Long.compare((long) a[1], (long) b[1]);
                }
            });
//        long sum = 0;
            long max = 0;
            Object[][] items2 = new Object[n - k][2];
            Object[][] items3 = new Object[n - k][2];
            PriorityQueue<Long> queue = new PriorityQueue();
            for (int i = 0; i < n; i++) {
                Item item = (Item) items1[i][0];
                if (i < k) {
                    m -= (long) items1[i][1];
                    max++;
                    queue.add(item.p - (long) items1[i][1]);
                    if (m < 0) {
                        out.println(max - 1);
                        break;
                    } else if (max == n || m == 0) {
                        out.println(max);
                        break;
                    }
                } else {
                    items2[i - k][0] = item.p;
                    items2[i - k][1] = items1[i][0];
                    items3[i - k][0] = items1[i][0];
                    items3[i - k][1] = items1[i][1];
                }
            }
            if (m > 0 && max < n) {
                Arrays.sort(items2, new Comparator<Object[]>() {
                    @Override
                    public int compare(Object[] a, Object[] b) {
                        return Long.compare((long) a[0], (long) b[0]);
                    }
                });
                Arrays.sort(items3, new Comparator<Object[]>() {
                    @Override
                    public int compare(Object[] a, Object[] b) {
                        return Long.compare((long) a[1], (long) b[1]);
                    }
                });
                int i = 0;
                int j = 0;
                boolean flag = true;
                while (m > 0) {
                    if (queue.size() != 0) {
                        Item item2 = (Item) items2[i][1];
                        while (item2.isBought) {
                            i++;
                            if (i < n - k) {
                                item2 = (Item) items2[i][1];
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        Item item3 = (Item) items3[j][0];
                        while (item3.isBought) {
                            j++;
                            if (j < n - k) {
                                item3 = (Item) items3[j][0];
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            out.println(max);
                            break;
                        }
                        long discount = queue.remove();
                        long min = Math.min((long) items2[i][0], discount + (long) items3[j][1]);
                        if (min == (long) items2[i][0]) {
                            Item item = (Item) items2[i][1];
                            item.isBought = true;
                            queue.add(discount);

                        } else {
                            Item item = (Item) items3[j][0];
                            item.isBought = true;
                            queue.add(item.p - (long) items3[j][1]);

                        }
                        m -= min;
                    } else {
                        long min = (long) items2[i][0];
                        i++;
                        m -= min;
                    }
                    max++;
                    if (m < 0) {
                        out.println(max - 1);
                        break;
                    } else if (max == n || m == 0) {
                        out.println(max);
                        break;
                    }
                }
            }
        }
        out.close();
    }
}

class Item {
    long p;
    long q;
    boolean isBought = false;

    public Item(long p, long q) {
        this.p = p;
        this.q = q;
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
