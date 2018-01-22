import java.util.ArrayList;
import java.util.List;

public class Action {
	Device device;
	List<String> actionList;
	public Action(Device device, String action) {  //이런식으로 Device 하나만 받으면 한 장치의 필드 값만 변경 할 수 있으니까 다른 방법을 생각해봐야겠다.
		this.device = device;
		actionList = new ArrayList<>();
	}
	public void AddAction(String action) {  // 각 Action 객체마다 여러개의 action을 수행하는 actionList를 갖는다. 거기에 action을 추가하는 메소드
		actionList.add(action);
	}
}
