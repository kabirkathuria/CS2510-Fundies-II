import tester.Tester;

// to represent an XML
interface IXML {
  // to compute content length of an XML
  int contentLength();
  
  // to determine whether an XML has the given tag
  boolean hasTag(String name);
  
  // to determine whether an XML has the given attribute
  boolean hasAttribute(String name);
  
  // to return an XML as String
  String renderAsString();
}

// to represent plain text
class Plaintext implements IXML {
  String txt;
  
  Plaintext(String txt) {
    this.txt = txt;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.txt ... -- String
   * 
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String name) ... -- boolean
   * ... this.hasAttribute(String name) ... -- boolean
   * ... this.renderAsString() ... -- String
   * 
   */
  
  // to compute content length of plain text
  public int contentLength() {
    return this.txt.length();
  }
  
  // to determine whether plain text has the given tag
  public boolean hasTag(String name) {
    return false;
  }
  
  // to determine whether plain text has the given attribute
  public boolean hasAttribute(String name) {
    return false;
  }
  
  // to return a plain text as a String
  public String renderAsString() {
    return this.txt;
  }
}

// to represent untagged XML
class Untagged implements IXML {
  ILoXML content;
  
  Untagged(ILoXML content) {
    this.content = content;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.content ... -- ILoXML
   * 
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String name) ... -- boolean
   * ... this.hasAttribute(String name) ... -- boolean
   * ... this.renderAsString() ... -- String
   * 
   * Methods of Fields:
   * ... this.content.contentLength() ... -- int
   * ... this.content.hasTag(String name) ... -- boolean
   * ... this.content.hasAttribute(String name) ... -- boolean
   * ... this.content.renderAsString() ... -- String
   * 
   */
  
  // to compute content length of untagged XML
  public int contentLength() {
    return this.content.contentLength();
  }
  
  // to determine whether untagged XML has the given tag
  public boolean hasTag(String name) {
    return this.content.hasTag(name);
  }
  
  // to determine whether untagged XML has the given attribute
  public boolean hasAttribute(String name) {
    return this.content.hasAttribute(name);
  }
  
  // to return a untagged XML as a String
  public String renderAsString() {
    return this.content.renderAsString();
  }
}

// to represent tagged XML
class Tagged implements IXML {
  Tag tag;
  ILoXML content;
  
  Tagged(Tag tag, ILoXML content) {
    this.tag = tag;
    this.content = content;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.tag ... -- Tag
   * ... this.content ... -- ILoXML
   * 
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String name) ... -- boolean
   * ... this.hasAttribute(String name) ... -- boolean
   * ... this.renderAsString() ... -- String
   * 
   * Methods of Fields:
   * ... this.content.contentLength() ... -- int
   * ... this.content.hasTag(String name) ... -- boolean
   * ... this.content.hasAttribute(String name) ... -- boolean
   * ... this.content.renderAsString() ... -- String
   * 
   */
  
  // to compute content length of tagged XML
  public int contentLength() {
    return this.content.contentLength();
  }
  
  // to determine whether tagged XML has the given tag
  public boolean hasTag(String name) {
    return (this.tag.name.equals(name)) || (this.content.hasTag(name));
  }
  
  // to determine whether tagged XML has the given tag
  public boolean hasAttribute(String name) {
    return (this.tag.atts.hasAttribute(name)) || (this.content.hasAttribute(name));
  }
  
  // to return a tagged XML as a String
  public String renderAsString() {
    return this.content.renderAsString();
  }
}

// to represent a tag
class Tag {
  String name;
  ILoAtt atts;
  
  Tag(String name, ILoAtt atts) {
    this.name = name;
    this.atts = atts;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.name ... -- String
   * ... this.atts ... -- ILoAtt
   * 
   */
}

// to represent an attribute
class Att {
  String name;
  String value;
  
  Att(String name, String value) {
    this.name = name;
    this.value = value;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.name ... -- String
   * ... this.value ... -- String
   * 
   */
}

// to represent a list of XML's
interface ILoXML {
  // to compute content length of an XML
  int contentLength();

  // to determine whether an XML has the given tag
  boolean hasTag(String name);
 
  // to determine whether an XML has the given attribute
  boolean hasAttribute(String name);
 
  // to return an XML as String
  String renderAsString();
}

// to represent an empty list of XML's
class MtLoXML implements ILoXML {
  MtLoXML(){}
  
  /* TEMPLATE:
   * 
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String name) ... -- boolean
   * ... this.hasAttribute(String name) ... -- boolean
   * ... this.renderAsString() ... -- String
   * 
   */
  
  // to compute content length of an empty list of XML's
  public int contentLength() {
    return 0;
  }
  
  // to determine whether an empty list of XML's has the given tag
  public boolean hasTag(String name) {
    return false;
  }
  
  // to determine whether an empty list of XML's has the given attribute
  public boolean hasAttribute(String name) {
    return false;
  }
  
  // to return an empty list of XML's as String
  public String renderAsString() {
    return "";
  }
}

// to represent a cons list of XML's
class ConsLoXML implements ILoXML {
  IXML first;
  ILoXML rest;
  
  ConsLoXML(IXML first, ILoXML rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.first ... -- IXML
   * ... this.rest ... -- ILoXML
   * 
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String name) ... -- boolean
   * ... this.hasAttribute(String name) ... -- boolean
   * ... this.renderAsString() ... -- String
   * 
   * Methods of Fields:
   * ... this.first.contentLength() ... -- int
   * ... this.first.hasTag(String name) ... -- boolean
   * ... this.first.hasAttribute(String name) ... -- boolean
   * ... this.first.renderAsString() ... -- String
   * ... this.rest.contentLength() ... -- int
   * ... this.rest.hasTag(String name) ... -- boolean
   * ... this.rest.hasAttribute(String name) ... -- boolean
   * ... this.rest.renderAsString() ... -- String
   * 
   */
  
