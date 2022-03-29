package edu.depauw.csc232.solitaire.klondike;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.model.Rank;
import edu.depauw.csc232.solitaire.ui.Packet;
import edu.depauw.csc232.solitaire.ui.PileStrategy;
import edu.depauw.csc232.solitaire.ui.Pile;

/**
 * This PileStrategy encapsulates the rules for a foundation pile in Klondike.
 * Single cards may be dragged onto the pile if either the pile is empty and the
 * card is an ace, or the card's suit matches the top card and its value is one
 * greater.
 * 
 * @author bhoward
 */
public class FoundationStrategy implements PileStrategy
{
   @Override
   public boolean checkCanDrag(Pile foundation)
   {
      // Cards may not be dragged off the foundation
      return false;
   }

   @Override
   public boolean checkCanDrop(Pile foundation, Packet packet)
   {
      // Only allow single cards
      if (packet.size() > 1) {
         return false;
      }

      Card card = packet.getTop();

      // Check for ace on empty foundation, or next card of same suit if
      // non-empty
      if (foundation.isEmpty()) {
         return card.getRank() == Rank.Ace;
      }
      else {
         Card top = foundation.getTop();
         return (card.getSuit() == top.getSuit()) && (card.getRank()
                                                          .getValue()
            - 1 == top.getRank()
                      .getValue());
      }
   }
}
