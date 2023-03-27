import tester.*;

import java.util.function.Predicate;

// predicate to find Strings that start with "b"
class StartsWithB implements Predicate<String> {

  // checks if the given String starts with "b"
  @Override
  public boolean test(String t) {
    return t.substring(0, 1).equals("b");
  }

}

// predicate to find Strings that end with "x"
class EndsWithX implements Predicate<String> {

  // checks if the given String ends with "x"
  @Override
  public boolean test(String t) {
    return t.substring(t.length() - 1, t.length()).equals("x");
  }

}

// predicate to find Integers that are even
class IsEven implements Predicate<Integer> {

  // checks if the given Integer is even
  @Override
  public boolean test(Integer t) {
    return t % 2 == 0;
  }

}

// predicate to find Integers that are greater than 5
class IsGreaterThan5 implements Predicate<Integer> {

  // checks if the given Integer is greater than 5
  @Override
  public boolean test(Integer t) {
    return t > 5;
  }

}

// to represent Sentinels and Nodes in a Deque
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  // initializes this.next and this.prev to itself
  ANode() {
    this.next = this;
    this.prev = this;
  }

  // EFFECT: changes this ANode's next to the given ANode
  void updateNextNode(ANode<T> node) {
    this.next = node;
  }

  // EFFECT: changes this ANode's prev to the given ANode
  void updatePrevNode(ANode<T> node) {
    this.prev = node;
  }

  // keeps track of the Deque size count so far
  abstract int sizeHelp(int soFar);

  // if Deque isn't empty,
  // returns data of this ANode
  // EFFECT: reroutes the Deque around this Node
  abstract T removeHelp();

  // takes a Predicate<T>
  // and produces the first node in this Deque for which the given predicate returns true
  abstract ANode<T> findHelp(Predicate<T> pred);
}

// to represent a Sentinel header for a Deque
class Sentinel<T> extends ANode<T> {

  Sentinel() {
    super();
  }

  // keeps track of the Deque size count so far (header not included)
  public int sizeHelp(int soFar) {
    return soFar;
  }

  // Throws a RuntimeException if an attempt is made to remove from an empty list
  T removeHelp() {
    throw new RuntimeException("cannot remove from an empty list");
  }

  // return this if Predicate<T> never returns true for any value in the Deque
  ANode<T> findHelp(Predicate<T> pred) {
    return this;
  }
}

// to represent a linked data Node in the Deque
class Node<T> extends ANode<T> {
  T data;

  // initializes data field
  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  // convenience constructor initializes data, next, and prev fields
  // updates the given nodes to refer back to this node
  Node(T data, ANode<T> next, ANode<T> prev) {
    super();
    this.data = data;

    if (next == null) {
      throw new IllegalArgumentException("next cannot be null");
    }
    else {
      this.next = next;
      next.updatePrevNode(this);
    }

    if (prev == null) {
      throw new IllegalArgumentException("previous cannot be null");
    }
    else {
      this.prev = prev;
      prev.updateNextNode(this);
    }
  }

  // keeps track of and adds to the Deque size count so far
  public int sizeHelp(int soFar) {
    return this.next.sizeHelp(soFar + 1);
  }

  // returns data of this node
  // EFFECT: reroutes the Deque around this Node
  T removeHelp() {
    this.next.updatePrevNode(this.prev);
    this.prev.updateNextNode(this.next);
    return this.data;
  }

  // takes a Predicate<T>
  // and produces the first node in this Deque for which the given predicate returns true
  ANode<T> findHelp(Predicate<T> pred) {
    if (pred.test(this.data)) {
      return this;
    }
    else {
      return this.next.findHelp(pred);
    }
  }
}

// to represent a double-ended queue (Deque)
class Deque<T> {
  Sentinel<T> header;

  Deque() {
    this.header = new Sentinel<T>();
  }

  Deque(Sentinel<T> header) {
    this.header = header;
  }

  // counts the number of nodes in a list Deque, not including the header node
  int size() {
    return this.header.next.sizeHelp(0);
  }

