
// Represents a credit line account
class Credit extends Account {

  int creditLine;  // Maximum amount accessible
  double interest; // The interest rate charged
    
  Credit(int accountNum, int balance, String name, int creditLine, double interest) {
    super(accountNum, balance, name);
    this.creditLine = creditLine;
    this.interest = interest;
  }
}
