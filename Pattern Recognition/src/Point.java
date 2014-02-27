import java.util.Comparator;

public class Point implements Comparable<Point> {
   private final int x;
   private final int y;
   
   public final Comparator<Point> SLOPE_ORDER = new bySlope(); 

   public Point(int x, int y) {
     this.x = x;
     this.y = y;
   }

   public void draw() {
     StdDraw.point(x, y);
   }
   
   public void drawTo(Point that) {
     StdDraw.line(this.x, this.y, that.x, that.y);
   }
   
   public String toString() {
     return "(" + x + ", " + y + ")";
   }

   public int compareTo(Point that) {
     if (y == that.y) {
          return x - that.x;
      }
     return y - that.y;
   }
   
   public double slopeTo(Point that) {
       int num = that.y - this.y;
       int den = that.x - this.x;
       double slope = 0.0;
       if ((num == 0) && (den == 0)) slope = Double.NEGATIVE_INFINITY;
       if ((num == 0) && (den != 0)) slope = 0.0;
       if ((num != 0) && (den == 0)) slope = Double.POSITIVE_INFINITY;
       if ((num != 0) && (den != 0)) slope = ((double) num) / ((double) den);
       return slope;
   }
   private class bySlope implements Comparator<Point> {
       public int compare(Point p1, Point p2) {
         if (p1 == null || p2 == null) {
           throw new NullPointerException();
         }
         double slope1 = slopeTo(p1);
         double slope2 = slopeTo(p2);
         if (slope1 < slope2) return -1;
         if (slope1 > slope2) return 1;
         return 0;
     
       }
   }
}
