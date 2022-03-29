package edu.depauw.csc232.solitaire.klondike;

import java.awt.event.MouseEvent;

import edu.depauw.csc232.solitaire.model.CardCollection;
import edu.depauw.csc232.solitaire.ui.Packet;
import edu.depauw.csc232.solitaire.ui.PileStrategy;
import edu.depauw.csc232.solitaire.ui.Pile;

/**
 * This PileStrategy encapsulates the rules for the waste pile in Klondike. The
 * only interaction allowed is to drag the top card to a tableau or foundation
 * pile.
 * 
 * @author bhoward
 */
public class WasteStrategy implements PileStrategy
{
   @Override
   public boolean checkCanDrag(Pile waste)
   {
      // Allow drag if not empty
      return !waste.isEmpty();
   }

   @Override
   public boolean checkCanDrop(Pile waste, Packet packet)
   {
      // Drops are not allowed
      return false;
   }

   @Override
   public boolean checkStartDrag(Pile waste, CardCollection cards)
   {
      // Nothing to check -- only one card may be dragged since pile is squared
      return true;
   }

   @Override
   public void handleClick(Pile waste, MouseEvent event)
   {
      // Do nothing (future extension -- search for a place to move the card)
   }
}
