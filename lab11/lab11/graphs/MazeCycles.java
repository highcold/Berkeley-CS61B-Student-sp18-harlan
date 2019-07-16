package lab11.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean isCycle;
    private Stack<Integer> path;

    public MazeCycles(Maze m) {
        super(m);
        isCycle = false;
        path = new Stack<>();
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        for (int v = 0; v < maze.V(); v++) {
            if (isCycle) {
                break;
            }
            if (!marked[v]) {
                dfs(maze, v, v);
            }
        }
    }

    // Helper methods go here
    public void dfs(Maze m, int v, int w) {
        marked[v] = true;
        path.push(v);
        announce();
        for (int u : m.adj(v)) {
            if (!marked[u] && !isCycle) {
                //edgeTo[u] = v;
                dfs(m, u, v);
            } else if (u != w && !isCycle) {
                isCycle = true;
                int parent = path.pop();
                int end = u;
                while (parent != end) {
                    edgeTo[u] = parent;
                    announce();
                    u = parent;
                    parent = path.pop();
                }
                edgeTo[u] = parent;
                announce();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int i = 10;
        while (i != 5) {
            System.out.println(i);
            i--;
        }
    }
}

