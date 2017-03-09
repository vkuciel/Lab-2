package pkgPokerBLL;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import pkgPokerEnum.eCardNo;
import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;



public class Hand {

	private UUID HandID;
	private boolean bIsScored;
	private HandScore HS;
	private ArrayList<Card> CardsInHand = new ArrayList<Card>();
	
	public Hand()
	{
		
	}
	
	public void AddCardToHand(Card c)
	{
		CardsInHand.add(c);
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}
	
	public HandScore getHandScore()
	{
		return HS;
	}
	
	public Hand EvaluateHand()
	{
		Hand h = Hand.EvaluateHand(this);
		return h;
	}
	
	private static Hand EvaluateHand(Hand h)  {

		Collections.sort(h.getCardsInHand());
		
		//	Another way to sort
		//	Collections.sort(h.getCardsInHand(), Card.CardRank);
		
		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pkgPokerBLL.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pkgPokerBLL.Hand.class;
				cArg[1] = pkgPokerBLL.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bIsScored = true;
			h.HS = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}
	
	
	
	
	
	
	public static boolean isHandRoyalFlush(Hand h, HandScore hs) {
		
		boolean isHandRoyalFlush = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if((isFlush(h.getCardsInHand()) == true) && (isStraight(h.getCardsInHand()) == true) 
			&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.KING)) {
			isHandRoyalFlush = true;
			hs.setHandStrength(eHandStrength.RoyalFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

				
		return isHandRoyalFlush;
	}
	
	public static boolean isHandStraightFlush(Hand h, HandScore hs) {
		
		boolean isHandStraightFlush = false;
		
		if((isFlush(h.getCardsInHand()) == true) && (isStraight(h.getCardsInHand()) == true)) {
			isHandStraightFlush = true;
			hs.setHandStrength(eHandStrength.StraightFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			
		}
				
		return isHandStraightFlush;
	}	
	public static boolean isHandFourOfAKind(Hand h, HandScore hs) {
		
		boolean isHandFourOfAKind = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank()) {
			isHandFourOfAKind = true;
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandFourOfAKind = true;
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		}
				
		return isHandFourOfAKind;
	}	
	
	
	public static boolean isFlush(ArrayList<Card> cards) {
		
		boolean isFlush = false;
		int iCardCount=0;
		for (eSuit suits: eSuit.values()) {
			for(Card c:cards) {
				if(c.geteSuit()==suits) {
					iCardCount++;
				}
			if (iCardCount > 0) {
				if(iCardCount==5)
					isFlush=true; }
				else
				{
					isFlush=false;
					break;
				}
			}	
		}
		return isFlush;
	}		
	
	public static boolean isHandFlush(Hand h, HandScore hs) {
		
		boolean isHandFlush = false;
		
		if(isFlush(h.getCardsInHand()) == true) {
			isHandFlush = true;
			hs.setHandStrength(eHandStrength.Flush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		} else
		{
			isHandFlush = false;
		}
		return isHandFlush;
		}
	
	
	public static boolean isStraight(ArrayList<Card> c) {

		boolean isStraight = false;
		int i=0;
		if (Hand.isAce(c) == true) {
			i=1;
		}
		else {
			i=0;	
		}
		for (; i<3; i++) {
			if (c.get(i).geteRank().getiRankNbr() == c.get(i+1).geteRank().getiRankNbr() +1) {
				isStraight = true;
			}
			else
			{
				isStraight = false;
				break;
			}
		}
			
		return isStraight;
	}
	
	public static boolean isHandStraight(Hand h, HandScore hs) {
		
		boolean isHandStraight = true;
		
		if(isStraight(h.getCardsInHand()) == true) {
			isHandStraight = true;
			hs.setHandStrength(eHandStrength.Straight);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
		} else
		{
			isHandStraight = false;
		}
		return isHandStraight;
	}
	

	public static boolean isAce(ArrayList<Card> c) {
		
		boolean isAce = false;
		
		if (c.get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE) {
			if ((c.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.KING) || (c.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.FIVE)) {
				isAce = true;
			}
			else
			{
				isAce = false;
			}
		}
		return isAce;
		
	}	
	
	
	public static boolean isHandThreeOfAKind(Hand h, HandScore hs) {
		
		boolean isHandThreeOfAKind = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank()) {
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		}
				
		return isHandThreeOfAKind;
	}		
	
	
	public static boolean isHandTwoPair(Hand h, HandScore hs) {
		
		boolean isHandTwoPair = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank()
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		} else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank()
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		}
				
		return isHandTwoPair;
	}	
	

	public static boolean isHandPair(Hand h, HandScore hs) {
		
		boolean isHandPair = false;
	
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank()) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
	}
			
	return isHandPair;
	}	
	

	public static boolean isHandHighCard(Hand h, HandScore hs) {

		boolean isHandHighCard = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		hs.setHandStrength(eHandStrength.HighCard);
		hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		hs.setLoHand(null);
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		
		return isHandHighCard;
	}
	
	

	public static boolean isAcesAndEights(Hand h, HandScore hs) {
		
		boolean isAcesAndEights = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE)
			&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.ACE)
			&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == eRank.EIGHT)
			&& (h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank() == eRank.EIGHT)) {
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE)
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.ACE)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT)
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == eRank.EIGHT)) {
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
				
		return isAcesAndEights;
	}		
	
	
	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

		return isFullHouse;

	}
}
