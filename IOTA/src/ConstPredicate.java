
public class ConstPredicate implements Predicate {
	boolean constValue;
	public ConstPredicate(boolean value) {   // ó�� ������ ��, ������ ��ȯ�ϴ� Ŭ����
		this.constValue = value;
	}
	public boolean CheckPredicate() {
		return this.constValue;
	}
}
