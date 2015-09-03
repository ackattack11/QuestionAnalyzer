import java.util.ArrayList;

public class Answer {
	private String answerText;
	private String[] positiveWords = {"yes", "correct", "true", "ABLE",   "ACCEPT", "ACCEPTANCE", "ACCEPTABLE", "ACCEPTED", "ACCEPTING",   "ACTION",   "ACTIVATE",   "ACTIVE",   "ADD",   "ADDITION",   "ADMIRABLE", "ADORABLE",   "ADVANTAGE",   "AFFIRM",   "AGELESS",   "AGREE",   "AGREEABLE",   "AID",   "AIM",   "ABUNDANCE",   "ACCOUNTABILITY",   "ACCOMPLISHMENT", "ACCOMPLISH",   "ACCURACY",   "ACHIEVEMENT",  "ACHIEVE",   "ACKNOWLEDGEMENT",   "ADAPTABILITY",   "ADVENTURE",   "AGILITY",   
			"ALERTNESS",   "AMBITION",   "ANTICIPATION",    "APPRECIATE", "ASSERTIVE",   "ATTENTIVENESS",   "AUDACITY",   "AWARE",   "AUTHENTIC",   "ABRACADABRA",   "ATTRACTION",   "ALLOW", "ALLOWING",   "AFFECTION",   "ABSORBED",   "ALERT",   "AMAZED",   "AWE",   "ARDENT",   "AMAZING",   "AWESOME",   "AROUSED",   "ASTONISHED","ASTONISHING",   "AMUSED","ALOHA",   "ADORE",    "ADMIRE",   "ADMIRABLE",   "ALLURE",    "ANGEL",    "ALTRUISM",   "ABOUND"
			, "BEATIFY",   "BEATITUDE",   "BENEFICIAL",   "BENEFIT",   "BENEVOLENT",   "BELOVED",   "BEST",   "BETTER",
			"COOPERATION",   "COURAGE",   "COURTESY", "COURTEOUS",    "CURIOUS",   "CHAKRA",   "COOL",   "CLEAR",
			"DIRECTION",   "DELICATE",   "DECENT",   "DESIRABLE",   "DELICIOUS",   "DREAM",
			"EMPATHY", "EMPATHIZE", "EMPHATIC",   "EASY", "EASILY", "EASIER",   "EDUCATE",
			"FLAWLESS", "FAVORITE",   "FAIRNESS",   "FAMILY",   "FIDELITY",   "FLEXIBILITY",
			"GOOD",   "GOODNESS",   "GIVING", "GENERATE",   "GENIAL",   "GENIUS",   "GENUINE",   "GIFT",   "GIVE",
			"HOPE", "HOPEFULNESS",   "HAPPINESS", "HAPPY",   "HARMONIOUS",
			"IMAGINATION",   "INSPIRE", "INSPIRATION","INSPIRED",   "IDEA",   "INCREDIBLE",
			"JOKE",   "JOLLY",   "JOVIAL",   "JUST",   "JUSTICE",   "JUBILANT",
			"KINDNESS", "KIND",  "KISS",
			"LIKE",   "LAUGH",   "LEARN", "LEARNING",   "LIVE",   "LUXURY",   "LONGEVITY",   "LOYALTY",
			"MEANING",   "MAGNIFICENT",   "MAJESTY",   "MARVELOUS",   "MERIT",   "MOTIVATE",
			"NOBLE", "NEAT", "NICE",
			"OPTIMIST", "OPTIMISTIC",   "OUTSTANDING",   "OK",   "ONWARDS",  "OPEN",
			"POSITIVE", "PROGRESS", "PERSISTENT", "PERFECT", 
			"QUALITY",
			"RESPECT",   "RADIANT",   "READY", "READINESS",   "REAL", "REASON",   "RECOMMEND",   "REFRESH",
			"SECURITY",   "SUSTAIN",   "SAVE", "SAVING",   "SIMPLE",
			"TRUST" ,   "TACT",   "TEACH",   "TEAM",   "THANKFUL",
			"UNIFICATION",   "UNIQUE",   "UPLIFT",   "ULTIMATE",   "UNCONDITIONAL",   "UPGRADE",   "USEFUL",
			"VITALITY",   "VALUE",  "VIRTUOUS",   "VALID",   "VERIFY",   "VIABLE",
			"WORTH", "WORTHY", "WORTHINESS",   "WEALTH",   "WARM", "WARMTH",  "WELCOME",
	};//199
	
