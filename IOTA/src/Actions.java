import java.util.ArrayList;
public class Actions {  //�ѹ��� ���� �׼ǵ��� �ϰ� �ϴ� �׼ǵ��� ���� �ְ� �����ϴ� Ŭ����
	ArrayList<Action> actions;

	public Actions() {
		actions = new ArrayList<>();
	}
	public void addAction(Action action) {
		actions.add(action);
	}
	public void PerformActions() { 
		for(Action action : this.actions) {
			action.GetActionDevice().DeviceFieldChange(action.GetActionValue());
		}
	}
}
