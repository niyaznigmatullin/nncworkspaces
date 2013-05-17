package lib.test.on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.D___Ksusha_and_Square;



import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TaskD {

    static long count;

    static void doCount(Point2DInteger[] p) {
        count = 0;
        long area = 0;
        for (int i = 0; i < p.length; i++) {
            int j = i + 1;
            if (j == p.length) j = 0;
            int dx = Math.abs(p[i].x - p[j].x);
            int dy = Math.abs(p[i].y - p[j].y);
            area += p[i].vmul(p[j]);
            count += MathUtils.gcd(dx, dy);
        }
        area = Math.abs(area);
        count = (area - count + 2) / 2 + count;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        doCount(p);
        double ans = solve(p.clone());
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(p[i].y, p[i].x);
        }
        ans += solve(p.clone());
        out.println(1. * ans / count / (count - 1));
    }

    static double solve(Point2DInteger[] p) {
        long area = 0;
        int n = p.length;
        for (int i = 0; i < n; i++) {
            area += p[i].vmul(p[(i + 1) % n]);
        }
        if (area < 0) {
            for (int i = 1, j = n - 1; i < j; i++, j--) {
                Point2DInteger t = p[i];
                p[i] = p[j];
                p[j] = t;
            }
        }
        int left = -1;
        int right = -1;
        for (int i = 0; i < n; i++) {
            if (left < 0 || p[i].x < p[left].x) {
                left = i;
            }
            if (right < 0 || p[i].x > p[right].x) {
                right = i;
            }
        }
        int cntDown = 1;
        int cntUp = 1;
        for (int i = left; i != right; i = (i + 1) % n) {
            cntDown++;
        }
        for (int i = right; i != left; i = (i + 1) % n) {
            cntUp++;
        }
        Point2DInteger[] down = new Point2DInteger[cntDown];
        Point2DInteger[] up = new Point2DInteger[cntUp];
        for (int i = left, j = 0; ; i = (i + 1) % n, j++) {
            down[j] = p[i];
            if (i == right) break;
        }
        for (int i = left, j = 0; ; i = (i + n - 1) % n, j++) {
            up[j] = p[i];
            if (i == right) break;
        }
        int d1 = 0;
        int d2 = 0;
        double sumxi = 0;
        double ret = 0;
        for (int x = p[left].x; x < p[right].x; x++) {
            while (d1 + 1 < up.length && up[d1 + 1].x <= x) {
                ++d1;
            }
            while (d2 + 1 < down.length && down[d2 + 1].x <= x) {
                ++d2;
            }
            long y1, y2;
            {
                long den2 = (down[d2 + 1].x - down[d2].x);
                long num2 = (long) (x - down[d2].x) * (down[d2 + 1].y - down[d2].y);
                long z = num2 / den2;
                while (den2 * z > num2) --z;
                while (den2 * z < num2) ++z;
                y2 = z + down[d2].y;
            }
            {
                long den1 = (up[d1 + 1].x - up[d1].x);
                long num1 = (long) (x - up[d1].x) * (up[d1 + 1].y - up[d1].y);
                long z = num1 / den1;
                while (den1 * z < num1) ++z;
                while (den1 * z > num1) --z;
                y1 = z + up[d1].y;
            }
            if (y1 < y2) {
                continue;
            }
            long curCount = y1 - y2 + 1;
            ret += 1. * (count - curCount) * (curCount) * x * x;
            ret -= 2. * sumxi * curCount * x;
            sumxi += 1. * curCount * x;
        }
        {
            long y1 = Long.MIN_VALUE;
            long y2 = Long.MAX_VALUE;
            for (Point2DInteger e : p) {
                if (e.x == p[right].x) {
                    if (y1 < e.y) y1 = e.y;
                    if (y2 > e.y) y2 = e.y;
                }
            }
            long curCount = y1 - y2 + 1;
            int x = p[right].x;
            ret += 1. * (count - curCount) * (curCount) * x * x;
            ret -= 2. * sumxi * curCount * x;
            sumxi += 1. * curCount * x;
        }
        return ret;
    }
}
