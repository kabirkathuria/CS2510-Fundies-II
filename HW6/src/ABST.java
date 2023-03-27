import tester.Tester;
import java.util.Comparator;

// represents a binary search tree (BST)
abstract class ABST<T> {
  
  /* TEMPLATE
   * FIELDS:
   * ... this.order ... - Comparator<T>
   * 
   * METHODS:
   * ... this.insert(T item) - ABST<T>
   * ... this.present(T item) - boolean
   * ... this.getLeftmost() - T
   * ... this.getRight() - ABST<T>
   * ... this.isLeaf() - boolean
   * ... this.isNode() - boolean
   * ... this.sameTree(ABST<T> tree) - boolean
   * ... this.getTreeData() ... - T
   * ... this.getRightHelp() ... - ABST<T>
   * ... this.getLeftHelp() ... - ABST<T>
   * ... this.sameData(ABST<T> tree) ... - boolean
   * ... this.buildList() ... - IList<T>
   * 
   */
  
  Comparator<T> order;
  
  ABST(Comparator<T> order) {
    this.order = order;
  }
  
  // returns a BST with inputted item inserted in the correct place
  abstract ABST<T> insert(T item);
  
  // determines whether inputted item is present in a BST
  abstract boolean present(T item);
  
  // returns leftmost item contained in a BST
  abstract T getLeftmost();
  
  // returns a BST containing all but the leftmost item of the tree
  abstract ABST<T> getRight();
  
  // determines whether a BST is a leaf 
  abstract boolean isLeaf();
  
  // determines whether a BST is a node
  abstract boolean isNode();
  
  // determines whether a BST is the same as an inputted BST
  abstract boolean sameTree(ABST<T> tree);
  
  // returns data of a BST
  abstract T getTreeData();
  
  // returns right side of a BST
  abstract ABST<T> getRightHelp();
  
  // returns left side of a BST
  abstract ABST<T> getLeftHelp();
  
  // determines whether a BST contains same data in same order as inputted BST
  abstract boolean sameData(ABST<T> tree);
  
  // returns a list representing a BST
  abstract IList<T> buildList();
}

// represents a leaf in a BST
class Leaf<T> extends ABST<T> {
  
  /* TEMPLATE
   * FIELDS: 
   * ... this.order ... Comparator<T>
   * 
   * METHODS:
   * ... this.insert(T item) ... - ABST<T>
   * ... this.present(T item) ... - boolean
   * ... this.getLeftmost() ... - T
   * ... this.getRight() ... - ABST<T>
   * ... this.isLeaf() ... - boolean
   * ... this.isNode() ... - boolean
   * ... this.sameTree(ABST<T> tree) ... - boolean
   * ... this.getTreeData() ... - T
   * ... this.getRightHelp() ... - ABST<T>
   * ... this.getLeftHelp() ... - ABST<T>
   * ... this.sameData(ABST<T> tree) ... - boolean
   * ... this.buildList() ... - IList<T>
   * 
   * METHODS OF FIELDS:
   * ... this.order.compare(T t1, T t2) ... - int
   * 
   */

  Leaf(Comparator<T> order) {
    super(order);
  }
  
  // returns a leaf with inputted item inserted in the correct place
  public ABST<T> insert(T item) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... item ... - T
     */
    
    return new Node<T>(this.order, item, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }
  
  // determines whether inputted item is present in a leaf
  public boolean present(T item) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... item ... - T
     */
    
