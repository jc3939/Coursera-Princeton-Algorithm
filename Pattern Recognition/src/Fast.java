import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Fast {
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
            ArrayList<Point> nList = new ArrayList<Point>(); 
            Arrays.sort(points, pivot.SLOPE_ORDER);
            nList.add(pivot);
            int j = 1;
            double slope1 = points[j].slopeTo(pivot);
            nList.add(points[j]);
            double slope2 = points[j+1].slopeTo(pivot);
            while (j < points.length-1 && slope1==slope2) {
                nList.add(points[j+1]);
                j++;
                slope1 = points[j].slopeTo(pivot);
                slope2 = points[j+1].slopeTo(pivot);
            }
            if (nList.size()>=4) {
            	Collections.sort(nList);
                String seg = nList.get(0).toString();
                for (int k=1; k<nList.size(); k++) {
                    seg += " -> "+nList.get(k).toString();
                }
                nList.get(0).drawTo(nList.get(nList.size()-1));
                StdOut.println(seg);
            }
        }
    }
}
