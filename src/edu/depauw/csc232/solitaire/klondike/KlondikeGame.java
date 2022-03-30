////////////////////////////////////////////////////////////////////////////////
// File:             KlondikeGame.java
// Course:           CSC 232, Spring 2022
// Authors:          bhoward
//
// Acknowledgments:  None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.solitaire.klondike;

import edu.depauw.csc232.solitaire.Game;
import edu.depauw.csc232.solitaire.ui.GameFrame;

/**
 * An implementation of the single-card-draw version of Klondike solitaire.
 * 
 * @author bhoward
 */
public class KlondikeGame implements Game
{
   /**
    * Create and display a GameFrame initialized to play a game of Klondike.
    */
   public void start()
   {
      GameFrame frame = new GameFrame("Klondike", 700, 600, table -> {
         UI ui = new UI();

         ui.dealGame();
         ui.layoutUI(table);
      });

      frame.display();
   }

   public static void main(String[] args)
   {
      Game game = new KlondikeGame();
      game.start();
   }
}
