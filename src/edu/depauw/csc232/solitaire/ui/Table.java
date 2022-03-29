package edu.depauw.csc232.solitaire.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

/**
 * Manage a collection of Piles for a card game, and route mouse actions (click,
 * move, and drag/drop) to them.
 * 
 * @author bhoward
 */
public class Table extends JPanel {
	/**
	 * Construct an empty table, and initialize the mouse listeners.
	 */
	public Table(CardImages images) {
		this.images = images;
		this.piles = new ArrayList<>();

		MouseInputListener tableListener = new TableListener();
		addMouseListener(tableListener);
		addMouseMotionListener(tableListener);
	}

	/**
	 * Add the given Pile on top of (in drawing order) any existing piles.
	 * 
	 * @param pile
	 */
	public void addItem(Pile pile) {
		piles.add(pile);
	}

	/**
	 * Removes the given Pile from the table.
	 * 
	 * @param pile
	 * @return true if the pile was present
	 */
	public boolean removeItem(Pile pile) {
		return piles.remove(pile);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draw each of the piles on the table;
		// the highlighted pile, if present, gets a border
		for (Pile pile : piles) {
			Image image = pile.getImage(images);
			int x = pile.getX();
			int y = pile.getY();
			if (pile == highlightPile) {
				int w = image.getWidth(null);
				int h = image.getHeight(null);

				g.setColor(HIGHLIGHT_BORDER_COLOR);
				int borderX = x - HIGHLIGHT_BORDER_WIDTH;
				int borderY = y - HIGHLIGHT_BORDER_WIDTH;
				int borderW = w + HIGHLIGHT_BORDER_WIDTH * 2;
				int borderH = h + HIGHLIGHT_BORDER_WIDTH * 2;
				g.fillRoundRect(borderX, borderY, borderW, borderH, HIGHLIGHT_BORDER_WIDTH * 2,
						HIGHLIGHT_BORDER_WIDTH * 2);
			}
			g.drawImage(image, x, y, null);
		}

		// now draw the packet being dragged, if any
		// it is drawn on top, with a shadow underneath
		if (packet != null) {
			Image image = packet.getImage(images);
			int x = packet.getX();
			int y = packet.getY();
			int w = image.getWidth(null);
			int h = image.getHeight(null);

			g.setColor(DRAG_SHADOW_COLOR);
			g.fillRoundRect(x, y, w + SHADOW_WIDTH, h + SHADOW_WIDTH, SHADOW_WIDTH * 2, SHADOW_WIDTH * 2);
			g.drawImage(image, x, y, null);
		}
	}

	private CardImages images;
	private List<Pile> piles; // maintain this in z-order (back to front)
	private Pile highlightPile;
	private Packet packet;

	private static final int HIGHLIGHT_BORDER_WIDTH = 3;
	private static final int SHADOW_WIDTH = 5;

	private static final Color DRAG_SHADOW_COLOR = new Color(0, 0, 0, 64);
	private static final Color HIGHLIGHT_BORDER_COLOR = new Color(0, 0, 0);

	private static final Cursor MAIN_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	private static final Cursor DRAG_CURSOR = new Cursor(Cursor.HAND_CURSOR);

	/**
	 * A TableListener translates mouse input events into appropriate actions for a
	 * game table and its piles. It supports clicking, dragging, and dropping of
	 * piles.
	 * 
	 * @author bhoward
	 */
	final class TableListener extends MouseInputAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Pile pile = findPile(e);
			if (pile != null) {
				pile.handleClick(e);
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			point = e.getPoint();
			dragStarting = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (packet != null) {
				Pile pile = findPile(e);
				if (pile != null && pile.canDrop(packet, e)) {
					packet.endDrag(pile, e);
				} else {
					packet.cancelDrag(e);
				}
				packet = null;
				highlightPile = null;

				repaint();
			}
			dragStarting = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (dragStarting) {
				Pile pile = findPile(e);
				if (pile != null && pile.canDrag(e)) {
					packet = pile.startDrag(e);
				}
				dragStarting = false;
			}

			if (packet != null) {
				Point point2 = e.getPoint();
				int dx = point2.x - point.x;
				int dy = point2.y - point.y;
				packet.setX(packet.getX() + dx);
				packet.setY(packet.getY() + dy);
				point = point2;

				Pile pile = findPile(e);
				if (pile != null && pile.canDrop(packet, e)) {
					highlightPile = pile;
				} else {
					highlightPile = null;
				}

				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Pile pile = findPile(e);
			if (pile != null && pile.canDrag(e)) {
				setCursor(DRAG_CURSOR);
			} else {
				setCursor(MAIN_CURSOR);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (packet != null) {
				packet.cancelDrag(e);
				packet = null;
				highlightPile = null;

				repaint();
			}
		}

		private Pile findPile(MouseEvent e) {
			for (int i = piles.size() - 1; i >= 0; i--) {
				Pile pile = piles.get(i);
				if (pile.underMouse(e)) {
					return pile;
				}
			}

			return null;
		}

		private Point point;
		private boolean dragStarting;
	}
}