  // EFFECT: consumes a value of type T and inserts it at the front of the list
  void addAtHead(T insert) {
    Node<T> inserted = new Node<T>(insert);
    inserted.updateNextNode(this.header.next);
    inserted.updatePrevNode(this.header);
    this.header.next.updatePrevNode(inserted);
    this.header.updateNextNode(inserted);
  }

  // EFFECT: consumes a value of type T and inserts it at the tail of this list
  void addAtTail(T insert) {
    Node<T> inserted = new Node<T>(insert);
    inserted.updateNextNode(this.header);
    inserted.updatePrevNode(this.header.prev);
    this.header.prev.updateNextNode(inserted);
    this.header.updatePrevNode(inserted);
  }

  // EFFECT: removes the first node from this Deque
  // and returns the removed item
  T removeFromHead() {
    return this.header.next.removeHelp();
  }

  // EFFECT: removes the last node from this Deque
  // and returns the removed item
  T removeFromTail() {
    return this.header.prev.removeHelp();
  }

  // takes a Predicate<T> and produces the first node in this Deque
  // for which the given predicate returns true
  ANode<T> find(Predicate<T> pred) {
    return this.header.next.findHelp(pred);
  }
}

// examples and tests for Deques
class ExamplesDeque {

  Sentinel<String> sent0;
  Node<String> something;
  Deque<String> deque0;

  Deque<String> deque1;

  Sentinel<String> sent1;
  Node<String> abc;
  Node<String> bcd;
  Node<String> cde;
  Node<String> def;
  Deque<String> deque2;

  Sentinel<String> sent2;
  Node<String> cat;
  Node<String> dog;
  Node<String> rat;
  Node<String> bunny;
  Deque<String> deque3;

  Sentinel<Integer> sent3;
  Node<Integer> one;
  Node<Integer> two;
  Node<Integer> three;
  Node<Integer> four;
  Deque<Integer> deque4;

  StartsWithB swb;
  EndsWithX ewx;
  IsEven ie;
  IsGreaterThan5 igtf;

  // resets all the examples
  void initData() {
    // deque1 is empty
    this.deque1 = new Deque<String>();

    this.sent1 = new Sentinel<String>();
    this.abc = new Node<String>("abc", this.sent1, this.sent1);
    this.bcd = new Node<String>("bcd", this.sent1, this.abc);
    this.cde = new Node<String>("cde", this.sent1, this.bcd);
    this.def = new Node<String>("def", this.sent1, this.cde);
    // deque2 contains: "abc", "bcd", "cde", "def"
    this.deque2 = new Deque<String>(this.sent1);

    this.sent2 = new Sentinel<String>();
    this.cat = new Node<String>("cat", this.sent2, this.sent2);
    this.dog = new Node<String>("dog", this.sent2, this.cat);
    this.rat = new Node<String>("rat", this.sent2, this.dog);
    this.bunny = new Node<String>("bunny", this.sent2, this.rat);
    // deque3 contains: "cat", "dog", "rat", "bunny"
    this.deque3 = new Deque<String>(this.sent2);

    this.sent3 = new Sentinel<Integer>();
    this.one = new Node<Integer>(1, this.sent3, this.sent3);
    this.four = new Node<Integer>(4, this.sent3, this.one);
    this.three = new Node<Integer>(3, this.sent3, this.four);
    this.two = new Node<Integer>(2, this.sent3, this.three);
    // deque4 contains: 1, 4, 3, 2
    this.deque4 = new Deque<Integer>(this.sent3);

    this.sent0 = new Sentinel<String>();
    this.something = new Node<String>("something", this.sent0, this.sent0);
    // deque0 contains: "something"
    this.deque0 = new Deque<String>(this.sent0);

    this.swb = new StartsWithB();
    this.ewx = new EndsWithX();
    this.ie = new IsEven();
    this.igtf = new IsGreaterThan5();
  }

