
public class CompPredicate implements Predicate {
	public static final int EQUAL = 1; // =
	public static final int LESS_THAN = 2; // <
	private String oprnd1, oprnd2;
	private int op;
	private Device device;
	
	public CompPredicate(Device device, String oprnd1, int op, String oprnd2) throws RuntimeException {   //oprnd1�� �ʵ峪 Ÿ�̸�, oprnd2�� �ʵ峪 ���
		super();
		if((oprnd1 != "Field") && (oprnd1 != "Timer"))
			throw new RuntimeException(oprnd1 + "�� Field�� Timer���� �մϴ�.");
		else if((op != LESS_THAN) && (op != EQUAL))
			throw new RuntimeException(op + "�� ��ϵ� �����ڰ� �ƴմϴ�. 1: =, 2: < �� �ϳ��� ����ϼ���.");
		else if(!device.GetProperty().IsRegisteredProperty(oprnd2) && !device.f.IsStringDouble(oprnd2))
			throw new RuntimeException(oprnd2 + "�� ��ġ�� ��ϵ� Property�� ������� �մϴ�.");
		
		this.device = device;
		this.oprnd1 = oprnd1;
		this.op = op;
		this.oprnd2 = oprnd2;
	}
	public boolean CheckPredicate() { 
		String currentElementValue = device.GetEventElement(oprnd1).GetCurrentValue(); //oprand1�� ���� ��
		switch(op) {
		case 1 :
			return currentElementValue == oprnd2;
		case 2 :
			return Double.parseDouble(currentElementValue) < Double.parseDouble(oprnd2);
		}
		return false;
	}
}
