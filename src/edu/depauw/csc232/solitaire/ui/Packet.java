package edu.depauw.csc232.solitaire.ui;

import java.awt.event.MouseEvent;
import java.util.List;

import edu.depauw.csc232.solitaire.model.Card;

/**
 * A Packet is a CardStack that contains a collection of cards currently being
 * dragged. The collection will not be empty.
 * 
 * @author bhoward
 */
public class Packet extends CardStack
{
   // Package-private constructor
   Packet(Pile origin, int horizontal, int vertical)
   {
      super(horizontal, vertical);
      this.origin = origin;
   }

   /**
    * Notify this packet that it has been dropped on the given pile. The cards
    * are added to the target pile, and the origin is notified of the drag
    * completion.
    * 
    * @param target
    *           the target of the drop
    * @param event
    *           the mouseReleased event for the drop
    */
   public void endDrag(CardStack target, MouseEvent event)
   {
      target.addAll(cards);
      origin.finishDrag(this, target, event);
   }

   /**
    * Notify this packet that its current drag motion has been cancelled. The
    * cards are returned to the origin pile.
    * 
    * @param event
    *           the mouse event that cancelled the drag
    */
   public void cancelDrag(MouseEvent event)
   {
      origin.addAll(cards);
   }

   /**
    * @return the pile from which this packet was dragged
    */
   public CardStack getOrigin()
   {
      return origin;
   }

   private Pile origin;
}
