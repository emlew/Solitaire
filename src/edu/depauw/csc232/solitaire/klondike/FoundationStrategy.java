////////////////////////////////////////////////////////////////////////////////
// File:             FoundationStrategy.java
// Course:           CSC 232, Spring 2022
// Authors:          bhoward
//
// Acknowledgments:  None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.solitaire.klondike;

import java.util.List;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.model.Rank;
import edu.depauw.csc232.solitaire.ui.Pile;
import edu.depauw.csc232.solitaire.ui.PileStrategy;

/**
 * This PileStrategy encapsulates the rules for a foundation pile in Klondike.
 * Single cards may be dragged onto the pile if either the pile is empty and the
 * card is an ace, or the card's suit matches the top card and its value is one
 * greater.
 * 
 * @author bhoward
 */
class FoundationStrategy implements PileStrategy
{
   public FoundationStrategy(KlondikeGame game)
   {
   }

   @Override
   public boolean checkCanDrag(Pile foundation)
   {
      // Cards may not be dragged off the foundation
      return false;
   }

   @Override
   public boolean checkCanDrop(Pile foundation, List<Card> packet)
   {
      // Only allow single cards
      if (packet.size() > 1) {
         return false;
      }

      Card card = packet.get(packet.size() - 1);

      // Check for ace on empty foundation, or next card of same suit if
      // non-empty
      if (foundation.isEmpty()) {
         return card.getRank() == Rank.Ace;
      }
      else {
         Card top = foundation.getTop();
         return (card.getSuit() == top.getSuit())
            && (card.getValue() - 1 == top.getValue());
      }
   }
}