  // test initData()
  void testInitData(Tester t) {
    this.initData();

    t.checkExpect(this.sent0.next, this.something);
    t.checkExpect(this.sent0.prev, this.something);
    t.checkExpect(this.something.next, this.sent0);
    t.checkExpect(this.something.prev, this.sent0);
    t.checkExpect(this.deque0.header.next, this.something);
    t.checkExpect(this.deque0.header.prev, this.something);
    t.checkExpect(this.deque0.header.next.next, this.sent0);
    t.checkExpect(this.deque0.header.next.prev, this.sent0);

    t.checkExpect(this.deque1.header, new Sentinel<String>());
    t.checkExpect(this.deque1.header.next, this.deque1.header);
    t.checkExpect(this.deque1.header.prev, this.deque1.header);

    t.checkExpect(this.abc.next, this.bcd);
    t.checkExpect(this.abc.prev, this.sent1);

    t.checkExpect(this.deque2.header.next, this.abc);
    t.checkExpect(this.deque2.header.prev, this.def);
    t.checkExpect(this.deque2.header.next.next, this.bcd);
    t.checkExpect(this.deque2.header.next.prev, this.sent1);
    t.checkExpect(this.deque2.header.next.next.next, this.cde);
    t.checkExpect(this.deque2.header.next.next.prev, this.abc);
    t.checkExpect(this.deque2.header.next.next.next.next, this.def);
    t.checkExpect(this.deque2.header.next.next.next.prev, this.bcd);
    t.checkExpect(this.deque2.header.next.next.next.next.next, this.sent1);
    t.checkExpect(this.deque2.header.next.next.next.next.prev, this.cde);

    t.checkExpect(this.deque3.header.next, this.cat);
    t.checkExpect(this.deque3.header.prev, this.bunny);
    t.checkExpect(this.deque3.header.next.next, this.dog);
    t.checkExpect(this.deque3.header.next.prev, this.sent2);
    t.checkExpect(this.deque3.header.next.next.next, this.rat);
    t.checkExpect(this.deque3.header.next.next.prev, this.cat);
    t.checkExpect(this.deque3.header.next.next.next.next, this.bunny);
    t.checkExpect(this.deque3.header.next.next.next.prev, this.dog);
    t.checkExpect(this.deque3.header.next.next.next.next.next, this.sent2);
    t.checkExpect(this.deque3.header.next.next.next.next.prev, this.rat);

    t.checkExpect(this.deque4.header.next, this.one);
    t.checkExpect(this.deque4.header.prev, this.two);
    t.checkExpect(this.deque4.header.next.next, this.four);
    t.checkExpect(this.deque4.header.next.prev, this.sent3);
    t.checkExpect(this.deque4.header.next.next.next, this.three);
    t.checkExpect(this.deque4.header.next.next.prev, this.one);
    t.checkExpect(this.deque4.header.next.next.next.next, this.two);
    t.checkExpect(this.deque4.header.next.next.next.prev, this.four);
    t.checkExpect(this.deque4.header.next.next.next.next.next, this.sent3);
    t.checkExpect(this.deque4.header.next.next.next.next.prev, this.three);
  }

  // test the Predicates
  void testPredicates(Tester t) {
    this.initData();

    t.checkExpect(swb.test("bro"), true);
    t.checkExpect(swb.test("sis"), false);
    t.checkExpect(ewx.test("fox"), true);
    t.checkExpect(ewx.test("hound"), false);
    t.checkExpect(ie.test(6), true);
    t.checkExpect(ie.test(5), false);
    t.checkExpect(igtf.test(7), true);
    t.checkExpect(igtf.test(3), false);
  }

  // tests for constructor exceptions in Node
  void testNodeConstructorException(Tester t) {
    this.initData();

    t.checkConstructorException(new IllegalArgumentException("next cannot be null"),
        "Node", "hi", null, new Sentinel<String>());
    t.checkConstructorException(new IllegalArgumentException("previous cannot be null"),
        "Node", ":)", new Node<String>("hello"), null);
    t.checkConstructorException(new IllegalArgumentException("next cannot be null"),
        "Node", "bye", null, null);
  }

