package stores;
import java.util.*;
import models.*;

public class RulesManager {
	private Map<RuleType, Integer> rulePriority;
	private Map<Rule, List<Long>> rulesStatus;
	
	private static RulesManager rulesManagerInstance = new RulesManager();
	
	private RulesManager() {
		this.rulePriority = new HashMap<RuleType, Integer>();
		this.rulesStatus = new HashMap<Rule, List<Long>>();
		
		this.rulePriority.put(RuleType.API_TYPE, 1);
		this.rulePriority.put(RuleType.API_ENDPOINT, 2);
		this.rulePriority.put(RuleType.API_ENDPOINT_WITH_HEADER, 3);
	}
	
	public static RulesManager getIntance() {
		return RulesManager.rulesManagerInstance;
	}
	
	public void addRule(Rule rule) {
		this.rulesStatus.put(rule, new ArrayList<Long>());
	}
	
	private List<Rule> getMatchedRule(Request request) {
		List<Rule> matchedRule = new ArrayList<Rule>();
		
		for (Rule rule : this.rulesStatus.keySet()) {
			if (request.getRequestParameter().contains(rule.getRuleTypeKey().toLowerCase())) {
				matchedRule.add(rule);
			}
		}
		matchedRule.sort(Comparator.comparingInt((Rule r) -> this.rulePriority.get(r.getRuleType())));
		return matchedRule;
	}
	
	private boolean satisfied(Rule rule, long currentTime) {
		List<Long> timeStamps = this.rulesStatus.get(rule);
		
		timeStamps.removeIf(time -> time < currentTime - rule.getTimeWindow() * 1000 * 60);
		if (timeStamps.size() >= rule.getThreshold()) {
			return false;
		}
		
		timeStamps.add(currentTime);
		return true;
    }
	
	public synchronized boolean verifyRules(Request request) {
		List<Rule> matchedRules = this.getMatchedRule(request);
		
		for (Rule rule : matchedRules) {
			if (!this.satisfied(rule, System.currentTimeMillis())) {
				throw(new RuntimeException("Rule Failed " + rule.getRuleType() + " " + rule.getThreshold() + " " + rule.getRuleTypeKey() + " " + rule.getRuleTypeValue()));
			}
		}
		
        return true;
	}
	
}
