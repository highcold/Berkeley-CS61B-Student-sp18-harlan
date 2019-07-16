package lab11.graphs;
import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private ArrayHeap<Integer> queue;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new ArrayHeap<>();
        //comparator = new MazeComparator();
        //queue = new MinPQ(comparator);

    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        int targetX = maze.toX(t);
        int targetY = maze.toY(t);
        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        queue.insert(s, distTo[s] + h(s));
        while (queue.size() != 0) {
            int parant = queue.removeMin();
            marked[parant] = true;
            announce();
            if (targetFound) {
                return;
            }
            for (int child : maze.adj(parant)) {
                if (!marked[child]) {
                    if (child == t) {
                        targetFound = true;
                    }
                    edgeTo[child] = parant;
                    distTo[child] = distTo[parant] + 1;
                    int priority = distTo[child] + h(child);
                    queue.changePriority(child, priority);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

