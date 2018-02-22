
import java.util.ArrayList;
import java.util.HashMap;
public class RegisteredDevices {
	HashMap<String, Device> deviceMap; //��ġ�� �̸��� key�� value�� ��ġ�� �Ѵ�.
	ArrayList<String> deviceMapList; // ��ġ��(key)�� �����ϴ� list, �ؽø��� �ݺ��ϴµ� ���
	
	public RegisteredDevices() {
		deviceMap = new HashMap<>();
		deviceMapList = new ArrayList<>();
		
		Door entranceDoor = new Door("EntranceDoor"); //��ġ��, �ʱ� �Ӽ���
		Door kitchenDoor = new Door("KitchenDoor");
		MotionSensor porchMotionSensor = new MotionSensor("PorchMotionSensor");
		HallwayLight hallwayLight = new HallwayLight("HallwayLight");

		AddDevice(entranceDoor);
		AddDevice(kitchenDoor);
		AddDevice(porchMotionSensor);
		AddDevice(hallwayLight);
	}
	public void AddDevice(Device device) {   // ����� ��ġ�� ����Ѵ�.
		deviceMap.put(device.GetDevName(), device);
		deviceMapList.add(device.GetDevName());
	}
	public void DeleteDevice(String devName) {  // ��ġ�� ����� �����Ѵ�.
		deviceMap.remove(devName);
		deviceMapList.remove(devName);
	}
	public Device GetDevice(String devName) {  // ��ġ�� �̸����� ��ġ�� �����´�.
		return this.deviceMap.get(devName);
	}
	public Property GetDeviceProperty(String devName) {  //��ġ�� �̸����� ��ġ�� Property�� �����´�.
		return this.deviceMap.get(devName).GetProperty();
	}
	public ArrayList<String> GetDeviceMapList() {   // ��ϵ� ��ġ�� ���� �ݺ��ϴµ� ����Ѵ�.
		return this.deviceMapList;
	}
	public boolean IsRegisteredDevice(String devName) {   //��ġ�� �̸����� ��ϵ� �ִ��� Ȯ���Ѵ�.
		return deviceMap.containsKey(devName);
	}
}
