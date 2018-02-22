import java.util.ArrayList;
public class AnyActions implements Action {  //�ѹ��� ���� �׼ǵ��� �ϰ� �ϴ� �׼ǵ��� ���� �ְ� �����ϴ� Ŭ����
	ArrayList<Action> actions;

	public AnyActions() {
		actions = new ArrayList<>();
	}
	public void addAction(Action action) {
		actions.add(action);
	}
	public void PerformAction() { 
		for(Action action : this.actions) {
			action.PerformAction();
		}
	}
}
