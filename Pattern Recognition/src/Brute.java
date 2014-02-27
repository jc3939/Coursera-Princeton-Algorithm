import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Point [] points = new Point[N];
        int it = 0;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            points[it] = p;
            it++;
        }
        
        for (int i = 0; i<points.length; i++) {
            Point pivot = points[i];
            for (int j = i+1; j<points.length; j++) {
                double slope1 = pivot.slopeTo(points[j]);
                for (int k = j+1; k<points.length; k++) {
                    double slope2 = pivot.slopeTo(points[k]);
                    for (int l = k+1; l<points.length; l++) {
                        double slope3 = pivot.slopeTo(points[l]);
                        if (slope1 == slope2 && slope1 == slope3) {

                            Point [] nList = new Point[] {points[i], points[j], points[k], points[l]};
                            Arrays.sort(nList);
                            nList[0].drawTo(nList[nList.length-1]);
                            StdOut.println(nList[0].toString()+" -> "+nList[1].toString()+" -> "+nList[2].toString()+
                                    " -> "+nList[3].toString());
                        }
                    }
                }
            }
        }
    }
}