	private String[] negativeWords = {"No", "Not", "None", "Neither", "Absent", "Few", "doesn't", "don't",
			"nobody", "Abysmal", "adverse", "alarming","angry","annoy","anxious","apathy","appalling","atrocious","awful", "bad","banal"
			,"barbed","belligerent","bemoan","beneath","boring","broken", "callous","can't","clumsy","coarse","cold","cold-hearted","collapse"
			,"confused","contradictory","contrary","corrosive","corrupt", "dead","decaying","damage","damaging","dastardly","deplorable",
			"depressed","deprived","deformed","enraged","eroding","evil","fail","faulty","fear","feeble","fight","filthy","haggard","hard",
			"hard-hearted","harmful","hate","hideous","homely","horrendous","horrible","hostile","hurt","hurtful", "icky","ignore","ignorant"
			,"ill","immature","imperfect","impossible","malicious","mean","menacing","messy","misshapen","missing","misunderstood","negate",
			"negative","never","no","nobody","nondescript","nonsense","objectionable","odious","offensive","old","pessimistic","petty","plain","poisonous"
			,"poor","prejudice", "questionable","quit","reject","renege","repellant","reptilian","repulsive","sad","savage","scare","scary","scream"
			,"severe","shoddy","shocking","sick","terrible","terrifying","threatening","unlucky","unpleasant","upset","unsatisfactory","worthless","zero"
			
			
	};//125
	
			
	
	public Answer(String a)
	{
		answerText = a;
		answerText = answerText +" ";
		
	}
	public String evaluate()
	{
		int positiveHits = 0, negativeHits = 0;
		ArrayList<String> answerArray = new ArrayList<String>();
		
		String parse = new String();
		while (answerText.length() > 1)
		{
			parse = answerText.substring(0 , answerText.indexOf(" "));
			answerArray.add(parse);
			answerText = answerText.substring(answerText.indexOf(" ")+1);
		}
		/*
		for(int x = 0;x<answerArray.size();x++)
		{
			if(endword.equals(answerArray.get(x)))
			{
				keywordHits++;
			}
		}
		*/
		for(int x = 0;x < answerArray.size();x++)
		{
			for(int y = 0;y<positiveWords.length;y++)
			{
				if(answerArray.get(x).equalsIgnoreCase(positiveWords[y]))
				{
					positiveHits++;
					//System.out.println(answerArray.get(x));
				}
			}
			
		}
		for(int x = 0;x < answerArray.size();x++)
		{
			for(int y = 0;y<negativeWords.length;y++)
			{
				if(answerArray.get(x).equalsIgnoreCase(negativeWords[y]))
				{
					negativeHits++;
					//System.out.println(answerArray.get(x));
				}
			}
			
		}
		double confidence = 0;
		if(positiveHits<=negativeHits)
		{
			if(positiveHits == 0)
			{
				positiveHits = 1;
				negativeHits = 1;
			}
			//System.out.println(negativeHits+"/"+positiveHits);
			confidence = negativeHits/(double)positiveHits;
			
			confidence = (confidence-1)/confidence;
			return "No: "+confidence;
			
		}
		else
		{
			if(negativeHits == 0)
			{
				positiveHits = 1;
				negativeHits = 1;
			}
				
			confidence = positiveHits/(double)negativeHits;
			
			confidence = (confidence-1)/confidence;
			return "Yes: "+confidence;
		}
		
		
	}
	
}
