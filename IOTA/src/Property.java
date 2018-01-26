import java.util.ArrayList;
import java.util.List;

public class Property {
	private ArrayList<String> values;
	
	public Property() {
		this.values = new ArrayList<>();
	}
	public String GetValue(int n) { //n�� 0�̸�
		return this.values.get(n);
	}
	public void AddPropertyValue(String value) {
		this.values.add(value);
	}
	public boolean IsRegisteredProperty(String property) { //��ϵ� property���� Ȯ���ϴ� �޼ҵ�
		return values.contains(property);
	}
	
}
