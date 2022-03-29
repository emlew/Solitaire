package edu.depauw.csc232.solitaire.ui;

import java.awt.event.MouseEvent;

import edu.depauw.csc232.solitaire.model.CardCollection;

/**
 * A PileStrategy permits customization of the behavior of a {@link Pile} in
 * response to various actions (clicks and drag/drops).
 * 
 * @author bhoward
 */
public interface PileStrategy
{
   /**
    * @param pile
    * @return true if the Pile allows dragging
    */
   default boolean checkCanDrag(Pile pile)
   {
      return true;
   }

   /**
    * @param pile
    * @param item
    * @return true if the Pile will allow the Packet to be dropped
    */
   default boolean checkCanDrop(Pile pile, Packet packet)
   {
      return true;
   }

   /**
    * @param pile
    * @param cards
    * @return true if the given CardCollection may be dragged away from the Pile
    */
   default boolean checkStartDrag(Pile pile, CardCollection cards)
   {
      return true;
   }

   /**
    * Respond to a click on the given Pile.
    * 
    * @param pile
    * @param event
    */
   default void handleClick(Pile pile, MouseEvent event)
   {
   }

   /**
    * Perform clean-up on the origin and target Piles after a drag is completed.
    * The cards will have already been added to the target by the time this is
    * called.
    * 
    * @param origin
    * @param packet
    * @param target
    * @param event
    */
   default void finishDrag(Pile origin, Packet packet, Pile target,
            MouseEvent event)
   {
   }

   /**
    * @return a PileStrategy where all actions are allowed and nothing extra
    *         happens in response
    */
   static PileStrategy makeDefault()
   {
      return new PileStrategy()
      {
      };
   }
}