  // test the method updateNextNode(ANode<T>) in ANode<T>
  void testUpdateNextNode(Tester t) {
    this.initData();

    Sentinel<Integer> s = new Sentinel<Integer>();
    Sentinel<String> s2 = new Sentinel<String>();
    Sentinel<String> s3 = new Sentinel<String>();
    Sentinel<String> s4 = new Sentinel<String>();
    Node<String> xyz = new Node<String>("xyz");
    Node<String> hamster = new Node<String>("hamster", this.rat, this.dog);


    this.deque1.header.updateNextNode(xyz);
    t.checkExpect(this.deque1.header.next, xyz);
    t.checkExpect(this.deque1.header.prev, this.deque1.header);
    t.checkExpect(xyz.next, null);
    t.checkExpect(xyz.prev, null);

    s2.updateNextNode(s3);
    t.checkExpect(s2.next, s3);
    t.checkExpect(s2.prev, s3);
    t.checkExpect(s3.next, s3);
    t.checkExpect(s3.prev, s3);

    this.deque0.header.updateNextNode(s4);
    t.checkExpect(this.deque0.header.next, s3);
    t.checkExpect(this.deque0.header.prev, this.something);
    t.checkExpect(s4.next, s4);
    t.checkExpect(s4.prev, s4);

    this.deque1.header.updateNextNode(xyz);
    t.checkExpect(this.deque1.header.next, xyz);
    t.checkExpect(this.deque1.header.prev, this.deque1.header);
    t.checkExpect(xyz.next, null);
    t.checkExpect(xyz.prev, null);

    this.deque0.header.updateNextNode(hamster);
    t.checkExpect(this.deque0.header.next, hamster);
    t.checkExpect(this.deque0.header.prev, this.something);
    t.checkExpect(hamster.next, this.rat);
    t.checkExpect(hamster.prev, this.dog);
    
    this.deque4.header.prev.updateNextNode(s);
    t.checkExpect(this.deque4.header.prev.next, s);
    t.checkExpect(this.deque4.header.prev.prev, this.three);
    t.checkExpect(hamster.next, this.rat);
    t.checkExpect(hamster.prev, this.dog);
    
    this.deque2.header.next.updateNextNode(xyz);
    t.checkExpect(this.deque2.header.next.next, xyz);
    t.checkExpect(this.deque2.header.next.prev, this.deque2.header);
    t.checkExpect(xyz.next, null);
    t.checkExpect(xyz.prev, null);

    this.deque3.header.prev.updateNextNode(hamster);
    t.checkExpect(this.deque3.header.prev.next, hamster);
    t.checkExpect(this.deque3.header.prev.prev, this.rat);
    t.checkExpect(hamster.next, this.rat);
    t.checkExpect(hamster.prev, this.dog);

  }

