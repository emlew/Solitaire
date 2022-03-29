package edu.depauw.csc232.solitaire.klondike;

import java.awt.event.MouseEvent;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.model.CardCollection;
import edu.depauw.csc232.solitaire.model.Rank;
import edu.depauw.csc232.solitaire.ui.Packet;
import edu.depauw.csc232.solitaire.ui.PileStrategy;
import edu.depauw.csc232.solitaire.ui.Pile;

/**
 * This PileStrategy encapsulates the rules for a tableau pile in Klondike. Any
 * number of face-up cards may be dragged off of the tableau, and cards may be
 * dropped if they are alternating red/black and decreasing in value.
 * 
 * @author bhoward
 */
public class TableauStrategy implements PileStrategy
{
   @Override
   public boolean checkCanDrag(Pile tableau)
   {
      // Allow drag if not empty
      return !tableau.isEmpty();
   }

   @Override
   public boolean checkCanDrop(Pile tableau, Packet packet)
   {
      // Bottom card of packet must have opposite color and one-less value of
      // top
      // card in tableau, or bottom card is a King and the tableau is empty
      if (tableau.isEmpty()) {
         Card bottom = packet.getBottom();
         return bottom.getRank() == Rank.King;
      }
      else {
         Card top = tableau.getTop();
         Card bottom = packet.getBottom();
         return top.isFaceUp() && (top.getSuit()
                                      .isRed() != bottom.getSuit()
                                                        .isRed())
            && (top.getRank()
                   .getValue()
               - 1 == bottom.getRank()
                            .getValue());
      }
   }

   @Override
   public boolean checkStartDrag(Pile tableau, CardCollection cards)
   {
      // Check that all of the cards are face-up
      for (int i = 0; i < cards.size(); i++) {
         Card card = cards.get(i);
         if (!card.isFaceUp()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void handleClick(Pile tableau, MouseEvent event)
   {
      // Do nothing (future extension -- search for a place to move the top
      // card)
   }

   @Override
   public void finishDrag(Pile tableau, Packet packet, Pile target,
            MouseEvent event)
   {
      // Flip over an exposed top card, if any
      if (!tableau.isEmpty() && !tableau.getTop()
                                        .isFaceUp()) {
         Card top = tableau.deal();
         top.flip();
         tableau.add(top);
      }
   }
}
