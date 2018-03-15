package sokoban;

public class Pillar extends Field {
	public Pillar() { name = "p"; }
	
	/**
	 * Nem fogad el semmit ez a mez�.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		//Nem csin�lunk semmit, csak visszat�r�nk hamissal.
		Printer.FieldReturn("RETURN: " + name, this, ".Accept(t, d): false");
		return false;
	}
	
	/**
	 * Nem fogad el semmit ez a mez�.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		//Nem csin�lunk semmit, csak visszat�r�nk hamissal.
		Printer.FieldReturn("RETURN: " + name, this, ".DirectAccept(t, d): false");
		return false;
	}
}