  // test the method updatePrevNode(ANode<T>) in ANode<T>
  void testUpdatePrevNode(Tester t) {
    this.initData();
    
    Sentinel<Integer> s = new Sentinel<Integer>();
    Sentinel<String> s2 = new Sentinel<String>();
    Sentinel<String> s3 = new Sentinel<String>();
    Sentinel<String> s4 = new Sentinel<String>();
    Node<String> xyz = new Node<String>("xyz");
    Node<String> hamster = new Node<String>("hamster", this.rat, this.dog);

    this.deque1.header.updatePrevNode(xyz);
    t.checkExpect(this.deque1.header.next, this.deque1.header);
    t.checkExpect(this.deque1.header.prev, xyz);
    t.checkExpect(xyz.next, null);
    t.checkExpect(xyz.prev, null);

    s2.updatePrevNode(s3);
    t.checkExpect(s2.next, s3);
    t.checkExpect(s2.prev, s3);
    t.checkExpect(s3.next, s3);
    t.checkExpect(s3.prev, s3);

    this.deque0.header.updatePrevNode(s4);
    t.checkExpect(this.deque0.header.next, this.something);
    t.checkExpect(this.deque0.header.prev, s4);
    t.checkExpect(s4.next, s4);
    t.checkExpect(s4.prev, s4);

    this.deque1.header.updatePrevNode(xyz);
    t.checkExpect(this.deque1.header.next, this.deque1.header);
    t.checkExpect(this.deque1.header.prev, xyz);
    t.checkExpect(xyz.next, null);
    t.checkExpect(xyz.prev, null);

    this.deque0.header.updatePrevNode(hamster);
    t.checkExpect(this.deque0.header.next, this.something);
    t.checkExpect(this.deque0.header.prev, hamster);
    t.checkExpect(hamster.next, this.rat);
    t.checkExpect(hamster.prev, this.dog);
    
    this.deque4.header.prev.updatePrevNode(s);
    t.checkExpect(this.deque4.header.prev.next, this.sent3);
    t.checkExpect(this.deque4.header.prev.prev, s);
    t.checkExpect(hamster.next, this.rat);
    t.checkExpect(hamster.prev, this.dog);

    this.deque2.header.next.updatePrevNode(xyz);
    t.checkExpect(this.deque2.header.next.next, this.bcd);
    t.checkExpect(this.deque2.header.next.prev, xyz);
    t.checkExpect(xyz.next, null);
    t.checkExpect(xyz.prev, null);

    this.deque3.header.prev.updatePrevNode(hamster);
    t.checkExpect(this.deque3.header.prev.next, this.deque3.header);
    t.checkExpect(this.deque3.header.prev.prev, hamster);
    t.checkExpect(hamster.next, this.rat);
    t.checkExpect(hamster.prev, this.dog);
  }

  // test the method sizeHelp(int) in Sentinel
  void testSentinelSizeHelp(Tester t) {
    this.initData();

    Sentinel<String> s2 = new Sentinel<String>();

    t.checkExpect(s2.sizeHelp(1), 1);
    t.checkExpect(this.deque1.header.sizeHelp(0), 0);
    t.checkExpect(this.deque2.header.sizeHelp(16), 16);
    t.checkExpect(this.deque4.header.sizeHelp(3), 3);
  }

  // test the method sizeHelp(int) in Node
  void testNodeSizeHelp(Tester t) {
    this.initData();

    Node<String> hamster = new Node<String>("hamster", this.rat, this.dog);

    t.checkExpect(hamster.sizeHelp(0), 3);
    t.checkExpect(this.deque0.header.next.sizeHelp(0), 1);
    t.checkExpect(this.deque2.header.prev.sizeHelp(2), 3);
    t.checkExpect(this.deque4.header.next.sizeHelp(15), 19);
  }

  // test the method removeHelp() in Sentinel
  void testSentinelRemoveHelp(Tester t) {
    this.initData();

    Sentinel<String> s2 = new Sentinel<String>();

    t.checkException(new RuntimeException("cannot remove from an empty list"), s2, "removeHelp");
    t.checkException(new RuntimeException("cannot remove from an empty list"), this.deque1.header,
        "removeHelp");
    t.checkException(new RuntimeException("cannot remove from an empty list"), this.deque2.header,
        "removeHelp");

  }

  // test the method removeHelp() in Node
  void testNodeRemoveHelp(Tester t) {
    this.initData();

    Node<String> hamster = new Node<String>("hamster", this.rat, this.dog);

    t.checkExpect(hamster.removeHelp(), "hamster");
    t.checkExpect(this.rat.prev, this.dog);
    t.checkExpect(this.dog.next, this.rat);

    t.checkExpect(this.cat.removeHelp(), "cat");
    t.checkExpect(this.dog.prev, this.sent2);
    t.checkExpect(this.sent2.next, this.dog);

    t.checkExpect(this.two.removeHelp(), 2);
    t.checkExpect(this.sent3.prev, this.three);
    t.checkExpect(this.three.next, this.sent3);

    t.checkExpect(this.something.removeHelp(), "something");
    t.checkExpect(this.sent0.prev, this.sent0);
    t.checkExpect(this.sent0.next, this.sent0);
  }

  // test the method findHelp(Predicate<T>) in Sentinel
  void testSentinelFindHelp(Tester t) {
    Sentinel<String> s2 = new Sentinel<String>();

    t.checkExpect(s2.findHelp(ewx), s2);
    t.checkExpect(this.deque4.header.findHelp(this.ie), this.sent3);
    t.checkExpect(this.deque1.header.findHelp(this.swb), this.deque1.header);
  }