    return false;
  }
  
  // returns leftmost item contained in a leaf
  public T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }
  
  // returns a leaf containing all but the leftmost item of the leaf
  public ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }
  
  // determines whether a leaf is a leaf
  public boolean isLeaf() {
    return true;
  }
  
  // determines whether a leaf is a node
  public boolean isNode() {
    return false;
  }
  
  // determines whether a leaf is the same as an inputted BST
  public boolean sameTree(ABST<T> tree) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... tree ... - ABST<T>
     * 
     * FIELDS OF PARAMETERS:
     * ... tree.order ... - Comparator<T>
     * 
     * METHODS OF PARAMETERS:
     * ... tree.insert(T item) ... - ABST<T>
     * ... tree.present(T item) ... - boolean
     * ... tree.getLeftmost() ... - T
     * ... tree.getRight() ... - ABST<T>
     * ... tree.isLeaf() ... - boolean
     * ... tree.isNode() ... - boolean
     * ... tree.sameTree(ABST<T> tree) ... - boolean
     * ... tree.getTreeData() ... - T
     * ... tree.getRightHelp() ... - ABST<T>
     * ... tree.getLeftHelp() ... - ABST<T>
     * ... tree.sameData(ABST<T> tree) ... - boolean
     * ... tree.buildList() ... - IList<T>
     * 
     */
    
    return tree.isLeaf()
        && this.order.equals(tree.order);
  }
  
  // returns data of a leaf
  public T getTreeData() {
    return null;
  }
  
  // returns right side of a leaf
  public ABST<T> getRightHelp() {
    return null;
  }
  
  // returns left side of a leaf
  public ABST<T> getLeftHelp() {
    return null;
  }
  
  // determines whether a leaf contains same data in same order as inputted BST
  public boolean sameData(ABST<T> tree) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... tree ... - ABST<T>
     * 
     * FIELDS OF PARAMETERS:
     * ... tree.order ... - Comparator<T>
     * 
     * METHODS OF PARAMETERS:
     * ... tree.insert(T item) ... - ABST<T>
     * ... tree.present(T item) ... - boolean
     * ... tree.getLeftmost() ... - T
     * ... tree.getRight() ... - ABST<T>
     * ... tree.isLeaf() ... - boolean
     * ... tree.isNode() ... - boolean
     * ... tree.sameTree(ABST<T> tree) ... - boolean
     * ... tree.getTreeData() ... - T
     * ... tree.getRightHelp() ... - ABST<T>
     * ... tree.getLeftHelp() ... - ABST<T>
     * ... tree.sameData(ABST<T> tree) ... - boolean
     * ... tree.buildList() ... - IList<T>
     * 
     */
    
    return tree.isLeaf();
  }
  
  // returns a list representing a leaf
  public IList<T> buildList() {
    return new MtList<T>();
  }
  
}

// represents a node in a BST
class Node<T> extends ABST<T> {
  
  /* TEMPLATE
   * FIELDS:
   * ... this.data ... - T
   * ... this.left ... - ABST<T>
   * ... this.right ... - ABST<T>
   * ... this.order ... - Comparator<T>
   * 
   * METHODS:
   * ... this.insert(T) ... - ABST<T>
   * ... this.present(T) ... - boolean
   * ... this.getLeftmost() ... - T
   * ... this.getRight() ... - ABST<T>
   * ... this.isLeaf() ... - boolean
   * ... this.isNode() ... - boolean
   * ... this.sameTree(ABST<T>) ... - boolean
   * ... this.getTreeData() ... - T
   * ... this.getRightHelp() ... - ABST<T>
   * ... this.getLeftHelp() ... - ABST<T>
   * ... this.sameData(ABST<T>) ... - boolean
   * ... this.buildList() ... - IList<T>
   * 
   * METHODS OF FIELDS:
   * ... this.order.compare(T t1, T t2) ... - int 
   * ... this.left.buildList() ... - IList<T>
   * ... this.left.getLeftHelp() ... - ABST<T>
   * ... this.left.getLeftmost() ... - T
   * ... this.left.getRight() ... - ABST<T>
   * ... this.left.getRightHelp() ... - ABST<T>
   * ... this.left.getTreeData() ... - T
   * ... this.left.insert(T) ... - ABST<T>
   * ... this.left.isLeaf() ... - boolean
   * ... this.left.isNode() ... - boolean
   * ... this.left.present(T) ... - boolean
   * ... this.left.sameData(ABST<T>) ... - boolean
   * ... this.left.sameTree(ABST<T>) ... - boolean
   * ... this.right.buildList() ... - IList<T>
   * ... this.right.getLeftHelp() ... - ABST<T>
   * ... this.right.getLeftmost() ... - T
   * ... this.right.getRight() ... - ABST<T>
   * ... this.right.getRightHelp() ... - ABST<T>
   * ... this.right.getTreeData() ... - T
   * ... this.right.insert(T) ... - ABST<T>
   * ... this.right.isLeaf() ... - boolean
   * ... this.right.isNode() ... - boolean
   * ... this.right.present(T) ... - boolean
   * ... this.right.sameData(ABST<T>) ... - boolean
   * ... this.right.sameTree(ABST<T>) ... - boolean
   * 
   */

