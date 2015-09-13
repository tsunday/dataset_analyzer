package confGUI;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import reader.Field;
import reader.Reader;
import test.EventDefineUI;
import dataset.Dataset;
import dataset.DatasetFilter;
import dataset.Event;
import dataset.Occurance;
import dataset.SingleEventExpression;
import Table.TableMaker;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.Checkbox;


public class ParserGUI extends JFrame {
	private JTextField textField;
	private JTextField txtPath;
	public Configuration configuration = new Configuration();
	public ArrayList<Field> listOfFields=new ArrayList<Field>();
	public SimpleDateFormat time = null;
	public int iterator=0;
	
	/**
	 * @wbp.nonvisual location=-159,269
	 */
	private final Checkbox checkbox = new Checkbox("New check box");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParserGUI frame = new ParserGUI();
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
	public ParserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Separator");
		lblNewLabel.setBounds(20, 51, 64, 24);
		getContentPane().add(lblNewLabel);

		
		textField = new JTextField();
		textField.setBounds(131, 51, 89, 24);
		getContentPane().add(textField);
		textField.setText(";");
		textField.setColumns(10);
		

		txtPath = new JTextField();
	/*	txtPath.setText("C:\\Users\\Mateusz\\Documents\\dataset_analyzer\\src\\java\\test\\test_data\\test.txt");*/
		txtPath.setBounds(20, 107, 200, 24);
	
		
		getContentPane().add(txtPath);
		txtPath.setColumns(10);
		
