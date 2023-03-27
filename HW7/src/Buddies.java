import tester.*;

// represents a list of Person's buddies
interface ILoBuddy {

  // determines whether a list of Person's buddies includes the inputted person
  boolean includes(Person person);

  // counts the common buddies between Person's list of buddies
  // and the inputted list of buddies
  int countCB(ILoBuddy buddies);

  // determines whether Person's list of buddies has given person 
  // as an extended buddy
  boolean extendedBuddy(Person person, ILoBuddy buddies);
  
  // returns the list of people invited to the party by Person
  ILoBuddy partyList(ILoBuddy invBuddies);
  
  // returns the list length
  int listLength();
}


//represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {

  MTLoBuddy() {}

  // determines whether an empty list of Person's buddies includes the inputted person
  public boolean includes(Person person) {
    return false;
  }

  // counts the common buddies between Person's empty list of buddies
  // and the inputted list of buddies
  public int countCB(ILoBuddy buddies) {
    return 0;
  }
  
  // determines whether Person's empty list of buddies has given person 
  // as an extended buddy
  public boolean extendedBuddy(Person person, ILoBuddy buddies) {
    return false;
  }
  
  // returns the list of people invited to the party by Person
  // with empty list of buddies
  public ILoBuddy partyList(ILoBuddy invBuddies) {
    return invBuddies;
  }
  
  // returns the empty list length
  public int listLength() {
    return 0;
  }
}


// represents a non-empty list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

  // determines whether a non-empty list of Person's buddies includes the inputted person
  public boolean includes(Person person) {
    return this.first.equals(person) || this.rest.includes(person);
  }

  // counts the common buddies between Person's non-empty list of buddies
  // and the inputted list of buddies 
  public int countCB(ILoBuddy buddies) {
    if (buddies.includes(this.first)) {
      return 1 + this.rest.countCB(buddies);
    }
    else {
      return this.rest.countCB(buddies);
    }
  }

  // determines whether Person's non-empty list of buddies has given person 
  // as an extended buddy
  public boolean extendedBuddy(Person person, ILoBuddy buddies) {
    if (!(buddies.includes(this.first))) {
      return this.first.buddies.extendedBuddy(person, new ConsLoBuddy(this.first, buddies))
          || this.rest.extendedBuddy(person, new ConsLoBuddy(this.first, buddies))
          || this.first.hasDirectBuddy(person);
    }
    else {
      return this.rest.extendedBuddy(person, buddies);
    }
  }
  
  // returns the list of people invited to the party by Person
  // with non-empty list of buddies
  public ILoBuddy partyList(ILoBuddy invBuddies) {
    if (invBuddies.includes(this.first)) {
      return this.rest.partyList(invBuddies);
    }
    else {
      return this.rest.partyList(this.first.buddies.partyList(
          new ConsLoBuddy(this.first, invBuddies)));
    }
  }
  
  // returns the non-empty list length
  public int listLength() {
    return 1 + this.rest.listLength();
  }
}


// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.buddies.includes(that);
  }

  // returns the number of people that are direct buddies 
  // of both this and that person
  int countCommonBuddies(Person that) {
    return this.buddies.countCB(that.buddies);
  }

  // will the given person be (directly or indirectly) 
  // invited to a party organized by this person?
  boolean hasExtendedBuddy(Person that) {
    return this.hasDirectBuddy(that) || this.buddies.extendedBuddy(that, new MTLoBuddy());
  }
  
  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    return this.buddies.partyList(new ConsLoBuddy(this, new MTLoBuddy())).listLength();
  }

  // EFFECT:
  // Change this person's buddy list so that it includes the given person 
  void addBuddy(Person that) {
    this.buddies = new ConsLoBuddy(that, this.buddies);
  }
}


// represents examples and tests for buddies
class ExamplesBuddies {

  Person ann = new Person("Ann");
  Person bob = new Person("Bob");
  Person cole = new Person("Cole");
  Person dan = new Person("Dan");
  Person ed = new Person("Ed");
  Person fay = new Person("Fay");
  Person gabi = new Person("Gabi");
  Person hank = new Person("Hank");
  Person jan = new Person("Jan");
  Person kim = new Person("Kim");
  Person len = new Person("Len");

