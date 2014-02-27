public class PercolationStats {
    private double[] thresholds;
    private double runs;
    public PercolationStats(int N, int T) {
        if (T <= 0 || N <= 0) {
            throw new IllegalArgumentException("N and T should be positive!");
        }
        
        int x;
        this.runs = T;
        this.thresholds = new double[T];
        for (x = 1; x < T; x++) {
            Percolation pl = new Percolation(N);
            double count = 0.0;
            while (!pl.percolates()) {
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);
                
                if (!pl.isOpen(i, j)) {
                    pl.open(i, j);
                    count++;
                    //StdOut.println(Integer.toString(i)+" "+Integer.toString(j)+" "+Double.toString(count));
                }
                
                //StdOut.println(i);
            }
            this.thresholds[x] = count/(N*N);
        }
    }
    public double mean() {
        return StdStats.mean(this.thresholds);
    }
    public double stddev() {
        if (this.thresholds.length > 1) {
            return StdStats.stddev(this.thresholds);
        } else {
            return Double.NaN;
        }
    }
    public double confidenceLo() {
        return mean()-1.96*stddev()/Math.sqrt(this.runs);
    }
    public double confidenceHi() {
        return mean()+1.96*stddev()/Math.sqrt(this.runs);
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        //StdOut.println("********");
        PercolationStats ps = new PercolationStats(N, T);
        //StdOut.println("********");
        StdOut.println(String.format("PercolationStats N=%s,T=%s", N, T));
        StdOut.println(String.format("mean = %s", ps.mean()));
        StdOut.println(String.format("stddev = %s", ps.stddev()));
        StdOut.println(String.format("95%%confidence interval = %s, %s", ps.confidenceLo(), ps.confidenceHi()));
    }
}
