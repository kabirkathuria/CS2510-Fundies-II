import tester.Tester;

// a piece of media
interface IMedia {

  // is this media really old?
  boolean isReallyOld();

  // are captions available in this language?
  boolean isCaptionAvailable(String language);

  // a string showing the proper display of the media
  String format();
}

//to represent a media 
abstract class AMedia implements IMedia {
  String title;
  ILoString captionOptions;

  AMedia(String title, ILoString captionOptions) {
    this.title = title;
    this.captionOptions = captionOptions;
  }

  // is this media really old?
  public boolean isReallyOld() {
    return false;
  }

  // are caption available in this language?
  public boolean isCaptionAvailable(String language) {
    return this.captionOptions.checkOption(language);
  }

  // a string showing the proper display of the media
  public abstract String format();
}

// represents a movie
class Movie extends AMedia {
  int year;

  Movie(String title, int year, ILoString captionOptions) {
    super(title, captionOptions);
    this.year = year;
  }
  
  /* TEMPLATE 
  *  FIELDS:
  *  ... this.title ...                 -- String 
  *  ... this.year ...                  -- int
  *  ... this.captionOptions ...        -- ILoString 
  *  METHODS:
  *  ... this.isReallyOld() ...         -- boolean 
  *  ... this.isCaptionAvailable ...    -- boolean 
  *  ... this.format() ...              -- boolean 
  *  METHODS FOR FIELDS:
  *  ... this.captionOptions.checkOptions(String) ...    -- boolean
  */

  //is this media really old?
  public boolean isReallyOld() {
    return this.year < 1930;
  }

  //a string showing the proper display of the media
  public String format() {
    return this.title + " (" + this.year + ")";
  }
}

// represents a TV episode
class TVEpisode extends AMedia {
  String showName;
  int seasonNumber;
  int episodeOfSeason;

  TVEpisode(String title, String showName, int seasonNumber, int episodeOfSeason,
      ILoString captionOptions) {
    super(title, captionOptions);
    this.showName = showName;
    this.seasonNumber = seasonNumber;
    this.episodeOfSeason = episodeOfSeason;

  }
  
  /* TEMPLATE 
  *  FIELDS:
  *  ... this.title ...                   -- String
  *  ... this.showName ...                -- String  
  *  ... this.seasonNumber...             -- int
  *  ... this.episodeOfSeason ...         -- int
  *  ... this.captionOptions ...          -- ILoString 
  *  METHODS:
  *  ... this.isReallyOld() ...         -- boolean 
  *  ... this.isCaptionAvailable ...    -- boolean 
  *  ... this.format() ...              -- boolean 
  *  METHODS FOR FIELDS:
  *  ... this.captionOptions.checkOptions(String) ...    -- boolean
  */

  //a string showing the proper display of the media
  public String format() {
    return this.showName + " " + this.seasonNumber  
          + "." + this.episodeOfSeason + " - " + this.title;
  }
}

// represents a YouTube video
class YTVideo extends AMedia {
  String channelName;

  public YTVideo(String title, String channelName, ILoString captionOptions) {
    super(title, captionOptions);
    this.channelName = channelName;
  }
  
  /* TEMPLATE 
  *  FIELDS:
  *  ... this.title ...                 -- String 
  *  ... this.channelName ...           -- String
  *  ... this.captionOptions ...        -- ILoString 
  *  METHODS:
  *  ... this.isReallyOld() ...         -- boolean 
  *  ... this.isCaptionAvailable ...    -- boolean 
  *  ... this.format() ...              -- boolean 
  *  METHODS FOR FIELDS:
  *  ... this.captionOptions.checkOptions(String) ...    -- boolean
  */
  
  //a string showing the proper display of the media
  public String format() {
    return this.title + " by " + this.channelName;
  }
}

// lists of strings
interface ILoString {

  boolean checkOption(String language);
}

// an empty list of strings
class MtLoString implements ILoString {
  MtLoString() {}

  public boolean checkOption(String language) {
    return false;
  }
}

// a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  // to check whether the given subtitle is in the list or not
  public boolean checkOption(String language) {
    if (this.first.equals(language)) {
      return true;
    }
    else {
      return this.rest.checkOption(language);
    }
  }
}

class ExamplesMedia {

  AMedia movieA = new Movie("Avatar", 2023, new MtLoString());

  AMedia movieB = new Movie("Iron Man", 2018, new ConsLoString("Spanish", new MtLoString()));

  AMedia tvEpisodeA = new TVEpisode("Friend or Woe", "Wednesday", 
        1, 3, new ConsLoString("Spanish", new MtLoString()));

  AMedia tvEpisodeB = new TVEpisode("A Fair World", "Squid Game", 
        1, 5, new ConsLoString("English", new ConsLoString("Spanish", new MtLoString())));

  AMedia ytVideoA = new YTVideo("Kill Bill (Official Video", "SZA", 
      new ConsLoString("English", new MtLoString()));

  AMedia ytVideoB = new YTVideo("lofi study playlist", "lofi", new MtLoString());
  
  ILoString subList = new ConsLoString("Spanish", new MtLoString());
  
  ILoString mtList = new MtLoString();

  boolean testisReallyOld(Tester t) {
    return 
      t.checkExpect(movieA.isReallyOld(), false)
      && t.checkExpect(tvEpisodeA.isReallyOld(), false);
  }

  boolean testisCaptionAvailable(Tester t) {
    return
      t.checkExpect(movieA.isCaptionAvailable("Spanish"), false)
      && t.checkExpect(ytVideoA.isCaptionAvailable("English"), true);
  }

  boolean testformat(Tester t) {
    return 
      t.checkExpect(movieB.format(), "Iron Man (2018)")
      && t.checkExpect(tvEpisodeB.format(), "Squid Game 1.5 - A Fair World");
  }

  boolean testCheckOption(Tester t) {
    return
      t.checkExpect(subList.checkOption("Spanish"), true)
      && t.checkExpect(mtList.checkOption("English"), false);
  }
}