////////////////////////////////////////////////////////////////////////////////
// File:             StockStrategy.java
// Course:           CSC 232, Spring 2022
// Authors:          bhoward
//
// Acknowledgments:  None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.solitaire.klondike;

import java.awt.event.MouseEvent;
import java.util.List;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.ui.Pile;
import edu.depauw.csc232.solitaire.ui.PileStrategy;

/**
 * This PileStrategy encapsulates the rules for the stock pile in Klondike. The
 * only interaction allowed is to click on the stock, which deals one card into
 * the waste pile. If the stock is empty, then the waste pile is turned over to
 * replenish the stock.
 * 
 * @author bhoward
 */
class StockStrategy implements PileStrategy
{
   /**
    * Construct the StockStrategy with a reference to the waste pile out of the
    * given {@link edu.depauw.csc232.solitaire.klondike.KlondikeGame
    * KlondikeGame}.
    * 
    * @param game
    */
   public StockStrategy(KlondikeGame game)
   {
      this.game = game;
   }

   @Override
   public boolean checkCanDrag(Pile stock)
   {
      // Not allowed to drag cards off of the stock
      return false;
   }

   @Override
   public boolean checkCanDrop(Pile stock, List<Card> packet)
   {
      // Not allowed to drop a packet on the stock
      return false;
   }

   @Override
   public void handleClick(Pile stock, MouseEvent event)
   {
      Pile waste = game.waste;

      if (stock.isEmpty()) {
         // If the stock is empty, turn over all of the cards from the waste
         // pile and refresh the stock
         while (!waste.isEmpty()) {
            Card card = waste.deal();
            card.flip();
            stock.add(card);
         }
      }
      else {
         // If the stock is non-empty, deal one card face-up onto the waste pile
         Card card = stock.deal();
         card.flip();
         waste.add(card);
      }
   }

   private final KlondikeGame game;
}
