package Objects;

public class Player {
	private int score;
	private String name;

	public Player(String name) {
		setScore(0);
		setName(name);
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
