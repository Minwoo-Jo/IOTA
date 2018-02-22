import java.util.Scanner;

public class IotaMain { 
	private RegisteredDevices devices;

	public static void main(String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		IotaMain main = new IotaMain();
		main.devices = new RegisteredDevices();
		
		/*
		Event e1 = Event.From(main.devices.GetDevice("EntranceDoor")
				.GetEventElement("Lock"), "Locked");
		CompPredicate p1 = CompPredicate.CompEqual(main.devices
				.GetDevice("EntranceDoor"), "Lock", "UnLocked");
		OneAction a1 = new OneAction(main.devices
				.GetDevice("HallwayLight"), "Switch", "On");
		Rule rule1 = new Rule(e1, p1, a1);

		RuleSet ruleset = new RuleSet();
		ruleset.add(rule1);
		Evaluation eval = new Evaluation(ruleset);
		*/

		Event e1 = Event.From(main.devices
				.GetDevice("EntranceDoor")
				.GetEventElement("Lock"), "UnLocked");
		CompPredicate p1 = CompPredicate.CompEqual(main.devices
				.GetDevice("EntranceDoor"), "Lock", "Locked");
		TimerAction a1 = TimerAction.TimerStart(main.devices
				.GetDevice("HallwayLight"));
		Rule rule1 = new Rule(e1, p1, a1);

		Event e2 = Event.UnConditional(main.devices
				.GetDevice("HallwayLight")
				.GetEventElement("Timer"));
		CompPredicate p2 = CompPredicate.CompEqual(main.devices
				.GetDevice("HallwayLight"), "Timer", "5");
		OneAction a21 = new OneAction(main.devices
				.GetDevice("HallwayLight"), "Switch", "Off");
		TimerAction a22 = TimerAction.TimerStop(main.devices
				.GetDevice("HallwayLight"));
		AnyActions as2 = new AnyActions();
		as2.addAction(a21);
		as2.addAction(a22);
		Rule rule2 = new Rule(e2, p2, as2);

		RuleSet ruleset = new RuleSet();
		ruleset.add(rule1);
		ruleset.add(rule2);
		Evaluation eval = new Evaluation(ruleset);

		while(true) {
			DeviceStatePrinter.print(main.devices);
			
			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop")) { // Stop�� �Է� �Ǹ� ������ ����
				System.out.println("IOTA�� ���� �Ǿ����ϴ�.");
				break;
			}

			System.out.print("Field: "); 
			String field = input.nextLine();

			System.out.print("State: "); // 
			String state = input.nextLine();

			main.EventTrigger(device, field, state);
			eval.Evaluate(main.devices);
		}
	}

	public IotaMain() {

	}
	public void EventTrigger(String devName, String field, String state) throws RuntimeException {
		if(!devices.IsRegisteredDevice(devName)) //��ϵ� ��ġ���� Ȯ��
			throw new RuntimeException(devName + "�� ��ϵ� ��ġ���� �ƴմϴ�. ��ġ���� �ٽ� Ȯ���� �ּ���.");
		if(!field.equals("Timer")) {
			if(!devices.GetDeviceProperty(devName).IsRegisteredProperty(field)) //��ϵ� property ���� �Է��ߴ��� Ȯ��
				throw new RuntimeException(field + "�� ��ġ�� ��ϵ� �Ӽ��� �ƴմϴ�. �Ӽ��� �ٽ� Ȯ���� �ּ���.");
			if(!devices.GetDeviceProperty(devName).IsRegisteredPropertyState(field, state)) //��ϵ� property ���� �Է��ߴ��� Ȯ��
				throw new RuntimeException(state + "�� ��ġ�� ��ϵ� �Ӽ� ���� �ƴմϴ�. �Ӽ� ���� �ٽ� Ȯ���� �ּ���.");
			//������� ���� ��ϵ� ��ġ�� ��ϵ� property�� �ٲٴ°� �ȴ�.
			//���⼭ ���� �������� �ʵ带 �ϳ��� ���� �ִ� �ŷ� �����ؼ� �׳� device�� �ʵ� 1���� �־�����
			//������ �ʵ尡 �ִ°ɷ� �ؾ��ϱ� ������ device�� HashMap(String fieldName, Field f)�� ����
			//������ �ʵ带 �� ��ġ���� �ʿ��ϸ� �߰��ϰ� �ʵ� ���� �ٲٴ� �͵� �� ��ġ�� �ʵ� �� Ư�� �ʵ带 �����ؼ� ��
			//���� �����ϵ��� �ϰ�, �̺�Ʈ���� �߻��ߴ��� üũ �Ҷ��� ��ġ�� Ư�� �ʵ带 ������ �װ��� ���� �ٲ����� üũ�ϰ� ����.
			devices.GetDevice(devName).DeviceFieldChange(field, state);
		}
		else {
			devices.GetDevice(devName).SetVirtualTime(state);
		}
	}
}
