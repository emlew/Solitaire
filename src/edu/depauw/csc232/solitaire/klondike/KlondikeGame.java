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
import edu.depauw.csc232.solitaire.ui.Pile;
import edu.depauw.csc232.solitaire.ui.PileStrategy;
import edu.depauw.csc232.solitaire.ui.Table;

/**
 * An implementation of the single-card-draw version of Klondike solitaire.
 * 
 * @author bhoward
 */
public class KlondikeGame implements Game
{
   /**
    * Construct the empty piles to be placed on the game table, and assign their
    * interaction strategies.
    */
   public KlondikeGame()
   {
      PileStrategy wasteStrategy = new WasteStrategy(this);
      waste = Pile.makeSquared(wasteStrategy);

      PileStrategy stockStrategy = new StockStrategy(this);
      stock = Pile.makeSquared(stockStrategy);

      PileStrategy tableauStrategy = new TableauStrategy(this);
      tableaus = new Pile[NUMBER_OF_TABLEAUS];
      for (int i = 0; i < NUMBER_OF_TABLEAUS; i++) {
         tableaus[i] = Pile.makeVertical(tableauStrategy);
      }

      PileStrategy foundationStrategy = new FoundationStrategy(this);
      foundations = new Pile[NUMBER_OF_FOUNDATIONS];
      for (int i = 0; i < NUMBER_OF_FOUNDATIONS; i++) {
         foundations[i] = Pile.makeSquared(foundationStrategy);
      }
   }

   public void checkWin()
   {
      for (Pile foundation : foundations) {
         if (foundation.size() != 13) {
            return;
         }
      }

      // All foundations have 13 cards -- we win!
      frame.showWin();
   }

   /**
    * Deal a deck of cards into the correct initial piles.
    */
   private void dealGame()
   {
      stock.addDeck();
      stock.shuffle();

      for (int i = 0; i < NUMBER_OF_TABLEAUS; i++) {
         for (int j = i; j < NUMBER_OF_TABLEAUS; j++) {
            tableaus[j].add(stock.deal());
         }

         // Flip the top cards in the tableau
         tableaus[i].flipTop();
      }
   }

   /**
    * Place the UI elements on the given Table.
    * 
    * @param table
    */
   private void layoutUI(Table table)
   {
      waste.setX(110);
      waste.setY(10);
      table.addItem(waste);

      stock.setX(10);
      stock.setY(10);
      table.addItem(stock);

      for (int i = 0; i < NUMBER_OF_TABLEAUS; i++) {
         tableaus[i].setX(10 + 100 * i);
         tableaus[i].setY(160);
         table.addItem(tableaus[i]);
      }

      for (int i = 0; i < NUMBER_OF_FOUNDATIONS; i++) {
         foundations[i].setX(310 + 100 * i);
         foundations[i].setY(10);
         table.addItem(foundations[i]);
      }
   }

   /**
    * Create and display a GameFrame initialized to play a game of Klondike.
    */
   public void start()
   {
      frame = new GameFrame("Klondike", 700, 600, table -> {
         dealGame();
         layoutUI(table);
      });

      frame.display();
   }

   public static void main(String[] args)
   {
      Game game = new KlondikeGame();
      game.start();
   }

   // TODO encapsulate these better?
   public final Pile waste;

   public final Pile stock;

   public final Pile[] tableaus;

   public final Pile[] foundations;

   private GameFrame frame;

   private static final int NUMBER_OF_FOUNDATIONS = 4;

   private static final int NUMBER_OF_TABLEAUS = 7;
}
