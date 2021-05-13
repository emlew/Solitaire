package edu.depauw.csc232.solitaire.ui;

import java.io.IOException;

import edu.depauw.csc232.solitaire.model.CardCollection;

/**
 * A simple demonstration of using Piles on a Table. Cards may be dragged
 * from one pile to the other.
 * 
 * @author bhoward
 */
public class GUIDemo {
	public static void main(String[] args) throws IOException {
		GameFrame frame = new GameFrame("Demo", 800, 600, table -> {
				CardCollection cards1 = new CardCollection();
				cards1.addDeck();
				for (int i = 0; i < cards1.size(); i++) {
					cards1.get(i).flip();
				}

				PileStrategy strategy = PileStrategy.makeDefault();
				
				Pile pile1 = Pile.makeHorizontal(cards1, strategy);
				pile1.setX(50);
				pile1.setY(100);
				table.addItem(pile1);

				CardCollection cards2 = new CardCollection();
				Pile pile2 = Pile.makeVertical(cards2, strategy);
				pile2.setX(50);
				pile2.setY(300);
				table.addItem(pile2);
			}
		);

		frame.display();
	}
}
