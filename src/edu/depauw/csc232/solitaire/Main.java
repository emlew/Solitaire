package edu.depauw.csc232.solitaire;

import javax.swing.JButton;
import javax.swing.JFrame;

import edu.depauw.csc232.solitaire.klondike.KlondikeGame;

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

      JButton klondike = new JButton("Klondike");
      klondike.addActionListener(event -> {
         Game game = new KlondikeGame();
         game.start();
         frame.setVisible(false);
      });
      frame.add(klondike);

      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(300, 200);
      frame.setVisible(true);
   }
}
