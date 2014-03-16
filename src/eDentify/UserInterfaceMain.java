package eDentify;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserInterfaceMain {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton button = new JButton("Search"); //create button
	JLabel title = new JLabel("eDentify");
	JLabel description = new JLabel("Find information about someone using public data");
	JLabel nameLabel = new JLabel("Name:");
	JLabel countyStateLabel = new JLabel("County / State:");
	JLabel countryLabel = new JLabel("Country:");
	JTextField nameTextField = new JTextField("e.g. Joe Bloggs");
	JTextField countyStateTextField = new JTextField("e.g. Dorset");
	JTextField countryTextField = new JTextField("e.g. England");
	
	Font standardFont = new Font("Trebuchet MS", Font.PLAIN, 12);
	Font standardFontItalic = new Font("Trebuchet MS", Font.ITALIC, 12);
	Font titleFont = new Font("Trebuchet MS", Font.BOLD, 30);
	
	public void setWindow(int width, int height){
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5); //troubleshooting

		frame.add(panel); //add panel to frame
		panel.setLayout(null); //clear layout to use setBounds
		
		//Add eDentify title to panel
		panel.add(title);
		title.setFont(titleFont);
		title.setBounds(250, 20, 130, 50);
		
		//Add description to panel
		panel.add(description);
		description.setFont(standardFont);
		description.setBounds(170, 50, 300, 50);
		
		//Add name search field to panel
		panel.add(nameLabel);
		nameLabel.setFont(standardFont);
		nameLabel.setBounds(130,110,150,50);
		panel.add(nameTextField);
		nameTextField.setFont(standardFontItalic);
		nameTextField.setForeground(Color.GRAY);
		nameTextField.setBounds(170, 118, 300, 30); //TODO: Delete contents and change colour when user selects field
		
		//Add county/state field to panel
		panel.add(countyStateLabel);
		countyStateLabel.setFont(standardFont);
		countyStateLabel.setBounds(80,150,150,50);
		panel.add(countyStateTextField);
		countyStateTextField.setFont(standardFontItalic);
		countyStateTextField.setForeground(Color.GRAY);
		countyStateTextField.setBounds(170, 158, 300, 30); //TODO: Delete contents and change colour when user selects field
		
		//Add country field to panel
		panel.add(countryLabel);
		countryLabel.setFont(standardFont);
		countryLabel.setBounds(120,190,150,50);
		panel.add(countryTextField);
		countryTextField.setFont(standardFontItalic);
		countryTextField.setForeground(Color.GRAY);
		countryTextField.setBounds(170, 198, 300, 30); //TODO: Delete contents and change colour when user selects field
		
		//Add Search button to panel
		panel.add(button); //add button to panel
		button.setBounds(250, 300, 100, 50); //set location of button
		
		//Set frame requirements and display it
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		nameTextField.setText("");
		nameTextField.setFont(standardFont);
	}

	public static void main(String[] args){
		UserInterfaceMain obj = new UserInterfaceMain();
		obj.setWindow(640,480);
		//something goes here
	}
	
}
