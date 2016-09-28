package model;

public class TweetModel {

	private String status;

	public TweetModel(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void sendToHell() {
		System.err.println("sent to hell: " + this.status);
	}

	public void sendToHeaven() {
		System.out.println("sent to heaven: " + this.status);
	}

}
