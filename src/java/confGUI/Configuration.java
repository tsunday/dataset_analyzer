package confGUI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import reader.Field;


public class Configuration {
	String separator;
	ArrayList <Field> namesOfFileds;
	SimpleDateFormat time;
	String file;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public ArrayList<Field> getNamesOfFileds() {
		return namesOfFileds;
	}
	public void setNamesOfFileds(ArrayList<Field> namesOfFileds) {
		this.namesOfFileds = namesOfFileds;
	}
	public SimpleDateFormat getTime() {
		return time;
	}
	public void setTime(SimpleDateFormat time) {
		this.time = time;
	}
	

}