  // test the method findHelp(Predicate<T>) in Node
  void testNodeFindHelp(Tester t) {
    t.checkExpect(this.deque0.header.next.findHelp(this.ewx), this.sent0);
    t.checkExpect(this.deque2.header.prev.findHelp(this.swb), this.sent1);
    t.checkExpect(this.deque4.header.next.next.findHelp(this.ie), this.four);
    t.checkExpect(this.deque4.header.prev.prev.findHelp(this.igtf), this.sent3);
  }

  // test the method size() in Deque
  void testDequeSize(Tester t) {
    this.initData();

    t.checkExpect(this.deque1.size(), 0);
    t.checkExpect(this.deque2.size(), 4);
    t.checkExpect(this.deque3.size(), 4);
    t.checkExpect(this.deque4.size(), 4);

  }

  // test the method addAtHead(T) in Deque
  void testDequeAddAtHead(Tester t) {
    this.initData();

    Sentinel<String> s = new Sentinel<String>();
    Node<String> hello = new Node<String>("hello", s, s);

    Sentinel<String> s2 = new Sentinel<String>();
    Node<String> xyz = new Node<String>("xyz", s2, s2);
    Node<String> abc2 = new Node<String>("abc", s2, xyz);
    Node<String> bcd2 = new Node<String>("bcd", s2, abc2);
    Node<String> cde2 = new Node<String>("cde", s2, bcd2);
    Node<String> def2 = new Node<String>("def", s2, cde2);

    Sentinel<Integer> s4 = new Sentinel<Integer>();
    Node<Integer> twenty = new Node<Integer>(20, s4, s4);
    Node<Integer> one2 = new Node<Integer>(1, s4, twenty);
    Node<Integer> four2 = new Node<Integer>(4, s4, one2);
    Node<Integer> three2 = new Node<Integer>(3, s4, four2);
    Node<Integer> two2 = new Node<Integer>(2, s4, three2);

    this.deque1.addAtHead("hello");
    t.checkExpect(this.deque1.header.next, hello);
    t.checkExpect(this.deque1.header.prev, hello);
    t.checkExpect(this.deque1.header.next.next, s);
    t.checkExpect(this.deque1.header.next.prev, s);

    this.deque2.addAtHead("xyz");
    t.checkExpect(this.deque2.header.next, xyz);
    t.checkExpect(this.deque2.header.prev, def2);
    t.checkExpect(this.deque2.header.next.next, abc2);
    t.checkExpect(this.deque2.header.next.prev, s2);
    t.checkExpect(this.deque2.header.next.next.next, bcd2);
    t.checkExpect(this.deque2.header.next.next.prev, xyz);
    t.checkExpect(this.deque2.header.next.next.next.next, cde2);
    t.checkExpect(this.deque2.header.next.next.next.prev, abc2);
    t.checkExpect(this.deque2.header.next.next.next.next.next, def2);
    t.checkExpect(this.deque2.header.next.next.next.next.prev, bcd2);
    t.checkExpect(this.deque2.header.next.next.next.next.next.next, s2);
    t.checkExpect(this.deque2.header.next.next.next.next.next.prev, cde2);

    this.deque4.addAtHead(20);
    t.checkExpect(this.deque4.header.next, twenty);
    t.checkExpect(this.deque4.header.prev, two2);
    t.checkExpect(this.deque4.header.next.next, one2);
    t.checkExpect(this.deque4.header.next.prev, s4);
    t.checkExpect(this.deque4.header.next.next.next, four2);
    t.checkExpect(this.deque4.header.next.next.prev, twenty);
    t.checkExpect(this.deque4.header.next.next.next.next, three2);
    t.checkExpect(this.deque4.header.next.next.next.prev, one2);
    t.checkExpect(this.deque4.header.next.next.next.next.next, two2);
    t.checkExpect(this.deque4.header.next.next.next.next.prev, four2);
    t.checkExpect(this.deque4.header.next.next.next.next.next.next, s4);
    t.checkExpect(this.deque4.header.next.next.next.next.next.prev, three2);
  }

