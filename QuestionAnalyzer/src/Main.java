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
		
		for(int x = 0;x<links.size();x++)
		{
			urls[x] = links.get(x).absUrl("href");
			urls[x] = URLDecoder.decode(urls[x].substring(urls[x].indexOf('=') + 1, urls[x].indexOf('&')), "UTF-8");
		}
		/*
		String url = links.get(0).absUrl("href"); 
		String url2 = links.get(1).absUrl("href");
		//System.out.println(url2);
		url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
		url2 = URLDecoder.decode(url2.substring(url2.indexOf('=')+1, url2.indexOf('&')), "UTF-8");
		*/
		
		for(int x = 0;x<links.size();x++)
		{
			try
			{
			Document doc = Jsoup.connect(urls[x]).get();
			String answer = doc.body().text();
			//System.out.println("Answer: "+answer);
			Answer output = new Answer(answer);
			System.out.println("Answer: "+output.evaluate());
			}
			catch(Exception e)
			{
				System.out.println("No response");
			}
		}
		/*
		Document doc2 = Jsoup.connect(url2).get();
		String answer = doc.body().text();
		String answer2 = doc2.body().text();
		//answer = answer.substring(answer.indexOf("the"));
		
		System.out.println("Answer: " + answer);
		
		
		
		
		System.out.println("Answer: "+output.evaluate());
		System.out.println("Answer: "+answer2);
		output = new Answer(answer2);
		System.out.println("Answer: "+output.evaluate());
		*/

		  
	}
}
