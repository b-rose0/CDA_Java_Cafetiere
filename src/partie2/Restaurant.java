package partie2;


public class Restaurant {
	Cafetiere cafetiere;
	double profit;


	public Restaurant() {
		cafetiere = new Cafetiere();
	}

	public double servir(Client client) {
		boolean estJeteDuRestaurant = clientVientSansCommande(client) || batardEstCommande(client);
		if(!estJeteDuRestaurant) {
			client.valeurFacture = client.commandeCafe.typeCafe.coutParMl * client.commandeCafe.quantiteLiquideMl;
			clientAUneTasse(client);
			cafetiere.remplirTasse(client.tasse, client.commandeCafe.typeCafe, client.commandeCafe.quantiteLiquideMl);
			contenantDeborde(client);
			//Mixe de différents café gérer dans cafetière
			profit += client.valeurFacture;
		}
		return client.valeurFacture;
	}


	private void clientAUneTasse(Client client) {
		if(client.tasse == null) {
			if(client.commandeCafe.quantiteLiquideMl <= 100) {
				client.valeurFacture += 2.00;
				client.tasse = new Tasse();
			} else {
				client.valeurFacture += 3.00;
				client.tasse = new Tasse(500);
			}
		}
	}

	private void estJeteDuRestaurant(Client client) {
		client.valeurFacture = 0;
		System.out.println("Le client "+ client.nom +" est jeté du restaurant.");
	}


	private boolean batardEstCommande(Client client) {
		if (client.commandeCafe != null) {
			if (client.commandeCafe.typeCafe == TypeCafe.BATARD) {
				estJeteDuRestaurant(client);
				return true;
			}
		}
		return false;
	}

	private boolean clientVientSansCommande(Client client) {
		if(client.commandeCafe == null) {
			estJeteDuRestaurant(client);
			return true;
		}
		return false;
	}

	private void contenantDeborde(Client client) {
		if(client.tasse.cafe.quantiteLiquideMl > client.tasse.quantiteCafeMax) {
			System.out.println("Le café a débordé.");
			client.tasse.cafe.quantiteLiquideMl = client.tasse.quantiteCafeMax;
		}
	}
}