  T data;
  ABST<T> left;
  ABST<T> right;
  
  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  // returns a node with inputted item inserted in the correct place
  public ABST<T> insert(T item) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... item ... - T
     */
    
    if (order.compare(item, this.data) >= 0) {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(item));
    }
    else {
      return new Node<T>(this.order, this.data, this.left.insert(item), this.right);
    }
  }
  
  // determines whether inputted item is present in a node
  public boolean present(T item) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... item ... - T
     */
    
    return this.order.compare(this.data, item) == 0
        || this.left.present(item)
        || this.right.present(item);
  }
  
  // returns leftmost item contained in a node
  public T getLeftmost() {
    if (this.left.isLeaf()) {
      return this.data;
    }
    else {
      return this.left.getLeftmost();
    }
  }
  
  // returns a node containing all but the leftmost item of the node
  public ABST<T> getRight() {
    if (this.left.isLeaf()) {
      return this.right;
    }
    else {
      return new Node<T>(this.order, this.data, this.left.getRight(), this.right);
    }
  }
  
  // determines whether a node is a leaf
  public boolean isLeaf() {
    return false;
  }
  
  // determines whether a node is a node
  public boolean isNode() {
    return true;
  }
  
  // determines whether a node is the same as an inputted BST
  public boolean sameTree(ABST<T> tree) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... tree ... - ABST<T>
     * 
     * FIELDS OF PARAMETERS:
     * ... tree.order ... - Comparator<T>
     * 
     * METHODS OF PARAMETERS:
     * ... tree.insert(T item) ... - ABST<T>
     * ... tree.present(T item) ... - boolean
     * ... tree.getLeftmost() ... - T
     * ... tree.getRight() ... - ABST<T>
     * ... tree.isLeaf() ... - boolean
     * ... tree.isNode() ... - boolean
     * ... tree.sameTree(ABST<T> tree) ... - boolean
     * ... tree.getTreeData() ... - T
     * ... tree.getRightHelp() ... - ABST<T>
     * ... tree.getLeftHelp() ... - ABST<T>
     * ... tree.sameData(ABST<T> tree) ... - boolean
     * ... tree.buildList() ... - IList<T>
     * 
     */
    
    if (tree.isNode()) {
      return this.order.compare(this.data, tree.getTreeData()) == 0
          && this.right.sameTree(tree.getRightHelp())
          && this.left.sameTree(tree.getLeftHelp());
    }
    else {
      return false;
    }
  }
  
  // returns data of a node
  public T getTreeData() {
    return this.data;
  }
  
  // returns right side of a node
  public ABST<T> getRightHelp() {
    return this.right;
  }
  
  // returns left side of a node
  public ABST<T> getLeftHelp() {
    return this.left;
  }
  
  // determines whether a node contains same data in same order as inputted BST
  public boolean sameData(ABST<T> tree) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... tree ... - ABST<T>
     * 
     * FIELDS OF PARAMETERS:
     * ... tree.order ... - Comparator<T>
     * 
     * METHODS OF PARAMETERS:
     * ... tree.insert(T item) ... - ABST<T>
     * ... tree.present(T item) ... - boolean
     * ... tree.getLeftmost() ... - T
     * ... tree.getRight() ... - ABST<T>
     * ... tree.isLeaf() ... - boolean
     * ... tree.isNode() ... - boolean
     * ... tree.sameTree(ABST<T> tree) ... - boolean
     * ... tree.getTreeData() ... - T
     * ... tree.getRightHelp() ... - ABST<T>
     * ... tree.getLeftHelp() ... - ABST<T>
     * ... tree.sameData(ABST<T> tree) ... - boolean
     * ... tree.buildList() ... - IList<T>
     * 
     */
    
    if (tree.isNode()) {
      return this.order.compare(this.getLeftmost(), tree.getLeftmost()) == 0
          && this.getRight().sameData(tree.getRight());
    }
    else {
      return false;
    }
  }
  
  // returns a list representing a node
  public IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }
  
}

// interface representing a list of type T
interface IList<T> {
  
}

// represents an empty list of T
class MtList<T> implements IList<T> {
  MtList() {}
  
}

// represents a non-empty list of T
class ConsList<T> implements IList<T> {
  
