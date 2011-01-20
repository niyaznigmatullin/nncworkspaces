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
    private int dig_root(BigInteger x) {
        int y = 0;
        while (x.compareTo(BigInteger.ZERO) == 1) {
            y += x.mod(BigInteger.valueOf(10)).intValue();
            x = x.divide(BigInteger.valueOf(10));
        }
        if (y == 0) return 0; else return (y % 9 == 0) ? 9 : y % 9;
    }
    private void solve() {
        int n = nextInt(), m = nextInt();
        boolean[] a = new boolean[100001], b = new boolean[100001];
        for (int i = 0; i < n; i++) a[nextInt()] = true;
        for (int i = 0; i < m; i++) b[nextInt()] = true;
        for (int i = 0; i <= 100000; i++) if (a[i] && b[i]) out.print(i + " ");
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
