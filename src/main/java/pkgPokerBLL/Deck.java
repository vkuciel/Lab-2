package pkgPokerBLL;

import java.util.ArrayList;
import java.util.UUID;

import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;
import java.util.Collections;

public class Deck {
	private UUID DeckID;
	private ArrayList<Card> DeckCards = new ArrayList<Card>();
	
	public Deck()
	{
		for (eRank Rank : eRank.values()){
			for (eSuit Suit : eSuit.values()){
				Card Card = new Card (Rank , Suit);
				DeckCards.add(Card);
			}
		}
		Collections.shuffle(DeckCards);
		
	}

	
	public Card DrawCard()
	{
		return DeckCards.remove(0); 
	}
}
