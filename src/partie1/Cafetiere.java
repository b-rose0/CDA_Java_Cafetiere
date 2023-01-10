package partie1;

public class Cafetiere {
	public Cafetiere() {	
	}

	public void remplirTasse(Tasse tasse) {
		this.remplirTasse(tasse, TypeCafe.MOKA, tasse.quantiteCafeMax);
	}

	public void remplirTasse(Tasse tasse, TypeCafe typeCafe, double quantiteCafe) {
		if(tasse.cafe == null) {
			tasse.cafe = new Cafe(typeCafe, quantiteCafe);
		} else {
			tasse.cafe.quantiteLiquideMl += quantiteCafe;
			tasse.cafe.typeCafe = typeCafe;
		}
	}
}