  // test the method addAtTail(T) in Deque
  void testDequeAddAtTail(Tester t) {
    this.initData();

    Sentinel<String> s = new Sentinel<String>();
    Node<String> hello = new Node<String>("hello", s, s);

    Sentinel<String> s2 = new Sentinel<String>();
    Node<String> abc2 = new Node<String>("abc", s2, s2);
    Node<String> bcd2 = new Node<String>("bcd", s2, abc2);
    Node<String> cde2 = new Node<String>("cde", s2, bcd2);
    Node<String> def2 = new Node<String>("def", s2, cde2);
    Node<String> xyz = new Node<String>("xyz", s2, def2);

    Sentinel<Integer> s4 = new Sentinel<Integer>();
    Node<Integer> one2 = new Node<Integer>(1, s4, s4);
    Node<Integer> four2 = new Node<Integer>(4, s4, one2);
    Node<Integer> three2 = new Node<Integer>(3, s4, four2);
    Node<Integer> two2 = new Node<Integer>(2, s4, three2);
    Node<Integer> twenty = new Node<Integer>(20, s4, two2);

    this.deque1.addAtTail("hello");
    t.checkExpect(this.deque1.header.next, hello);
    t.checkExpect(this.deque1.header.prev, hello);
    t.checkExpect(this.deque1.header.next.next, s);
    t.checkExpect(this.deque1.header.next.prev, s);

    this.deque2.addAtTail("xyz");
    t.checkExpect(this.deque2.header.next, abc2);
    t.checkExpect(this.deque2.header.prev, xyz);
    t.checkExpect(this.deque2.header.next.next, bcd2);
    t.checkExpect(this.deque2.header.next.prev, s2);
    t.checkExpect(this.deque2.header.next.next.next, cde2);
    t.checkExpect(this.deque2.header.next.next.prev, abc2);
    t.checkExpect(this.deque2.header.next.next.next.next, def2);
    t.checkExpect(this.deque2.header.next.next.next.prev, bcd2);
    t.checkExpect(this.deque2.header.next.next.next.next.next, xyz);
    t.checkExpect(this.deque2.header.next.next.next.next.prev, cde2);
    t.checkExpect(this.deque2.header.next.next.next.next.next.next, s2);
    t.checkExpect(this.deque2.header.next.next.next.next.next.prev, def2);

    this.deque4.addAtTail(20);
    t.checkExpect(this.deque4.header.next, one2);
    t.checkExpect(this.deque4.header.prev, twenty);
    t.checkExpect(this.deque4.header.next.next, four2);
    t.checkExpect(this.deque4.header.next.prev, s4);
    t.checkExpect(this.deque4.header.next.next.next, three2);
    t.checkExpect(this.deque4.header.next.next.prev, one2);
    t.checkExpect(this.deque4.header.next.next.next.next, two2);
    t.checkExpect(this.deque4.header.next.next.next.prev, four2);
    t.checkExpect(this.deque4.header.next.next.next.next.next, twenty);
    t.checkExpect(this.deque4.header.next.next.next.next.prev, three2);
    t.checkExpect(this.deque4.header.next.next.next.next.next.next, s4);
    t.checkExpect(this.deque4.header.next.next.next.next.next.prev, two2);
  }

