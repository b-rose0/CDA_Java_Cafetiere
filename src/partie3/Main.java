package partie3;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Main {

	public static void main(String args[])
	{
		Restaurant latteSurLesRochers = new Restaurant("Latte sur les rochers");
		Restaurant uneTasseDeJoie = new Restaurant("Une tasse de joie");
		Restaurant leRestaurant = new Restaurant();
		ArrayList<Restaurant> listeRestaurant = new ArrayList<>();
		listeRestaurant.add(latteSurLesRochers);
		listeRestaurant.add(uneTasseDeJoie);
		listeRestaurant.add(leRestaurant);
		
		ArrayList<Client> listeClient1 = new ArrayList<>();
		ArrayList<Client> listeClient2 = new ArrayList<>();
		ArrayList<Client> listeClient3 = new ArrayList<>();
		creerClientListeAleatoire(listeClient1, 20);
		creerClientListeAleatoire(listeClient2, 20);
		creerClientListeAleatoire(listeClient3, 20);
		
		ArrayList<Client> listeClientsExpulse = new ArrayList<>();
		
		servirClientsDeListe(listeClient1, listeClientsExpulse, listeRestaurant);
		servirClientsDeListe(listeClient2, listeClientsExpulse, listeRestaurant);
		servirClientsDeListe(listeClient3, listeClientsExpulse, listeRestaurant);
		
		String clientsExpulses = "";
		int i =0;
		for (Client clientExpulse : listeClientsExpulse) {
			clientsExpulses += clientExpulse.nom;
			if(i != listeClientsExpulse.size()) {
				clientsExpulses += ", ";
				if(i%5 == 4)
					clientsExpulses += "\n";
			}
			i++;
		}
		for(Restaurant restaurant: listeRestaurant) {
			System.out.println("CLient servis dans resto "+restaurant.nom+" : "+restaurant.listeClientServi.size()+", profit : "+restaurant.profit);
			String outputRestaurant = "Le restaurant "+restaurant.nom+" a fait un profit de "+restaurant.profit+"€.";
			outputRestaurant+="\n"+"Et "+restaurant.listeClientServi.size()+" clients ont été servis.";
			JOptionPane.showInternalMessageDialog(null, outputRestaurant, restaurant.nom.toUpperCase(),JOptionPane.INFORMATION_MESSAGE);
		}
			
		JOptionPane.showInternalMessageDialog(null, clientsExpulses, "Client expulsés : "+ listeClientsExpulse.size(), JOptionPane.INFORMATION_MESSAGE);
	}

	private static int getRandomNumberBetween0AndX(int x) {
		return (int)(Math.random()*x);		
	}
	
	private static void creerClientListeAleatoire(ArrayList<Client> listeClient, int nombreClients) {
		for (int i = 0; i < nombreClients; i++) {
			Client client = new Client();
			creerClientDepuisBanqueDeDonne(client);
			listeClient.add(client);
		}
	}
	
	private static void creerClientDepuisBanqueDeDonne(Client client) {
		int indexClientCommande = getRandomNumberBetween0AndX(BanqueDeDonne.listeCommandes.size());
		int indexClientNom = getRandomNumberBetween0AndX(BanqueDeDonne.listeNoms.size());
		int indexClientTasse = getRandomNumberBetween0AndX(BanqueDeDonne.listeTasses.size());
		client.commandeCafe = BanqueDeDonne.listeCommandes.get(indexClientCommande);
		client.nom = BanqueDeDonne.listeNoms.get(indexClientNom);
		client.tasse = BanqueDeDonne.listeTasses.get(indexClientTasse);
	}
	
	private static void servirClientsDeListe(ArrayList<Client> listeClient, ArrayList<Client> listeClientExpulse, ArrayList<Restaurant> listeRestaurant) {
		for(Client client : listeClient) {			
			Restaurant restaurant = listeRestaurant.get(getRandomNumberBetween0AndX(listeRestaurant.size()));
			boolean estServi = restaurant.servir(client);
			if (estServi) {
				restaurant.listeClientServi.add(client);
			} else {
				listeClientExpulse.add(client);
			}
		}
	}
	
}