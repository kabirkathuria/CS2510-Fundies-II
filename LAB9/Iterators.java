import tester.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// represents a ListOfLists that stores an ArrayList of ArrayList<T>
class ListOfLists<T> implements Iterable<T> {
  // ArrayList of ArrayList<T>
  ArrayList<ArrayList<T>> lol;

  // construct a ListOfLists
  ListOfLists(ArrayList<ArrayList<T>> lol) {
    this.lol = lol;
  }

  // construct an empty ListOfLists
  ListOfLists() {
    this.lol = new ArrayList<ArrayList<T>>();
  }

  // adds a new empty ArrayList<T> to the end of the list-of-lists
  void addNewList() {
    this.lol.add(new ArrayList<T>());
  }

  // adds the provided object to the end of the ArrayList<T>
  // at the provided index in the list-of-lists
  void add(int index, T object) {
    if (index < this.size()) {
      this.lol.get(index).add(object);
    }
    else {
      throw new IndexOutOfBoundsException("index is invalid");
    }
  }

  // returns the ArrayList<T> at the provided index in the list-of-lists
  ArrayList<T> get(int index) {
    if (index < this.size()) {
      return this.lol.get(index);
    }
    else {
      throw new IndexOutOfBoundsException("index is invalid");
    }
  }

  // returns the number of lists in this list-of-lists
  int size() {
    return this.lol.size();
  }

  // returns iterator for ListOfList
  @Override
  public Iterator<T> iterator() {
    return new ListOfListIterator<T>(this);
  }

}

// represents an iterator for a ListOfLists
class ListOfListIterator<T> implements Iterator<T> {
  // the list of lists that this iterator iterates over
  ListOfLists<T> items;

  // the index of the next list to be returned
  int nextIdx;

  // iterator for inner lists
  Iterator<T> iter;

  // Construct an iterator for a given ListOfLists
  ListOfListIterator(ListOfLists<T> items) {
    this.items = items;
    this.nextIdx = 0;
    if (this.items.size() != 0) {
      this.iter = this.items.get(nextIdx).iterator();
    }
    else {
      throw new NoSuchElementException("no more elements");
    }
  }

  // returns true if there are more items left in the ListOfLists
  public boolean hasNext() {
    return (this.nextIdx < this.items.size() - 1) || (this.iter.hasNext());
  }

  // returns the next item in the ListOfLists
  public T next() {
    if (this.iter.hasNext()) {
      return this.iter.next();
    }
    else if ((this.nextIdx < this.items.size() - 1) && !(this.iter.hasNext())) {
      this.nextIdx++;
      this.iter = this.items.get(nextIdx).iterator();
      return this.iter.next();
    }
    else {
      throw new NoSuchElementException("no more elements");
    }
  }

}

// examples and tests for ListsOfLists
class ExamplesLol {

  ArrayList<String> empty;
  ArrayList<ArrayList<String>> empty2;
  ListOfLists<String> emptyLol;

  ArrayList<String> one;
  ArrayList<String> two;
  ArrayList<String> three;

  ArrayList<ArrayList<String>> oneEmpty;
  ArrayList<ArrayList<String>> emptyTwo;
  ArrayList<ArrayList<String>> oneTwo;
  ArrayList<ArrayList<String>> threeEmptyOne;
  ArrayList<ArrayList<String>> oneThreeTwo;

  ListOfLists<String> oneEmptyLol;
  ListOfLists<String> emptyTwoLol;
  ListOfLists<String> oneTwoLol;
  ListOfLists<String> threeEmptyOneLol;
  ListOfLists<String> oneThreeTwoLol;
  ListOfLists<Integer> emptyIntegerLol;
  ListOfLists<Integer> twoEmptyLol;
  ListOfLists<Integer> emptyFourLol;

  ListOfListIterator<String> iterOneEmptyLol;
  ListOfListIterator<String> iterEmptyTwoLol;
  ListOfListIterator<String> iterOneTwoLol;
  ListOfListIterator<String> iterThreeEmptyOneLol;
  ListOfListIterator<String> iterOneThreeTwoLol;
  ListOfListIterator<Integer> iterTwoEmptyLol;
  ListOfListIterator<Integer> iterEmptyFourLol;

