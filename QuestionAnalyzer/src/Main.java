import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.*;
import java.text.DecimalFormat;
public class Main{
	
	public static String runQuestion(String q) throws Exception
	{
		String text = q;
		Question question1 = new Question(text);
		//System.out.println(question1.toString());
		
		String google = "http://www.google.com/search?q=";
		String search = text;
		String charset = "UTF-8";
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; 
		
		Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select("li.g>h3>a");
		
		    
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
		DecimalFormat fmt = new DecimalFormat("00.00%");
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
				finalConfidence -= .75;
			}
			//System.out.println(finalConfidence);
			
		}
		if(finalConfidence > 0)
		{
			finalConfidence = Math.log10(finalConfidence+1);
			return ("Yes: "+fmt.format(finalConfidence));
			
		}
		else if(finalConfidence < 0)
		{
			
			finalConfidence = Math.abs(finalConfidence);
			finalConfidence = Math.log10(finalConfidence+1);
			return ("No: "+fmt.format(finalConfidence));
		}
		else
		{
			return ("0");
		}
		
	}
	
	public static void main(String args[]) throws Exception
	{
		JFrame frame = new JFrame("Yorn");
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000,600);
		frame.setLocationRelativeTo(null);
		JLabel answerField = new JLabel("<html>Hello! I am Yorn.<br> How can I be of service?<html>");
		JTextField questionField = new JTextField();
		questionField.setBounds(150, 100, 200, 20);
		answerField.setBounds(140, 140, 220, 40);
		JLabel y = new JLabel();
		y.setBounds(100, 100, 600, 600);
		y.setIcon(new ImageIcon("C:/Users/Adam/Pictures/Y.png"));
		frame.add(y);
		
		questionField.addActionListener(new ActionListener(){

		                public void actionPerformed(ActionEvent e){
		                	answerField.setText("Calculating...");
		                	frame.update(frame.getGraphics());
		                        try {
		                        	
									answerField.setText(runQuestion(questionField.getText()));
									
									//questionField.setText("");
								} catch (Exception e1) {
									e1.printStackTrace();
								}

		                }});
	
		frame.add(answerField);
		frame.add(questionField);
		frame.setVisible(true);
		
	
	}
	
}
