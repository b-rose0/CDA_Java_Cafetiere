package partie2;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class Main {

	public static void main(String args[])
	{
	//	Restaurant resto = new Restaurant() ; 
		
		ArrayList<Client> mesClientsBasic = new ArrayList<Client>(
			Arrays.asList(
						new Client("Moka_100", new Cafe(), true),
						new Client("Java_100", new Cafe(TypeCafe.JAVA, 100), true),
						new Client("Typica_100", new Cafe(TypeCafe.TYPICA, 100), true),
						new Client("Bourbon_100", new Cafe(TypeCafe.BOURBON, 100), true)			
		)) ;
		
		Restaurant restoMesClientsBasic = checkFactureTotal(mesClientsBasic) ; 
		
		int checkBasics = JOptionPane.showConfirmDialog(null, "Les petits clients aiment bien le café ! "
				+ "\n\nTu devrais avoir fait environ 11.7 en profit"
				+ "\nTu a fais " + restoMesClientsBasic.profit
				+ "\n\nC'est réussi ? ") ;
		
		
		ArrayList<Client> mesClientsEtrange = new ArrayList<Client>(
				Arrays.asList(
						new Client("Moka_600", new Cafe(TypeCafe.MOKA,600), false),
						new Client("Moka_2000", new Cafe(TypeCafe.MOKA,2000), new Tasse(2000)),
						new Client("Moka_2000", new Cafe(TypeCafe.MOKA,2000), new Tasse(1000))
		)) ; 
		
		Restaurant restoMesClientsEtrange = checkFactureTotal(mesClientsEtrange) ; 
		System.out.println(restoMesClientsEtrange.profit);
		int checkEtranges = JOptionPane.showConfirmDialog(null, "Même les clients étranges aiment bien le café ! "
				+ "\n\nTu devrais avoir fait environs 118.0 en profit"
				+ "\nTu a fais " + restoMesClientsEtrange.profit
				+ "\n\nMon Client 1 devrait avoir 500ml de café"
				+ "\nIl a " + mesClientsEtrange.get(0).tasse.cafe.quantiteLiquideMl
				+ "\n\nMon Client 2 devrait avoir 2000ml de café et être facturé 50"
				+ "\nIl a " + mesClientsEtrange.get(1).tasse.cafe.quantiteLiquideMl + "ml de café facturé à " + mesClientsEtrange.get(1).valeurFacture
				+ "\n\nMon Client 3 devrait avoir 1000ml de café et être facturé 50"
				+ "\nIl a " + mesClientsEtrange.get(2).tasse.cafe.quantiteLiquideMl + "ml de café facturé à " + mesClientsEtrange.get(2).valeurFacture
				+ "\n\nC'est réussi ? ") ;
		
		ArrayList<Client> mesClientsInacceptable = new ArrayList<Client>(
				Arrays.asList(
						new Client(),
						new Client("Batard_STasse", new Cafe(TypeCafe.BATARD, 600), false),
						new Client("Batard_ATasse", new Cafe(TypeCafe.BATARD, 600), true),
						new Client("Sans_Commande", null, false)
		)) ; 
		
		Restaurant restoMesClientsInacceptable = checkFactureTotal(mesClientsInacceptable) ; 
		
		int checkInacceptables = JOptionPane.showConfirmDialog(null, "Aucun de ces client n'est acceptable ! Aucun argent a faire avec ces gens !"
				+ "\n\nTu devrais faire un profit de 0.0"
				+ "\nTu a fait un profit de " + restoMesClientsInacceptable.profit
				+ "\n\nC'est réussi ? ") ;
		
		
		Client devientBatard = new Client("Batard_STasse",new Cafe(TypeCafe.JAVA, 100), new Tasse(200)) ; 
		devientBatard.tasse.cafe = new Cafe() ; 
		restoMesClientsBasic.servir(devientBatard) ; 
		
		int checkBatard = JOptionPane.showConfirmDialog(null,"Mon client devrait avoir 200ml de café batard (Si tout fonctionne correctement)"
				+"\nMon client a " + devientBatard.tasse.cafe.quantiteLiquideMl + " ml de café " + devientBatard.tasse.cafe.typeCafe.name()
				+ "\n\nC'est réussi ? ") ;
		
		if(checkBasics == 0 && checkEtranges == 0 && checkInacceptables == 0 && checkBatard == 0)
			JOptionPane.showMessageDialog(null, "Bravo tu as réussi cet exo ! Toujours plus simple non ?");
		else 
			JOptionPane.showMessageDialog(null, "Quelque chose ne fonctionne pas :/ Dommage ! N'hésite pas à demander si tu as besoin d'aide ! ");
		
	}
	
	public static Restaurant checkFactureTotal(ArrayList<Client> listeClient) {
		Restaurant resto = new Restaurant() ; 
		
		for(Client client : listeClient) {
			resto.servir(client) ; 
		}
		
		return resto ; 	
	}
	
}