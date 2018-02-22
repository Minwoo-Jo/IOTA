import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Property {
	private HashMap<String, ArrayList<String>> properties;
	public Property() {
		this.properties = new HashMap<>();
	}
	public HashMap<String, ArrayList<String>> GetProperties() { 
		return this.properties;
	}
	public void AddProperty(String property, ArrayList<String> values) {
		this.properties.put(property, values);
	}
	public boolean IsRegisteredProperty(String property) { 
		//��ϵ� property���� Ȯ���ϴ� �޼ҵ� ex)Lock, Switch ...
		return properties.containsKey(property);
	}
	public boolean IsRegisteredPropertyState(String property, String state) { 
		//��ϵ� property ������ Ȯ���ϴ� �޼ҵ� ex)Locked, UnLocked, On, Off ...
		return properties.get(property).contains(state);
	}
	
}
