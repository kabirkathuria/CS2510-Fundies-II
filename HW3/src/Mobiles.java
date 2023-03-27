import tester.Tester;
import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.worldcanvas.WorldCanvas;

import java.awt.Color;

// to represent a mobile
interface IMobile {

  // to compute total weight of mobile
  int totalWeight();

  // to compute total height of mobile
  int totalHeight();

  // to compute whether a mobile is balanced
  boolean isBalanced();

  // to combine balanced mobile with given balanced mobile
  IMobile buildMobile(IMobile m, int desiredLength, int strutLength);

  // to compute current width of left side of mobile
  int leftWidth();

  // to compute current width of right side of mobile
  int rightWidth();

  // to compute current width of mobile
  int curWidth();

  // produces image of a mobile
  WorldImage drawMobile();
}

// to represent a simple mobile
class Simple implements IMobile {
  int length;
  int weight;
  Color color;

  Simple(int length, int weight, Color color) {
    this.length = length;
    this.weight = weight;
    this.color = color;
  }

  /*
   * TEMPLATE
   * 
   * Fields:
   * ... this.length ... -- int
   * ... this.weight ... -- int
   * ... this.color ... -- Color
   * 
   * Methods:
   * ... this.totalWeight() ... -- int
   * ... this.totalHeight() ... -- int
   * ... this.isBalanced() ... -- boolean
   * ... this.buildMobile(IMobile m, int desiredLength, int strutLength) ... -- IMobile
   * ... this.leftWidth() ... -- int
   * ... this.rightWidth() ... -- int
   * ... this.curWidth() ... -- int
   * ... this.drawMobile() ... -- WorldImage
   * 
   */

  // to compute total weight of simple mobile
  public int totalWeight() {
    return this.weight;
  }

  // to compute total height of simple mobile
  public int totalHeight() {
    return this.length + this.weight / 10;
  }

  // to compute whether a simple mobile is balanced
  public boolean isBalanced() {
    return true;
  }

  // to combine simple balanced mobile with given balanced mobile

  /*
   * TEMPLATE
   * 
   * Parameters:
   * ... this.m ... -- IMobile
   * ... this.desiredLength ... -- int
   * ... this.strutLength ... -- int
   * 
   * Methods of Parameters:
   * ... this.m.totalWeight() ... -- int
   * ... this.m.totalHeight() ... -- int
   * ... this.m.isBalanced() ... -- boolean
   * ... this.m.buildMobile() ... -- IMobile
   * ... this.m.leftWidth() ... -- int
   * ... this.m.rightWidth() ... -- int
   * ... this.m.curWidth() ... -- int
   * ... this.m.drawMobile() ... -- WorldImage
   * 
   */

  public IMobile buildMobile(IMobile m, int desiredLength, int strutLength) {
    int measureStrut = (this.totalWeight() * strutLength) / (m.totalWeight() + this.totalWeight());
    return new Complex(desiredLength, measureStrut, (strutLength - measureStrut), this, m);
  }

  // to compute current width of left side of simple mobile
  public int leftWidth() {
    return this.curWidth() / 2;
  }

  // to compute current width of right side of simple mobile
  public int rightWidth() {
    return this.curWidth() / 2;
  }

  // to compute current width of simple mobile
  public int curWidth() {
    if (this.weight % 10 > 0) {
      return 1 + (this.weight / 10);
    }
    else {
      return (this.weight / 10);
    }
  }

  // to produce an image of simple mobile
  public WorldImage drawMobile() {
    WorldImage verticalLength = new RectangleImage(2, this.length * 30, OutlineMode.SOLID, Color.BLACK);
    WorldImage shape = new RectangleImage(this.weight, this.weight, OutlineMode.SOLID, Color.BLACK);
    WorldImage finalImage = new OverlayImage(verticalLength, shape)
        .movePinhole(0, -((this.length * 30) + this.weight) / 2);
    return finalImage;
  }
}

