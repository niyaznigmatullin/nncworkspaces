import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.math.*;
import java.io.*;

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
    StringTokenizer st;
    PrintWriter out;
    BufferedReader br;
    boolean eof = false, in_out = false;
    private String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return "0";
            }
        }
        return st.nextToken();
    }
    private String nextLine() {
        String ret = "";
        try {
            ret = br.readLine();
        } catch (Exception e) {
            ret = "";
        }
        if (ret == null) {
            eof = true;
            return "$";
        }
        return ret;
    }
    private String nextString() {
        return nextToken();
    }
    private int nextInt() {
        return Integer.parseInt(nextToken());
    }
    private long nextLong() {
        return Long.parseLong(nextToken());
    }
    private double nextDouble() {
        return Double.parseDouble(nextToken());
    }
    private BigInteger nextBigInteger() {
        return new BigInteger(nextToken());
    }
    private String to_precision(double x, int kol) {
        if (kol == 0) {
            return Long.toString((long)Math.floor(x));
        }
        String ret = Long.toString((long)Math.floor(x));
        x -= Math.floor(x);
        for (int i=0; i<kol; i++) {
            x *= 10;
        }
        ret += "." + Long.toString((long)Math.floor(x));
        if ((long)Math.floor(x) == 0) {
            ret += "0";
        }
        return ret;
    }
    private static double EPS = 1e-9;
    String INFILE = "bear.in", OUTFILE = "bear.out";
    int maxN = (1 << 18) - 1;
    int[] tr, a;
    private void update(int x, int y) {
        x += maxN;
        tr[x] = x - maxN;
        x >>= 1;
        for (;x > 0;x >>= 1) {
            if (a[tr[x << 1]] > a[tr[(x << 1) | 1]]) tr[x] = tr[x << 1]; else tr[x] = tr[(x << 1) | 1];
        }
    }
    private int get_max(int l, int r) {
        l += maxN;
        r += maxN;
        int ans = -1;
        while (l <= r) {
            if ((l & 1) == 1) {
                if (ans == -1 || a[tr[l]] > a[ans]) ans = tr[l];
                l++;
            }
            if ((r & 1) == 0) {
                if (ans == -1 || a[tr[r]] > a[ans]) ans = tr[r];
                r--;
            }
            l >>= 1;
            r >>= 1;
        }
        return ans;
    }
    private void solve() {
        int n = nextInt();
        tr = new int[2 * maxN + 4];
        a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i + 1] = nextInt();
            update(i + 1, a[i + 1]);
        }
        int[] que1 = new int[n * 2], que2 = new int[n * 2], rt = new int[n * 2];
        int head = 0, tail = 0;
        rt[tail] = 0;
        que1[tail] = 1;
        que2[tail++] = n;
        long ans = 0;
        while (head < tail) {
            int l = que1[head], r = que2[head], rr = rt[head];
            head++;
            int yy = get_max(l, r);
            ans = ans + (long)Math.max(0, rr - a[yy]);
            if (yy + 1 <= r) {
                que1[tail] = yy + 1;
                que2[tail] = r;
                rt[tail] = a[yy];
                tail++;
            }
            if (yy - 1 >= l) {
                que1[tail] = l;
                que2[tail] = yy - 1;
                rt[tail] = a[yy];
                tail++;
            }
        }
        out.println(ans);
    }
    public void run() {
        try {
            br = new BufferedReader(new FileReader(new File((in_out) ?  INFILE  : "input.txt")));
            out = new PrintWriter(new File((in_out) ? OUTFILE : "output.txt"));
            //br = new BufferedReader(new FileReader(new File("input.txt")));
            //out = new PrintWriter(new File("output.txt"));
        } catch (Exception e) {
            System.exit(111);
        }
        solve();
        out.close();
    }
}
