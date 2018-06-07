package abs4.beans;

import java.time.LocalDate;

public class Detail {

	private LocalDate day;
	private String type;
	private String content;
	private int cost;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Detail(LocalDate day, String type, String content, int cost) {
		super();
		this.day = day;
		this.type = type;
		this.content = content;
		this.cost = cost;
	}
}
