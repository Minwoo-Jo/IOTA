import java.util.ArrayList;
import java.util.List;

public class Actions {  //�ѹ��� ���� �׼ǵ��� �ϰ� �ϴ� �׼ǵ��� ���� �ְ� �����ϴ� Ŭ����
	List<Action> actionList;
	public Actions() {
		actionList = new ArrayList<>();
	}
	public void addAction(Action action) {
		actionList.add(action);
	}
}