  // test the method removeFromHead() in Deque
  void testDequeRemoveFromHead(Tester t) {
    this.initData();

    Sentinel<String> s2 = new Sentinel<String>();
    Node<String> bcd2 = new Node<String>("bcd", s2, s2);
    Node<String> cde2 = new Node<String>("cde", s2, bcd2);
    Node<String> def2 = new Node<String>("def", s2, cde2);

    Sentinel<Integer> s4 = new Sentinel<Integer>();
    Node<Integer> four2 = new Node<Integer>(4, s4, s4);
    Node<Integer> three2 = new Node<Integer>(3, s4, four2);
    Node<Integer> two2 = new Node<Integer>(2, s4, three2);

    t.checkException(new RuntimeException("cannot remove from an empty list"), this.deque1,
        "removeFromHead");

    t.checkExpect(this.deque2.removeFromHead(), "abc");
    t.checkExpect(this.deque2.header.next, bcd2);
    t.checkExpect(this.deque2.header.prev, def2);
    t.checkExpect(this.deque2.header.next.next, cde2);
    t.checkExpect(this.deque2.header.next.prev, s2);
    t.checkExpect(this.deque2.header.next.next.next, def2);
    t.checkExpect(this.deque2.header.next.next.prev, bcd2);
    t.checkExpect(this.deque2.header.next.next.next.next, s2);
    t.checkExpect(this.deque2.header.next.next.next.prev, cde2);

    t.checkExpect(this.deque4.removeFromHead(), 1);
    t.checkExpect(this.deque4.header.next, four2);
    t.checkExpect(this.deque4.header.prev, two2);
    t.checkExpect(this.deque4.header.next.next, three2);
    t.checkExpect(this.deque4.header.next.prev, s4);
    t.checkExpect(this.deque4.header.next.next.next, two2);
    t.checkExpect(this.deque4.header.next.next.prev, four2);
    t.checkExpect(this.deque4.header.next.next.next.next, s4);
    t.checkExpect(this.deque4.header.next.next.next.prev, three2);
  }

  // test the method removeFromTail() in Deque
  void testDequeRemoveFromTail(Tester t) {
    this.initData();

    Sentinel<String> s2 = new Sentinel<String>();
    Node<String> abc2 = new Node<String>("abc", s2, s2);
    Node<String> bcd2 = new Node<String>("bcd", s2, abc2);
    Node<String> cde2 = new Node<String>("cde", s2, bcd2);

    Sentinel<Integer> s4 = new Sentinel<Integer>();
    Node<Integer> one2 = new Node<Integer>(1, s4, s4);
    Node<Integer> four2 = new Node<Integer>(4, s4, one2);
    Node<Integer> three2 = new Node<Integer>(3, s4, four2);

    t.checkException(new RuntimeException("cannot remove from an empty list"), this.deque1,
        "removeFromTail");

    t.checkExpect(this.deque2.removeFromTail(), "def");
    t.checkExpect(this.deque2.header.next, abc2);
    t.checkExpect(this.deque2.header.prev, cde2);
    t.checkExpect(this.deque2.header.next.next, bcd2);
    t.checkExpect(this.deque2.header.next.prev, s2);
    t.checkExpect(this.deque2.header.next.next.next, cde2);
    t.checkExpect(this.deque2.header.next.next.prev, abc2);
    t.checkExpect(this.deque2.header.next.next.next.next, s2);
    t.checkExpect(this.deque2.header.next.next.next.prev, bcd2);

    t.checkExpect(this.deque4.removeFromTail(), 2);
    t.checkExpect(this.deque4.header.next, one2);
    t.checkExpect(this.deque4.header.prev, three2);
    t.checkExpect(this.deque4.header.next.next, four2);
    t.checkExpect(this.deque4.header.next.prev, s4);
    t.checkExpect(this.deque4.header.next.next.next, three2);
    t.checkExpect(this.deque4.header.next.next.prev, one2);
    t.checkExpect(this.deque4.header.next.next.next.next, s4);
    t.checkExpect(this.deque4.header.next.next.next.prev, four2);
  }

  // test the method find(Predicate<T>) in Deque
  void testDequeFind(Tester t) {
    this.initData();

    t.checkExpect(this.deque1.find(this.swb), new Sentinel<String>());
    t.checkExpect(this.deque1.find(this.ewx), new Sentinel<String>());
    t.checkExpect(this.deque2.find(this.swb), this.bcd);
    t.checkExpect(this.deque2.find(this.ewx), this.sent1);
    t.checkExpect(this.deque4.find(this.ie), this.four);
    t.checkExpect(this.deque4.find(this.igtf), this.sent3);
  }
}
