package models;

public class Rule {
	private RuleType ruleType;
	private int threshold;
	private int timeWindow;
	
	private String ruleTypeKey;
	private String ruleTypeValue;

	public Rule(RuleType ruleType, int threshold, int timeWindow, String ruleTypeKey, String ruleTypeValue) {
		super();
		this.ruleType = ruleType;
		this.threshold = threshold;
		this.timeWindow = timeWindow;
		this.ruleTypeKey = ruleTypeKey;
		this.ruleTypeValue = ruleTypeValue;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getTimeWindow() {
		return timeWindow;
	}

	public void setTimeWindow(int timeWindow) {
		this.timeWindow = timeWindow;
	}

	public String getRuleTypeKey() {
		return ruleTypeKey;
	}

	public void setRuleTypeKey(String ruleTypeKey) {
		this.ruleTypeKey = ruleTypeKey;
	}
	
	public String getRuleTypeValue() {
		return ruleTypeValue;
	}

	public void setRuleTypeValue(String ruleTypeValue) {
		this.ruleTypeValue = ruleTypeValue;
	}
}

