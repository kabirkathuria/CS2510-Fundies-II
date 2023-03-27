import tester.Tester;

// to represent a circuit component
interface ICircuit {
  
  // to compute the number of simple components in a circuit
  int countComponents();
  
  // to compute the total voltage between the terminals of a circuit
  double totalVoltage();
  
  // to compute the total resistance between the terminals of a circuit
  double totalResistance();
  
  // to compute the total current between the terminals of a circuit
  double totalCurrent();
  
  // to compute a circuit component with reversed voltages
  ICircuit reversePolarity();
}

// to represent a battery
class Battery implements ICircuit {
  String name;
  double voltage;
  double nominalResistance;
  
  Battery(String name, double voltage, double nominalResistance) {
    this.name = name;
    this.voltage = voltage;
    this.nominalResistance = nominalResistance;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.name ... -- String
   * ... this.voltage ... -- double
   * ... this.nominalResistance ... -- double
   * 
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * 
   */
  
  // to compute the number of simple components in a battery
  public int countComponents() {
    return 1;
  }
  
  // to compute the total voltage in a battery
  public double totalVoltage() {
    return this.voltage;
  }
  
  // to compute the total resistance in a battery
  public double totalResistance() {
    return this.nominalResistance;
  }
  
  // to compute the total current in a battery
  public double totalCurrent() {
    return (this.voltage / this.nominalResistance);
  }
  
  // to compute a battery with reversed voltage
  public ICircuit reversePolarity() {
    return new Battery(this.name, (this.voltage * (-1)), this.nominalResistance);
  }
}

// to represent a resistor
class Resistor implements ICircuit {
  String name;
  double resistance;
  
  Resistor(String name, double resistance) {
    this.name = name;
    this.resistance = resistance;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.name ... -- String
   * ... this.resistance ... -- double
   * 
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * 
   */
  
  // to compute the number of simple components in a resistor
  public int countComponents() {
    return 1;
  }
  
  // to compute the total voltage in a resistor
  public double totalVoltage() {
    return 0;
  }
  
  // to compute the total resistance in a resistor
  public double totalResistance() {
    return this.resistance;
  }

  // to compute the total current in a resistor
  public double totalCurrent() {
    return this.resistance;
  }
  
  // to compute a resistor with reversed voltage
  public ICircuit reversePolarity() {
    return new Resistor(this.name, this.resistance);
  }
}

// to represent a series
class Series implements ICircuit {
  ICircuit first;
  ICircuit second;
  
  Series(ICircuit first, ICircuit second) {
    this.first = first;
    this.second = second;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.first ... -- ICircuit
   * ... this.second ... -- ICircuit
   * 
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * 
   * Methods of Fields:
   * ... this.first.countComponents() ... -- int
   * ... this.second.countComponents() ... -- int
   * ... this.first.totalVoltage() ... -- double
   * ... this.second.totalVoltage() ... -- double
   * ... this.first.totalResistance() ... -- double
   * ... this.second.totalResistance() ... -- double
   * ... this.first.reversePolarity() ... -- ICircuit
   * ... this.second.reversePolarity() ... -- ICircuit
   * 
   */
  
  // to compute the number of simple components in a series
  public int countComponents() {
    return this.first.countComponents() + this.second.countComponents();
  }
  
  // to compute the total voltage in a series
  public double totalVoltage() {
    return this.first.totalVoltage() + this.second.totalVoltage();
  }
  
  // to compute the total resistance in a series
  public double totalResistance() {
    return this.first.totalResistance() + this.second.totalResistance();
  }
  
  // to compute the total current in a series
  public double totalCurrent() {
    return this.totalVoltage() / this.totalResistance();
  }
  
  // to compute a series with reversed voltage
  public ICircuit reversePolarity() {
    return new Series(this.first.reversePolarity(), this.second.reversePolarity());
  }
}

// to represent a parallel
class Parallel implements ICircuit {
  ICircuit first;
  ICircuit second;
  
  Parallel(ICircuit first, ICircuit second) {
    this.first = first;
    this.second = second;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.first ... -- ICircuit
   * ... this.second ... -- ICircuit
   * 
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * 
   * Methods of Fields:
   * ... this.first.countComponents() ... -- int
   * ... this.second.countComponents() ... -- int
   * ... this.first.totalVoltage() ... -- double
   * ... this.second.totalVoltage() ... -- double
   * ... this.first.totalResistance() ... -- double
   * ... this.second.totalResistance() ... -- double
   * ... this.first.reversePolarity() ... -- ICircuit
   * ... this.second.reversePolarity() ... -- ICircuit
   * 
   */
  
  // to compute the number of simple components in a parallel
  public int countComponents() {
    return this.first.countComponents() + this.second.countComponents();
  }
  
  // to compute the total voltage in a parallel
  public double totalVoltage() {
    return this.first.totalVoltage() + this.second.totalVoltage();
  }
  
  // to compute the total resistance in a parallel
  public double totalResistance() {
    return (1.0 / ((1.0 / this.first.totalResistance()) + (1.0 / this.second.totalResistance())));
  }
  
  // to compute the total current in a parallel
  public double totalCurrent() {
    return this.totalVoltage() / this.totalResistance();
  }
  
  // to compute a battery with reversed parallel
  public ICircuit reversePolarity() {
    return new Parallel(this.first.reversePolarity(), this.second.reversePolarity());
  }
}

/*
 * Contents of the custom circuit:
 * 
 * Battery 1 with 15V and 20 nominal resistance
 * Battery 2 with 5V and 20 nominal resistance
 * Batteries 1 and 2 connected by a series
 * 
 * Resistor 1 with 50 resistance
 * Resistor 2 with 150 resistance
 * Resistors 1 and 2 connected by a series
 * 
 * Both series connected by a parallel to form a circuit
 *  
 */

// to represent examples for circuit components
class ExamplesCircuits {
  ExamplesCircuits() {}
  
