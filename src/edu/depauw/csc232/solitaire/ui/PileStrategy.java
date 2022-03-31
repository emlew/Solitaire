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

import java.util.List;

import edu.depauw.csc232.solitaire.model.Card;

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
   default boolean checkCanDrag(Pile pile)
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
   default boolean checkCanDrop(Pile pile, List<Card> packet)
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
   default boolean checkStartDrag(Pile pile, List<Card> packet)
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
    */
   default void finishDrag(Pile origin, List<Card> packet, Pile target)
   {
   }

   /**
    * Respond to a click on card n of the given Pile. The bottom card is index
    * 0, and the top card is index pile.size()-1. If the pile is empty, n will
    * be -1.
    * 
    * @param pile
    * @param n
    */
   default void handleClick(Pile pile, int n)
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
