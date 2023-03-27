import java.util.*;
import tester.*;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */

class PermutationCode {

  // The original list of characters to be encoded
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code 
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> finalAlphabet = new ArrayList<Character>();
    ArrayList<Character> alphabetClone = 
        new ArrayList<Character>(Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
            't', 'u', 'v', 'w', 'x', 'y', 'z'));

    while (alphabetClone.size() > 0) {
      int num = this.rand.nextInt(alphabetClone.size());
      finalAlphabet.add(alphabetClone.remove(num));
    }

    return finalAlphabet;
  }

  // produces an encoded or decoded String from the given String
  String abstCode(String str, ArrayList<Character> abstList1, ArrayList<Character> abstList2) {
    String codedString = "";
    int i;

    for (i = 0; i < str.length(); i++) {
      int indexNum = abstList2.indexOf(str.charAt(i));
      codedString += abstList1.get(indexNum);
    }

    return codedString;
  }

  // produce an encoded String from the given String
  String encode(String source) {
    return this.abstCode(source, this.code, this.alphabet);
  }

  // produce a decoded String from the given String
  String decode(String code) {
    return this.abstCode(code, this.alphabet, this.code);
  }
}  

// represents examples and tests for permutations
class ExamplesPermutation {
  
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> alphabet1 = 
      new ArrayList<Character>(Arrays.asList(
          'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 
          'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 
          'g', 'f', 'e', 'd', 'c', 'b', 'a'));

  ArrayList<Character> alphabet2 = 
      new ArrayList<Character>(Arrays.asList(
          'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 
          'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 
          'z', 'x', 'c', 'v', 'b', 'n', 'm'));

  PermutationCode permCode1 = new PermutationCode(alphabet1);
  PermutationCode permCode2 = new PermutationCode(alphabet2);
  PermutationCode permCode3 = new PermutationCode();
  
  ArrayList<Character> alphabet3 = this.permCode3.initEncoder();

  String dString1 = "permutation";
  String eString1 = "kvinfgzgrlm";

  String dString2 = "fundies";
  String eString2 = "ufmwrvh";

  String dString3 = "sneakers";
  String eString3 = "lftqatkl";

  String dString4 = "basketball";
  String eString4 = "wqlatzwqss";

  // tests the initEncoder method
  void testInitEncoder(Tester t) {
    t.checkExpect(this.alphabet.size(), 26);
    t.checkExpect(this.permCode1.initEncoder().size(), 26);
    t.checkExpect(this.permCode2.initEncoder().size(), 26);
    t.checkExpect(this.permCode3.initEncoder().size(), 26);
    t.checkExpect(this.permCode1.alphabet.size(), 26);
    t.checkExpect(this.permCode2.alphabet.size(), 26);
    t.checkExpect(this.permCode3.alphabet.size(), 26);
    t.checkExpect(this.permCode1.alphabet.size(), this.permCode1.initEncoder().size());
    t.checkExpect(this.permCode1.alphabet.size(), this.permCode2.initEncoder().size());
    t.checkExpect(this.permCode1.alphabet.size(), this.permCode3.initEncoder().size());
    t.checkExpect(this.permCode2.alphabet.size(), this.permCode2.initEncoder().size());
    t.checkExpect(this.permCode2.alphabet.size(), this.permCode1.initEncoder().size());
    t.checkExpect(this.permCode2.alphabet.size(), this.permCode3.initEncoder().size());
    t.checkExpect(this.permCode3.alphabet.size(), this.permCode3.initEncoder().size());
    t.checkExpect(this.permCode3.alphabet.size(), this.permCode1.initEncoder().size());
    t.checkExpect(this.permCode3.alphabet.size(), this.permCode2.initEncoder().size());
    for (Character character : this.alphabet) {
      t.checkExpect(alphabet3.contains(character), true);
    }
  }

  // tests the abstCode method
  void testAbstCode(Tester t) {
    t.checkExpect(this.permCode1.abstCode("", this.permCode1.alphabet, 
        this.permCode1.code), "");
    t.checkExpect(this.permCode1.abstCode("", this.permCode1.code, 
        this.permCode1.alphabet), "");
    t.checkExpect(this.permCode1.abstCode(this.eString1, this.permCode1.alphabet, 
        this.permCode1.code), this.dString1);
    t.checkExpect(this.permCode1.abstCode(this.dString1, this.permCode1.code, 
        this.permCode1.alphabet), this.eString1);
    t.checkExpect(this.permCode1.abstCode(this.eString1, this.permCode1.alphabet, 
        this.permCode1.alphabet), this.eString1);
    t.checkExpect(this.permCode1.abstCode(this.dString1, this.permCode1.code, 
        this.permCode1.code), this.dString1);
    t.checkExpect(this.permCode2.abstCode("", this.permCode2.alphabet, 
        this.permCode2.code), "");
    t.checkExpect(this.permCode2.abstCode("", this.permCode2.code, 
        this.permCode2.alphabet), "");
    t.checkExpect(this.permCode2.abstCode(this.eString3, this.permCode2.alphabet, 
        this.permCode2.code), this.dString3);
    t.checkExpect(this.permCode2.abstCode(this.dString3, this.permCode2.code, 
        this.permCode2.alphabet), this.eString3);
    t.checkExpect(this.permCode2.abstCode(this.eString3, this.permCode2.code, 
        this.permCode2.code), this.eString3);
    t.checkExpect(this.permCode2.abstCode(this.dString3, this.permCode2.alphabet, 
        this.permCode2.alphabet), this.dString3);
  }

  // tests the encode method
  void testEncode(Tester t) {
    t.checkExpect(this.permCode1.encode(""), "");
    t.checkExpect(this.permCode1.encode(this.dString1), this.eString1);
    t.checkExpect(this.permCode1.encode(this.dString2), this.eString2);
    t.checkExpect(this.permCode2.encode(""), "");
    t.checkExpect(this.permCode2.encode(this.dString3), this.eString3);
    t.checkExpect(this.permCode2.encode(this.dString4), this.eString4);
  }

  // tests the decode method
  void testDecode(Tester t) {
    t.checkExpect(this.permCode1.decode(""), "");
    t.checkExpect(this.permCode1.decode(this.eString1), this.dString1);
    t.checkExpect(this.permCode1.decode(this.eString2), this.dString2);
    t.checkExpect(this.permCode2.decode(""), "");
    t.checkExpect(this.permCode2.decode(this.eString3), this.dString3);
    t.checkExpect(this.permCode2.decode(this.eString4), this.dString4);
  }
}