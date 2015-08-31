import java.util.ArrayList;

public class Question {
	private String rawText, interrogative, type, keyword;
	private String[] Interrogatives = {"Is", "Who", "Why", "How", "When", "Where"};
	private String[] Types = {"yes/no", "person", "reason", "process", "date", "location"};
	public Question(String q)
	{
		
		
		rawText = q;
		interrogative = rawText.substring(0,rawText.indexOf(" "));
		rawText = rawText.substring(rawText.indexOf(" ")+1);
		keyword = rawText.substring(0, rawText.indexOf(" "));
		rawText = q;
		for(int x = 0; x< 6; x++)
		{
			if(interrogative.equalsIgnoreCase(Interrogatives[x]))
			{
				type = Types[x];
			}
		}
	}
	
	public String toString()
	{
		return "Question: " +rawText + "\n" +"Interrogative: "+interrogative+"\n"+"Question Type: "+type+"\n"+"Keyword: "+keyword;
	}
	public String getkey()
	{
		return keyword;
	}
	/*public ArrayList<String> QtoArray()
	{
		ArrayList<String> Question = new ArrayList<String>();
		String buffer = "";
		for(int i = 0;i<rawText.length();i++)
		{
			buffer = rawText.substring(0, rawText.indexOf(" "));
			Question.add(buffer);
			rawText = rawText.substring(rawText.indexOf(" ")+1);
		}
		return Question;
	}
	*/
	
	

	
}
