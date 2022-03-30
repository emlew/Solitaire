package edu.depauw.csc232.solitaire;

/**
 * An implementation of this interface provides a starting point for playing a
 * particular variation of solitaire. The intention is that each variation is
 * defined in its own package, where the only public class implements Game.
 * Creating an instance of such a class and calling its {@link #start() start}
 * method should cause a {@link edu.depauw.csc232.solitaire.ui.GameFrame
 * GameFrame} window to be initialized and displayed.
 * 
 * @author bhoward
 */
public interface Game
{
   /**
    * Initialize a new game and show its
    * {@link edu.depauw.csc232.solitaire.ui.GameFrame GameFrame}.
    */
   void start();
}