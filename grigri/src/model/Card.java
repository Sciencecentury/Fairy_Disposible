package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Serializable{
	private String title,
				   botton;
	private List<String> taskSummary = new ArrayList<String>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBotton() {
		return botton;
	}
	public void setBotton(String botton) {
		this.botton = botton;
	}
	public List<String> getTaskSummary() {
		return taskSummary;
	}
	public void setTaskSummary(List<String> taskSummary) {
		this.taskSummary = taskSummary;
	}

	public Card(){
		title = "provisional";
		taskSummary.add("<div id='task></div>");
		botton = "<form action='/WEB-INF/output/scheduleCreation.jsp'><input type='submit' value='新規課題作成'></form>";
	}

	public String test(String tes){
		for(String ts : this.getTaskSummary()){
			tes = "<br>"+ts;
		}
		return tes;
	}
}
