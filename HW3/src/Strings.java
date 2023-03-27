// CS 2510, Assignment 3

import tester.Tester;

// to represent a list of Strings
interface ILoString {

  // combine all Strings in this list into one
  String combine();

  // produce a new list, sorted in alphabetical order in a case-insensitive way
  ILoString sort();

  // compare two strings and make a new list with correct order 
  ILoString compare(String s);

  // determines whether this list is sorted in alphabetical order 
  boolean isSorted();

  // compute whether each string is sorted in alphabetical order 
  boolean isSortedHelp(String s);

  // take this list of string and given list of string, 
  // and produce a list where the first, third, fifth... elements are from this list, 
  // and the second, fourth, sixth... elements are from the given list 
  ILoString interleave(ILoString los);

  // produce a new list with this list of string and given list of string 
  // with correct order 
  ILoString interleaveHelp(ILoString los);

  // produce a sorted list of Strings that contains all items in both original lists,
  // including duplicates in case-insensitive way
  ILoString merge(ILoString los);

  // check the order in a new list of string 
  boolean mergeHelp(String s);

  // produce a new list of strings containing the same elements as this list of strings
  // in reverse order 
  ILoString reverse();

  // make reverse order for a new list of strings 
  ILoString reverseHelp(String s);

  // determine if this list contains pairs of identical strings
  boolean isDoubledList();

  // check whether two strings are identical 
  boolean doubledListChecker(String s);

  // determine whether this list contains the same words reading the list in each order 
  boolean isPalindromeList();
}

// to represent an empty list of Strings
class MtLoString implements ILoString {

  MtLoString(){} 

  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  // to return sorted list of string 
  public ILoString sort() {
    return new MtLoString();
  }

  // to compare two strings and return new sorted list 
  public ILoString compare(String s) {
    return new ConsLoString(s, this);
  }

  // to compute whether this list is sorted or not 
  public boolean isSorted() {
    return true;
  }

  // to compute whether this string and that string is in correct order  
  public boolean isSortedHelp(String s) {
    return true;
  }

  // to return a new list of string that contains two list of strings 
  // in correct order 
  public ILoString interleave(ILoString los) {
    return los;
  }

  // to make a correctly ordered list 
  public ILoString interleaveHelp(ILoString los) {
    return los;
  }

  // to return a sorted list that contains this list of string 
  // and that list of string 
  public ILoString merge(ILoString los) {
    return los;
  }

  // to compare this string and that string  
  public boolean mergeHelp(String s) {
    return true;
  }

  // to return a new list of string in reverse order  
  public ILoString reverse() {
    return this;
  }

  // to compare a two strings and compute a new list of string
  // in reverse order 
  public ILoString reverseHelp(String s) {
    return new ConsLoString(s, this);
  }

  // to check whether the list of string contains pair of identical strings or not 
  public boolean isDoubledList() {
    return true;
  }

  // to check whether this string and that string is identical 
  public boolean doubledListChecker(String s) {
    return false;
  }

