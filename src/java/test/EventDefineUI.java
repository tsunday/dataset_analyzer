package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private JTextField textField, textFieldB;
	Configuration c;
	Dataset d;
	SimpleDateFormat sdf;
	double dblValue;
	String sValue;
	Date dValue;

	private List<JTextField> textFields = new ArrayList<JTextField>();
	private List<JTextField> textFieldsB = new ArrayList<JTextField>();

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
		this.c=c;
		
		for(int i=0; i<c.getNamesOfFileds().size();i++)
			System.out.println(c.getNamesOfFileds().get(i).name);
		System.out.println(c.getNamesOfFileds().toString());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstruction = new JLabel("        Podaj zakres kolumn");
		lblInstruction.setBounds(60, 10, 250, 20);
		contentPane.add(lblInstruction);
		
		JLabel lblEvents = new JLabel("Event 1                               Event 2");
		lblEvents.setBounds(120, 40, 350, 20);
		contentPane.add(lblEvents);
		
		
		
		
		
		for(int i=0; i<c.getNamesOfFileds().size(); i++){
			JLabel lblNewLabel = new JLabel(c.getNamesOfFileds().get(i).name);
			lblNewLabel.setBounds(42, 71 + (40*i), 46, 14);
			contentPane.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setBounds(110, 67+(39*i), 86, 20);
			
			
			
			textField.setColumns(10);
			
			contentPane.add(textField);
			textFields.add(textField);
			
		}
		
		for(int i=0; i<c.getNamesOfFileds().size(); i++){
			

			textFieldB = new JTextField();
			textFieldB.setBounds(260,67+(39*i), 86, 20);
			contentPane.add(textFieldB);
			textFieldsB.add(textFieldB);
			
		}
		
		JLabel lblLogic = new JLabel("Wybierz wyra�enie logiczne kt�re chcesz znale��:");
		lblLogic.setBounds(85, 200, 350, 20);
		contentPane.add(lblLogic);
		
		
		JRadioButton rbAbsence = new JRadioButton("Absencja");
		rbAbsence.setBounds(20, 220, 150, 30);
		contentPane.add(rbAbsence);
		
		JRadioButton rbInvariance = new JRadioButton("Niezmienno��");
		rbInvariance.setBounds(20, 250, 150, 30);
		contentPane.add(rbInvariance);
		
		JRadioButton rbExistence = new JRadioButton("Mo�liwo��");
		rbExistence.setBounds(20, 280, 150, 30);
		contentPane.add(rbExistence);
		
		JRadioButton rbResponsiveTwo = new JRadioButton("Responsywno�� 2 zdarze�");
		rbResponsiveTwo.setBounds(20, 310, 190, 30);
		contentPane.add(rbResponsiveTwo);
		
		
		
		JRadioButton rbObligation = new JRadioButton("Obligacja ");
		rbObligation.setBounds(200, 220, 150, 30);
		contentPane.add(rbObligation);
		
		JRadioButton rbAutoresponsive = new JRadioButton("Autoresponsywno��");
		rbAutoresponsive.setBounds(200, 250, 150, 30);
		contentPane.add(rbAutoresponsive);
		
		JRadioButton rbLong = new JRadioButton("Trwa�o��");
		rbLong.setBounds(200, 280, 150, 30);
		contentPane.add(rbLong);
		
		JRadioButton rbReaktive= new JRadioButton("Reaktywno��");
		rbReaktive.setBounds(200, 310, 190, 30);
		contentPane.add(rbReaktive);
		
		
		
		JButton ok = new JButton("GO!");
		ok.setText("GO!");
		ok.setBounds(250, 350, 60,40);
		contentPane.add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean twoEventFlag=false;
				Event eventA = null;
				try {
					eventA = createEvent(textFields);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				Event eventB=null;
				
				for(int i=0;i<textFieldsB.size();i++){
					if(textFieldsB.get(i).getText().equals(""))			//SPRAWDZAMY CZY COS w EVENT 2 jest wype�nione
					continue;
					else{ twoEventFlag=true; break; }
						
				}
				if(twoEventFlag){
				 try {
					eventB = createEvent(textFieldsB);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Stworzony EventB");
				/*for(int i=0; i<eventB.occurances.size(); i++)
					System.out.println(eventB.occurances.get(i).condition + "     " + eventB.occurances.get(i).value);
				*/
				}
				SingleEventExpression.Operator op1 = SingleEventExpression.Operator.Invariance;
				DatasetFilter filter = new DatasetFilter(new SingleEventExpression(eventA,op1));  
		        Dataset result = filter.FilterDataset(d);
		        System.out.println(result.toString());
				
			}
		});
		
	}
	
	public Event createEvent(List<JTextField> tL) throws ParseException{
	 List<JTextField> tmpList = tL;
	 Condition con=null;
		Object value = null;
		Object range=null;
		HashMap<String, Occurance> occurances = new HashMap<String, Occurance>();
		for(int i=0; i<tmpList.size(); i++){
			
		
			String text = tmpList.get(i).getText();
			String firstSign = text.substring(0, 1);
			switch(firstSign){
			
			case(">"):con=Condition.More;
			value=text.substring(1, text.length());
			break;
			
			case("<"):con=Condition.Less;
			value=text.substring(1, text.length());
			break;
			
			}
			System.out.println("PIERWSZY:"+firstSign);
			if(isNumeric(firstSign)){
				
				if(text.contains("<")){
				con=Condition.InRange;
				value=text.substring(0, text.indexOf("<"));
				range=text.substring(text.indexOf("<"), text.length());
						
				}else if(text.contains(">")){
					con=Condition.InRange;
					value=text.substring(0, text.indexOf(">"));
					range=text.substring(text.indexOf(">"), text.length());
				}else{
					con=Condition.Equal;
					value=text;
				}
				
				if(c.getNamesOfFileds().get(i).type.equals(reader.Field.Type.Time)){
					System.out.println(c.getNamesOfFileds().get(i).type);
					 sdf = new SimpleDateFormat("HH:mm:ss");
					 dValue = sdf.parse("12:21:23");
					 
					 
					
				}else if (c.getNamesOfFileds().get(i).type.equals(reader.Field.Type.Number)){
					value = new Double((double) value);
					
				}
				
				System.out.println("TEEEERAZ:   " + con+ "  "+value);
				System.out.println(this.c.getNamesOfFileds().get(i).name); 
				occurances.put(this.c.getNamesOfFileds().get(i).name, new Occurance(con, value,range));
			}else{
			
			
			con=Condition.Equal;	
			System.out.println("ABO TU " +con+ "  "+value);
			System.out.println(this.c.getNamesOfFileds().get(i).name);   //TU LECI NULL
			
			if(c.getNamesOfFileds().get(i).type.equals(reader.Field.Type.Time)){
				System.out.println(c.getNamesOfFileds().get(i).type);
				 sdf = new SimpleDateFormat("HH:mm:ss");
				 dValue = sdf.parse("12:21:23");
				 
				 
				
			}
			
			
		occurances.put(this.c.getNamesOfFileds().get(i).name, new Occurance(con, value));
		
		}
		}
		for(int i=0; i<occurances.size();i++)
			System.out.println(occurances.get(i));
		return new Event(occurances);
		
	}
	private boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}