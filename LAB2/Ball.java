import tester.Tester;
import java.awt.Color;
 
class Ball {
  CartPt center;
  int radius;
  Color color;
 
  Ball(CartPt center, int radius, Color color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.center ... -- CartPt
   * ... this.radius ... -- int
   * ... this.color ... -- Color
   * 
   */
 
  // Returns the area of this ball
  double area() {
    return Math.PI * Math.pow(this.radius, 2);
  }
  
  // Returns the circumference of this ball 
  double circumference() {
    return Math.PI * this.radius * 2;
  }
  
  // Returns the distance between the center of this ball
  // and the center of the given ball
  double distanceTo(Ball given) {
    return Math.sqrt(Math.pow(given.center.x - this.center.x, 2)
                   + Math.pow(given.center.y - this.center.y, 2));
  }
  
  // Returns a boolean value indicating whether this ball
  // overlaps with the given ball
  boolean overlaps(Ball given) {
    return (this.distanceTo(given) <= this.radius);
  }
}
 
class CartPt {
  int x;
  int y;
 
  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.x ... -- int
   * ... this.y ... -- int
   * 
   */
}
 
class ExamplesBall {
  Ball b = new Ball(new CartPt(0, 0), 5, Color.BLUE);
  Ball g = new Ball(new CartPt(0, 10), 10, Color.GREEN);
  Ball r = new Ball(new CartPt(0, 15), 8, Color.RED);
 
  boolean testBall1(Tester t) {
    return t.checkInexact(b.area(), 78.5, 0.001);
  }
  
  boolean testBall2(Tester t) {
    return t.checkInexact(r.circumference(), 50.26, 0.001);
  }
  
  boolean testBall3(Tester t) {
    return t.checkInexact(b.distanceTo(g), 10.0, 0.001);
  }
  
  boolean testBall4(Tester t) {
    return t.checkExpect(b.overlaps(r), false);
  }
  
  boolean testBall5(Tester t) {
    return t.checkExpect(g.overlaps(r), true);
  }
}