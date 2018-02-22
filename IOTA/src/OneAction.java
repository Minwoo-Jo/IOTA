
public class OneAction implements Action {
	protected Device device;
	protected String field;
	protected String action;
	
	public OneAction(Device device, String field, String action) throws RuntimeException {  
		if(!device.GetProperty().IsRegisteredProperty(field))
			throw new RuntimeException(field + " : ��ϵ��� ����  �ʵ�δ� ���� �� �� �����ϴ�.");
		else if(!device.GetProperty().IsRegisteredPropertyState(field, action))
			throw new RuntimeException(action + " : ��ϵ��� ����  �Ӽ� �����δ� ���� �� �� �����ϴ�.");
		this.device = device;	
		this.field = field;
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
		this.device.DeviceFieldChange(this.field, this.action);
	}
}
