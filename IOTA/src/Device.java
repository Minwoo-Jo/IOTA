
public class Device {
	protected Property property;   // ��ġ�� On, Off ���� ���� ����Ʈ�� �����Ѵ�.(On->5 ���� ������ ���� ���� ���)
	protected String devName; // Door1, Door2 ���� ��ġ�� �̸�
	protected Field f;
	protected Timer m;
	
	public Device(String devName, String field) {
		this.f = new Field(field);
		this.devName = devName;
		this.property = new Property();
	}
	public Property GetProperty() {
		return this.property;
	}
	public String GetDevName() { //��ġ�� �̸��� ��� �޼ҵ�
		return this.devName;
	}
	public EventElement GetEventElement(String element)throws RuntimeException { 
		switch(element) {
		case "Field" :
			return this.f;
		case "Timer" :
			return this.m;
		default:
			throw new RuntimeException("Field�� Timer�ܿ� �ٸ� ���� �ԷµǾ����ϴ�.");
		}
	}
	public void DeviceFieldChange(String changedValue) {
		f.FieldChange(changedValue);
	}
	public void SetTimer(int time) { // start timer at time
		this.m = new Timer(time);
	}
	public void StopTimer() { //stop timer
		this.m = null;
	}
	public String GetCurrentState() throws NotInitializeException {
		if(this.f == null) {
			throw new NotInitializeException("�ʵ� ���� �ʱ�ȭ ���� �ʾҽ��ϴ�.");// lock �ʵ��� ���� �ʱ�ȭ �� ��µ� ����Ϸ��� �ϸ� ����
		}
		return this.f.GetCurrentValue();
	}
	public Timer GetTimer() throws NotInitializeException {
		if(m == null) {
			throw new NotInitializeException("Ÿ�̸Ӱ� �ʱ�ȭ ���� �ʾҽ��ϴ�.");// m �ʵ��� ���� �ʱ�ȭ �� ��µ� ����Ϸ��� �ϸ� ����
		}
		return this.m;
	}
}
