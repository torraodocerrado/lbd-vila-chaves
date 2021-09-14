package models;

public class Catchphrase {
	private String phrase;
	private String mood;

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public Catchphrase(String phrase, String mood) {
		this.phrase = phrase;
		this.mood = mood;
	}

	public void print() {
		System.out.println("catchphrase: '" + this.phrase + "' --> " + this.mood);
	}

}