  /* TEMPLATE
   * FIELDS:
   * ... this.first ... - T
   * ... this.rest ... - IList<T>
   * 
   */
  
  T first;
  IList<T> rest;
  
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
}

// represents a Book
class Book {
  
  /* TEMPLATE
   * FIELDS:
   * ... this.title ... - String
   * ... this.author ... - String
   * ... this.price ... - int
   * 
   */

  String title;
  String author;
  int price;
  
  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
}

// represents books compared by title
class BooksByTitle implements Comparator<Book> {
  
  /* TEMPLATE
   * METHODS:
   * ... this.compare(Book book1, Book book2) ... - int
   * 
   */

  // compares book titles
  public int compare(Book book1, Book book2) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... book1 ... - Book
     * ... book2 ... - Book
     * 
     * FIELDS OF PARAMETERS:
     * ... book1.title ... - String
     * ... book1.author ... - String
     * ... book1.price ... - int
     * ... book2.title ... - String
     * ... book2.author ... - String
     * ... book2.price ... - int
     * 
     */
    
    return book1.title.compareTo(book2.title);
  }
}

// represents books compared by author
class BooksByAuthor implements Comparator<Book> {
  
  /* TEMPLATE
   * METHODS:
   * ... this.compare(Book book1, Book book2) ... - int
   * 
   */

  // compares book authors
  public int compare(Book book1, Book book2) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... book1 ... - Book
     * ... book2 ... - Book
     * 
     * FIELDS OF PARAMETERS:
     * ... book1.title ... - String
     * ... book1.author ... - String
     * ... book1.price ... - int
     * ... book2.title ... - String
     * ... book2.author ... - String
     * ... book2.price ... - int
     * 
     */
    
    return book1.author.compareTo(book2.author);
  }
}

// represents books compared by price
class BooksByPrice implements Comparator<Book> {
  
  /* TEMPLATE
   * METHODS:
   * ... this.compare(Book book1, Book book2) ... - int
   * 
   */

  // compares book prices
  public int compare(Book book1, Book book2) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... book1 ... - Book
     * ... book2 ... - Book
     * 
     * FIELDS OF PARAMETERS:
     * ... book1.title ... - String
     * ... book1.author ... - String
     * ... book1.price ... - int
     * ... book2.title ... - String
     * ... book2.author ... - String
     * ... book2.price ... - int
     * 
     */
    
    return book1.price - book2.price;
  }
}

// represents integers compared by order
class IntegersByOrder implements Comparator<Integer> {
  
  /* TEMPLATE
   * METHODS:
   * ... this.compare(Integer int1, Integer int2) ... - int
   * 
   */

  // compares integer prices
  public int compare(Integer int1, Integer int2) {
    
    /* TEMPLATE
     * PARAMETERS:
     * ... int1 ... - Integer
     * ... int2 ... - Integer
     * 
     */
    
    return int1 - int2;
  }
}

// represent examples of BST's
class ExamplesABST {
  ExamplesABST(){}
  
  //////////////////// Integers Examples ///////////////////////
  
  // comparator for integers by order
  Comparator<Integer> compareByInteger = new IntegersByOrder();
  
  // represents a leaf of a BST comparing integers by order
  Leaf<Integer> intLeaf = new Leaf<Integer>(this.compareByInteger);
  
  // represents first node of a BST comparing integers by order
  Node<Integer> intNode1 = new Node<Integer>(this.compareByInteger, 7, 
      this.intLeaf, this.intLeaf);
  
  // represents second node of a BST comparing integers by order
  Node<Integer> intNode2 = new Node<Integer>(this.compareByInteger, 12, 
      this.intLeaf, this.intLeaf);
  
  // represents third node of a BST comparing integers by order
  ABST<Integer> intNode3 = new Node<Integer>(this.compareByInteger, 10, 
      this.intNode1, this.intNode2);
  
  // represents first node of an inputted BST comparing integers by order
  Node<Integer> inputIntNode1 = new Node<Integer>(this.compareByInteger, 11, 
      this.intLeaf, this.intLeaf);
  
  // represents second node of an inputted BST comparing integers by order
  Node<Integer> inputIntNode2 = new Node<Integer>(this.compareByInteger, 12, 
      this.inputIntNode1, this.intLeaf);
  
