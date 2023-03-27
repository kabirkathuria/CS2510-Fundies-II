
// Represents a checking account
class Checking extends Account {

  int minimum; // The minimum account balance allowed

  Checking(int accountNum, int balance, String name, int minimum) {
    super(accountNum, balance, name);
    this.minimum = minimum;
  }
  
}
