package model;

import java.io.Serializable;

public class Card implements Serializable{

	private String title;
	private Boolean bln;

	private int cardNumber;


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getBln() {
		return bln;
	}
	public void setBln(Boolean bln) {
		this.bln = bln;
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
		bln = true;
	}

}
