import java.util.Calendar;

public class Timer implements EventElement { //Timer �����尡 ���� �ǰ� ���� �����忡�� �����带 ������� notify���ָ� ������� �����.(���۽ð�, ����ð��� ����� ���´�.)
	private int startTime; //IOTA���� start time at 0 ó�� at �ڿ� ���� ����
	private int startMinute; //���� Ÿ�̸Ӱ� start �� ���� �ð� �� ��(minute)�� ���Ѵ�.
	private int virtualTime;
	private Boolean IsStart;
	
	public Timer() {
		this.startTime = 0;
		this.virtualTime = 0;
		this.IsStart = false;
	}
	public void StartTime() {
		this.IsStart = true;
		this.startTime = 0;
		startMinute = Calendar.getInstance().get(Calendar.MINUTE);
	}
	public void StopTime() {
		this.IsStart = false;
		this.startMinute = 0;
	}
	public boolean IsTimePassed(int minute) { // timer = 5 ó�� timer�� ���� �ð��� �귯�� 5�� �Ǿ������� �˷��ش�. Predicate üũ �� �� ���
		int time = Calendar.getInstance().get(Calendar.MINUTE);
		int realPassedTime = time - startMinute;
		int passedTime = minute - startTime; // timer�� ���� �ð��� 0�� �ƴҼ��� �ֱ� ������ ���� 2���� �����ؼ� 5�� ��İ� ����� 3���� ������ ���� ��ȯ�Ѵ�.
		return (realPassedTime == passedTime) ? true : false; //���� ���� �ð��� IOTA���� ������ �ð�, �ٲ� �ð��� ���� ������ ���� ��ȯ�Ѵ�.
	}
	public void ChangeVirtualTime(int virtualTime) { // �������� �ð��� �帧�� �ش�.
		this.virtualTime = virtualTime;
	}
	public void SetVirtualTime() {
		this.IsStart = true;
	}
	public void StopVirtualTime() {
		this.virtualTime = 0;
		this.IsStart = false;
	}
	public boolean IsVirtualTimerStart() {
		if(this.IsStart)
			return true;
		return false;
	}
	public boolean IsVirtualTimePassed(int minute) { // ������ �ð��� �귶���� Ȯ���Ѵ�.
		return (this.virtualTime == minute) ? true : false;
	}
	public String GetOldValue() {
		return Integer.toString(this.startTime);
	}
	public String GetCurrentValue() {
		int currentTime = Calendar.getInstance().get(Calendar.MINUTE);
		int currentValue = currentTime - startMinute + startTime; //���� �ð����� �귯�� ���� �� ������ ��ȯ�Ѵ�.
		return Integer.toString(currentValue);
	}
	public String GetType() {
		return "Timer";
	}
	public boolean IsStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e) {

			return false;
		}
	}
}
