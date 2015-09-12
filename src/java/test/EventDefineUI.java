package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import confGUI.Configuration;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dataset.Dataset;
import dataset.DatasetFilter;
import dataset.Event;
import dataset.Occurance;
import dataset.Occurance.Condition;
import dataset.SingleEventExpression;

public class EventDefineUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Configuration c;
	Dataset d;

	private List<JTextField> textFields = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					EventDefineUI frame = new EventDefineUI(new Configuration(), new Dataset());
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
	public EventDefineUI(Configuration c, final Dataset d){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblInstruction = new JLabel("Podaj zakres kolumn hbhbh");
		lblInstruction.setBounds(10, 10, 250, 20);
		contentPane.add(lblInstruction);
		
		for(int i=0; i<c.getNamesOfFileds().size(); i++){
			JLabel lblNewLabel = new JLabel(c.getNamesOfFileds().get(i).name);
			lblNewLabel.setBounds(42, 71 + (40*i), 46, 14);
			contentPane.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setBounds(162, 67+(39*i), 86, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			textFields.add(textField);
			
		}
		
		JButton ok = new JButton("GO!");
		ok.setText("GO!");
		ok.setBounds(100, 200, 60,40);
		contentPane.add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Event eventA = createEvent();
				SingleEventExpression.Operator op1 = SingleEventExpression.Operator.Invariance;
				DatasetFilter filter = new DatasetFilter(new SingleEventExpression(eventA,op1));  
		        Dataset result = filter.FilterDataset(d);
		        System.out.println(result.toString());
				
			}
		});
		
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
			
			
		if(range==null){
			System.out.println(con+ "  "+value);
		occurances.put(this.c.getNamesOfFileds().get(i).name, new Occurance(con, value));
		}else if(range!=null)
			occurances.put(this.c.getNamesOfFileds().get(i).name, new Occurance(con, value,range));
		
		}
		
		return new Event(occurances);
		
	}
	private boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}