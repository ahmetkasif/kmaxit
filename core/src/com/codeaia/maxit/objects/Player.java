package com.codeaia.maxit.objects;

public class Player {
	private int score;
	private String name;
	private boolean turn;

	public Player(String name, boolean turn) {
		setScore(0);
		setName(name);
		setTurn(turn);
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean getTurn(){
		return turn;
	}

	public int getScore() {
		return score;
	}

	public void resetScore() {
		this.score = 0;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addScore(int score) {
		this.score += score;
	}
}
