package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF id;
    private WeightedQuickUnionUF idTop;
    private int virtualTop;
    private int virtualBottom;
    private int numberOfOpenSites;
    private int size;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException(N + "must > 0");
        }
        grid = new boolean[N][N];
        size = N;
        numberOfOpenSites = 0;

        id = new WeightedQuickUnionUF(N * N + 2);
        virtualTop = N * N;
        virtualBottom = virtualTop + 1;
        virtualHelper(id, 0, N - 1, virtualTop);
        virtualHelper(id, N * N - N, N * N - 1, virtualBottom);

        idTop = new WeightedQuickUnionUF(N * N + 1);
        virtualHelper(idTop, 0, N - 1, virtualTop);
    }

    private void virtualHelper(WeightedQuickUnionUF uf, int start, int end, int num) {
        for (int i = start; i <= end; i++) {
            uf.union(i, num);
        }
    }

    private void validation(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new java.lang.IndexOutOfBoundsException(row
                    + " or " + col + " is not between 0 and " + (size - 1));
        }
    }

    private int idHelper(int row, int col) {
        return row * size + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validation(row, col);
        if (!grid[row][col]) {
            grid[row][col] = true;
            numberOfOpenSites++;
            connectHelper(row, col);
        }
    }

    private void connectHelper(int row, int col) {
        int[][] coordinate = {{row - 1, col}, {row + 1, col}, {row, col + 1}, {row, col - 1}};
        for (int i = 0; i < 4; i++) {
            int x = coordinate[i][0];
            int y = coordinate[i][1];
            if (validation(x) && validation(y) && grid[x][y]) {
                id.union(idHelper(row, col), idHelper(x, y));
                idTop.union(idHelper(row, col), idHelper(x, y));
            }
        }
    }
    private Boolean validation(int p) {
        if (p < 0 || p > size - 1) {
            return false;
        }
        return true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validation(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int num = idHelper(row, col);
        return idTop.connected(virtualTop, num) && grid[row][col];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return id.connected(virtualTop, virtualBottom) && numberOfOpenSites > 0;
    }

    public static void main(String[] args) {
        boolean[][]  b = new boolean[1][1];
        System.out.println(b[0][0]);
    }
}

