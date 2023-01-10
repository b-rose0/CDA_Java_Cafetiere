package partie4;


public class Tasse {
	double quantiteCafeMax;
	Cafe cafe;


	public Tasse() {
		this(100);
	}
	public Tasse(double quantiteCafeMax) {
		this.quantiteCafeMax = quantiteCafeMax;
	}

	public double boire(double quantiteBut) {
		cafe.quantiteLiquideMl -= quantiteBut;
		return cafe.quantiteLiquideMl;
	}

	public void boire() {			
		cafe.quantiteLiquideMl = 0;
	}
}
