package edu.depauw.csc232.solitaire.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import edu.depauw.csc232.solitaire.model.Card;
import edu.depauw.csc232.solitaire.model.CardCollection;

/**
 * A CardStack is an object that displays a collection of cards. It keeps track
 * of the position and image to be displayed. It is a common superclass of Pile
 * (stack of cards on a Table) and Packet (stack of cards being dragged).
 * 
 * @author bhoward
 */
public abstract class CardStack
{
   /**
    * Construct a CardStack for the given packet, where each successive card is
    * offset by the given amounts horizontally and vertically.
    * 
    * @param cards
    * @param horizontal
    * @param vertical
    */
   protected CardStack(CardCollection cards, int horizontal, int vertical)
   {
      this.cards = cards;
      xOFFSET = horizontal * HOFFSET;
      yOFFSET = vertical * VOFFSET;
   }

   /**
    * Add one card to this stack.
    * 
    * @param card
    */
   public void add(Card card)
   {
      cards.add(card);
      invalidateImage();
   }

   /**
    * Add all of the cards from a collection to this stack.
    * 
    * @param other
    */
   public void addAll(CardCollection other)
   {
      for (int i = 0; i < other.size(); i++) {
         cards.add(other.get(i));
      }
      invalidateImage();
   }

   /**
    * Deal one card off the top of this stack.
    * 
    * @return the card
    */
   public Card deal()
   {
      Card card = cards.deal();
      invalidateImage();
      return card;
   }

   /**
    * @return the top card in the stack
    */
   public Card getTop()
   {
      return cards.getTop();
   }

   /**
    * @return the bottom card in the stack
    */
   public Card getBottom()
   {
      return cards.getBottom();
   }

   /**
    * @return true if the stack is empty.
    */
   public boolean isEmpty()
   {
      return cards.isEmpty();
   }

   /**
    * @return the number of cards in the stack
    */
   public int size()
   {
      return cards.size();
   }

   /**
    * @return the current x-coordinate of the upper-left corner of this stack
    */
   public int getX()
   {
      return x;
   }

   /**
    * @param x
    *           the new x-coordinate of the upper-left corner of this stack
    */
   public void setX(int x)
   {
      this.x = x;
   }

   /**
    * @return the current y-coordinate of the upper-left corner of this stack
    */
   public int getY()
   {
      return y;
   }

   /**
    * @param y
    *           the new y-coordinate of the upper-left corner of this stack
    */
   public void setY(int y)
   {
      this.y = y;
   }

   /**
    * @return the image to display for this stack
    */
   public Image getImage(CardImages images)
   {
      if (cachedImage == null) {
         if (cards.isEmpty()) {
            cachedImage = images.getImage(null);
         }
         else if (xOFFSET == 0 && yOFFSET == 0) {
            cachedImage = images.getImage(cards.getTop());
         }
         else {
            Image top = images.getImage(cards.getTop());
            int width = top.getWidth(null) + xOFFSET * (cards.size() - 1);
            int height = top.getHeight(null) + yOFFSET * (cards.size() - 1);
            cachedImage = new BufferedImage(width, height,
                     BufferedImage.TYPE_INT_ARGB);

            Graphics g = cachedImage.getGraphics();
            for (int i = 0; i < cards.size(); i++) {
               Card card = cards.get(i);
               Image cardImage = images.getImage(card);
               g.drawImage(cardImage, xOFFSET * i, yOFFSET * i, null);
            }
         }
      }

      return cachedImage;
   }

   protected Image getCachedImage()
   {
      return cachedImage;
   }

   /**
    * This should be called whenever the underlying collection of cards has
    * changed.
    */
   protected void invalidateImage()
   {
      cachedImage = null;
   }

   private int x;
   private int y;

   protected static int HOFFSET = 12;
   protected static int VOFFSET = 18;

   protected int xOFFSET;
   protected int yOFFSET;

   protected CardCollection cards;

   private Image cachedImage;
}