//to represent a complex mobile
class Complex implements IMobile {
  int length;
  int leftside;
  int rightside;
  IMobile left;
  IMobile right;

  Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
    this.length = length;
    this.leftside = leftside;
    this.rightside = rightside;
    this.left = left;
    this.right = right;
  }

  /*
   * TEMPLATE
   * 
   * Fields:
   * ... this.length ... -- int 
   * ... this.leftside ... -- int 
   * ... this.rightside ... -- int
   * ... this.left ... -- IMobile
   * ... this.right ... -- IMobile
   * 
   * Methods:
   * ... this.totalWeight() ... -- int
   * ... this.totalHeight() ... -- int
   * ... this.isBalanced() ... -- boolean
   * ... this.buildMobile(IMobile m, int desiredLength, int strutLength) ... -- IMobile
   * ... this.leftWidth() ... -- int 
   * ... this.rightWidth() ... -- int 
   * ... this.curWidth() ... -- int 
   * ... this.drawMobile() ... -- WorldImage
   * 
   * Methods of Fields: 
   * ... this.left.totalWeight() ... -- int 
   * ... this.right.totalWeight() ... -- int 
   * ... this.left.totalHeight() ... -- int
   * ... this.right.totalHeight() ... -- int 
   * ... this.left.isBalanced() ... -- boolean 
   * ... this.right.isBalanced() ... -- boolean 
   * ... this.left.leftWidth() ... -- int 
   * ... this.right.leftWidth() ... -- int 
   * ... this.left.rightWidth() ... -- int 
   * ... this.right.rightWidth() ... -- int
   * 
   */

  // to compute total weight of complex mobile
  public int totalWeight() {
    return this.left.totalWeight() + this.right.totalWeight();
  }

  // to compute total height of complex mobile
  public int totalHeight() {
    if (this.left.totalHeight() > this.right.totalHeight()) {
      return this.left.totalHeight() + this.length;
    }
    else {
      return this.right.totalHeight() + this.length;
    }
  }

  // to compute whether a complex mobile is balanced
  public boolean isBalanced() {
    return (this.leftside * this.left.totalWeight()) == (this.rightside * this.right.totalWeight())
        && this.left.isBalanced() && this.right.isBalanced();
  }

  // to combine complex balanced mobile with given balanced mobile

  /*
   * TEMPLATE
   * 
   * Parameters: 
   * ... this.m ... -- IMobile 
   * ... this.desiredLength ... -- int 
   * ... this.strutLength ... -- int
   * 
   * Methods of Parameters: 
   * ... this.m.totalWeight() ... -- int 
   * ... this.m.totalHeight() ... -- int 
   * ... this.m.isBalanced() ... -- boolean 
   * ... this.m.buildMobile() ... -- IMobile 
   * ... this.m.leftWidth() ... -- int 
   * ... this.m.rightWidth() ... -- int 
   * ... this.m.curWidth() ... -- int 
   * ... this.m.drawMobile() ... -- WorldImage
   * 
   */

  public IMobile buildMobile(IMobile m, int desiredLength, int strutLength) {
    int measureStrut = (this.totalWeight() * strutLength) / (m.totalWeight() + this.totalWeight());
    return new Complex(desiredLength, (strutLength - measureStrut), measureStrut, this, m);
  }

  // to compute current width of left side of complex mobile
  public int leftWidth() {
    return Math.max(this.left.leftWidth() + this.leftside, this.right.leftWidth() - this.rightside);
  }

  // to compute current width of right side of complex mobile
  public int rightWidth() {
    return Math.max(this.right.rightWidth() + this.rightside,
        this.left.rightWidth() - this.leftside);
  }

  // to compute current width of complex mobile
  public int curWidth() {
    return this.leftWidth() + this.rightWidth();
  }

  // to produce an image of complex mobile
  public WorldImage drawMobile() {
    WorldImage verticalLength = new RectangleImage(2, this.length * 30, OutlineMode.SOLID,
        Color.BLUE).movePinhole(0, this.length * 20);
    WorldImage leftsideStrut = new RectangleImage(this.leftside * 30, 2, OutlineMode.SOLID,
        Color.BLUE).movePinhole(-this.leftside * 20, 0);
    WorldImage rightsideStrut = new RectangleImage(this.rightside * 30, 2, OutlineMode.SOLID,
        Color.BLUE).movePinhole(this.rightside * 20, 0);
    WorldImage leftsideMobile = this.left.drawMobile();
    WorldImage rightsideMobile = this.right.drawMobile();

    WorldImage leftSide = new OverlayImage(leftsideStrut, leftsideMobile)
        .movePinhole(this.leftside * 30, 0);
    WorldImage rightSide = new OverlayImage(rightsideStrut, rightsideMobile)
        .movePinhole(-this.rightside * 30, 0);
    WorldImage lineLeft = new OverlayImage(verticalLength, leftSide);
    WorldImage lineRight = new OverlayImage(lineLeft, rightSide).movePinhole(0, -this.length * 30);

    return lineRight;
  }
}

