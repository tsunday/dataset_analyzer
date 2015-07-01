package confGUI;

public class Field {
String field;
String type;
public Field(String text, String string) {
	this.field = text;
	this.type = string;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getField() {
	return field;
}
public void setField(String field) {
	this.field = field;
}

}
