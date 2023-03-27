import tester.Tester;

// to represent the recipe of Bagel 
class BagelRecipe {
  
  // represents the weight of the flour
  double flour;
  
  // represents the weight of the water
  double water;
  
  // represents the weight of the yeast
  double yeast;
  
  // represents the weight of the salt
  double salt;
  
  // represents the weight of the malt
  double malt;

  // constructor that takes in all of the fields in weight 
  // and computes perfect bagel recipe 
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    this.flour = new Utility().checkRecipe(flour, water, 
        "The weight of the flour is not equal to the weight of the water");
    this.water = new Utility().checkRecipe(water, flour, 
        "The weight of the water is not equal to the weight of the flour");
    this.yeast = new Utility().checkRecipe(yeast, malt, 
        "The weight of the yeast is not equal to the weight of the malt");
    if (Math.abs(salt - (flour / 20 - yeast)) <= 0.001) {
      this.salt = salt;
    }
    else {
      throw new IllegalArgumentException("The weight of the salt is " + Double.toString(salt) 
          + ", and combined with the weight of the yeast, it is not equal to "
          + "1/20th of the weight of the flour");
    }
    this.malt = new Utility().checkRecipe(malt, yeast, 
        "The weight of the malt is not equal to the weight of the yeast");
  }

  // constructor that only takes the weight of flour and yeast
  // and produce perfect bagel recipe 
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, (flour / 20.0) - yeast, yeast);
  }

  // constructor that only takes the volume of flour, yeast, and salt
  // and produce perfect bagel recipe 
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 4.25, flour * 4.25, yeast / 48.0 * 5.0, salt / 48.0 * 10.0 , yeast / 48.0 * 5.0);
  }

  /* TEMPLATE:
  * 
  * FIELDS:
  * ... this.flour ... -- double 
  * ... this.water ... -- double
  * ... this.yeast ... -- double 
  * ... this.salt ...  -- double 
  * ... this.malt ...  -- double
  * 
  * METHODS:
  * ... this.sameRecipe(BagelRecipe other) ... -- boolean
  * 
  * METHODS OF FIELD:
  * ... Math.abs(this.flour - other.flour) <= 0.001 ... -- boolean 
  * ... Math.abs(this.water - other.water) <= 0.001 ... -- boolean
  * ... Math.abs(this.yeast - other.yeast) <= 0.001 ... -- boolean
  * ... Math.abs(this.salt - other.salt) <= 0.001 ... -- boolean
  * ... Math.abs(this.malt - other.malt) <= 0.001 ... -- boolean
  * 
  * METHODS OF PARAMETERS:
  * ... other.flour ... -- double 
  * ... other.water ... -- double 
  * ... other.yeast ... -- double 
  * ... other.salt ... -- double 
  * ... other.malt ... -- double 
  */

  // computes if two bagels use the same recipes
  boolean sameRecipe(BagelRecipe other) {
    return Math.abs(this.flour - other.flour) <= 0.001
        && Math.abs(this.water - other.water) <= 0.001
        && Math.abs(this.yeast - other.yeast) <= 0.001 
        && Math.abs(this.salt - other.salt) <= 0.001
        && Math.abs(this.malt - other.malt) <= 0.001;
  }
}


// the utility class containing checkRecipe method
class Utility {

  /* TEMPLATE
  * 
  * METHODS:
  * ... this.checkRecipe(double val1, double val2, String msg)... -- double
  * 
  * METHODS OF FIELD:
  * ... Math.abs(val1 - val2) <= 0.001 ... -- boolean
  */

  // checks if two weights are the same
  double checkRecipe(double val1, double val2, String msg) {
    if (Math.abs(val1 - val2) <= 0.001)  {
      return val1;
    }
    else {
      throw new IllegalArgumentException(msg + Double.toString(val1));
    }
  }
}

// examples of bagel recipe 
class ExamplesBagel {

  ExamplesBagel(){}

  BagelRecipe constructor1a = new BagelRecipe(30.0, 30.0, 0.5, 1.0, 0.5);
  BagelRecipe constructor1b = new BagelRecipe(40.0, 40.0, 1.0, 1.0, 1.0);

  BagelRecipe constructor2a = new BagelRecipe(30.0, 0.5);
  BagelRecipe constructor2b = new BagelRecipe(40.0, 1.0);

  BagelRecipe constructor3a = new BagelRecipe(7.0588, 4.8, 4.8);
  BagelRecipe constructor3b = new BagelRecipe(9.4118, 9.6, 4.8);

  // to test the method SameRecipe
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(constructor1a.sameRecipe(constructor2a), true)
        && t.checkExpect(constructor1b.sameRecipe(constructor2a), false) 
        && t.checkExpect(constructor1a.sameRecipe(constructor3a), true) 
        && t.checkExpect(constructor1b.sameRecipe(constructor3b), true);
  }

  // to test if the wrong value for flour is detected
  boolean checkFlourException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid flour amount." + Double.toString(10.0)),
           "BagelRecipe", 10.0, 30.0, 0.5, 1.0, 0.5);
  }

  // to test if the wrong value for water is detected
  boolean checkWaterException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid water amount." + Double.toString(60.0)),
           "BagelRecipe", 30.0, 60.0, 0.5, 0.5, 0.5);
  }

  // to test if the wrong value for yeast is detected
  boolean checkYeastException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid yeast amount." + Double.toString(3.5)),
           "BagelRecipe", 30.0, 30.0, 3.5, 1.0, 0.5);
  }

  // to test if the wrong value for salt is detected
  boolean checkSaltException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid salt amount." + Double.toString(0.2)),
           "BagelRecipe", 30.0, 30.0, 0.5, 0.2, 0.5);
  }

  // to test if the wrong value for malt is detected
  boolean checkMaltException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid malt amount." + Double.toString(7.0)),
           "BagelRecipe", 30.0, 30.0, 0.5, 1.0, 7.0);
  }
}