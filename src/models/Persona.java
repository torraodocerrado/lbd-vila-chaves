package models;

import java.util.ArrayList;
import java.util.List;

public class Persona {
	private String name;
	private String gender;
	private int age;
	private List<Catchphrase> catchphrases;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Catchphrase> getCatchphrases() {
		return catchphrases;
	}

	public void addCatchphrases(String phrase, String mood) {
		this.catchphrases.add(new Catchphrase(phrase, mood));
	}

	public Persona(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.catchphrases = new ArrayList<Catchphrase>();
	}

	public void print() {
		System.out.println();
		System.out.printf("==== Persona hash %s ====", this.hashCode());
		System.out.println();
		System.out.println("Name: " + this.name);
		System.out.println("Gender: " + this.gender);
		System.out.println("Age: " + String.valueOf(this.age));
		for (Catchphrase catchphrase : this.catchphrases) {
			catchphrase.print();
		}
	}

}
