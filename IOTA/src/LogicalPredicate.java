
public class LogicalPredicate implements Predicate {
	public static final int AND = 1; 
	public static final int OR = 2; 
	public static final int NOT = 3; 
	private Predicate predicate1;
	private int op;
	private Predicate predicate2;

	public LogicalPredicate(int op, Predicate predicate1) throws RuntimeException { //NOT �϶� ���
		super();
		if(op != 3)
			throw new RuntimeException(op + "�� �߸��� ���� ��ȣ �Դϴ�: NOT ������ ���� ��ȣ�� 3�Դϴ�.");
		this.op = op;
		this.predicate1 = predicate1;
	}
	public LogicalPredicate(Predicate predicate1, int op, Predicate predicate2) throws RuntimeException {
		super();
		if((op != 1) && (op != 2))
			throw new RuntimeException(op + "�� �߸��� ���� ��ȣ �Դϴ�: AND ������ ���� ��ȣ�� 1, OR�� 2�Դϴ�.");
		this.predicate1 = predicate1;
		this.op = op;
		this.predicate2 = predicate2;
	}
	public boolean CheckPredicate() {
		if(predicate2 == null) { //NOT ������ ��� �� ��
			return !predicate1.CheckPredicate();
		}
		
		switch(op) {
		case 1 :
			return predicate1.CheckPredicate() && predicate2.CheckPredicate();
		case 2 :
			return predicate1.CheckPredicate() || predicate2.CheckPredicate();
		}
		return false;
	}
}
