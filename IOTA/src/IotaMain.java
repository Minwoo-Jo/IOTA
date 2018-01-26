import java.util.Scanner;

public class IotaMain { //3���� ��ġ�� ����ϰ� �ִٴ� ���� �Ͽ� simulation, �ַܼ� �ٲٷ��� ��ġ ���¸� �Է��ϸ� �ٲ�� Device�� Stop�� �Է��ϸ� �ݺ��� ���߰� ��ġ���� ���� ���
	static RegisteredDevices devices;
	
	public static void main(String[] args) {
		// ���⿡ IOTA ���α׷��� �ϴ� �� ó�� ���α׷��� �Ѵ�.
		/*eval�� ���� ���� ������� ������ ������� ���� �۵��ϰ� �ؾ߰ڴ�.
		eval.setDaemon(true);
		eval.start();
		 */
		Scanner input = new Scanner(System.in);
		
		Door entranceDoor = new Door("EntranceDoor", "Locked"); //��ġ��, �ʱ� �Ӽ���
		Door kitchenDoor = new Door("KitchenDoor", "Locked");
		MotionSensor motionSensor = new MotionSensor("MotionSensor1", "Off");
		HallwayLight hallwayLight = new HallwayLight("HallwayLight1", "Off");
		
		devices = new RegisteredDevices();
		devices.AddDevice(entranceDoor);
		devices.AddDevice(kitchenDoor);
		devices.AddDevice(motionSensor);
		devices.AddDevice(hallwayLight);
		
		CompPredicate cp1 = new CompPredicate(entranceDoor, "Field", 1, "Locked"); // predicate �۵� Ȯ��
		CompPredicate cp2 = new CompPredicate(motionSensor, "Field", 1, "On");
		LogicalPredicate cp3 = new LogicalPredicate(cp1, 2, cp2);
		LogicalPredicate cp4 = new LogicalPredicate(3, cp2);
		ConstPredicate cp5 = new ConstPredicate(false);
		System.out.println(cp1.CheckPredicate());
		System.out.println(cp2.CheckPredicate());
		System.out.println(cp3.CheckPredicate());
		System.out.println(cp4.CheckPredicate());
		System.out.println(cp5.CheckPredicate());
		while(true) {
			for(String devName : devices.GetDeviceMapList()) {
				System.out.println(devName + " : " + devices.GetDevice(devName).GetCurrentState());
			} 
			
			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop")) { // Stop�� �Է� �Ǹ� ������ ����
				System.out.println("IOTA�� ���� �Ǿ����ϴ�.");
				break;
			}
 
			System.out.print("State: "); // �ٲ� ���� Filed���� Timer����
			String state = input.nextLine();

			EventTrigger(device, state);
			//System.out.println(cp.CheckPredicate());
		}
	}

	public IotaMain() {
	}
	public static void EventTrigger(String devName, String state) throws RuntimeException {
		if(!devices.IsRegisteredDevice(devName)) //��ϵ� ��ġ���� Ȯ��
			throw new RuntimeException(devName + "�� ��ϵ� ��ġ���� �ƴմϴ�. ��ġ���� �ٽ� Ȯ���� �ּ���.");
		else if(!devices.GetDeviceProperty(devName).IsRegisteredProperty(state)) //��ϵ� property ���� �Է��ߴ��� Ȯ��
			throw new RuntimeException(state + "�� ��ġ�� ��ϵ� �Ӽ� ���� �ƴմϴ�. �Ӽ� ���� �ٽ� Ȯ���� �ּ���.");
		
		//������� ���� ��ϵ� ��ġ�� ��ϵ� property�� �ٲٴ°� �ȴ�.
		devices.GetDevice(devName).DeviceFieldChange(state);
	}
}
