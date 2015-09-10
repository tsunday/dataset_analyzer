package Table;


import java.awt.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;

import reader.Field;
import reader.Reader;
import dataset.Dataset;

public class TableMaker extends JFrame {

	JTable table;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableMaker frame = new TableMaker();
					frame.setBounds(600,200,600,600);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	
	public TableMaker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
	
		/// TU KONFIGURACJA
		Path path = FileSystems.getDefault().getPath("C:\\Users\\Mateusz\\Documents\\dataset_analyzer\\src\\java\\test\\test_data", "test.txt");
        String delimiter = ";";
        List<Field> fields = new ArrayList<>();
        Field f1 = new Field("f1", Field.Type.Number);
        Field f2 = new Field("f2", Field.Type.String);
        Field f3 = new Field("f3", Field.Type.Time, "HH:mm:ss");

        // TU KONIEC KOFIGURACJII
        
        
        
        
        
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
      
        Reader reader = new Reader(path, fields, delimiter);
        Dataset dataset = reader.GenerateDataset();
        List<HashMap<String, Object>> ds = dataset.dataset;
        
       int x=0;
       HashMap<String, Object> hm =  ds.get(1);
      String[] arrayOfNames = new String[ds.get(0).keySet().size()];
       
    	for (Entry<String, Object> entry : hm.entrySet()) {
    		arrayOfNames[x]=entry.getKey();
    		
    		x++;
    	}
    	System.out.println(dataset.toString());
    	Object[][] entries = new Object[ds.size()][ds.get(0).size()];
    	for(int i=0; i<ds.size(); i++){
    		
    			entries[i]=ds.get(i).values().toArray();
    	
    	}
        
		
		table = new JTable(entries,arrayOfNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
	}

}