  // represents third node of an inputted BST comparing integers by order
  ABST<Integer> inputIntNode3 = new Node<Integer>(this.compareByInteger, 10, 
      this.intNode1, this.inputIntNode2);
  
  // represents fourth node of an inputted BST comparing integers by order
  ABST<Integer> rightSideInputIntNode3 = new Node<Integer>(this.compareByInteger, 10, 
      this.intLeaf, this.inputIntNode2);
  
  // represents an empty list of integers
  IList<Integer> mtIntegerList = new MtList<Integer>();
  
  // represents a list of integers sorted by order
  IList<Integer> inputIntegerList = 
      new ConsList<Integer>(7, 
          new ConsList<Integer>(10, 
              new ConsList<Integer>(11, 
                  new ConsList<Integer>(12, 
                      new MtList<Integer>()))));
  
  
  //////////////////// Books Examples ///////////////////////
  
  // book example 1
  Book b1 = new Book("Awakening", "Chopin", 75);
  
  // book example 2
  Book b2 = new Book("Dune", "Herbert", 60);
  
  // book example 3
  Book b3 = new Book("Hamlet", "Shakespeare", 40);
  
  // book example 4
  Book b4 = new Book("Fahrenheit 451", "Bradbury", 100);
  
  // comparator for books sorted by title
  Comparator<Book> compareByTitle = new BooksByTitle();
  
  // comparator for books sorted by author
  Comparator<Book> compareByAuthor = new BooksByAuthor();
  
  // comparator for books sorted by price
  Comparator<Book> compareByPrice = new BooksByPrice();
  
  // represents a leaf of a BST comparing books by title
  Leaf<Book> titleLeaf = new Leaf<Book>(this.compareByTitle);
  
  // represents first node of a BST comparing books by title
  Node<Book> titleNode1 = new Node<Book>(this.compareByTitle, this.b1, 
      this.titleLeaf, this.titleLeaf);
  
  // represents second node of a BST comparing books by title
  Node<Book> titleNode2 = new Node<Book>(this.compareByTitle, this.b2, 
      this.titleNode1, this.titleLeaf);
  
  // represents third node of a BST comparing books by title
  ABST<Book> titleNode3 = new Node<Book>(this.compareByTitle, this.b3, 
      this.titleNode2, this.titleLeaf);
  
  // represents first node of an inputted BST comparing books by title
  Node<Book> inputTitleNode1 = new Node<Book>(this.compareByTitle, this.b1, 
      this.titleLeaf, this.titleLeaf);
  
  // represents second node of an inputted BST comparing books by title
  Node<Book> inputTitleNode2 = new Node<Book>(this.compareByTitle, this.b4, 
      this.titleLeaf, this.titleLeaf);
  
  // represents third node of an inputted BST comparing books by title
  Node<Book> inputTitleNode3 = new Node<Book>(this.compareByTitle, this.b2, 
      this.inputTitleNode1, this.inputTitleNode2);
  
  // represents fourth node of an inputted BST comparing books by title
  ABST<Book> inputTitleNode4 = new Node<Book>(this.compareByTitle, this.b3, 
      this.inputTitleNode3, this.titleLeaf);
  
  
  
  // represents a leaf of a BST comparing books by author
  Leaf<Book> authorLeaf = new Leaf<Book>(this.compareByAuthor);
  
  // represents first node of a BST comparing books by author
  Node<Book> authorNode1 = new Node<Book>(this.compareByAuthor, this.b3, 
      this.authorLeaf, this.authorLeaf);
  
  // represents second node of a BST comparing books by author
  Node<Book> authorNode2 = new Node<Book>(this.compareByAuthor, this.b2, 
      this.authorLeaf, this.authorNode1);
  
  // represents third node of a BST comparing books by author
  ABST<Book> authorNode3 = new Node<Book>(this.compareByAuthor, this.b1, 
      this.authorLeaf, this.authorNode2);
  
  // represents first node of an inputted BST comparing books by author
  Node<Book> inputAuthorNode1 = new Node<Book>(this.compareByAuthor, this.b4, 
      this.authorLeaf, this.authorLeaf);
  
  // represents second node of an inputted BST comparing books by author
  Node<Book> inputAuthorNode2 = new Node<Book>(this.compareByAuthor, this.b3, 
      this.authorLeaf, this.authorLeaf);
  