  ICircuit batteryOne = new Battery("B 1", 10.0, 25.0);
  ICircuit resistorOne = new Resistor("R 1", 100.0);
  ICircuit simpleSeries = new Series(this.batteryOne, this.resistorOne);
  
  ICircuit bt1 = new Battery("BT 1", 10.0, 0.0);
  ICircuit bt2 = new Battery("BT 2", 20.0, 0.0);
  ICircuit r1 = new Resistor("R 1", 100.0);
  ICircuit r2 = new Resistor("R 2", 250.0);
  ICircuit r3 = new Resistor("R 3", 500.0);
  ICircuit r4 = new Resistor("R 4", 200.0);
  ICircuit r5 = new Resistor("R 5", 50.0);
  
  ICircuit series1 = new Series(this.bt1, this.bt2);
  ICircuit series2 = new Series(this.r4, this.r5);
  ICircuit parallel1 = new Parallel(this.series2, this.r1);
  ICircuit parallel2 = new Parallel(this.parallel1, this.r2);
  ICircuit parallel3 = new Parallel(this.parallel2, this.r3);
  ICircuit complexCircuit = new Series(this.series1, this.parallel3);
  
  ICircuit customBT1 = new Battery("Custom BT 1", 15.0, 20.0);
  ICircuit customBT2 = new Battery("Custom BT 2", 5.0, 20.0);
  ICircuit customR1 = new Resistor("Custom R 1", 50.0);
  ICircuit customR2 = new Resistor("Custom R 2", 150.0);
  ICircuit customSeries1 = new Series(this.customBT1, this.customBT2);
  ICircuit customSeries2 = new Series(this.customR1, this.customR2);
  ICircuit customCircuit = new Parallel(this.customSeries1, this.customSeries2);
  
  ICircuit reverseBatteryOne = new Battery("B 1", -10.0, 25.0);
  ICircuit reverseSimpleSeries = new Series(this.reverseBatteryOne, this.resistorOne);
  
  ICircuit reversebt1 = new Battery("BT 1", -10.0, 0.0);
  ICircuit reversebt2 = new Battery("BT 2", -20.0, 0.0);
  
  ICircuit reverseSeries1 = new Series(this.reversebt1, this.reversebt2);
  ICircuit reverseComplexCircuit = new Series(this.reverseSeries1, this.parallel3);
  
  // to test the countComponents method
  boolean testCountComponents(Tester t) {
    return t.checkExpect(this.batteryOne.countComponents(), 1) &&
           t.checkExpect(this.series1.countComponents(), 2) &&
           t.checkExpect(this.complexCircuit.countComponents(), 7);
  }
  
  // to test the totalVoltage method
  boolean testTotalVoltage(Tester t) {
    return t.checkInexact(this.batteryOne.totalVoltage(), 10.0, 0.01) &&
           t.checkInexact(this.series1.totalVoltage(), 30.0, 0.01) &&
           t.checkInexact(this.complexCircuit.totalVoltage(), 30.0, 0.01);
  }
  
  // to test the totalCurrent method
  boolean testTotalCurrent(Tester t) {
    return t.checkInexact(this.batteryOne.totalCurrent(), 0.4, 0.1) &&
           t.checkInexact(this.simpleSeries.totalCurrent(), 0.08, 0.1) &&
           t.checkInexact(this.complexCircuit.totalCurrent(), 0.6, 0.1);
  }
  
  // to test the totalResistance method
  boolean testTotalResistance(Tester t) {
    return t.checkInexact(this.batteryOne.totalResistance(), 25.0, 0.1) &&
           t.checkInexact(this.series1.totalResistance(), 0.0, 0.1) &&
           t.checkInexact(this.complexCircuit.totalResistance(), 50.0, 0.1);
  }
  
  // to test the reversePolarity method
  boolean testReversePolarity(Tester t) {
    return t.checkExpect(this.batteryOne.reversePolarity(), this.reverseBatteryOne) &&
           t.checkExpect(this.series1.reversePolarity(), this.reverseSeries1) &&
           t.checkExpect(this.complexCircuit.reversePolarity(), this.reverseComplexCircuit);
  }
}