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
		MotionSensor porchMotionSensor = new MotionSensor("PorchMotionSensor", "Off");
		HallwayLight hallwayLight = new HallwayLight("HallwayLight1", "Off");
		
		devices = new RegisteredDevices();
		devices.AddDevice(entranceDoor);
		devices.AddDevice(kitchenDoor);
		devices.AddDevice(porchMotionSensor);
		devices.AddDevice(hallwayLight);
		
		//Predicate Ȯ��
		CompPredicate cp1 = new CompPredicate(devices.GetDevice("EntranceDoor"), "Field", 1, "Locked"); // predicate �۵� Ȯ��
		CompPredicate cp2 = new CompPredicate(devices.GetDevice("PorchMotionSensor"), "Field", 1, "On");
		LogicalPredicate cp3 = new LogicalPredicate(cp1, 2, cp2);
		LogicalPredicate cp4 = new LogicalPredicate(3, cp2);
		ConstPredicate cp5 = new ConstPredicate(false);
		
		System.out.println("Predicate Ȯ�� ------------------------");
		System.out.println(cp1.CheckPredicate());
		System.out.println(cp2.CheckPredicate());
		System.out.println(cp3.CheckPredicate());
		System.out.println(cp4.CheckPredicate());
		System.out.println(cp5.CheckPredicate());
		System.out.println("------------------------");
		
		//Action Ȯ��
		System.out.println("Action Ȯ�� ------------------------");
		Action action1 = new Action(kitchenDoor, "UnLocked");
		action1.PerformAction();
		System.out.println(devices.GetDevice("KitchenDoor").GetCurrentState());
		
		Action action2 = new Action(kitchenDoor, "Locked");
		Action action3 = new Action(porchMotionSensor, "On");
		Actions actions1 = new Actions();
		actions1.addAction(action2);
		actions1.addAction(action3);
		actions1.PerformActions();
		System.out.println(devices.GetDevice("KitchenDoor").GetCurrentState());
		System.out.println(devices.GetDevice("PorchMotionSensor").GetCurrentState());
		System.out.println("------------------------");
		
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
			// �������� �帧�� IOTA ������� ���α׷��� �� ���� Evaluation �ϴ� ���� �ݺ��ϴ� ������� ��� ���鼭 ��ġ�� ���¸� �Է��ϴ� �����带 ���ٰ� ��ġ�Է��� ������,
			// ��, ���⼭ ��ġ�� ���¸� �����ϸ� Evaluation�� �ݺ��ϴ� ������� �װ��� �ݿ��ϰ� �ٽ� Evaluation�� �����Ѵ�.
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
