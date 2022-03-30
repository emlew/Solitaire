package edu.depauw.csc232.solitaire.klondike;

import edu.depauw.csc232.solitaire.ui.Pile;
import edu.depauw.csc232.solitaire.ui.PileStrategy;
import edu.depauw.csc232.solitaire.ui.Table;

public class UI
{
   public Pile waste;
   public Pile stock;
   public Pile[] tableau;
   public Pile[] foundation;

   public UI()
   {
      PileStrategy wasteStrategy = new WasteStrategy();
      waste = Pile.makeSquared(wasteStrategy);
      
      PileStrategy stockStrategy = new StockStrategy(this);
      stock = Pile.makeSquared(stockStrategy);

      PileStrategy tableauStrategy = new TableauStrategy();
      tableau = new Pile[7];
      for (int i = 0; i < 7; i++) {
         tableau[i] = Pile.makeVertical(tableauStrategy);
      }

      PileStrategy foundationStrategy = new FoundationStrategy();
      foundation = new Pile[4];
      for (int i = 0; i < 4; i++) {
         foundation[i] = Pile.makeSquared(foundationStrategy);
      }
   }

   public void dealGame()
   {
      stock.addDeck();
      stock.shuffle();
      
      for (int i = 0; i < tableau.length; i++) {
         for (int j = i; j < tableau.length; j++) {
            tableau[j].add(stock.deal());
         }
   
         // Flip the top cards in the tableau
         tableau[i].flipTop();
      }
   }
   
   public void layoutUI(Table table)
   {
      waste.setX(110);
      waste.setY(10);
      table.addItem(waste);
   
      stock.setX(10);
      stock.setY(10);
      table.addItem(stock);
   
      for (int i = 0; i < tableau.length; i++) {
         tableau[i].setX(10 + 100 * i);
         tableau[i].setY(160);
         table.addItem(tableau[i]);
      }
   
      for (int i = 0; i < foundation.length; i++) {
         foundation[i].setX(310 + 100 * i);
         foundation[i].setY(10);
         table.addItem(foundation[i]);
      }
   }
}