  void initBuddies() {
    
    this.ann.buddies = new MTLoBuddy();
    this.bob.buddies = new MTLoBuddy();
    this.cole.buddies = new MTLoBuddy();
    this.dan.buddies = new MTLoBuddy();
    this.ed.buddies = new MTLoBuddy();
    this.fay.buddies = new MTLoBuddy();
    this.gabi.buddies = new MTLoBuddy();
    this.hank.buddies = new MTLoBuddy();
    this.jan.buddies = new MTLoBuddy();
    this.kim.buddies = new MTLoBuddy();
    this.len.buddies = new MTLoBuddy();
    
    this.ann.addBuddy(this.bob);
    this.ann.addBuddy(this.cole);
    this.bob.addBuddy(this.ann);
    this.bob.addBuddy(this.ed);
    this.bob.addBuddy(this.hank);
    this.cole.addBuddy(this.dan);
    this.dan.addBuddy(this.cole);
    this.ed.addBuddy(this.fay);
    this.fay.addBuddy(this.ed);
    this.fay.addBuddy(this.gabi);
    this.gabi.addBuddy(this.ed);
    this.gabi.addBuddy(this.fay);
    this.jan.addBuddy(this.kim);
    this.jan.addBuddy(this.len);
    this.kim.addBuddy(this.jan);
    this.kim.addBuddy(this.len);
    this.len.addBuddy(this.jan);
    this.len.addBuddy(this.kim);
  }

  // tests the hasDirectBuddy method
  void testHasDirectBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.bob.hasDirectBuddy(this.ann), true);
    t.checkExpect(this.gabi.hasDirectBuddy(this.ann), false);
    t.checkExpect(this.cole.hasDirectBuddy(this.dan), true);
    t.checkExpect(this.jan.hasDirectBuddy(this.gabi), false);
  }

  // tests the countCommonBuddies method
  void testCountCommonBuddies(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.countCommonBuddies(this.hank), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.dan), 1);
    t.checkExpect(this.cole.countCommonBuddies(this.kim), 0);
  }

  // tests the hasExtendedBuddy method
  void testHasExtendedBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddy(this.hank), true);
    t.checkExpect(this.fay.hasExtendedBuddy(this.kim), false);
    t.checkExpect(this.bob.hasExtendedBuddy(this.fay), true);
    t.checkExpect(this.cole.hasExtendedBuddy(this.len), false);
  }

  // tests the partyCount method
  void testPartyCount(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.partyCount(), 8);
    t.checkExpect(this.ed.partyCount(), 3);
    t.checkExpect(this.hank.partyCount(), 1);
    t.checkExpect(this.gabi.partyCount(), 3);
  }

  // tests the addBuddy method
  void testAddBuddy(Tester t) {
    initBuddies();
    this.hank.addBuddy(this.gabi);
    this.fay.addBuddy(this.bob);
    t.checkExpect(this.hank.buddies, 
        new ConsLoBuddy(this.gabi, 
            new MTLoBuddy()));
    t.checkExpect(this.fay.buddies, 
        new ConsLoBuddy(this.bob, 
            new ConsLoBuddy(this.gabi, 
                new ConsLoBuddy(this.ed, 
                    new MTLoBuddy()))));
  }

  
  // tests the includes method
  void testIncludes(Tester t) {
    initBuddies();
    t.checkExpect(this.bob.buddies.includes(this.ann), true);
    t.checkExpect(this.gabi.buddies.includes(this.ann), false);
    t.checkExpect(this.cole.buddies.includes(this.dan), true);
    t.checkExpect(this.jan.buddies.includes(this.gabi), false);
  }

  // tests the countCB method
  void testCountCB(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.buddies.countCB(this.hank.buddies), 0);
    t.checkExpect(this.ann.buddies.countCB(this.dan.buddies), 1);
    t.checkExpect(this.cole.buddies.countCB(this.kim.buddies), 0);
  }

  // tests the extendedBuddy method
  void testExtendedBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.buddies.extendedBuddy(this.hank, new MTLoBuddy()), true);
    t.checkExpect(this.fay.buddies.extendedBuddy(this.kim, new MTLoBuddy()), false);
    t.checkExpect(this.bob.buddies.extendedBuddy(this.fay, this.ann.buddies), true);
    t.checkExpect(this.cole.buddies.extendedBuddy(this.len, this.bob.buddies), false);
  }

  // tests the partyList method
  void testPartyList(Tester t) {
    initBuddies();
    t.checkExpect(this.cole.buddies.partyList(new MTLoBuddy()), 
        new ConsLoBuddy(this.cole, 
            new ConsLoBuddy(this.dan, 
                new MTLoBuddy())));
    t.checkExpect(this.hank.buddies.partyList(new MTLoBuddy()), new MTLoBuddy());
  }
  
  // tests the listLength method
  void testListLength(Tester t) {
    initBuddies();
    t.checkExpect(this.hank.buddies.listLength(), 0);
    t.checkExpect(this.cole.buddies.listLength(), 1);
    t.checkExpect(this.ann.buddies.listLength(), 2);
  }
}