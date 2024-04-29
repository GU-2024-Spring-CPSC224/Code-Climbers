package chutesandladders;


import java.util.Random;

public class Die implements Comparable<Die> {
	private Integer sideUp; // Current die 'value' in range 1..numSides
	private final Integer numSides; // Sides on the die (should be 1...INF integer)
	
	public Die(Integer numSides, Integer startingSide) {
		this.numSides = numSides;
		this.sideUp = startingSide;
	}
	
	public void roll() {
		Random rand = new Random();
		this.sideUp = rand.nextInt(this.numSides) + 1;
	}
	
	public Integer getSideUp() {
		return this.sideUp;
	}
	
	@Override
	public String toString() {
		String ret = "";
		// ret += "Die: " + this.sideUp.toString() + " of " + this.numSides.toString() + " sides";
		ret += this.sideUp.toString();
		return ret;
	}
	
	@Override
	public int compareTo(Die otherDie) {
		return this.sideUp.compareTo(otherDie.sideUp);
	}
}