// to represent examples for mobiles
class ExamplesMobiles {
  ExamplesMobiles() {
  }

  IMobile exampleSimple = new Simple(2, 20, Color.blue);

  IMobile exampleSimple1 = new Simple(1, 36, Color.blue);
  IMobile exampleSimple2 = new Simple(1, 12, Color.red);
  IMobile exampleSimple3 = new Simple(2, 36, Color.red);
  IMobile exampleSimple4 = new Simple(1, 60, Color.green);

  IMobile exampleComplex1 = new Complex(2, 5, 3, this.exampleSimple3, this.exampleSimple4);
  IMobile exampleComplex2 = new Complex(2, 8, 1, this.exampleSimple2, this.exampleComplex1);
  IMobile exampleComplex = new Complex(1, 9, 3, this.exampleSimple1, this.exampleComplex2);

  IMobile exampleSimple5 = new Simple(1, 48, Color.orange);
  IMobile exampleSimple6 = new Simple(1, 20, Color.blue);
  IMobile exampleSimple7 = new Simple(1, 16, Color.red);
  IMobile exampleSimple8 = new Simple(1, 30, Color.blue);
  IMobile exampleSimple9 = new Simple(1, 66, Color.green);

  IMobile exampleComplex3 = new Complex(2, 2, 4, this.exampleSimple8, this.exampleSimple9);
  IMobile exampleComplex4 = new Complex(2, 3, 3, this.exampleSimple7, this.exampleComplex3);
  IMobile exampleComplex5 = new Complex(2, 4, 2, this.exampleSimple6, this.exampleComplex4);
  IMobile example3 = new Complex(1, 7, 3, this.exampleSimple5, this.exampleComplex5);

  // to test the method totalWeight
  boolean testTotalWeight(Tester t) {
    return t.checkExpect(exampleSimple.totalWeight(), 20)
        && t.checkExpect(exampleComplex.totalWeight(), 144)
        && t.checkExpect(example3.totalWeight(), 180);
  }

  // to test the method totalHeight
  boolean testTotalHeight(Tester t) {
    return t.checkExpect(exampleSimple.totalHeight(), 4)
        && t.checkExpect(exampleComplex.totalHeight(), 12)
        && t.checkExpect(example3.totalHeight(), 14);
  }

  // to test the method isBalanced
  boolean testIsBalanced(Tester t) {
    return t.checkExpect(exampleSimple.isBalanced(), true)
        && t.checkExpect(exampleComplex.isBalanced(), true)
        && t.checkExpect(example3.isBalanced(), false);
  }   

  // to test the method leftWidth
  boolean testLeftWidth(Tester t) {
    return t.checkExpect(exampleSimple.leftWidth(), 1)
        && t.checkExpect(exampleComplex.leftWidth(), 11) && t.checkExpect(example3.leftWidth(), 9);
  }

  // to test the method rightWidth
  boolean testRightWidth(Tester t) {
    return t.checkExpect(exampleSimple.rightWidth(), 1)
        && t.checkExpect(exampleComplex.rightWidth(), 10)
        && t.checkExpect(example3.rightWidth(), 15);
  }

  // to test the method curWidth
  boolean testCurWidth(Tester t) {
    return t.checkExpect(exampleSimple.curWidth(), 2)
        && t.checkExpect(exampleComplex.curWidth(), 21) && t.checkExpect(example3.curWidth(), 24);
  }

  // to test the method drawMobile
  boolean testDrawMobile(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(exampleComplex.drawMobile(), 250, 250)) && c.show();
  }
}
