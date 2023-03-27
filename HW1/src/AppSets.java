// to represent an app set
interface IAppSet { }

// to represent a folder
class Folder implements IAppSet {
  String title;
  
  Folder(String title) {
    this.title = title;
  }
}

// to represent apps
class Apps implements IAppSet {
  String appName;
  IAppSet others;
  
  Apps(String appName, IAppSet others) {
    this.appName = appName;
    this.others = others;
  }
}

// to represent examples for app sets
class ExamplesSets {
  ExamplesSets() {}
  
  IAppSet travel = new Folder("Travel");
  IAppSet uber = new Apps("Uber", this.travel);
  IAppSet mTicket = new Apps("mTicket", this.uber);
  IAppSet moovit = new Apps("Moovit", this.mTicket);
  IAppSet travelApps = new Apps("Orbitz", this.moovit);
  
  IAppSet food = new Folder("Food");
  IAppSet grubhub = new Apps("Grubhub", this.food);
  IAppSet bGood = new Apps("B. Good", this.grubhub);
  IAppSet foodApps = new Apps("Gong Cha", this.bGood);
}