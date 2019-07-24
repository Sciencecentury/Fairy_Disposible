package model;

public class Task {
	private String taskTitle,
				   taskContents;
	private int cardNumber;

	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskContents() {
		return taskContents;
	}
	public void setTaskContents(String taskContents) {
		this.taskContents = taskContents;
	}

	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Task(){
		taskTitle = "タスクを追加してください";
		taskContents = "provisional";
	}
}
