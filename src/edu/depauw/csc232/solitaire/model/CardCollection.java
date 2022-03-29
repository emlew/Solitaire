package edu.depauw.csc232.solitaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Represent a collection of cards.
 * 
 * @author bhoward
 */
public class CardCollection
{
   /**
    * Construct an empty collection of cards.
    */
   public CardCollection()
   {
      cards = new ArrayList<Card>();
   }

   /**
    * Add a standard deck of 52 playing cards to this collection. May be called
    * multiple times to play with multiple decks.
    */
   public void addDeck()
   {
      for (Suit suit : Suit.values()) {
         addSuit(suit);
      }
   }

   /**
    * Add all 13 cards of the given suit to this collection, in order from Ace
    * to King.
    * 
    * @param suit
    */
   public void addSuit(Suit suit)
   {
      for (Rank rank : Rank.values()) {
         add(new Card(rank, suit));
      }
   }

   /**
    * Shuffle this collection into a random order, where all orderings are
    * equally likely.
    */
   public void shuffle()
   {
      Collections.shuffle(cards);
   }

   /**
    * Remove and return the topmost (most recently added) card from this
    * collection. If this collection is empty, throws
    * {@link NoSuchElementException}.
    * 
    * @return the former top card from this deck
    */
   public Card deal()
   {
      if (cards.isEmpty()) {
         throw new NoSuchElementException();
      }
      return cards.remove(cards.size() - 1);
   }

   /**
    * Add the given card to the top of this collection.
    * 
    * @param card
    */
   public void add(Card card)
   {
      cards.add(card);
   }

   /**
    * Check whether this collection is empty.
    * 
    * @return true if there are no cards in the collection
    */
   public boolean isEmpty()
   {
      return cards.isEmpty();
   }

   /**
    * Look at the card at the given index in this deck, where position 0 is at
    * the bottom of the deck. If there is no such card, throws
    * {@link NoSuchElementException}.
    * 
    * @param index
    *           position of desired card, starting from 0 at the bottom
    * @return the card at the given index
    */
   public Card get(int index)
   {
      if (index >= 0 && index < cards.size()) {
         return cards.get(index);
      }
      else {
         throw new NoSuchElementException();
      }
   }

   /**
    * Look at the topmost (most recently added) card of this collection. If this
    * collection is empty, throws {@link NoSuchElementException}.
    * 
    * @return the top card
    */
   public Card getTop()
   {
      return get(cards.size() - 1);
   }

   /**
    * Look at the bottommost card of this collection. If this collection is
    * empty, throws {@link NoSuchElementException}.
    * 
    * @return the bottom card
    */
   public Card getBottom()
   {
      return get(0);
   }

   /**
    * Flip over the topmost card of this collection. If this collection is
    * empty, throws {@link NoSuchElementException}.
    */
   public void flipTop()
   {
      getTop().flip();
   }

   /**
    * Clear out all of the cards from this collection, leaving it empty.
    */
   public void clear()
   {
      cards.clear();
   }

   /**
    * Get the number of cards in this collection.
    * 
    * @return the size of the collection
    */
   public int size()
   {
      return cards.size();
   }

   private ArrayList<Card> cards;
}
