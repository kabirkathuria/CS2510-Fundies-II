import tester.Tester;

interface IGamePiece {

  int getValue();

  IGamePiece merge(IGamePiece otherPiece);
  
  boolean isValid();
}

class BaseTile implements IGamePiece {
  int value;

  BaseTile(int value) {
    this.value = value;
  }

  /* TEMPLATE
   * FIELDS:
   * ... this.value ...                -- int
   * METHODS
   * ... this.getValue()...            -- int 
   * ... this.merge(IGamePiece) ...    -- IGamePiece 
   * ... this.isValid() ...            -- boolean 
   */
  
  // get the value of a game piece 
  public int getValue() {
    return this.value;
  }
  
  // combine this game piece with the given game piece to form a merged piece 
  public IGamePiece merge(IGamePiece otherPiece) {
    return new MergeTile(this, otherPiece);
  }
  
  // check whether this game piece was created according to the rule of 2048
  public boolean isValid() {
    return true;
  }
}

class MergeTile implements IGamePiece {
  IGamePiece piece1;
  IGamePiece piece2;
  
  MergeTile(IGamePiece piece1, IGamePiece piece2) {
    this.piece1 = piece1;
    this.piece2 = piece2;
  }
  
  /* TEMPLATE
   * FIELDS:
   * ... this.piece1 ...              -- piece1
   * ... this.piece2 ...              -- piece2
   * METHODS:
   * ... this.getValue ...            -- int 
   * ... this.merge(IGamePiece) ...   -- IGamePiece 
   * ... this.isValid() ...           -- boolean 
   * METHODS FOR FIELDS:
   * ... this.piece1.getValue() ...   -- int 
   * ... this.piece2.getValue() ...   -- int 
   */
  
  // get the value of a game piece 
  public int getValue() {
    return this.piece1.getValue() + this.piece2.getValue();
  }
  
  // combine this game piece with the given game piece to form a merged piece 
  public IGamePiece merge(IGamePiece otherPiece) {
    return new MergeTile(this, otherPiece);
  }
  
  // check whether this game piece was created according to the rule of 2048
  public boolean isValid() {
    return this.piece1.getValue() == this.piece2.getValue();
  }
}

class ExamplesGamePieces {
  ExamplesGamePieces() {}
  
  IGamePiece baseTile1 = new BaseTile(2);
  IGamePiece baseTile2 = new BaseTile(4);
  IGamePiece baseTile3 = new BaseTile(8);
  IGamePiece baseTile4 = new BaseTile(8);
  
  IGamePiece mergeTile1 = new MergeTile(this.baseTile1, this.baseTile2);
  IGamePiece mergeTile2 = new MergeTile(this.baseTile3, this.baseTile4);
  
  boolean testGetValue(Tester t) {
    return t.checkExpect(baseTile1.getValue(), 2)
        && t.checkExpect(baseTile3.getValue(), 8)
        && t.checkExpect(mergeTile1.getValue(), 6)
        && t.checkExpect(mergeTile2.getValue(), 16);
  }
  
  boolean testMerge(Tester t) {
    return t.checkExpect(baseTile1.merge(baseTile3), 
            new MergeTile(this.baseTile1, this.baseTile3))
        && t.checkExpect(baseTile2.merge(baseTile4),
            new MergeTile(this.baseTile2, this.baseTile4))
        && t.checkExpect(baseTile3.merge(mergeTile1),
            new MergeTile(this.baseTile3, this.mergeTile1))
        && t.checkExpect(mergeTile1.merge(mergeTile2),
            new MergeTile(this.mergeTile1, this.mergeTile2));
  }
  
  boolean testIsValid(Tester t) {
    return t.checkExpect(baseTile2.isValid(), true)
        && t.checkExpect(baseTile4.isValid(), true)
        && t.checkExpect(mergeTile1.isValid(), false)
        && t.checkExpect(mergeTile2.isValid(), true);
  }
}