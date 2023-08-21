package view.game.cards;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.game.cards.*;
import model.cards.Card;
import model.cards.JollyCard;
import model.cards.NumberedCard;
import model.cards.PlusFourCard;
import model.cards.PlusTwoCard;
import model.cards.ReverseCard;
import model.cards.SkipCard;
/**
 * This class represents a card held by a player in his hand. It is also used to construct the {@link CardOnTableView} instances.
 * For computer-controlled players, {@link EmptyCard} is used.
 * @author pierp
 *
 */
public final class CardInHandView extends JLabel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The card used for constructing this class.
	 */
	private Card card;
	/**
	 * One of the two <code>MouseListener</code>s of the card.
	 */
	private CardHandler cardClicked=null, cardSelected=null;
	/**
	 * The constructor for this class.
	 * @param c The card to be represented.
	 * @param rotation The rotation of the card (0 = 0°, 1 = 90°, 2 = 180°, 3 = 270°).
	 */
	public CardInHandView(Card c, int rotation) {
		card = c;
		if(rotation == 0 || rotation == 2) setSize(70, 105);
		else setSize(105, 70);
		setOpaque(false);
		
		String cardName = "";
		if(c instanceof JollyCard) cardName = ((JollyCard)c).toString();
		else if(c instanceof NumberedCard) cardName = ((NumberedCard)c).toString();
		else if(c instanceof PlusFourCard) cardName = ((PlusFourCard)c).toString();
		else if(c instanceof PlusTwoCard) cardName = ((PlusTwoCard)c).toString();
		else if(c instanceof ReverseCard) cardName = ((ReverseCard)c).toString();
		else if(c instanceof SkipCard) cardName = ((SkipCard)c).toString();
		
		try {
			if(!(c instanceof EmptyCard)) {
				setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/"+cardName.toLowerCase()+".png")).getScaledInstance(70, 105, 0)));
				cardClicked = new CardClicked();
				cardSelected = new CardSelected();
				addMouseListener(cardClicked);
				addMouseListener(cardSelected);
			}
			else
				if(rotation == 1) setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/player 1 background.png")).getScaledInstance(105, 75, 0)));
				else if(rotation == 2) {
					setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/player 2 background.png")).getScaledInstance(70, 105, 0)));
				}
				else if(rotation == 3) setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/player 3 background.png")).getScaledInstance(105, 70, 0)));
				else if(rotation == 0) setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/background.png")).getScaledInstance(70, 105, 0)));
			}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * The constructor used to instantiate the cards for constructing {@link CardOnTableView} instances.
	 * @param c The card to be represented.
	 */
	protected CardInHandView(Card c) {
		card=c;
		setSize(100,150);
		setOpaque(false);
		
		try {
			if(!(c instanceof EmptyCard)) {
				String cardName = "";
				if(c instanceof JollyCard) cardName = ((JollyCard)c).toString();
				else if(c instanceof NumberedCard) cardName = ((NumberedCard)c).toString();
				else if(c instanceof PlusFourCard) cardName = ((PlusFourCard)c).toString();
				else if(c instanceof PlusTwoCard) cardName = ((PlusTwoCard)c).toString();
				else if(c instanceof ReverseCard) cardName = ((ReverseCard)c).toString();
				else if(c instanceof SkipCard) cardName = ((SkipCard)c).toString();
				setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/"+cardName.toLowerCase()+".png")).getScaledInstance(100, 150, 0)));
			}
			else {
				setIcon(
						(Icon) new ImageIcon(ImageIO.read(
								new File("src/resources/cards/background.png")).getScaledInstance(100, 150, 0)));
				cardClicked = new DeckClicked();
				addMouseListener(cardClicked);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Getter for the card stored in this class.
	 * @return The card.
	 */
	public Card getCard() {
		return card;
	}
	/**
	 * This method is used for making the card appear bigger when the mouse hovers on it.
	 */
	public void enlargeCard() {
		try {
			setIcon(
					(Icon) new ImageIcon(ImageIO.read(
							new File("src/resources/cards/"+card.toString().toLowerCase()+".png")).getScaledInstance(100, 150, 0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(100, 150);
		setLocation(getX()-20,getY()-30);
	}
	/**
	 * This method is used for making the card appear smaller when the mouse leaves it.
	 */
	public void shrinkCard() {
		try {
			setIcon(
					(Icon) new ImageIcon(ImageIO.read(
							new File("src/resources/cards/"+card.toString().toLowerCase()+".png")).getScaledInstance(70, 105, 0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(70, 105);
		setLocation(getX()+20,getY()+30);
	}
}
