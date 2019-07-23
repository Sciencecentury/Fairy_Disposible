package model;

import java.io.Serializable;

public class Card implements Serializable{

	private String title,
				   botton;

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
		botton = "<form action='/grigri/Main'><input type='hidden' name='cardNumber'value="
				  +this.cardNumber+"><input type='hidden' name='move' value='cts'>"
				  + "<input type='submit' name='card' value='新規課題作成'></form>";
	}

	public void bottonReload(){
		botton = "<form action='/grigri/Main'><input type='hidden' name='cardNumber'value="
				  +this.cardNumber+"><input type='hidden' name='move' value='cts'>"
				  + "<input type='submit' name='card' value='新規課題作成'></form>";
	}
}
