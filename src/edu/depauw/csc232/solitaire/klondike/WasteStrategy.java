////////////////////////////////////////////////////////////////////////////////
// File:             WasteStrategy.java
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
import edu.depauw.csc232.solitaire.ui.Pile;
import edu.depauw.csc232.solitaire.ui.PileStrategy;

/**
 * This PileStrategy encapsulates the rules for the waste pile in Klondike. The
 * only interaction allowed is to drag the top card to a tableau or foundation
 * pile.
 * 
 * @author bhoward
 */
class WasteStrategy implements PileStrategy
{
   public WasteStrategy(KlondikeGame game)
   {
      this.game = game;
   }

   @Override
   public boolean checkCanDrag(CardStack waste)
   {
      // Allow drag if not empty
      return !waste.isEmpty();
   }

   @Override
   public boolean checkCanDrop(CardStack waste, CardStack packet)
   {
      // Drops are not allowed
      return false;
   }

   @Override
   public boolean checkStartDrag(CardStack waste, CardStack packet)
   {
      // Nothing to check -- only one card may be dragged since pile is squared
      return true;
   }

   @Override
   public void finishDrag(CardStack origin, CardStack packet, CardStack target,
            MouseEvent event)
   {
      // Check for winning the game after playing a card from the waste pile
      game.checkWin();
   }

   @Override
   public void handleClick(CardStack waste, MouseEvent event)
   {
      // Search for a place to move the card
      if (!waste.isEmpty()) {
         Card card = waste.getTop();

         for (Pile foundation : game.foundations) {
            // Check for ace on empty foundation, or next card of same suit if
            // non-empty
            if (foundation.isEmpty()) {
               if (card.getRank() != Rank.Ace) {
                  continue;
               }
            }
            else {
               Card top = foundation.getTop();

               if (card.getSuit() != top.getSuit()
                  || card.getValue() - 1 != top.getValue()) {
                  continue;
               }
            }

            // If we get here, the card from waste may be played on the
            // foundation
            foundation.add(waste.deal());
            game.checkWin();
            break;
         }
      }
   }

   private final KlondikeGame game;
}
