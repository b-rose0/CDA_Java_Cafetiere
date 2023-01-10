package partie4;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Main {
	//TO DO gérer annuler
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		Restaurant restaurant = new Restaurant();
		String nomClient = JOptionPane.showInputDialog(frame, "Bonjour, quel est votre nom ?");
		if (nomClient == null) {
			JOptionPane.showMessageDialog(frame, "Passez une bonne journée");
			return;
		}
		JOptionPane.showMessageDialog(frame, "Puis-je prendre votre commande "+nomClient+" ?");
		TypeCafe choixCommande = (TypeCafe) JOptionPane.showInputDialog(frame, "Choisi un type de café...", "Choix du café", 
				JOptionPane.QUESTION_MESSAGE, null, TypeCafe.values(), TypeCafe.MOKA);
		if(choixCommande == TypeCafe.BATARD) {
			JOptionPane.showMessageDialog(frame, "Vous n'êtes pas le bienvenue !");
			System.exit(0);
		}
		Tasse tasse = null;
		if(JOptionPane.showConfirmDialog(frame, "Avez-vous une tasse") == JOptionPane.YES_OPTION) {
			tasse = new Tasse(demandeTailleTasse(frame, 0));
		}
		JOptionPane optionPane = new JOptionPane();
	    JSlider slider = getSlider(optionPane);
	    optionPane.setMessage(new Object[] { "Combien voulez_vous de café : ", slider });
	    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
	    JDialog dialog = optionPane.createDialog(frame, "Quantité de café");
	    dialog.setVisible(true);
	    int quantiteLiquideCommande = Integer.parseInt(""+optionPane.getInputValue());

	    
	    Cafe commandeCafe = new Cafe(choixCommande, quantiteLiquideCommande);
	    Client client;
	    if(tasse != null) {
	    	client = new Client(nomClient, commandeCafe, tasse);
	    } else {
		    client = new Client(nomClient, commandeCafe, false);	    	
	    }
	    restaurant.servir(client);
	    int facture = (int)(client.valeurFacture * 100);
	    demandePaiement(facture, frame, 0);
	}

	//Slider pour choisir la quantité de 
	private static JSlider getSlider(final JOptionPane optionPane) {
		optionPane.setInputValue(100);
		JSlider slider = new JSlider(0, 500, 100);
		slider.setMajorTickSpacing(500);
		slider.setMinorTickSpacing(50);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		ChangeListener changeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider theSlider = (JSlider) e.getSource();	
				if(!theSlider.getValueIsAdjusting()) {
					optionPane.setInputValue((int) theSlider.getValue());
				}
			}
		};
		slider.addChangeListener(changeListener);
		return slider;
	}
	
	//Demande et traitement du paiement
	private static void demandePaiement(int facture, JFrame frame, int tentative) {
		try {
			if(tentative <4) {
				if(tentative>0)
					JOptionPane.showMessageDialog(frame, "Vous n'avez pas réglé votre addition.");
				int paiement = Integer.parseInt(JOptionPane.showInputDialog(frame, "Veuillez payer " + facture + " centimes."));
				if (paiement == facture) {
					JOptionPane.showMessageDialog(frame, "Merci, bonne journée !");
					System.exit(0);
				} else if(paiement > facture) {
					JOptionPane.showMessageDialog(frame, "Merci");
					JOptionPane.showMessageDialog(frame, "Cela fait " +(paiement-facture)+" centimes de trop. Voici votre monnaie, bonne journée !");
					System.exit(0);
				} else if (paiement < facture) {
					demandePaiement(facture - paiement, frame, tentative+1);
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Au voleur, au voleur ! Mais que fait la police ?");
				System.exit(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Ce n'et pas un nombre. Veuillez entrez un nombre.");
			demandePaiement(facture, frame, tentative+1);			
		}
	}

	private static double demandeTailleTasse(JFrame frame, int tentative) {
		try {
			if (tentative < 3) {
				double tailleTasse = Double.parseDouble(JOptionPane.showInputDialog(frame, "Indiquez la taille de votre tasse :"));
				return tailleTasse;
			} else {
				JOptionPane.showMessageDialog(frame,"Si vous ne pouvez être sérieux, au revoir !");
				System.exit(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Ce n'et pas un nombre. Veuillez entrez un nombre.");
			return demandeTailleTasse(frame, tentative+1);
		}


		return 0;
	}
	
}