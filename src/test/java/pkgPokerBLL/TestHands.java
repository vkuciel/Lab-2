package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {

	@Test
	public void TestFullHouse() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		assertEquals(0,h.getHandScore().getKickers().size());	
	}
	@Test
	public void TestRoyalFlush() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.KING,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a royal flush
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(),h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Ace'
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

	}
	
	public void TestStraight() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
	
		//	Hand better be a straight
		assertEquals(eHandStrength.Straight.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Seven'
		assertEquals(eRank.SEVEN.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	
		//	Straight has no kickers.
				assertEquals(0,h.getHandScore().getKickers().size());
	}
	@Test
	public void TestThreeOfAKind() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
	
		//	Hand better be a ThreeOfAKind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Queen'
		assertEquals(eRank.QUEEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

	
		//	Kickers are 'Seven' and 'Three'
		assertEquals(2,h.getHandScore().getKickers().size());	
	}
	
	public void TestThreeOfAKind2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.JACK,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));		
		h.EvaluateHand();
	
		//	Hand better be a ThreeOfAKind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Ten'
		assertEquals(eRank.TEN.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	ThreeOfAKind has no LO hand
				assertEquals(0,h.getHandScore().getKickers().size());
	
		//	Kickers are 'Jack' and 'Five'
		assertEquals(eRank.JACK.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		assertEquals(eRank.FIVE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());	
	}
	
	public void TestThreeOfAKind3() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
	
		//	Hand better be a ThreeOfAKind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Ace'
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	ThreeOfAKind has no LO hand
				assertEquals(0,h.getHandScore().getKickers().size());
	
		//	Kickers are 'Ace' and 'King'
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		assertEquals(eRank.KING.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	
	public void TestTwoPair1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Ace'
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	LO hand better be 'Queen'
		assertEquals(eRank.SEVEN.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	
		//	Kicker is 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	
	public void TestTwoPair2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Six'
		assertEquals(eRank.SIX.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	LO hand better be 'Eight'
		assertEquals(eRank.EIGHT.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	
		//	Kicker is 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	public void TestPair1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.Pair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Five'
		assertEquals(eRank.FIVE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	Pair has no LO hand
				assertEquals(1,h.getHandScore().getKickers().size());
	
		//	Kickers must be 'Four' 'Three' 'Two'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		assertEquals(eRank.TWO.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	
	public void TestPair2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'Six'
		assertEquals(eRank.SIX.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
	
		//	Pair has no LO hand
				assertEquals(0,h.getHandScore().getKickers().size());
	
		//	Kickers must be 'Eight' 'Four' 'Two'
		assertEquals(eRank.EIGHT.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		assertEquals(eRank.TWO.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
}