  // to compute content length of a cons list of XML's
  public int contentLength() {
    return this.first.contentLength() + this.rest.contentLength();
  }
  
  // to determine whether a cons list of XML's has the given tag
  public boolean hasTag(String name) {
    return this.first.hasTag(name) || this.rest.hasTag(name);
  }
  
  // to determine whether a cons list of XML's has the given attribute
  public boolean hasAttribute(String name) {
    return this.first.hasAttribute(name) || this.rest.hasAttribute(name);
  }
  
  // to return a cons list of XML's as String
  public String renderAsString() {
    return this.first.renderAsString() + this.rest.renderAsString();
  }
}

// to represent a list of attributes
interface ILoAtt {
  // to determine whether an XML has the given attribute
  boolean hasAttribute(String name);
}

// to represent an empty list of attributes
class MtLoAtt implements ILoAtt {
  MtLoAtt(){}
  
  /* TEMPLATE:
   * 
   * Methods:
   * ... this.hasAttribute(String name) ... -- boolean
   * 
   */
  
  // to determine whether an empty list of attributes has the given attribute
  public boolean hasAttribute(String name) {
    return false;
  }
}

// to represent an cons list of attributes
class ConsLoAtt implements ILoAtt {
  Att first;
  ILoAtt rest;
  
  ConsLoAtt(Att first, ILoAtt rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* TEMPLATE:
   * 
   * Fields:
   * ... this.first ... -- Att
   * ... this.rest ... -- ILoAtt
   * 
   * Methods:
   * ... this.hasAttribute(String name) ... -- boolean
   * 
   * Methods of Fields:
   * ... this.rest.hasAttribute(String name) ... -- boolean
   * 
   */
  
  // to determine whether a cons list of attributes has the given attribute
  public boolean hasAttribute(String name) {
    return (this.first.name.equals(name)) || this.rest.hasAttribute(name);
  }
}

// to represent examples for XML text
class ExamplesXML {
  ExamplesXML() {}
  
  IXML xml1 = new Plaintext("I am XML!");
      
  IXML xml2 = new Untagged(new ConsLoXML(new Plaintext("I am "), 
              new ConsLoXML(new Tagged(new Tag("yell", new MtLoAtt()), 
              new ConsLoXML(new Plaintext("XML"), new MtLoXML())),
              new ConsLoXML(new Plaintext("!"), new MtLoXML()))));
  
  IXML xml3 = new Untagged(new ConsLoXML(new Plaintext("I am "), 
              new ConsLoXML(new Tagged(new Tag("yell", new MtLoAtt()),
              new ConsLoXML(new Tagged(new Tag("italic", new MtLoAtt()), 
              new ConsLoXML(new Plaintext("X"), new MtLoXML())), new ConsLoXML(new Plaintext("ML"),
              new MtLoXML()))), new ConsLoXML(new Plaintext("!"), new MtLoXML()))));
  
  IXML xml4 = new Untagged(new ConsLoXML(new Plaintext("I am "), 
              new ConsLoXML(new Tagged(new Tag("yell", new ConsLoAtt(new Att("volume", "30db"), 
              new MtLoAtt())), new ConsLoXML(new Tagged(new Tag("italic", new MtLoAtt()), 
              new ConsLoXML(new Plaintext("X"), new MtLoXML())), new ConsLoXML(new Plaintext("ML"), 
              new MtLoXML()))), new ConsLoXML(new Plaintext("!"), new MtLoXML()))));
  
  IXML xml5 = new Untagged(new ConsLoXML(new Plaintext("I am "), 
              new ConsLoXML(new Tagged(new Tag("yell", new ConsLoAtt(new Att("volume", "30db"), 
              new ConsLoAtt(new Att("duration", "5sec"), new MtLoAtt()))), 
              new ConsLoXML(new Tagged(new Tag("italic", new MtLoAtt()),
              new ConsLoXML(new Plaintext("X"), new MtLoXML())), new ConsLoXML(new Plaintext("ML"), 
              new MtLoXML()))), new ConsLoXML(new Plaintext("!"), new MtLoXML()))));
  
  // to test the contentLength method
  boolean testContentLength(Tester t) {
    return t.checkExpect(xml1.contentLength(), 9)
        && t.checkExpect(xml3.contentLength(), 9)
        && t.checkExpect(xml5.contentLength(), 9);  
  }
  
  // to test the hasTag method
  boolean testHasTag(Tester t) {
    return t.checkExpect(xml1.hasTag("yell"), false)
        && t.checkExpect(xml3.hasTag("italic"), true)
        && t.checkExpect(xml5.hasTag("yell"), true);
  }
  
  // to test the hasAttribute method
  boolean testHasAttribute(Tester t) {
    return t.checkExpect(new MtLoAtt().hasAttribute("volume"), false)
        && t.checkExpect(xml1.hasAttribute("duration"), false)
        && t.checkExpect(xml4.hasAttribute("volume"), true)
        && t.checkExpect(xml5.hasAttribute("duration"), true);
  }
  
  // to test the renderAsString method
  boolean testRenderAsString(Tester t) {
    return t.checkExpect(xml1.renderAsString(), "I am XML!")
        && t.checkExpect(xml3.renderAsString(), "I am XML!")
        && t.checkExpect(xml5.renderAsString(), "I am XML!");
  }
}