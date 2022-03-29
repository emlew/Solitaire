package edu.depauw.csc232.solitaire.model;

/**
 * Represent a card from an ordinary 52-card deck, including a rank, a suit, and
 * whether the card is face-up or face-down. The rank and suit are immutable,
 * but the face-up/face-down status may be changed.
 * 
 * @author bhoward
 */
public class Card
{
   /**
    * Construct a card with the given rank and suit; it is initially face-down.
    * 
    * @param rank
    * @param suit
    */
   public Card(Rank rank, Suit suit)
   {
      this.rank = rank;
      this.suit = suit;
      this.faceUp = false;
   }

   /**
    * Construct a card with the given rank, suit, and face-up status.
    * 
    * @param rank
    * @param suit
    * @param faceUp
    *           true if the card is initially face-up
    */
   public Card(Rank rank, Suit suit, boolean faceUp)
   {
      this.rank = rank;
      this.suit = suit;
      this.faceUp = faceUp;
   }

   public Rank getRank()
   {
      return rank;
   }

   /**
    * @return the value (1 to 13) corresponding to this card's rank
    */
   public int getValue()
   {
      return rank.getValue();
   }

   public Suit getSuit()
   {
      return suit;
   }

   /**
    * @return true if this card's suit is red (heart or diamond)
    */
   public boolean isRed()
   {
      return suit.isRed();
   }

   public boolean isFaceUp()
   {
      return faceUp;
   }

   public String getAbbrev()
   {
      return "" + rank.getAbbrev() + suit.getAbbrev();
   }

   /**
    * Change the state (face-up/face-down) by flipping the card over.
    */
   public void flip()
   {
      faceUp = !faceUp;
   }

   @Override
   public String toString()
   {
      return "Card [rank=" + rank + ", suit=" + suit + ", faceUp=" + faceUp
         + "]";
   }

   private Rank rank;
   private Suit suit;
   private boolean faceUp;
}
