package sokoban;

public class Pillar extends Field {
	public Pillar() { name = "p"; }
	
	/**
	 * Nem fogad el semmit ez a mezõ.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		//Nem csinálunk semmit, csak visszatérünk hamissal.
		Printer.FieldReturn("RETURN: " + name, this, ".Accept(t, d): false");
		return false;
	}
	
	/**
	 * Nem fogad el semmit ez a mezõ.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		//Nem csinálunk semmit, csak visszatérünk hamissal.
		Printer.FieldReturn("RETURN: " + name, this, ".DirectAccept(t, d): false");
		return false;
	}
}
