////////////////////////////////////////////////////////////////////////////////
// File:             TableauStrategy.java
// Course:           CSC 232, Spring 2022
// Authors:          bhoward
//
// Acknowledgments:  None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.solitaire.klondike;

import java.awt.event.MouseEvent;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.model.Rank;
import edu.depauw.csc232.solitaire.ui.CardStack;
import edu.depauw.csc232.solitaire.ui.PileStrategy;

/**
 * This PileStrategy encapsulates the rules for a tableau pile in Klondike. Any
 * number of face-up cards may be dragged off of the tableau, and cards may be
 * dropped if they are alternating red/black and decreasing in value.
 * 
 * @author bhoward
 */
class TableauStrategy implements PileStrategy
{
   public TableauStrategy(UI ui)
   {
   }

   @Override
   public boolean checkCanDrag(CardStack tableau)
   {
      // Allow drag if not empty
      return !tableau.isEmpty();
   }

   @Override
   public boolean checkCanDrop(CardStack tableau, CardStack packet)
   {
      // Bottom card of packet must have opposite color and one-less value of
      // top card in tableau, or bottom card is a King and the tableau is empty
      if (tableau.isEmpty()) {
         Card bottom = packet.getBottom();
         return bottom.getRank() == Rank.King;
      }
      else {
         Card top = tableau.getTop();
         Card bottom = packet.getBottom();
         return top.isFaceUp() && (top.isRed() != bottom.isRed())
            && (top.getValue() - 1 == bottom.getValue());
      }
   }

   @Override
   public boolean checkStartDrag(CardStack tableau, CardStack packet)
   {
      // Check that all of the selected cards are face-up
      return packet.allFaceUp();
   }

   @Override
   public void finishDrag(CardStack tableau, CardStack packet, CardStack target,
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

   @Override
   public void handleClick(CardStack tableau, MouseEvent event)
   {
      // TODO search for a place to move the top card
   }
}
