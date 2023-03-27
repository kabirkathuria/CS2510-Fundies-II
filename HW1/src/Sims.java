// to represent a mode
interface IMode { }

// to represent study mode
class Study implements IMode {
  String subject;
  boolean examReview;
  
  Study(String subject, boolean examReview) {
    this.subject = subject;
    this.examReview = examReview;
  }
}

// to represent socializing mode
class Socialize implements IMode {
  String description;
  int friends;
  
  Socialize(String description, int friends) {
    this.description = description;
    this.friends = friends;
  }
}

// to represent exercising mode
class Exercise implements IMode {
  String name;
  boolean aerobic;
  
  Exercise(String name, boolean aerobic) {
    this.name = name;
    this.aerobic = aerobic;
  }
}

// to represent a place
interface IPlace { }

// to represent a classroom
class Classroom implements IPlace {
  String name;
  int roomCapacity;
  int occupantCount;
  
  Classroom(String name, int roomCapacity, int occupantCount) {
    this.name = name;
    this.roomCapacity = roomCapacity;
    this.occupantCount = occupantCount;
  }
}

// to represent a gym
class Gym implements IPlace {
  String name;
  int exerciseMachines;
  int patrons;
  boolean open;
  
  Gym(String name, int exerciseMachines, int patrons, boolean open) {
    this.name = name;
    this.exerciseMachines = exerciseMachines;
    this.patrons = patrons;
    this.open = open;
  }
}

// to represent a student center
class StudentCenter implements IPlace {
  String name;
  boolean open;
  
  StudentCenter(String name, boolean open) {
    this.name = name;
    this.open = open;
  }
}

// to represent a simulated student
class SimStudent {
  String name;
  IMode mode;
  IPlace location;
  double gpa;
  String major;
  
  SimStudent(String name, IMode mode, IPlace location, double gpa, String major) {
    this.name = name;
    this.mode = mode;
    this.location = location;
    this.gpa = gpa;
    this.major = major;
  }
}

// to represent examples for locations and simulated students
class ExamplesSims {
  ExamplesSims() {}
  
  IPlace shillman105 = new Classroom("Shillman 105", 115, 86);
  IPlace marino = new Gym("Marino Recreation Center", 78, 47, true);
  IPlace curry = new StudentCenter("Curry Student Center", false);
  IPlace dodge370 = new Classroom("Dodge 370", 65, 60);
  IPlace squashbusters = new Gym("SquashBusters Recreation Center", 46, 24, true);
  IPlace hayden424 = new Classroom("Hayden 424", 40, 25);
  
  SimStudent student1 = new SimStudent("John", new Study("Marketing", false), 
                                       this.dodge370, 2.76, "Business Administration");
  SimStudent student2 = new SimStudent("George", new Exercise("Chest Press", false), 
                                       this.marino, 3.86, "Communications");
  SimStudent student3 = new SimStudent("Shaun", new Socialize("Talking", 3), 
                                       this.curry, 3.42, "Data Science");
  SimStudent student4 = new SimStudent("Bill", new Study("Fundamentals of CS 2", true), 
                                       this.shillman105, 4.0, "Computer Science");
}