  // to check whether this list contains the same words reading 
  // in the list in either order
  public boolean isPalindromeList() {
    return true;
  }
}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;  
  }

  /*
  TEMPLATE
  FIELDS:
  ... this.first ...                              -- String
  ... this.rest ...                               -- ILoString

  METHODS
  ... this.combine() ...                          -- String
  ... this.sort() ...                             -- String 
  ... this.compare(String s) ...                  -- String 
  ... this.isSorted() ...                         -- String 
  ... this.isSortedHelp(String s) ...             -- boolean 
  ... this.interleave(IloString los) ...            -- ILoString 
  ... this.interleaveHelp(ILoString los) ...        -- ILoString 
  ... this.merge(ILoString los) ...                 -- ILoString
  ... this.mergeHelp(String s) ...                  -- boolean
  ... this.reverse() ...                            -- ILoString 
  ... this.reverseHelp(String s) ...                -- ILoString 
  ... this.isDoubledList() ...                      -- boolean 
  ... this.doubledListChecker(String s) ...         -- boolean 
  ... this.isPalindromeList() ...                   -- boolean

  METHODS FOR FIELDS
  ... this.first.concat(String) ...                            -- String
  ... this.rest.sort().compare(String) ...                     -- ILoString 
  ... this.first.toLowerCase().compareTo(s.toLowerCase()) ...  -- boolean
  ... this.rest.isSortedHelp(String) ...                       -- boolean 
  ... los.interleaveHelp(this.rest) ...                        -- ILoString 
  ... this.rest.reverse().reverseHelp(String) ...              -- ILoString 
  ... this.rest.doubledListChecker(String) ...                 -- boolean 
  ... this.interleave(this.reverse()).isDoubledList()...       -- boolean 
  */

  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }
  
  // produce a new list, sorted in alphabetical order in a case-insensitive way
  public ILoString sort() {
    return this.rest.sort().compare(this.first);
  }

  // compare two strings and make a new list with correct order
  public ILoString compare(String s) {
    if (this.first.toLowerCase().compareTo(s.toLowerCase()) < 0) {
      return new ConsLoString(this.first, this.rest.compare(s));
    }
    else {
      return new ConsLoString(s, this);
    }
  }

  // determines whether this list is sorted in alphabetical order 
  public boolean isSorted() {
    return this.rest.isSortedHelp(this.first);
  }

  // compute whether each string is sorted in alphabetical order
  public boolean isSortedHelp(String s) {
    return this.first.toLowerCase().compareTo(s.toLowerCase()) >= 0
        && this.rest.isSortedHelp(this.first);
  }

  // take this list of string and given list of string, 
  // and produce a list where the first, third, fifth... elements are from this list, 
  // and the second, fourth, sixth... elements are from the given list
  public ILoString interleave(ILoString los) {
    return new ConsLoString(this.first, los.interleaveHelp(this.rest));
  }

  // produce a new list with this list of string and given list of string 
  // with correct order
  public ILoString interleaveHelp(ILoString los) {
    return new ConsLoString(this.first, los.interleave(this.rest));
  }

  // produce a sorted list of Strings that contains all items in both original lists,
  // including duplicates in case-insensitive way
  public ILoString merge(ILoString los) {
    if (los.mergeHelp(this.first)) {
      return los.merge(this);
    }
    else {
      return new ConsLoString(this.first, this.rest.merge(los)); 
    }
  }

  // check the order in a new list of string
  public boolean mergeHelp(String s) {
    return this.first.toLowerCase().compareTo(s.toLowerCase()) < 0;
  }

  // produce a new list of strings containing the same elements as this list of strings
  // in reverse order 
  public ILoString reverse() {
    return this.rest.reverse().reverseHelp(this.first);
  }

  // to compare a two strings and compute a new list of string
  // in reverse order
  public ILoString reverseHelp(String s) {
    return new ConsLoString(this.first, this.rest.reverseHelp(s));   
  }

  // determine if this list contains pairs of identical strings
  public boolean isDoubledList() {
    return this.rest.doubledListChecker(this.first);
  }
  
  // check whether two strings are identical
  public boolean doubledListChecker(String s) {
    return this.first == s 
        && this.rest.isDoubledList(); 
  }

  //to check whether this list contains the same words reading 
  // in the list in either order 
  public boolean isPalindromeList() {
    return this.interleave(this.reverse()).isDoubledList();
  }
}

// to represent examples for lists of strings
class ExamplesStrings {

  ILoString sortingA = new ConsLoString("a", new ConsLoString("b", 
        new ConsLoString("c", new ConsLoString("d",
          new ConsLoString("e", new ConsLoString("d", new ConsLoString("c", 
             new ConsLoString("b", new ConsLoString("a", new MtLoString())))))))));

  ILoString mary = new ConsLoString("Mary ",
                     new ConsLoString("had ",
                        new ConsLoString("a ",
                            new ConsLoString("little ", 
                                new ConsLoString("lamb.", new MtLoString())))));

  ILoString a = new ConsLoString("a",
          new ConsLoString("b",
            new ConsLoString("c", new MtLoString())));

  ILoString b = new ConsLoString("d",
          new ConsLoString("e",
              new ConsLoString("f", new MtLoString())));

