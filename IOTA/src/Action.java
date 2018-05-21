import java.util.ArrayList;

public interface Action {
	public void PerformAction();
	public String ActionType();
	public String ActionName();
	public boolean ActionComplete();
	public ArrayList<Action> ForAnyAction();
}
