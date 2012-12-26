package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.List;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int heads = in.nextInt();
        int tails = in.nextInt();
        int[][] edges = new int[n][];
        int[] deg = new int[n];
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            deg[from[i]]++;
            deg[to[i]]++;
        }
        for (int i = 0; i < n; i++) {
            edges[i] = new int[deg[i]];
        }
        for (int i = 0; i < m; i++) {
            edges[from[i]][--deg[from[i]]] = to[i];
            edges[to[i]][--deg[to[i]]] = from[i];
        }
        int[] version = new int[n];
        int[] h = new int[n];
        int[] t = new int[n];
        int tim = 0;
        for (int i = 0; i < m; i++) {
            int v = from[i];
            int u = to[i];
            for (int it = 0; it < 2; it++) {
                int temp = u;
                u = v;
                v = temp;
                ++tim;
                int degV = edges[v].length - 1;
                int degU = edges[u].length - 1;
                if (degV >= heads && degU >= tails) {
                    int headsLeft = heads;
                    int tailsLeft = tails;
                    int hNumber = 0;
                    int tNumber = 0;
                    boolean ok;
                    if (degV >= heads + tails) {
                        ok = true;
                        for (int j : edges[u]) {
                            if (tailsLeft <= 0) {
                                break;
                            }
                            if (j == v) {
                                continue;
                            }
                            version[j] = tim;
                            --tailsLeft;
                            t[tNumber++] = j;
                        }
                        for (int j : edges[v]) {
                            if (headsLeft <= 0) {
                                break;
                            }
                            if (j == u || version[j] == tim) {
                                continue;
                            }
                            --headsLeft;
                            h[hNumber++] = j;
                        }
                    } else if (degU >= heads + tails) {
                        ok = true;
                        for (int j : edges[v]) {
                            if (headsLeft <= 0) {
                                break;
                            }
                            if (j == u) {
                                continue;
                            }
                            version[j] = tim;
                            --headsLeft;
                            h[hNumber++] = j;
                        }
                        for (int j : edges[u]) {
                            if (tailsLeft <= 0) {
                                break;
                            }
                            if (j == v || version[j] == tim) {
                                continue;
                            }
                            --tailsLeft;
                            t[tNumber++] = j;
                        }
                    } else {
                        ok = true;
                        for (int j : edges[v]) {
                            if (j == u) {
                                continue;
                            }
                            version[j] = tim;
                        }
                        ++tim;
                        int common = 0;
                        for (int j : edges[u]) {
                            if (j == v) {
                                continue;
                            }
                            if (version[j] == tim - 1) {
                                version[j] = tim;
                                ++common;
                            }
                        }
                        if (degU + degV - common < heads + tails) {
                            continue;
                        }
                        for (int j : edges[v]) {
                            if (headsLeft <= 0) {
                                break;
                            }
                            if (j == u || version[j] == tim) {
                                continue;
                            }
                            --headsLeft;
                            h[hNumber++] = j;
                        }
                        for (int j : edges[u]) {
                            if (tailsLeft <= 0) {
                                break;
                            }
                            if (j == v || version[j] == tim) {
                                continue;
                            }
                            --tailsLeft;
                            t[tNumber++] = j;
                        }
                        int oldTim = tim;
                        ++tim;
                        for (int j : edges[v]) {
                            if (headsLeft <= 0) {
                                break;
                            }
                            if (j == u || version[j] != oldTim) {
                                continue;
                            }
                            --headsLeft;
                            version[j] = tim;
                            h[hNumber++] = j;
                        }
                        for (int j : edges[u]) {
                            if (tailsLeft <= 0) {
                                break;
                            }
                            if (j == v || version[j] != oldTim) {
                                continue;
                            }
                            --tailsLeft;
                            version[j] = tim;
                            t[tNumber++] = j;
                        }
                    }
//                    if (headsLeft > 0 || tailsLeft > 0) {
//                        throw new AssertionError();
//                    }
//                    if (tails != tNumber) {
//                        throw new AssertionError();
//                    }
//                    if (heads != hNumber) {
//                        throw new AssertionError();
//                    }
                    if (ok) {
                        out.println("YES");
                        out.println((v + 1) + " " + (u + 1));
                        for (int z = 0; z < hNumber; z++) {
                            out.print(h[z] + 1 + " ");
                        }
                        out.println();
                        for (int z = 0; z < tNumber; z++) {
                            out.print(t[z] + 1 + " ");
                        }
                        out.println();
                        return;
                    }
                }
            }
        }
        out.println("NO");
    }
}
