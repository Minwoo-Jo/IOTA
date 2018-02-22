import java.util.ArrayList;

public class MotionSensor extends Device{
	public MotionSensor(String devName) {
		super(devName);
		ArrayList<String> switches = new ArrayList<>();
		switches.add("On");
		switches.add("Off");
		property.AddProperty("Switch", switches);
		AddUsingField("Switch"); // ���� ��밡���� property�� Switch�� ����Ѵ�.
	}
}
