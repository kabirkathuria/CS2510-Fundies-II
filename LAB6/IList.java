import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import tester.Tester;


interface ILoString {
 
}

interface IList<T> {

  IList<T> filter(Predicate<T> pred);

  <U> IList<U> map(Function<T,U> converter);

  <U> U fold(BiFunction<T,U,U> converter,U initial);  
}

class MtList<T> implements IList<T> {
 
  MtList() {}

  @Override
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new MtList<U>();
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return initial;
  }
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first,IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first,this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new ConsList<U>(converter.apply(this.first),this.rest.map(converter));
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return converter.apply(this.first, this.rest.fold(converter,initial));
  }
}

// compute whether String starts with "J" or not 
class StartsWithJ implements Predicate<String> {
  /* TEMPLATE
  * METHODS
  * ... thos.test(String) ... -- Boolean 
  */
  public boolean test(String s) {
    return s.startsWith("J");
  }
}

// compute how many months ends with "er"
class EndsEr implements BiFunction<String, Integer, Integer> {
  /* TEMPLATE
  * METHODS
  * ... this.test(String) ... -- Boolean 
  * 
  */
  public boolean test(String s) {
    return s.substring(s.length() - 2, s.length() + 1).equals("er");
  }

  @Override
  public Integer apply(String t, Integer u) {
    // TODO Auto-generated method stub
    if (t.endsWith("er")) {
      return u + 1;
    }
    else {
      return u;
    }
  }
}

class ExamplesLists {
  ExamplesLists() {}
 
  IList<Integer> ints = new ConsList<Integer>(1,
      new ConsList<Integer>(4, new MtList<Integer>()));
  IList<String> months = new ConsList<String>("January", new ConsList<String>("September",
      new ConsList<String>("July", new MtList<String>())));
  IList<String> JMonths = new ConsList<String>("January", 
      new ConsList<String>("July", new MtList<String>()));
  IList<String> abbMonth = new ConsList<String>("Jan",
      new ConsList<String>("Sep", new ConsList<String>("Jul", new MtList<String>())));
 
  boolean testFilter(Tester t) {
    return  t.checkExpect(months.filter(new StartsWithJ()), JMonths); 
  }
 
  boolean testFold(Tester t) {
    return t.checkExpect(months.fold(new EndsEr(), 1), 2);
  }
}