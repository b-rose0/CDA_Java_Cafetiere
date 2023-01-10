package partie4;

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
			if(tasse.cafe.typeCafe != typeCafe)
				mixeCafe(tasse);
			else
				tasse.cafe.typeCafe = typeCafe;
			tasse.cafe.quantiteLiquideMl += quantiteCafe;

		}
	}
	
	private void mixeCafe(Tasse tasse) {
		tasse.cafe.typeCafe = TypeCafe.BATARD;
	}
}
