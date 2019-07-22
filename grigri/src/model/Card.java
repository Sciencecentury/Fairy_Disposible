package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Serializable{

	Task task = new Task();

	private String title,
				   botton;
	private List<String> taskSummary = new ArrayList<String>();
	private List<Task> taskStrage = new ArrayList<Task>();

	private int cardNumber;

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

	public List<Task> getTaskStrage() {
		return taskStrage;
	}
	public void setTaskStrage(List<Task> taskStrage) {
		this.taskStrage = taskStrage;
	}

/**
 * cardNumberはインスタンスにしたカードを判別するもの
 */
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
/**
 * bottonの中身は変更しない
 */
	public Card(){
		cardNumber = 0;
		title = "provisional";
		//taskSummary.add(0,"<div class='task'>"+taskStrage.get(0).getTaskTitle()+"</div>");
		taskStrage.add(0,task);
		botton = "<form action='/grigri/Main'><input type='hidden' name='cardNumber'value="
				  +cardNumber+"><input type='hidden' name='move' value='cts'>"
				  + "<input type='submit' name='card' value='新規課題作成'></form>";
	}

}
