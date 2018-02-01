
public class Action {
	private Device device;
	private String action;
	
	public Action(Device device, String action) throws RuntimeException {  
		if(!device.GetProperty().IsRegisteredProperty(action))
			throw new RuntimeException(action + " : ��ϵ��� ����  �Ӽ����δ� ���� �� �� �����ϴ�.");
		this.device = device;	
		this.action = action;
	}
	//GetActionDevice(), GetActionValue()�� Actions���� ���� �׼��� �ѹ��� ���� �� �� ����Ѵ�.
	public Device GetActionDevice() {
		return this.device;
	}
	public String GetActionValue() {
		return this.action;
	}
	public void PerformAction() { 
		this.device.DeviceFieldChange(this.action);
	}
}
