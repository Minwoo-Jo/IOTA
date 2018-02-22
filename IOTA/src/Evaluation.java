import java.util.ArrayList;
import java.util.Set;

public class Evaluation {
	private RuleSet ruleSet;
	private ArrayList<Integer> triggeredEvent;
	private ArrayList<Integer> truePredicate;

	public Evaluation(RuleSet ruleSet) {
		this.ruleSet = ruleSet;	
		triggeredEvent = new ArrayList<>();
		truePredicate = new ArrayList<>();
	}
	public void Evaluate(RegisteredDevices devices){
		for(Event e : ruleSet.GetEventHandlerList()) {
			if(e.IsEventTriggered())
				triggeredEvent.add(ruleSet.GetEventHandlerList().indexOf(e));
		}
		for(Integer i : triggeredEvent) {
			Predicate p = ruleSet.GetPredicateList().get(i);
			if(p.CheckPredicate())
				truePredicate.add(ruleSet.GetPredicateList().indexOf(p));
		}
		while(!truePredicate.isEmpty()) { // Predicate�� true�� action���� random�� ������ ����
			int rand = (int)(Math.random() * truePredicate.size());
			ruleSet.GetActionList().get(truePredicate.remove(rand)).PerformAction();
		}
		if(!truePredicate.isEmpty()) {
			System.out.println("Evaluate�� ���� ���� ����");
			DeviceStatePrinter.print(devices);
		}	
	}
}