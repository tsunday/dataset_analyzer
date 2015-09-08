package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import confGUI.Configuration;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dataset.Event;
import dataset.Occurance;
import dataset.Occurance.Condition;

public class EventDefineUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Configuration c;

	private List<JTextField> textFields = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					EventDefineUI frame = new EventDefineUI(new Configuration());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EventDefineUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
	}
	public EventDefineUI(Configuration c){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		for(int i=0; i<7/*c.getNamesOfFileds().size()*/; i++){
			JLabel lblNewLabel = new JLabel("CHUJ"/*c.getNamesOfFileds().get(i).name*/);
			lblNewLabel.setBounds(42, 11 + (40*i), 46, 14);
			contentPane.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setBounds(162, 8+(39*i), 86, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			textFields.add(textField);
			
		}
		
	}
	
	public Event createEvent(){
		HashMap<String, Occurance> occurances = new HashMap<String, Occurance>();
		for(int i=0; i<textFields.size(); i++){
			Condition con=null;
			Object value = null;
			Object range=null;
			String text = textFields.get(i).getText();
			String firstSign = text.substring(0, 0);
			switch(firstSign){
			
			case(">"):con=con.More;
			value=text.substring(1, text.length());
			break;
			
			case("<"):con=con.Less;
			value=text.substring(1, text.length());
			break;
			
			}
			
			if(isNumeric(firstSign)){
				
				if(text.contains("<")){
				con=con.InRange;
				value=text.substring(0, text.indexOf("<"));
				range=text.substring(text.indexOf("<"), text.length());
						
				}else if(text.contains(">")){
					con=con.InRange;
					value=text.substring(0, text.indexOf(">"));
					range=text.substring(text.indexOf(">"), text.length());
				}else{
					con=con.Equal;
					value=text;
				}
			}
			
			
		if(range==null)
		occurances.put(this.c.getNamesOfFileds().get(i).name, new Occurance(con, value));
		else if(range!=null)
			occurances.put(this.c.getNamesOfFileds().get(i).name, new Occurance(con, value,range));
		
		}
		
		return new Event(occurances);
		
	}
	private boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
