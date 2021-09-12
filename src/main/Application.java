package main;

import controllers.PersonaController;
import models.Persona;

public class Application {

	private static Persona getChaves() {
		Persona chaves = new Persona("Chaves", "masculino", 8);
		chaves.addCatchphrases("Isso, isso, isso!", "concorda");
		chaves.addCatchphrases("Pipipipipi!", "choro");
		return chaves;
	}

	public static void main(String[] args) {
		System.out.println("========== Bem vindo ao programa Vila do chaves! ========== ");

		Persona chaves = Application.getChaves();

		PersonaController personaCtl = new PersonaController();

		personaCtl.delete(chaves);
		personaCtl.insert(chaves);

		chaves.print();

		Persona chavesFromDB = personaCtl.find_by_name("Chaves");

		chavesFromDB.print();

		System.out.println("========== Fim do programa! ==========");
	}

}