  // represents third node of an inputted BST comparing books by author
  Node<Book> inputAuthorNode3 = new Node<Book>(this.compareByAuthor, this.b2, 
      this.authorLeaf, this.inputAuthorNode2);
  
  // represents fourth node of an inputted BST comparing books by author
  ABST<Book> inputAuthorNode4 = new Node<Book>(this.compareByAuthor, this.b1, 
      this.inputAuthorNode1, this.inputAuthorNode3);
  
  
  
  // represents a leaf of a BST comparing books by price
  Leaf<Book> priceLeaf = new Leaf<Book>(this.compareByPrice);
  
  // represents first node of a BST comparing books by price
  Node<Book> priceNode1 = new Node<Book>(this.compareByPrice, this.b3, 
      this.priceLeaf, this.priceLeaf);
  
  // represents second node of a BST comparing books by price
  Node<Book> priceNode2 = new Node<Book>(this.compareByPrice, this.b1, 
      this.priceLeaf, this.priceLeaf);
  
  // represents third node of a BST comparing books by price
  ABST<Book> priceNode3 = new Node<Book>(this.compareByPrice, this.b2, 
      this.priceNode1, this.priceNode2);
  
  // represents first node of an inputted BST comparing books by price
  Node<Book> inputPriceNode1 = new Node<Book>(this.compareByPrice, this.b3, 
      this.priceLeaf, this.priceLeaf);
  
  // represents second node of an inputted BST comparing books by price
  Node<Book> inputPriceNode2 = new Node<Book>(this.compareByPrice, this.b4, 
      this.priceLeaf, this.priceLeaf);
  
  // represents third node of an inputted BST comparing books by price
  Node<Book> inputPriceNode3 = new Node<Book>(this.compareByPrice, this.b1, 
      this.priceLeaf, this.inputPriceNode2);
  
  // represents fourth node of an inputted BST comparing books by price
  ABST<Book> inputPriceNode4 = new Node<Book>(this.compareByPrice, this.b2, 
      this.inputPriceNode1, this.inputPriceNode3);
  
  
  // represents an empty list of books
  IList<Book> mtBookList = new MtList<Book>();
  
  // represents a list of books sorted by title
  IList<Book> titleList = 
      new ConsList<Book>(this.b1, 
          new ConsList<Book>(this.b2, 
              new ConsList<Book>(this.b4, 
                  new ConsList<Book>(this.b3, 
                      new MtList<Book>()))));
  
  // represents a list of books sorted by author
  IList<Book> authorList = 
      new ConsList<Book>(this.b4, 
          new ConsList<Book>(this.b1, 
              new ConsList<Book>(this.b2, 
                  new ConsList<Book>(this.b3, 
                      new MtList<Book>()))));
  
  // represents a list of books sorted by price
  IList<Book> priceList = 
      new ConsList<Book>(this.b3, 
          new ConsList<Book>(this.b2, 
              new ConsList<Book>(this.b1, 
                  new ConsList<Book>(this.b4, 
                      new MtList<Book>()))));
  
  
  
  // tests the method insert
  boolean testInsert(Tester t) {
    return t.checkExpect(this.intLeaf.insert(7), this.intNode1)
        && t.checkExpect(this.intNode3.insert(11), this.inputIntNode3)
        && t.checkExpect(this.titleLeaf.insert(this.b1), this.titleNode1)
        && t.checkExpect(this.titleNode3.insert(this.b4), this.inputTitleNode4)
        && t.checkExpect(this.authorLeaf.insert(this.b3), this.authorNode1)
        && t.checkExpect(this.authorNode3.insert(this.b4), this.inputAuthorNode4)
        && t.checkExpect(this.priceLeaf.insert(this.b3), this.priceNode1)
        && t.checkExpect(this.priceNode3.insert(this.b4), this.inputPriceNode4);
  }
  
