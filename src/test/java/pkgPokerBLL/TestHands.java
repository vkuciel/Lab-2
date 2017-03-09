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
		assertEquals(eHandStrength.FullHouse.getHandStrength(),h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),h.getHandScore().getLoHand().getiRankNbr());
	
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
	@Test
	public void TestStraightFlush() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a straight flush
		assertEquals(eHandStrength.StraightFlush.getHandStrength(),h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'seven'
		assertEquals(eRank.SEVEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

	}
	@Test
	public void TestFourOfAKind1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.SPADES));		
		h.EvaluateHand();
	
		//	Hand better be a four of a kind
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'eight'
		assertEquals(eRank.EIGHT.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		//	1 kicker
		assertEquals(1,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestFourOfAKind2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a four of a kind
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'two'
		assertEquals(eRank.TWO.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		//	1 kicker
		assertEquals(1,h.getHandScore().getKickers().size());	

	}
	
	@Test
	public void TestAcesAndEights1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a aces and eights
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'ace'
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		//	LO hand better be 'eight'
		assertEquals(eRank.EIGHT.getiRankNbr(),h.getHandScore().getLoHand().getiRankNbr());
		
		//	1 kicker
		assertEquals(1,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestAcesAndEights2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a aces and eights
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'ace'
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		//	LO hand better be 'eight'
		assertEquals(eRank.EIGHT.getiRankNbr(),h.getHandScore().getLoHand().getiRankNbr());
		
		//	1 kicker
		assertEquals(1,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestFlush() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a flush
		assertEquals(eHandStrength.Flush.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'king'
		assertEquals(eRank.KING.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

	}
	@Test
	public void TestStraight() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));		
		h.EvaluateHand();
	
		//	Hand better be a straight
		assertEquals(eHandStrength.Straight.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'two'
		assertEquals(eRank.TWO.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

	}
	@Test
	public void TestThreeOfAKind1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.KING,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'king'
		assertEquals(eRank.KING.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// 2 kickers
		assertEquals(2,h.getHandScore().getKickers().size());
	}
	@Test
	public void TestThreeOfAKind2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'seven'
		assertEquals(eRank.SEVEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// 2 kickers
		assertEquals(2,h.getHandScore().getKickers().size());
	}
	@Test
	public void TestThreeOfAKind3() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.JACK,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'nine'
		assertEquals(eRank.NINE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// 2 kickers
		assertEquals(2,h.getHandScore().getKickers().size());
	}
	@Test
	public void TestTwoPair1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'queen'
		assertEquals(eRank.QUEEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		//	LO hand better be 'seven'
		assertEquals(eRank.SEVEN.getiRankNbr(),h.getHandScore().getLoHand().getiRankNbr());
		
		//	1 kicker
		assertEquals(1,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestTwoPair2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'nine'
		assertEquals(eRank.NINE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		//	LO hand better be 'seven'
		assertEquals(eRank.SEVEN.getiRankNbr(),h.getHandScore().getLoHand().getiRankNbr());
		
		//	1 kicker
		assertEquals(1,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestOnePair1() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'ace'
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());
		
		//	3 kicker
		assertEquals(3,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestOnePair2() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'jack'
		assertEquals(eRank.JACK.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());
		
		//	3 kicker
		assertEquals(3,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestOnePair3() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'seven'
		assertEquals(eRank.SEVEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());
		
		//	3 kicker
		assertEquals(3,h.getHandScore().getKickers().size());	
	}
	@Test
	public void TestOnePair4() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());
		
		//	3 kicker
		assertEquals(3,h.getHandScore().getKickers().size());	
	
	}
	@Test
	public void TestHighCard() {
	
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));		
		h.EvaluateHand();
	
		//	Hand better be a two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());
	
		//	HI hand better be 'four'
		assertEquals(eRank.TEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());
		
		//	3 kicker
		assertEquals(4,h.getHandScore().getKickers().size());	
	 
	}
}
	