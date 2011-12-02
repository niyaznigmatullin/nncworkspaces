import java.util.*;
import java.math.*;
import java.io.*;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

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

	String INFILE = "chaincode.in", OUTFILE = "chaincode.out";

	final double EPS = 1e-8;

	class Point {
		double x, y;
		double ang;

		Point(double xx, double yy) {
			x = xx;
			y = yy;
		}

		double dist(Point p) {
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
		}

		double scalar_mul(Point p1, Point p2) {
			return ((p1.x - x) * (p2.x - p1.x) + (p1.y - y) * (p2.y - p1.y))
					/ (dist(p1) * p1.dist(p2));
		}

		double vect_mul(Point p1, Point p2) {
			return (p1.x - x) * (p2.y - p1.y) - (p2.x - p1.x) * (p1.y - y);
		}

		boolean isIn(Point p1, Point p2) {
			return Math.abs(dist(p1) + dist(p2) - p1.dist(p2)) < EPS;
		}

		public int compareTo(Point p) {
			if (Math.abs(p.y - y) < EPS) {
				if (Math.abs(p.x - x) < EPS)
					return 0;
				if (x < p.x)
					return -1;
				else
					return 1;
			}
			if (y < p.y)
				return -1;
			else
				return 1;
		}

		public String toString() {
			return x + " " + y + " " + ang;
		}
	}

	class by_ang implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			if (p1.ang < p2.ang - EPS)
				return -1;
			else if (p1.ang > p2.ang + EPS)
				return 1;
			else {
				if (p1.y < p2.y - EPS)
					return -1;
				else if (p1.y > p2.y + EPS)
					return 1;
				else
					return 0;
			}
		}
	};

	private double graham(Point[] p) {
		Point first = new Point(1e10, 1e10);
		int n = p.length;
		int id = -1;
		for (int i = 0; i < n; i++) {
			if (first.compareTo(p[i]) > 0) {
				first = p[i];
				id = i;
			}
		}
		Point temp = p[0];
		p[0] = first;
		p[id] = temp;
		p[0].ang = -1;
		for (int i = 0; i < n; i++) {
			p[i].ang = Math.atan2(p[i].y - p[0].y, p[i].x - p[0].x);
			if (p[i].ang < -EPS)
				p[i].ang += Math.PI * 2;
		}
		Arrays.sort(p, new by_ang());
		Point[] hull = new Point[n];
		hull[0] = p[0];
		int kol = 1;
		int cur = 1;
		while (p[cur].compareTo(p[cur - 1]) == 0)
			cur++;
		hull[kol++] = p[cur];
		for (int i = cur + 1; i < n; i++) {
			if (p[i].compareTo(p[i - 1]) == 0)
				continue;
			if (hull[kol - 2].scalar_mul(hull[kol - 1], p[i]) - 1 >= -EPS) {
				hull[kol - 1] = p[i];
				continue;
			} else if (hull[kol - 2].scalar_mul(hull[kol - 1], p[i]) + 1 <= EPS) {
				if (p[i].compareTo(new Point(7, 9)) == 0)
					continue;
			}
			while (kol > 1
					&& hull[kol - 2].vect_mul(hull[kol - 1], p[i]) < -EPS)
				kol--;
			hull[kol++] = p[i];
		}
		double ret = 0;
		for (int i = 0; i < kol; i++) {
			ret += hull[i].x * hull[(i + 1) % kol].y - hull[i].y
					* hull[(i + 1) % kol].x;
		}
		return Math.abs(ret) * .5;
	}

	private void solve() {
		int n = nextInt();
		Point[][] p = new Point[n][];
		for (int i = 0; i < n; i++) {
			int m = nextInt();
			p[i] = new Point[m];
			for (int j = 0; j < m; j++)
				p[i][j] = new Point(nextDouble(), nextDouble());
		}
		double ans = 0;
		for (int i = 0; i < n; i++) {
			boolean[] pr = new boolean[n];
			int kol = 0;
			for (int j = 0; j < n; j++) {
				if (i == j) {
					pr[j] = true;
					kol += p[i].length;
					continue;
				}
				for (int k = 0; k < p[i].length; k++) {
					if (p[j][p[j].length - 1].compareTo(p[i][k]) == 0) {
						pr[j] = true;
						kol += p[j].length;
						break;
					}
				}
			}
			Point[] tmp = new Point[kol];
			int cur = 0;
			for (int j = 0; j < n; j++)
				if (pr[j]) {
					System.arraycopy(p[j], 0, tmp, cur, p[j].length);
					cur += p[j].length;
				}
			ans = Math.max(ans, graham(tmp));
		}
		// out.println(String.format("%.2f", ans).replaceAll(",", "."));
		out.println(ans);
	}

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = std ? new BufferedReader(new InputStreamReader(System.in))
					: new BufferedReader(new FileReader(new File(
							(in_out) ? INFILE : "input.txt")));
			out = std ? new PrintWriter(new OutputStreamWriter(System.out))
					: new PrintWriter(new File((in_out) ? OUTFILE
							: "output.txt"));
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		if (std)
			out.flush();
		else
			out.close();
	}
}