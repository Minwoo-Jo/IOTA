import java.util.ArrayList;
import java.util.HashMap;

public class Device {
	protected Property property;   // ��ġ�� On, Off ���� ���� ����Ʈ�� �����Ѵ�.(On->5 ���� ������ ���� ���� ���)
	protected String devName; // Door1, Door2 ���� ��ġ�� �̸�
	protected Timer m;
	protected HashMap<String, Field> fields;
	protected ArrayList<String> fieldList; // �� �ݺ��� ���� ����

	public Device(String devName) {
		m = new Timer();
		this.devName = devName;
		this.property = new Property();
		fields = new HashMap<>();
		fieldList = new ArrayList<>();
	}
	public void AddUsingField(String fieldName) throws RuntimeException { // Property�� ��� ������ �Ӽ����̰�, Field�� ���� ����ϴ� �͵�
		if(!this.property.IsRegisteredProperty(fieldName))
			throw new RuntimeException(fieldName + "�� ��� ������ �ʵ尡 �ƴմϴ�. ��ġ�� Property�� Ȯ���ϼ���.");
		//�ʵ� �ʿ� property�� �ʿ��� fieldName���� property�� ã��, �װͿ� ù��°�� ��ϵ� value�� �־� �ʵ带 �����Ѵ�.
		fields.put(fieldName, new Field(property.GetProperties().get(fieldName).get(0)));
		fieldList.add(fieldName);
	}

	public ArrayList<String> GetFieldList() {
		return this.fieldList;
	}
	public Property GetProperty() {
		return this.property;
	}
	public String GetDevName() { //��ġ�� �̸��� ��� �޼ҵ�
		return this.devName;
	}
	public EventElement GetEventElement(String element)throws RuntimeException { 
		switch(element) {
		case "Timer" :
			return this.m;
		default:
			if(!fields.containsKey(element))
				throw new RuntimeException(element + "�� �� ��ġ�� �������� �ʾҽ��ϴ�.");
			return this.fields.get(element);
		}
	}
	public void DeviceFieldChange(String fieldName, String changedValue) {
		fields.get(fieldName).FieldChange(changedValue);
	}
	public void SetTimer() { // start timer at time
		this.m.StartTime();
	}
	public void StopTimer() { //stop timer
		this.m.StopTime();
	}
	public void SetVirtualTimer() {
		this.m.SetVirtualTime();
	}
	public void StopVirtualTimer() { //stop timer
		this.m.StopVirtualTime();
	}
	public String GetCurrentState(String fieldName) throws NotInitializeException {
		if(this.fields.get(fieldName) == null) {
			throw new NotInitializeException("�ʵ� ���� �ʱ�ȭ ���� �ʾҽ��ϴ�.");// lock �ʵ��� ���� �ʱ�ȭ �� ��µ� ����Ϸ��� �ϸ� ����
		}
		return this.fields.get(fieldName).GetCurrentValue();
	}
	public Timer GetTimer() throws NotInitializeException {
		if(m == null) {
			throw new NotInitializeException("Ÿ�̸Ӱ� �ʱ�ȭ ���� �ʾҽ��ϴ�.");// m �ʵ��� ���� �ʱ�ȭ �� ��µ� ����Ϸ��� �ϸ� ����
		}
		return this.m;
	}
	public void SetVirtualTime(String virtualTime) {
		this.m.ChangeVirtualTime(Integer.valueOf(virtualTime));
	}
}
