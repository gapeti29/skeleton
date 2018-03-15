package sokoban;

import java.util.Scanner;

public class Hole extends Field {
	private boolean isOpen;
	
	public Hole() { name = "h"; }
	
	/**
	 * Kinyitja a lyukat.
	 */
	public void Open( ) { 
		isOpen = true;
		Printer.FieldReturn("RETURN: " + name, this, ".Open(): void");
	}
	
	/**
	 * Bez�rja a lyukat.
	 */
	public void Close() {
		isOpen = false;
		Printer.FieldReturn("RETURN: " + name, this, ".Close(): void");
	}
	
	/**
	 * Ki�rt�keli, hogy a lyukra l�phet-e a MovableThing, �s ha igen, akkor megsemmis�ti, ha a lyuk nyitva van. 
	 * @param t MovableThing t�pus�, ez az objektum l�p a mez�re (�s adott esetben semmis�l meg).
	 * @param d Direction t�pus�, azaz ir�ny, amerre a MovableThing haladna.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".Accept(t, d)");
		if(super.Accept(t, d)) {
			System.out.println("Nyitva legyen-e a lyuk?");
			System.out.println("Input 'N': Nem");
			System.out.println("Input 'Y': Igen");
			Scanner Cin1 = new Scanner(System.in);
			String open;
			open = Cin1.nextLine();
			if(open.equals("Y")) {
				Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".Disappear(): void");
				t.Disappear();
			}
			Printer.FieldReturn("RETURN " + name, this, ".Accept(t, d): true");
			return true;
		}
		else {
			Printer.FieldReturn("RETURN " + name, this, ".Accept(t, d): false");
			return false;
		}
	}
	
	/**
	 * Ki�rt�keli, hogy a lyukra l�phet-e a MovableThing (ami elvileg egy Worker lesz), �s ha igen, akkor megsemmis�ti, ha a lyuk nyitva van. 
	 * @param t MovableThing t�pus�, ez az objektum l�p a mez�re (�s adott esetben semmis�l meg).
	 * @param d Direction t�pus�, azaz ir�ny, amerre a MovableThing haladna.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".DirectAccept(t, d)");
		if(super.DirectAccept(t, d)) {
			System.out.println("Nyitva legyen-e a lyuk?");
			System.out.println("Input 'N': Nem");
			System.out.println("Input 'Y': Igen");
			Scanner Cin1 = new Scanner(System.in);
			String open;
			open = Cin1.nextLine();
			if(open.equals("Y")) {
				Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".Disappear(): void");
				t.Disappear();
			}
			Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(t, d): true");
			return true;
		}
		else {
			Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(t, d): false");
			return false;
		}
	}
}
