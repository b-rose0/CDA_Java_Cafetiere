package partie4;


public class Client {
	Tasse tasse;
	Cafe commandeCafe;
	String nom;
	double valeurFacture;
	
	public Client(String nom, Cafe commandeCafe, boolean creerTasse) {
		this.nom = nom;
		this.commandeCafe = commandeCafe;
		if(creerTasse) {
			tasse = new Tasse();
		}
	}
	public Client(String nom, Cafe commandeCafe, Tasse tasse) {
		this(nom, commandeCafe, false);
		this.tasse = tasse;
	}

	public Client() {
		this("Jean", null, true);
	}
}
