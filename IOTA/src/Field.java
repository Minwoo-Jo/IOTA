
public class Field implements EventElement{
	private String old_value;
	private String current_value;
	private String fieldType;

	public Field(String value) {
		this.old_value = value;
		this.current_value = value;
		if(IsStringDouble(value)) {
			this.fieldType = "Double";
		} 
		else {
			this.fieldType = "String";
		}
	}
	public void FieldChange(String changedValue) { // �ʵ� ���� �ٲٷ��� �ٲ�� �� ���� ���� ������ �ٲ� ���� ���� ������ �Ѵ�.
		this.old_value = current_value;
		this.current_value = changedValue;
	}
	public boolean IsStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e) {

			return false;
		}
	}
	public String GetOldValue() { //�ٲ�� ���� �ʵ� ���� ��ȯ
		return this.old_value;
	}
	public String GetCurrentValue() { //���� �ʵ��� ���� ��ȯ
		return this.current_value;
	}
	public String GetType() {
		return "Field";
	}
}
