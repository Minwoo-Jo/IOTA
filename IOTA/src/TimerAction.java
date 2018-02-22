
public class TimerAction implements Action { 
	// Stop m, Start m ó�� Timer�� �۵��ϴ� Action
	//����� Timer Ŭ�������� �޼ҵ带 ������ ����ϸ� �� �� ����.
	private Device device;
	private String action;
	
	public TimerAction(Device device, String action) throws RuntimeException { //action�� Start�� Stop		
		this.device = device;
		this.action = action;
	}
	public static TimerAction TimerStart(Device device) {
		return new TimerAction(device, "Start");
	}
	public static TimerAction TimerStop(Device device) {
		return new TimerAction(device, "Stop");
	}
	public void PerformAction() { 
		switch(action) { // start timer at 0, ������ 0������ �����Ѵٴ� ������
		case "Start" :
			this.device.m.SetVirtualTime();
			//this.device.m.StartTime();
			break;
		case "Stop" :
			this.device.m.StopVirtualTime();
			//this.device.m.StopTime();
			break;
		}
	}
}
