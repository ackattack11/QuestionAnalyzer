import java.util.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.*;
public class Main {
	
	public static void main(String args[]) throws Exception
	{
		System.out.println("What is your question?");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		Question question1 = new Question(text);
		//ArrayList<String> qformat = question1.QtoArray();
		System.out.println(question1.toString());
		//TESTING GOOGLE ZONE
		String google = "http://www.google.com/search?q=";
		String search = text;
		String charset = "UTF-8";
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; 
		
		Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select("li.g>h3>a");
		//System.out.println(links.size());
		    
		String[] urls = new String[links.size()];
		String[] answers = new String[links.size()];
		String[] finalAnswers = new String[links.size()];
		
		for(int x = 0;x<links.size();x++)
		{
			urls[x] = links.get(x).absUrl("href");
			urls[x] = URLDecoder.decode(urls[x].substring(urls[x].indexOf('=') + 1, urls[x].indexOf('&')), "UTF-8");
		}
		
		
		for(int x = 0;x<links.size();x++)
		{
			try
			{
			Document doc = Jsoup.connect(urls[x]).get();
			String answer = doc.body().text();
			//System.out.println("Answer: "+answer);
			Answer output = new Answer(answer);
			finalAnswers[x] = output.evaluate();
			System.out.println("Answer: "+finalAnswers[x]);
			}
			catch(Exception e)
			{
				System.out.println("Could not connect");
				finalAnswers[x] = "Could not connect";
			}
		}
		double finalConfidence = 0;
		for(int x = 0;x<finalAnswers.length;x++)
		{
			double cvalue = 0;
			String a = finalAnswers[x];
			if(a.substring(0,1).equalsIgnoreCase("Y"))
			{
				cvalue = Double.parseDouble(a.substring(5));
				finalConfidence += cvalue;
			}
			else if(a.substring(0, 1).equalsIgnoreCase("N"))
			{
				cvalue = Double.parseDouble(a.substring(4));
				finalConfidence -= cvalue;
			}
			else
			{
				finalConfidence -= .5;
			}
			//System.out.println(finalConfidence);
			
		}
		if(finalConfidence > 0)
		{
			finalConfidence = Math.log10(finalConfidence+1);
			System.out.println("Yes: "+finalConfidence);
		}
		else if(finalConfidence < 0)
		{
			
			finalConfidence = Math.abs(finalConfidence);
			finalConfidence = Math.log10(finalConfidence+1);
			System.out.println("No: "+finalConfidence);
		}
		else
		{
			System.out.println("0");
		}
		
		

		  
	}
}
