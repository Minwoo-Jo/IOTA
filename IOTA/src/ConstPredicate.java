
public class ConstPredicate implements Predicate {
	boolean constValue;
	public ConstPredicate(boolean value) {   // ó�� ������ ��, ������ ��ȯ�ϴ� Ŭ����
		super();
		this.constValue = value;
	}
	public boolean CheckPredicate() {
		return this.constValue;
	}
}
