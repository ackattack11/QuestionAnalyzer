import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI {
	public void setUp()
	{
		JFrame frame = new JFrame("Yorn");
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(800,500);
		frame.setLocationRelativeTo(null);
		JLabel background = new JLabel();
		background.setBounds(0,0,800,500);
		background.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("graybackground.jpg")));
		
		JLabel answerField = new JLabel("<html>Hello! I am Yorn.<br> How can I be of service?<html>");
		JTextField questionField = new JTextField();
		questionField.setBounds(450, 210, 200, 20);
		answerField.setBounds(440, 250, 220, 40);
		JLabel y = new JLabel();
		y.setBounds(120, 100, 600, 230);
		y.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("y2.png")));
		frame.add(y);
		
		questionField.addActionListener(new ActionListener(){

		                public void actionPerformed(ActionEvent e){
		                	answerField.setText("Calculating...");
		                	frame.update(frame.getGraphics());
		                        try {
		                        	
									answerField.setText(Main.runQuestion(questionField.getText()));
									
									//questionField.setText("");
								} catch (Exception e1) {
									e1.printStackTrace();
								}

		                }});
	
		frame.add(answerField);
		frame.add(questionField);
		frame.add(background);
		frame.setVisible(true);
	}
}
