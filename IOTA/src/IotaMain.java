import java.util.Scanner;

public class IotaMain { //3���� ��ġ�� ����ϰ� �ִٴ� ���� �Ͽ� simulation, �ַܼ� �ٲٷ��� ��ġ ���¸� �Է��ϸ� �ٲ�� Device�� Stop�� �Է��ϸ� �ݺ��� ���߰� ��ġ���� ���� ���
	static MotionSensor motionSensor;
	static Door door;
	static HallwayLight hallwayLight;
	static RegisteredDevices devices;
	
	public static void main(String[] args) {
		// ���⿡ IOTA ���α׷��� �ϴ� �� ó�� ���α׷��� �Ѵ�.
		/*eval�� ���� ���� ������� ������ ������� ���� �۵��ϰ� �ؾ߰ڴ�.
		eval.setDaemon(true);
		eval.start();
		 */
		IotaMain main = new IotaMain();
		Scanner input = new Scanner(System.in);
		
		door = new Door("Door1", "Locked");
		motionSensor = new MotionSensor("MotionSensor1", "Off");
		hallwayLight = new HallwayLight("HallwayLight1", "Off");
		
		devices = new RegisteredDevices();
		devices.addDevice(door);
		devices.addDevice(motionSensor);
		devices.addDevice(hallwayLight);
		
		while(true) {
			System.out.println("MotionSensor: " + motionSensor.GetCurrentState());
			System.out.println("Door: " + door.GetCurrentState());
			System.out.println("HallwayLight: " + hallwayLight.GetCurrentState());
			
			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop")) { // Stop�� �Է� �Ǹ� ������ ����
				System.out.println("IOTA�� ���� �Ǿ����ϴ�.");
				break;
			}
 
			System.out.print("State: "); // �ٲ� ���� Filed���� Timer����
			String state = input.nextLine();

			EventTrigger(device, state);
		}
	}

	public IotaMain() {
	}
	public static void EventTrigger(String device, String state) throws RuntimeException{ // ex)����� ���ͼ� motion sensor�� on �Ȱ��� �ݿ��Ѵ�.
		switch(device) { 
		case "MotionSensor" : 
				switch(state) {
				case "On" :
					motionSensor.f.FieldChange("On");
					break;
				case "Off" :
					motionSensor.f.FieldChange("Off");
					break;
				default : 
					throw new RuntimeException("Please submit \"On\" or \"Off\".");
				}
				break;
		case "Door" : 
				switch(state) {
				case "Locked" :
					door.f.FieldChange("Locked");
					break;
				case "UnLocked" :
					door.f.FieldChange("UnLocked");
					break;
				default : 
					throw new RuntimeException("Please submit \"Locked\" or \"UnLocked\".");
				}
				break;
		case "HallwayLight" : 
				switch(state) {
				case "On" :
					hallwayLight.f.FieldChange("On");
					break;
				case "Off" :
					hallwayLight.f.FieldChange("Off");
					break;
				default : 
					throw new RuntimeException("Please submit \"On\" or \"Off\".");
				}
				break;
		default :
			throw new RuntimeException("Unregistered Device is used.");
		}
	}
}
