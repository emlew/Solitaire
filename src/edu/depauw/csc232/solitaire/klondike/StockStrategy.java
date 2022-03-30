package edu.depauw.csc232.solitaire.klondike;

import java.awt.event.MouseEvent;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.ui.CardStack;
import edu.depauw.csc232.solitaire.ui.PileStrategy;

/**
 * This PileStrategy encapsulates the rules for the stock pile in Klondike. The
 * only interaction allowed is to click on the stock, which deals one card into
 * the waste pile. If the stock is empty, then the waste pile is turned over to
 * replenish the stock.
 * 
 * @author bhoward
 */
public class StockStrategy implements PileStrategy
{
   /**
    * Construct the StockStrategy given a reference to the associated waste
    * pile.
    * 
    * @param waste
    */
   public StockStrategy(UI ui)
   {
      this.ui = ui;
   }

   @Override
   public boolean checkCanDrag(CardStack stock)
   {
      // Not allowed to drag cards off of the stock
      return false;
   }

   @Override
   public boolean checkCanDrop(CardStack stock, CardStack packet)
   {
      // Not allowed to drop a packet on the stock
      return false;
   }

   @Override
   public void handleClick(CardStack stock, MouseEvent event)
   {
      // If the stock is empty, turn over all of the cards from the waste pile
      // and refresh the stock
      if (stock.isEmpty()) {
         while (!ui.waste.isEmpty()) {
            Card card = ui.waste.deal();
            card.flip();
            stock.add(card);
         }
      }

      // If the stock is non-empty, deal one card face-up onto the waste pile
      if (!stock.isEmpty()) {
         Card card = stock.deal();
         card.flip();
         ui.waste.add(card);
      }
   }

   private UI ui;
}
