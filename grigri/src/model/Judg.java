package model;

public class Judg {
	private Boolean judg;
	private int cnt;

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Boolean getJudg() {
		return judg;
	}

	public void setJudg(Boolean judg) {
		this.judg = judg;
	}

	public Judg(){
		judg = false;
	}

	public void cnt(){
		this.setCnt(0);
	}
}
