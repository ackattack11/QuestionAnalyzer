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
		
		    
		String url = links.get(0).absUrl("href"); 
		url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
		
		Document doc = Jsoup.connect(url).get();
		
		String answer = doc.body().text();
		//answer = answer.substring(answer.indexOf("the"));
		
		System.out.println("Answer: " + answer);
		Answer output = new Answer(answer);
		
		
		System.out.println("Answer: "+output.evaluate());
		
		

		  
	}
}
