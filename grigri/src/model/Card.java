package model;

import java.io.Serializable;

public class Card implements Serializable{

	Task task = new Task();
	private String title,
				   botton;

	private int cardNumber;


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		title = "provisional";
		cardNumber = 0;
	}

}