  // initialize example data
  void initData() {
    this.empty = new ArrayList<String>();
    this.empty2 = new ArrayList<ArrayList<String>>();
    this.emptyLol = new ListOfLists<String>();

    this.one = new ArrayList<String>(Arrays.asList("one"));
    this.two = new ArrayList<String>(Arrays.asList("one", "two"));
    this.three = new ArrayList<String>(Arrays.asList("one", "two", "three"));

    this.oneEmpty = new ArrayList<ArrayList<String>>(Arrays.asList(this.one, this.empty));
    this.emptyTwo = new ArrayList<ArrayList<String>>(Arrays.asList(this.empty, this.two));
    this.oneTwo = new ArrayList<ArrayList<String>>(Arrays.asList(this.one, this.two));
    this.threeEmptyOne = new ArrayList<ArrayList<String>>(Arrays.asList(this.three, this.empty,
        this.one));
    this.oneThreeTwo = new ArrayList<ArrayList<String>>(Arrays.asList(this.one, this.three,
        this.two));

    this.oneEmptyLol = new ListOfLists<String>(this.oneEmpty);
    this.emptyTwoLol = new ListOfLists<String>(this.emptyTwo);
    this.oneTwoLol = new ListOfLists<String>(this.oneTwo);
    this.threeEmptyOneLol = new ListOfLists<String>(this.threeEmptyOne);
    this.oneThreeTwoLol = new ListOfLists<String>(this.oneThreeTwo);
    this.emptyIntegerLol = new ListOfLists<Integer>();
    this.twoEmptyLol = new ListOfLists<Integer>(new ArrayList<ArrayList<Integer>>(
        Arrays.asList(new ArrayList<Integer>(Arrays.asList(1, 2)), new ArrayList<Integer>(
            Arrays.asList()))));
    this.emptyFourLol = new ListOfLists<Integer>(new ArrayList<ArrayList<Integer>>(
        Arrays.asList(new ArrayList<Integer>(Arrays.asList()), new ArrayList<Integer>(
            Arrays.asList(1, 2, 3, 4)))));

    this.iterOneEmptyLol = new ListOfListIterator<String>(this.oneEmptyLol);
    this.iterEmptyTwoLol = new ListOfListIterator<String>(this.emptyTwoLol);
    this.iterOneTwoLol = new ListOfListIterator<String>(this.oneTwoLol);
    this.iterThreeEmptyOneLol = new ListOfListIterator<String>(this.threeEmptyOneLol);
    this.iterOneThreeTwoLol = new ListOfListIterator<String>(this.oneThreeTwoLol);
    this.iterTwoEmptyLol = new ListOfListIterator<Integer>(this.twoEmptyLol);
    this.iterEmptyFourLol = new ListOfListIterator<Integer>(this.emptyFourLol);


  }


