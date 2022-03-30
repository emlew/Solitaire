package edu.depauw.csc232.solitaire.ui;

import java.io.IOException;

/**
 * A simple demonstration of using Piles on a Table. Cards may be dragged from
 * one pile to the other.
 * 
 * @author bhoward
 */
public class GUIDemo
{
   public static void main(String[] args) throws IOException
   {
      GameFrame frame = new GameFrame("Demo", 800, 600, table -> {
         PileStrategy strategy = PileStrategy.makeDefault();

         Pile pile1 = Pile.makeHorizontal(strategy);
         pile1.addDeck();
         pile1.flipAll();
         
         pile1.setX(50);
         pile1.setY(100);
         table.addItem(pile1);

         Pile pile2 = Pile.makeVertical(strategy);
         
         pile2.setX(50);
         pile2.setY(300);
         table.addItem(pile2);
      });

      frame.display();
   }
}
