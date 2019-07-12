package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] experiements;
    private int time;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <=0 || T <= 0) {
            throw new java.lang.IllegalArgumentException(N + " or " + T + "must > 0");
        }
        experiements = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation grid = pf.make(N);
            while (grid.percolates() == false) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                grid.open(x, y);
            }
            experiements[i] = (double) grid.numberOfOpenSites() / Math.pow(N, 2);
        }
        time = T;

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(experiements);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(experiements);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(time);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(time);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(100, 1000, new PercolationFactory());
        double low = ps.confidenceLow();
        double high = ps.confidenceHigh();
        System.out.printf("confidence interval: [%f : %f]\n", low, high);
    }
}