  // tests the method present
  boolean testPresent(Tester t) {
    return t.checkExpect(this.intLeaf.present(6), false)
        && t.checkExpect(this.inputIntNode3.present(10), true)
        && t.checkExpect(this.inputIntNode3.present(5), false)
        && t.checkExpect(this.titleLeaf.present(this.b1), false)
        && t.checkExpect(this.titleNode3.present(this.b2), true)
        && t.checkExpect(this.titleNode3.present(this.b4), false)
        && t.checkExpect(this.authorLeaf.present(this.b1), false)
        && t.checkExpect(this.authorNode3.present(this.b2), true)
        && t.checkExpect(this.authorNode3.present(this.b4), false)
        && t.checkExpect(this.priceLeaf.present(this.b1), false)
        && t.checkExpect(this.priceNode3.present(this.b2), true)
        && t.checkExpect(this.priceNode3.present(this.b4), false);
  }
  
  // tests the method getLeftmost
  boolean testGetLeftMost(Tester t) {
    return t.checkException(new RuntimeException("No leftmost item of an empty tree"), 
        this.intLeaf, "getLeftmost")
        && t.checkExpect(this.intNode3.getLeftmost(), 7)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), 
            this.titleLeaf, "getLeftmost")
        && t.checkExpect(this.titleNode3.getLeftmost(), this.b1)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), 
            this.authorLeaf, "getLeftmost")
        && t.checkExpect(this.authorNode3.getLeftmost(), this.b1)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), 
            this.priceLeaf, "getLeftmost")
        && t.checkExpect(this.priceNode3.getLeftmost(), this.b3);
  }

  // tests the method getRight
  boolean testGetRight(Tester t) {
    return t.checkException(new RuntimeException("No right of an empty tree"), 
        this.intLeaf, "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), 
            this.titleLeaf, "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), 
            this.authorLeaf, "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), 
            this.priceLeaf, "getRight")
        && t.checkExpect(this.inputIntNode3.getRight(), this.rightSideInputIntNode3);
  }
  
  // tests the method isLeaf
  boolean testIsLeaf(Tester t) {
    return t.checkExpect(this.intLeaf.isLeaf(), true)
        && t.checkExpect(this.titleLeaf.isLeaf(), true)
        && t.checkExpect(this.authorNode1.isLeaf(), false)
        && t.checkExpect(this.priceNode1.isLeaf(), false);
  }
  
  // tests the method isNode
  boolean testIsNode(Tester t) {
    return t.checkExpect(this.intLeaf.isNode(), false)
        && t.checkExpect(this.titleLeaf.isNode(), false)
        && t.checkExpect(this.authorNode1.isNode(), true)
        && t.checkExpect(this.priceNode1.isNode(), true);
  }

  // tests the method sameTree
  boolean testSameTree(Tester t) {
    return t.checkExpect(this.intLeaf.sameTree(this.intLeaf), true)
        && t.checkExpect(this.intNode3.sameTree(this.intNode3), true)
        && t.checkExpect(this.intNode3.sameTree(this.inputIntNode3), false)
        && t.checkExpect(this.titleLeaf.sameTree(this.titleNode2), false)
        && t.checkExpect(this.titleNode3.sameTree(this.titleNode3), true)
        && t.checkExpect(this.titleNode3.sameTree(this.inputTitleNode2), false)
        && t.checkExpect(this.authorLeaf.sameTree(this.authorNode2), false)
        && t.checkExpect(this.authorNode3.sameTree(this.authorNode3), true)
        && t.checkExpect(this.authorNode3.sameTree(this.inputAuthorNode2), false)
        && t.checkExpect(this.priceLeaf.sameTree(this.priceNode2), false)
        && t.checkExpect(this.priceNode3.sameTree(this.priceNode3), true)
        && t.checkExpect(this.priceNode3.sameTree(this.inputPriceNode2), false);
  }
  
  // tests the method getTreeData
  boolean testGetTreeData(Tester t) {
    return t.checkExpect(intLeaf.getTreeData(), null)
        && t.checkExpect(intNode1.getTreeData(), 7)
        && t.checkExpect(titleLeaf.getTreeData(), null)
        && t.checkExpect(titleNode1.getTreeData(), this.b1)
        && t.checkExpect(authorLeaf.getTreeData(), null)
        && t.checkExpect(authorNode1.getTreeData(), this.b3)
        && t.checkExpect(priceLeaf.getTreeData(), null)
        && t.checkExpect(priceNode1.getTreeData(), this.b3);
  }
  
  // tests the method getRightHelp
  boolean testGetRightHelp(Tester t) {
    return t.checkExpect(intLeaf.getRightHelp(), null)
        && t.checkExpect(intNode1.getRightHelp(), this.intLeaf)
        && t.checkExpect(titleLeaf.getRightHelp(), null)
        && t.checkExpect(inputTitleNode3.getRightHelp(), this.inputTitleNode2)
        && t.checkExpect(authorLeaf.getRightHelp(), null)
        && t.checkExpect(authorNode3.getRightHelp(), this.authorNode2)
        && t.checkExpect(priceLeaf.getRightHelp(), null)
        && t.checkExpect(priceNode3.getRightHelp(), this.priceNode2);  
  }
  
  // tests the method getLeftHelp
  boolean testGetLeftHelp(Tester t) {
    return t.checkExpect(intLeaf.getLeftHelp(), null)
        && t.checkExpect(intNode1.getLeftHelp(), this.intLeaf)
        && t.checkExpect(titleLeaf.getLeftHelp(), null)
        && t.checkExpect(titleNode3.getLeftHelp(), this.titleNode2)
        && t.checkExpect(authorLeaf.getLeftHelp(), null)
        && t.checkExpect(inputAuthorNode4.getLeftHelp(), this.inputAuthorNode1)
        && t.checkExpect(priceLeaf.getLeftHelp(), null)
        && t.checkExpect(priceNode3.getLeftHelp(), this.priceNode1);  
  }
  
  // tests the method sameData
  boolean testSameData(Tester t) {
    return t.checkExpect(this.intLeaf.sameData(this.intLeaf), true)
        && t.checkExpect(this.intNode3.sameData(this.intNode3), true)
        && t.checkExpect(this.intNode3.sameData(this.inputIntNode3), false)
        && t.checkExpect(this.titleLeaf.sameData(this.titleNode2), false)
        && t.checkExpect(this.titleNode3.sameData(this.titleNode3), true)
        && t.checkExpect(this.titleNode1.sameData(this.inputTitleNode1), true)
        && t.checkExpect(this.authorLeaf.sameData(this.authorNode2), false)
        && t.checkExpect(this.authorNode3.sameData(this.authorNode3), true)
        && t.checkExpect(this.authorNode1.sameData(this.inputAuthorNode2), true)
        && t.checkExpect(this.priceLeaf.sameData(this.priceNode2), false)
        && t.checkExpect(this.priceNode3.sameData(this.priceNode3), true)
        && t.checkExpect(this.priceNode1.sameData(this.inputPriceNode1), true);  
  }

  // tests the method buildList
  boolean testBuildList(Tester t) {
    return t.checkExpect(this.intLeaf.buildList(), this.mtIntegerList)
        && t.checkExpect(this.inputIntNode3.buildList(), this.inputIntegerList)
        && t.checkExpect(this.titleLeaf.buildList(), this.mtBookList)
        && t.checkExpect(this.inputTitleNode4.buildList(), this.titleList)
        && t.checkExpect(this.authorLeaf.buildList(), this.mtBookList)
        && t.checkExpect(this.inputAuthorNode4.buildList(), this.authorList)
        && t.checkExpect(this.priceLeaf.buildList(), this.mtBookList)
        && t.checkExpect(this.inputPriceNode4.buildList(), this.priceList);
  }
  
  // tests the method compare
  boolean testCompare(Tester t) {
    return t.checkExpect(this.compareByInteger.compare(5, 5), 0)
        && t.checkExpect(this.compareByInteger.compare(10, 4), 6)
        && t.checkExpect(this.compareByInteger.compare(4, 8), -4)
        && t.checkExpect(this.compareByTitle.compare(this.b1, this.b1), 0)
        && t.checkExpect(this.compareByTitle.compare(this.b3, this.b1), 7)
        && t.checkExpect(this.compareByTitle.compare(this.b4, this.b3), -2)
        && t.checkExpect(this.compareByAuthor.compare(this.b2, this.b2), 0)
        && t.checkExpect(this.compareByAuthor.compare(this.b1, this.b4), 1)
        && t.checkExpect(this.compareByAuthor.compare(this.b1, this.b2), -5)
        && t.checkExpect(this.compareByPrice.compare(this.b3, this.b3), 0)
        && t.checkExpect(this.compareByPrice.compare(this.b2, this.b3), 20)
        && t.checkExpect(this.compareByPrice.compare(this.b2, this.b1), -15);
  }
}