  // tests for addNewList() in ListOfLists
  void testAddNewList(Tester t) {
    this.initData();

    t.checkExpect(this.emptyLol, new ListOfLists<String>(this.empty2));
    t.checkExpect(this.oneEmptyLol, new ListOfLists<String>(this.oneEmpty));
    t.checkExpect(this.emptyTwoLol, new ListOfLists<String>(this.emptyTwo));
    t.checkExpect(this.oneTwoLol, new ListOfLists<String>(this.oneTwo));
    t.checkExpect(this.threeEmptyOneLol, new ListOfLists<String>(this.threeEmptyOne));
    t.checkExpect(this.oneThreeTwoLol, new ListOfLists<String>(this.oneThreeTwo));

    this.emptyLol.addNewList();
    this.oneEmptyLol.addNewList();
    this.emptyTwoLol.addNewList();
    this.oneTwoLol.addNewList();
    this.threeEmptyOneLol.addNewList();
    this.oneThreeTwoLol.addNewList();

    t.checkExpect(this.emptyLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.empty))));
    t.checkExpect(this.oneEmptyLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.one, this.empty, this.empty))));
    t.checkExpect(this.emptyTwoLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.empty, this.two, this.empty))));
    t.checkExpect(this.oneTwoLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.one, this.two, this.empty))));
    t.checkExpect(this.threeEmptyOneLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.three, this.empty, this.one, this.empty))));
    t.checkExpect(this.oneThreeTwoLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.one, this.three, this.two, this.empty))));
  }

  // tests for add(int, T) in ListOfLists
  void testAdd(Tester t) {
    this.initData();

    t.checkException(new IndexOutOfBoundsException("index is invalid"), this.emptyLol, "add", 0,
        "hello");
    t.checkException(new IndexOutOfBoundsException("index is invalid"), this.oneThreeTwoLol, "add",
        10, "hey");

    this.emptyLol.addNewList();
    this.emptyLol.add(0, "goodbye");
    t.checkExpect(this.emptyLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(new ArrayList<String>(Arrays.asList("goodbye"))))));

    this.oneEmptyLol.add(1, ":)");
    t.checkExpect(this.oneEmptyLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(this.one, new ArrayList<String>(Arrays.asList(":)"))))));

    this.initData();
    this.emptyTwoLol.add(0, "hi");
    t.checkExpect(this.emptyTwoLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(new ArrayList<String>(Arrays.asList("hi")), this.two))));

    this.initData();
    this.threeEmptyOneLol.add(0, "wow");
    t.checkExpect(this.threeEmptyOneLol, new ListOfLists<String>(new ArrayList<ArrayList<String>>(
        Arrays.asList(new ArrayList<String>(Arrays.asList("one", "two", "three", "wow")),
            this.empty, this.one))));
  }

  // tests for get(int) in ListOfLists
  void testGet(Tester t) {
    this.initData();

    t.checkException(new IndexOutOfBoundsException("index is invalid"), this.emptyLol, "get", 0);
    this.emptyLol.addNewList();
    t.checkExpect(this.emptyLol.get(0), this.empty);

    t.checkExpect(this.oneEmptyLol.get(1), this.empty);
    t.checkExpect(this.emptyTwoLol.get(1), this.two);
    t.checkException(new IndexOutOfBoundsException("index is invalid"), this.oneThreeTwoLol, "get",
        17);

    t.checkException(new IndexOutOfBoundsException("index is invalid"), this.emptyIntegerLol, "get",
        0);
    this.emptyIntegerLol.addNewList();
    t.checkExpect(this.emptyIntegerLol.get(0), new ArrayList<Integer>());

    t.checkExpect(this.twoEmptyLol.get(1), new ArrayList<Integer>());
    t.checkExpect(this.emptyFourLol.get(1), new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
    t.checkException(new IndexOutOfBoundsException("index is invalid"), this.emptyFourLol, "get",
        17);
  }

  // tests for size() in ListOfLists
  void testSize(Tester t) {
    this.initData();

    t.checkExpect(this.emptyLol.size(), 0);
    this.emptyLol.addNewList();
    t.checkExpect(this.emptyLol.size(), 1);
    t.checkExpect(this.oneEmptyLol.size(), 2);
    t.checkExpect(this.emptyTwoLol.size(), 2);
    t.checkExpect(this.threeEmptyOneLol.size(), 3);
    t.checkExpect(this.oneThreeTwoLol.size(), 3);
    t.checkExpect(this.emptyIntegerLol.size(), 0);
    this.emptyIntegerLol.addNewList();
    t.checkExpect(this.emptyIntegerLol.size(), 1);
    t.checkExpect(this.twoEmptyLol.size(), 2);
    t.checkExpect(this.emptyFourLol.size(), 2);
  }

  // tests for constructor exception in ListOfListIterator
  void testListOfListIteratorConstructorException(Tester t) {
    this.initData();

    t.checkConstructorException(new NoSuchElementException("no more elements"),
        "ListOfListIterator", this.emptyLol);
    t.checkConstructorException(new NoSuchElementException("no more elements"),
        "ListOfListIterator", this.emptyIntegerLol);

  }

  // tests for hasNext() in ListOfListIterator
  void testHasNext(Tester t) {
    this.initData();

    t.checkExpect(this.iterTwoEmptyLol.hasNext(), true);
    this.iterTwoEmptyLol.nextIdx++;
    this.iterTwoEmptyLol.iter =
        this.iterTwoEmptyLol.items.get(this.iterTwoEmptyLol.nextIdx).iterator();
    t.checkExpect(this.iterTwoEmptyLol.hasNext(), false);

    t.checkExpect(this.iterOneEmptyLol.hasNext(), true);
    this.iterOneEmptyLol.nextIdx++;
    this.iterOneEmptyLol.iter =
        this.iterOneEmptyLol.items.get(this.iterOneEmptyLol.nextIdx).iterator();
    t.checkExpect(this.iterOneEmptyLol.hasNext(), false);

    t.checkExpect(this.iterEmptyTwoLol.hasNext(), true);
    this.iterEmptyTwoLol.nextIdx++;
    this.iterEmptyTwoLol.iter =
        this.iterEmptyTwoLol.items.get(this.iterEmptyTwoLol.nextIdx).iterator();
    t.checkExpect(this.iterEmptyTwoLol.hasNext(), true);

    t.checkExpect(this.iterOneTwoLol.hasNext(), true);
    this.iterOneTwoLol.nextIdx++;
    this.iterOneTwoLol.iter =
        this.iterOneTwoLol.items.get(this.iterOneTwoLol.nextIdx).iterator();
    t.checkExpect(this.iterOneTwoLol.hasNext(), true);

    t.checkExpect(this.iterThreeEmptyOneLol.hasNext(), true);
    this.iterThreeEmptyOneLol.nextIdx++;
    this.iterThreeEmptyOneLol.iter =
        this.iterThreeEmptyOneLol.items.get(this.iterThreeEmptyOneLol.nextIdx).iterator();
    t.checkExpect(this.iterThreeEmptyOneLol.hasNext(), true);
    this.iterThreeEmptyOneLol.nextIdx++;
    this.iterThreeEmptyOneLol.iter =
        this.iterThreeEmptyOneLol.items.get(this.iterThreeEmptyOneLol.nextIdx).iterator();
    t.checkExpect(this.iterThreeEmptyOneLol.hasNext(), true);

  }

  // tests for next() in ListOfListIterator
  void testNext(Tester t) {
    this.initData();

    t.checkExpect(this.iterTwoEmptyLol.next(), 1);
    this.iterTwoEmptyLol.nextIdx++;
    this.iterTwoEmptyLol.iter =
        this.iterTwoEmptyLol.items.get(this.iterTwoEmptyLol.nextIdx).iterator();
    t.checkException(new NoSuchElementException("no more elements"), this.iterTwoEmptyLol, "next");

    t.checkExpect(this.iterOneEmptyLol.next(), "one");
    this.iterOneEmptyLol.nextIdx++;
    this.iterOneEmptyLol.iter =
        this.iterOneEmptyLol.items.get(this.iterOneEmptyLol.nextIdx).iterator();
    t.checkException(new NoSuchElementException("no more elements"), this.iterOneEmptyLol, "next");

    t.checkExpect(this.iterEmptyTwoLol.next(), "one");
    t.checkExpect(this.iterEmptyTwoLol.next(), "two");
    t.checkException(new NoSuchElementException("no more elements"), this.iterEmptyTwoLol, "next");

    t.checkExpect(this.iterOneTwoLol.next(), "one");
    t.checkExpect(this.iterOneTwoLol.next(), "one");
    t.checkExpect(this.iterOneTwoLol.next(), "two");
    t.checkException(new NoSuchElementException("no more elements"), this.iterOneTwoLol, "next");


    t.checkExpect(this.iterThreeEmptyOneLol.next(), "one");
    t.checkExpect(this.iterThreeEmptyOneLol.next(), "two");
    t.checkExpect(this.iterThreeEmptyOneLol.next(), "three");
    t.checkExpect(this.iterThreeEmptyOneLol.next(), "one");
    t.checkException(new NoSuchElementException("no more elements"), this.iterThreeEmptyOneLol,
        "next");


    t.checkExpect(this.iterOneThreeTwoLol.next(), "one");
    t.checkExpect(this.iterOneThreeTwoLol.next(), "one");
    t.checkExpect(this.iterOneThreeTwoLol.next(), "two");
    t.checkExpect(this.iterOneThreeTwoLol.next(), "three");
    t.checkExpect(this.iterOneThreeTwoLol.next(), "one");
    t.checkExpect(this.iterOneThreeTwoLol.next(), "two");
    t.checkException(new NoSuchElementException("no more elements"), this.iterOneThreeTwoLol,
        "next");


  }

  // tests for ListsOfStrings iterator
  void testListOfListsOfStringsIterator(Tester t) {
    ListOfLists<String> lol = new ListOfLists<String>();
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();

    //add elements "wow","wow!","wow!!" in first list
    lol.add(0, "wow");
    lol.add(0, "wow!");
    lol.add(0, "wow!!");

    //add elements "wow!!!","wow!!!!","wow!!!!!" in second list
    lol.add(1, "wow!!!");
    lol.add(1, "wow!!!!");
    lol.add(1, "wow!!!!!");

    //add elements "wow!!!!!!","wow!!!!!!!","wow!!!!!!!!" in third list
    lol.add(2, "wow!!!!!!");
    lol.add(2, "wow!!!!!!!");
    lol.add(2, "wow!!!!!!!!");

    //iterator should return elements in order
    // "wow","wow!","wow!!","wow!!!","wow!!!!","wow!!!!!","wow!!!!!!","wow!!!!!!!","wow!!!!!!!!"
    String s = "wow";
    for (String str : lol) {
      t.checkExpect(str, s);
      s = s + "!";
    }
  }

  // tests for ListsOfIntegers iterator
  void testListOfListsOfIntegersIterator(Tester t) {
    ListOfLists<Integer> lol = new ListOfLists<Integer>();
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();

    //add elements 1,2,3 in first list
    lol.add(0, 1);
    lol.add(0, 2);
    lol.add(0, 3);

    //add elements 4,5,6 in second list
    lol.add(1, 4);
    lol.add(1, 5);
    lol.add(1, 6);

    //add elements 7,8,9 in third list
    lol.add(2, 7);
    lol.add(2, 8);
    lol.add(2, 9);

    //iterator should return elements in order 1,2,3,4,5,6,7,8,9
    int number = 1;
    for (Integer num : lol) {
      t.checkExpect(num, number);
      number = number + 1;
    }
  }
}