		JButton btnNewButton = new JButton("File");
		btnNewButton.setBounds(46, 142, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			            public void actionPerformed(ActionEvent e) {
			            
			            	
			            	       
			            	
			            	      
			               txtPath.setText(createFileChooser());
			
			            }

				
			
			        });

		getContentPane().add(btnNewButton);
		
	
		final List list_1 = new List();
		list_1.setBounds(276, 10, 110, 121);
		getContentPane().add(list_1);
		
		
		
		JButton btnNewButton_1 = new JButton("add new Field");
		btnNewButton_1.setBounds(262, 142, 124, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("READY");
		btnNewButton_2.setBounds(161, 205, 124, 46);
		getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				configuration.setNamesOfFileds(listOfFields);
				configuration.setFile(txtPath.getText().toString());
				configuration.setSeparator(textField.getText().toString());
				
				System.out.println(configuration.getFile() + "   " + configuration.getSeparator());
			
			 TableMaker tableGui = new TableMaker();
			 tableGui.setBounds(600,200,600,600);
				tableGui.setVisible(true);
				
				
			
				Reader reader = new Reader(Paths.get(configuration.getFile()),configuration.getNamesOfFileds(), configuration.getSeparator());
		        Dataset dataset = reader.GenerateDataset();
		        System.out.println(dataset.toString());
		        
		        
		        
		     /*   Double limit = 100.50;
		        String weather = "pada";
		        Occurance o1 = new Occurance(Occurance.Condition.More, limit);
		        Occurance o2 = new Occurance(Occurance.Condition.Equal, weather);
		        
		        Event eventA = new Event();
		        eventA.addOccurance("f1", o1);
		        eventA.addOccurance("f2", o2);

		        // Filter data*/
		        EventDefineUI ed = new EventDefineUI(configuration, dataset);
		        ed.setVisible(true);
		        
		    
			}
			
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFrame newField = new JFrame();
				newField.setBounds(300, 300, 300, 300);
				newField.getContentPane().setLayout(null);
				
				JLabel lblNameOfField = new JLabel("Nazwa Pola");
				lblNameOfField.setBounds(20, 10, 90, 15);
				newField.getContentPane().add(lblNameOfField);
				
				
				JLabel lblTypeOfField = new JLabel("Typ Pola");
				lblTypeOfField.setBounds(180, 10, 90, 15);
				newField.getContentPane().add(lblTypeOfField);


				
				final JTextField textField = new JTextField();
				textField.setBounds(10,55, 110, 15);
				newField.getContentPane().add(textField);
				textField.setColumns(5);
				
				
				final JTextField textField1 = new JTextField();
				textField1.setBounds(10,105, 110, 15);
				newField.getContentPane().add(textField1);
				textField.setColumns(5);
				
				final JTextField textField2 = new JTextField();
				textField2.setBounds(10,155, 110, 15);
				newField.getContentPane().add(textField2);
				textField.setColumns(5);
				
				
				
				
				
				
				 final JComboBox faceCombo = this.makeList(140,55,110,15);
			     newField.getContentPane().add(faceCombo);				
			     final JComboBox faceCombo1 = this.makeList(140,105,110,15);
			     newField.getContentPane().add(faceCombo1);
			     final JComboBox faceCombo2 = this.makeList(140,155,110,15);
			     newField.getContentPane().add(faceCombo2);
			     
				JButton btnAdd = new JButton("Add");
				btnAdd.setBounds(100, 200, 100, 40);
				btnAdd.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						if(faceCombo.getSelectedItem().toString().equals("Time") || faceCombo1.getSelectedItem().toString().equals("Time") || faceCombo2.getSelectedItem().toString().equals("Time")){
							final JDialog d = new JDialog();
							d.setBounds(500, 500, 200, 150);
							d.setLayout(null);
							JLabel lblPatern = new JLabel("Please write your time regex");
							lblPatern.setBounds(10,10,170,30);
							d.add(lblPatern);
							final JTextField timePatern= new JTextField();
							timePatern.setBounds(10,50,100, 20);
							d.add(timePatern);
							
							JButton ok = new JButton("Close");
							ok.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
								time = new SimpleDateFormat(timePatern.getText().toString());
								d.dispose();
								
								if(textField.getText().toString().length()!=0){
									Field f=null;
									if(faceCombo.getSelectedItem().toString().equals("Time"))
										f= new Field(textField.getText(), Field.Type.valueOf(faceCombo.getSelectedItem().toString()),time.toString());
									else
										 f= new Field(textField.getText(), Field.Type.valueOf(faceCombo.getSelectedItem().toString()));
									
									listOfFields.add(f);
									
									
								
								}
								if(textField1.getText().toString().length()!=0){
									Field f=null;
									if(faceCombo1.getSelectedItem().toString().equals("Time"))
										f= new Field(textField1.getText(), Field.Type.valueOf(faceCombo1.getSelectedItem().toString()),time.toString());
									else
										 f= new Field(textField1.getText(), Field.Type.valueOf(faceCombo1.getSelectedItem().toString()));
									
									listOfFields.add(f);
									
									System.out.println(listOfFields.size());
									System.out.println(f);
								}
								if(textField2.getText().toString().length()!=0){
									Field f=null;
									if(faceCombo2.getSelectedItem().toString().equals("Time"))
										f= new Field(textField2.getText(), Field.Type.valueOf(faceCombo2.getSelectedItem().toString()),time.toPattern().toString());
									else
										 f= new Field(textField2.getText(), Field.Type.valueOf(faceCombo2.getSelectedItem().toString()));
									
									listOfFields.add(f);
									
									
								}
								for(int i=iterator; i<listOfFields.size(); i++){
									
									list_1.add(listOfFields.get(i).name + "     " + listOfFields.get(i).type.toString());
									iterator = listOfFields.size();
								}
								
								for(Field f: listOfFields){
									System.out.println(f.name + "  " + f.time_regexp + "  " + f.type);
								}
								
								}
							});
							
							ok.setText("ok");
							ok.setBounds(40, 80, 50, 45);
							d.add(ok);
							
							d.setVisible(true);
							
						}
						
						
						
					}
					
				});
			
				
				newField.getContentPane().add(btnAdd);
				
				
				newField.setVisible(true);
				
				
				
			}

			private JComboBox makeList(int x, int y, int width, int height) {
				JComboBox faceCombo = new JComboBox();
			      faceCombo.setEditable(true);
			      faceCombo.addItem("String");
			      faceCombo.addItem("Number");
			      faceCombo.addItem("Time");
			     
			      faceCombo.setBounds(x, y, width, height);
			      return faceCombo;
			}
		});
		
	}
	
	private static String createFileChooser() {
		JFrame frame = new JFrame();

		
		        String filename = File.separator+"tmp";
		
		        JFileChooser fileChooser = new JFileChooser(new File(filename));
		
		 
		
		        // pop up an "Open File" file chooser dialog
		
		        fileChooser.showOpenDialog(frame);
		
		        
		
		        System.out.println("File to open: " + fileChooser.getSelectedFile());
		
		 
		
		 return fileChooser.getSelectedFile().toString();
		
		    }
	public Configuration returnConfiguration(){
		return configuration;
		
	}
}
