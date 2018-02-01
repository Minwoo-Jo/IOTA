import java.util.Calendar;

public class Timer extends Thread implements EventElement { //Timer �����尡 ���� �ǰ� ���� �����忡�� �����带 ������� notify���ָ� ������� �����.(���۽ð�, ����ð��� ����� ���´�.)
	private int startTime; //IOTA���� start time at 0 ó�� at �ڿ� ���� ����
	private int startMinute; //���� Ÿ�̸Ӱ� start �� ���� �ð� �� ��(minute)�� ���Ѵ�.
	private Calendar cal;
	
	public Timer() {
		this.startTime = 0;
		cal.getInstance();
		startTime = cal.get(Calendar.MINUTE);
	}
	public Timer(int time) { //IOTA���� start time at 0 ó�� at �ڿ� ���� ���ڸ� �޴´�.
		this.startMinute = time;
		cal.getInstance();
		startTime = cal.get(Calendar.MINUTE); //Ÿ�̸Ӹ� ���� �� �� ���� �ð��� ���� ����
	}
	public boolean IsTimePassed(int minute) { // timer = 5 ó�� timer�� ���� �ð��� �귯�� 5�� �Ǿ������� �˷��ش�. Predicate üũ �� �� ���
		int realPassedTime = cal.get(Calendar.MINUTE) - startMinute;
		int passedTime = minute - startTime; // timer�� ���� �ð��� 0�� �ƴҼ��� �ֱ� ������ ���� 2���� �����ؼ� 5�� ��İ� ����� 3���� ������ ���� ��ȯ�Ѵ�.
		return (realPassedTime == passedTime) ? true : false; //���� ���� �ð��� IOTA���� ������ �ð�, �ٲ� �ð��� ���� ������ ���� ��ȯ�Ѵ�.
	}
	public String GetOldValue() {
		return Integer.toString(this.startTime);
	}
	public String GetCurrentValue() {
		int currentValue = cal.get(Calendar.MINUTE) - startMinute + startTime; //���� �ð����� �귯�� ���� �� ������ ��ȯ�Ѵ�.
		return Integer.toString(currentValue);
	}
	public String GetType() {
		return "Timer";
	}
}
