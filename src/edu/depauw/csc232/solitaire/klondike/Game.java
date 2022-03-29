package edu.depauw.csc232.solitaire.klondike;

import edu.depauw.csc232.solitaire.model.CardCollection;
import edu.depauw.csc232.solitaire.ui.GameFrame;
import edu.depauw.csc232.solitaire.ui.PileStrategy;
import edu.depauw.csc232.solitaire.ui.Pile;

/**
 * An implementation of the single-card-draw version of Klondike solitaire.
 * 
 * @author bhoward
 */
public class Game {

	public static void main(String[] args) {
		GameFrame frame = new GameFrame("Klondike", 700, 600, table -> {
			// Create the card collections
			CardCollection wasteCards = new CardCollection();

			CardCollection stockCards = new CardCollection();
			stockCards.addDeck();
			stockCards.shuffle();

			CardCollection[] tableauCards = new CardCollection[7];
			for (int i = 0; i < tableauCards.length; i++) {
				tableauCards[i] = new CardCollection();
			}

			CardCollection[] foundationCards = new CardCollection[4];
			for (int i = 0; i < foundationCards.length; i++) {
				foundationCards[i] = new CardCollection();
			}

			// Deal the cards
			for (int i = 0; i < tableauCards.length; i++) {
				for (int j = i; j < tableauCards.length; j++) {
					tableauCards[j].add(stockCards.deal());
				}

				// Flip the top cards in the tableau
				tableauCards[i].flipTop();
			}

			// Create the UI
			PileStrategy wasteStrategy = new WasteStrategy();
			Pile waste = Pile.makeSquared(wasteCards, wasteStrategy);
			waste.setX(110);
			waste.setY(10);
			table.addItem(waste);

			PileStrategy stockStrategy = new StockStrategy(waste);
			Pile stock = Pile.makeSquared(stockCards, stockStrategy);
			stock.setX(10);
			stock.setY(10);
			table.addItem(stock);

			PileStrategy tableauStrategy = new TableauStrategy();
			for (int i = 0; i < tableauCards.length; i++) {
				Pile tableau = Pile.makeVertical(tableauCards[i], tableauStrategy);
				tableau.setX(10 + 100 * i);
				tableau.setY(160);
				table.addItem(tableau);
			}

			PileStrategy foundationStrategy = new FoundationStrategy();
			for (int i = 0; i < foundationCards.length; i++) {
				Pile foundation = Pile.makeSquared(foundationCards[i], foundationStrategy);
				foundation.setX(310 + 100 * i);
				foundation.setY(10);
				table.addItem(foundation);
			}
		});

		frame.display();
	}

}
