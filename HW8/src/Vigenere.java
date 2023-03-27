import java.util.*;
import tester.*;

/*
 * A class that defines a new vigenere cipher, as well as methods for encoding
 * and decoding of the messages that use this table.
 */

class Vigenere {

  // The original list of characters
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<ArrayList<Character>> table; 

  // Create a new instance of the encoder/decoder with a new vigenere table
  Vigenere() {
    this.table = this.initVigenere();
  }

  // Initialize the vigenere table
  ArrayList<ArrayList<Character>> initVigenere() {
    int i;
    int j;
    ArrayList<ArrayList<Character>> finalCipher = new ArrayList<ArrayList<Character>>();

    for (i = 0; i < 26; i++) {
      ArrayList<Character> cipherRow = new ArrayList<Character>(this.alphabet);

      for (j = 0; j < i; j++) {
        cipherRow.add(cipherRow.remove(0));
      }

      finalCipher.add(cipherRow);
    }

    return finalCipher;
  }

  // creates a key using a keyword
  String keyFromKeyword(String message, String keyword) {
    int i;
    String key = "";

    for (i = 0; i < message.length(); i++) {
      key += keyword.charAt(i % keyword.length());
    }

    return key;
  }

  // produce an encoded String from the given message and keyword
  String encode(String message, String keyword) {
    int i;
    String str = "";
    keyword = this.keyFromKeyword(message, keyword);

    for (i = 0; i < message.length(); i++) {
      int messageIndex = this.alphabet.indexOf(message.charAt(i));
      int keywordIndex = this.alphabet.indexOf(keyword.charAt(i));
      str += this.table.get(messageIndex).get(keywordIndex);
    }

    return str;
  }

  // produce a decoded String from the given message and keyword
  String decode(String message, String keyword) {
    int i;
    String str = "";
    keyword = this.keyFromKeyword(message, keyword);

    for (i = 0; i < message.length(); i++) {
      str += this.alphabet.get(this.table.get(
          this.alphabet.indexOf(keyword.charAt(i))).indexOf(message.charAt(i)));
    }

    return str;
  }
}  

// represents examples and tests for vigenere
class ExamplesVigenere {
  
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));
  
  Vigenere vigenere1 = new Vigenere();
  Vigenere vigenere2 = new Vigenere();
  
  // tests the initVigenere method
  void testInitVigenere(Tester t) {
    t.checkExpect(this.alphabet.size(), 26);
    t.checkExpect(this.vigenere1.initVigenere().size(), 26);
    t.checkExpect(this.vigenere2.initVigenere().size(), 26);
    t.checkExpect(this.vigenere1.table.size(), 26);
    t.checkExpect(this.vigenere2.table.size(), 26);
    t.checkExpect(this.vigenere1.initVigenere(), this.vigenere1.table);
    t.checkExpect(this.vigenere1.initVigenere(), this.vigenere2.table);
    t.checkExpect(this.vigenere2.initVigenere(), this.vigenere2.table);
    t.checkExpect(this.vigenere2.initVigenere(), this.vigenere1.table);
    t.checkExpect(this.vigenere1.initVigenere().size(), this.vigenere1.table.size());
    t.checkExpect(this.vigenere1.initVigenere().size(), this.vigenere2.table.size());
    t.checkExpect(this.vigenere2.initVigenere().size(), this.vigenere2.table.size());
    t.checkExpect(this.vigenere2.initVigenere().size(), this.vigenere1.table.size());
  }
  
  // tests the keyFromKeyword method
  void testKeyFromKeyword(Tester t) {
    t.checkExpect(this.vigenere1.keyFromKeyword("", ""), "");
    t.checkExpect(this.vigenere1.keyFromKeyword("", "happy"), "");
    t.checkExpect(this.vigenere1.keyFromKeyword("word", "cats"), "cats");
    t.checkExpect(this.vigenere1.keyFromKeyword("thanksgiving", "happy"), "happyhappyha");
    t.checkExpect(this.vigenere1.keyFromKeyword("gift", "christmas"), "chri");
  }
  
  // tests the encode method
  void testEncode(Tester t) {
    t.checkExpect(this.vigenere1.encode("", ""), "");
    t.checkExpect(this.vigenere1.encode("", "happy"), "");
    t.checkExpect(this.vigenere1.encode("word", "cats"), "yokv");
    t.checkExpect(this.vigenere1.encode("thanksgiving", "happy"), "ahpcizgxkgug");
    t.checkExpect(this.vigenere1.encode("gift", "christmas"), "ipwb");
  }
  
  // tests the decode method
  void testDecode(Tester t) {
    t.checkExpect(this.vigenere1.decode("", ""), "");
    t.checkExpect(this.vigenere1.decode("", "happy"), "");
    t.checkExpect(this.vigenere1.decode("yokv", "cats"), "word");
    t.checkExpect(this.vigenere1.decode("ahpcizgxkgug", "happy"), "thanksgiving");
    t.checkExpect(this.vigenere1.decode("ipwb", "christmas"), "gift");
  }
}

