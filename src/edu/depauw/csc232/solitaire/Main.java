package edu.depauw.csc232.solitaire;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

import edu.depauw.csc232.solitaire.klondike.KlondikeGame;

/**
 * 
 * @author bhoward
 */
public class Main
{
   public static void main(String[] args)
   {
      Main main = new Main();
      main.start();
   }

   public void start()
   {
      JFrame frame = new JFrame("CSC232 Solitaire");

      Box buttons = Box.createVerticalBox();
      frame.add(buttons);

      JButton klondike = new JButton("Klondike");
      klondike.setAlignmentX(JButton.CENTER_ALIGNMENT);
      klondike.addActionListener(event -> {
         Game game = new KlondikeGame();
         game.start();
         frame.setVisible(false);
      });
      buttons.add(klondike);

      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(300, 200);
      frame.setVisible(true);
   }
}
