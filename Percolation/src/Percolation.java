public class Percolation {
    private int gridSize;
    private WeightedQuickUnionUF wquf;
    private int[][] grid;
    private WeightedQuickUnionUF wqufnobackwash;
    
    public Percolation(int N) {
        this.gridSize = N;
        this.wquf = new WeightedQuickUnionUF(N * N + 2);
        this.wqufnobackwash = new WeightedQuickUnionUF(N * N + 2);
        this.grid = new int[N][N];
    }

    public void open(int ii, int jj) {
        validate(ii, jj);
        int i = ii - 1;
        int j = jj - 1;

        int current = xyTo1D(i, j);

        if (i == 0) {
            this.wquf.union(0, current);
            this.wqufnobackwash.union(0, current);
        }
        if (i == this.gridSize - 1) {
            this.wquf.union(1, current);
        }

        // check left side.
        if (j > 0) {
            if (this.grid[i][j - 1] == 1) {
                int left = xyTo1D(i, j - 1);
                wquf.union(current, left);
                this.wqufnobackwash.union(current, left);
            }
        }

        // check right side;
        if (j < this.gridSize - 1) {
            if (this.grid[i][j + 1] == 1) {
                int right = xyTo1D(i, j + 1);
                wquf.union(current, right);
                this.wqufnobackwash.union(current, right);
            }
        }

        // check upper side;
        if (i > 0) {
            if (this.grid[i - 1][j] == 1) {
                int upper = xyTo1D(i - 1, j);
                wquf.union(current, upper);
                this.wqufnobackwash.union(current, upper);
            }
        }

        // check down side;
        if (i < this.gridSize - 1) {
            if (this.grid[i + 1][j] == 1) {
                int down = xyTo1D(i + 1, j);
                wquf.union(current, down);
                this.wqufnobackwash.union(current, down);
            }
        }

        this.grid[i][j] = 1;
    }

    public boolean isOpen(int ii, int jj) {
        validate(ii, jj);
        int i = ii - 1;
        int j = jj - 1;
        if (this.grid[i][j] == 1) {
            return true;
        }
        return false;
    }

    public boolean isFull(int ii, int jj) {
        validate(ii, jj);
        int i = ii - 1;
        int j = jj - 1;
        int current = xyTo1D(i, j);
        return this.wqufnobackwash.connected(0, current);
    }

    public boolean percolates() {
        return this.wquf.connected(0, 1);
    }

    private void validate(int i, int j) {
        if (i < 1 || j < 1 || i > this.gridSize || j > this.gridSize) {
            throw new IndexOutOfBoundsException(String.format(
                    "index i=%s,j=%s out of bounds!", i, j));
        }
    }

    private int xyTo1D(int i, int j) {
        return i * this.gridSize + j + 2;
    }
}