  ILoString sortedA = new ConsLoString("a", new ConsLoString("b", 
        new ConsLoString("a", new ConsLoString("b", new MtLoString()))));

  ILoString sortedB = new ConsLoString("All ", new ConsLoString("any ", 
        new ConsLoString("before ", new ConsLoString("Broken", new MtLoString()))));
  ILoString mergeA = new ConsLoString("a",
         new ConsLoString("b",
             new ConsLoString("b",
                 new ConsLoString("f", new ConsLoString("i", new MtLoString())))));

  ILoString mergeB = new ConsLoString("c",
         new ConsLoString("c",
           new ConsLoString("d", new ConsLoString("d", 
             new ConsLoString("g", new MtLoString()))))); 
  ILoString mergeC = new ConsLoString("a", new ConsLoString("b", new MtLoString()));
  ILoString mergeD = new ConsLoString("a", new ConsLoString("b", new MtLoString()));
  ILoString doubledA = new ConsLoString("a", new ConsLoString("a", 
        new ConsLoString("b", new ConsLoString("b", new MtLoString()))));
  ILoString notdoubledB = new ConsLoString("a", 
        new ConsLoString("a", new ConsLoString("a", new MtLoString())));
  ILoString palindromeA = new ConsLoString("red", 
        new ConsLoString("blue", new ConsLoString("purple", 
          new ConsLoString("blue", new ConsLoString("red", new MtLoString())))));

  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) { 
    return 
      t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }
      
  // test the method sort for the lists of Strings
  boolean testSort(Tester t) {
    return 
      t.checkExpect(this.mary.sort(), new ConsLoString("a ", 
        new ConsLoString("had ", new ConsLoString("lamb.", 
            new ConsLoString("little ", new ConsLoString("Mary ", new MtLoString()))))))
      && t.checkExpect(this.sortingA.sort(), new ConsLoString("a",
        new ConsLoString("a", new ConsLoString("b", new ConsLoString("b",
          new ConsLoString("c", new ConsLoString("c", new ConsLoString("d", 
            new ConsLoString("d", new ConsLoString("e", new MtLoString()))))))))));
  }
  
  // test the method isSorted for the list of Strings 
  boolean testIsSorted(Tester t) {
    return
      t.checkExpect(this.mary.isSorted(), false)
      && t.checkExpect(this.sortedB.isSorted(), true);
  }
  
  // test the method interleave for the list of Strings
  boolean testInterleave(Tester t) {
    return 
      t.checkExpect(this.a.interleave(b), new ConsLoString("a",
         new ConsLoString("d", new ConsLoString("b", 
             new ConsLoString("e", new ConsLoString("c", 
               new ConsLoString("f", new MtLoString())))))));
  }
  
  // test the method merge for the list of Strings
  boolean testMerge(Tester t) {
    return
      t.checkExpect(this.mergeA.merge(mergeB), new ConsLoString("a", new ConsLoString("b", 
          new ConsLoString("b", new ConsLoString("c", new ConsLoString("c", new ConsLoString("d",
             new ConsLoString("d", new ConsLoString("f", new ConsLoString("g", 
               new ConsLoString("i", new MtLoString())))))))))))
      && t.checkExpect(this.mergeC.merge(mergeD), new ConsLoString("a", 
          new ConsLoString("a", new ConsLoString("b", new ConsLoString("b", new MtLoString())))));
  }
  
  // test the method reverse for the list of Strings 
  boolean testReverse(Tester t) {
    return
      t.checkExpect(this.a.reverse(), new ConsLoString("c", 
          new ConsLoString("b", new ConsLoString("a", new MtLoString()))));
  }
  
  // test the method isDoubledList for the list of Strings 
  boolean testIsDoubledList(Tester t) {
    return
      t.checkExpect(doubledA.isDoubledList(), true)
      && t.checkExpect(notdoubledB.isDoubledList(), false);
  }
  
  // test the method isPalindromeList for the list of Strings
  boolean testIsPalindromeList(Tester t) {
    return
       t.checkExpect(palindromeA.isPalindromeList(), true);   
  }
}