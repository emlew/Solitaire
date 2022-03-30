////////////////////////////////////////////////////////////////////////////////
// File:             PileStrategy.java
// Course:           CSC 232, Spring 2022
// Authors:          bhoward
//
// Acknowledgments:  None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.solitaire.ui;

import java.awt.event.MouseEvent;

/**
 * A PileStrategy permits customization of the behavior of a {@link Pile} in
 * response to various actions (clicks and drag/drops).
 * 
 * @author bhoward
 */
public interface PileStrategy
{
   /**
    * Check whether the given Pile allows packets of one or more cards to be
    * dragged off of it. To avoid lag, this should do minimal computation and
    * return quickly.
    * 
    * @param pile
    * @return true if the Pile allows dragging
    */
   default boolean checkCanDrag(CardStack pile)
   {
      return true;
   }

   /**
    * Check whether the given Pile will allow a particular Packet to be dropped
    * on it. To avoid lag, this should do minimal computation and return
    * quickly.
    * 
    * @param pile
    * @param packet
    * @return true if the Pile will allow the Packet to be dropped
    */
   default boolean checkCanDrop(CardStack pile, CardStack packet)
   {
      return true;
   }

   /**
    * Check whether the given Packet may be dragged off of the Pile.
    * 
    * @param pile
    * @param packet
    * @return true if the given Packet may be dragged away from the Pile
    */
   default boolean checkStartDrag(CardStack pile, CardStack packet)
   {
      return true;
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
   default void finishDrag(CardStack origin, CardStack packet, CardStack target,
            MouseEvent event)
   {
   }

   /**
    * Respond to a click on the given Pile.
    * 
    * @param pile
    * @param event
    */
   default void handleClick(CardStack pile, MouseEvent event)
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
