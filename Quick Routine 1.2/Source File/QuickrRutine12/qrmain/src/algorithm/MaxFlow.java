package algorithm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import global.*;

public class MaxFlow {
    int n, src, sink;
    int cap[][], adj[][], deg[];

    int p[];
    boolean pathFound, visited[];

    public MaxFlow() {
    }

    void memsetCap(int cap[][]) {
        for(int i = 0; i < cap.length; i++)
            for(int j = 0; j < cap[i].length; j++)
                cap[i][j] = 0;
    }

    void memsetAdj(int deg[]) {
        for(int k = 0; k < deg.length; k++)
            deg[k] = 0;
    }

    void connectDirect(int i, int j) {
        adj[i][ deg[i]++ ] = j;
    }

    void memsetFree(boolean isFree[][]) {
        for(int i = 0; i < isFree.length; i++)
            for(int j = 0; j < isFree[i].length; j++)
                isFree[i][j] = true;
    }

    void memsetVisited(boolean visited[]) {
        for(int i = 0; i < visited.length; i++)
            visited[i] = false;
    }

    void p2(int a[][]) {
        G.p("\n");
        for(int i = 0; i < a.length; i++) {
            G.p(+i+". ");
            for(int j = 0; j < a[i].length; j++)
                G.p(" "+a[i][j]);

            G.p("\n");
        }
        G.p("\n");
    }

    void pAdj(int adj[][]) {
        for(int u = 0; u < n; u++) {
            G.p(+u+"-->");
            for(int j = 0; j < deg[u]; j++)
                G.p(" "+adj[u][j]);
            G.p("\n");
        }

        G.p("\n");
    }
}