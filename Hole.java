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
		if(GetThing() != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			Printer.FieldCallThing("CALL: " + name, this, "boolean " + GetThing().GetName() , GetThing(), ".PushedBy(" + d.toString() + ")");
			if(GetThing().PushedBy(d)){
				Accepted(t);
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
				Printer.FieldReturn("RETURN " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): true");
				return true;
			}
			else {
				Printer.FieldReturn("RETURN " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): false");
				return false;
			}
		}
		else {
			Accepted(t);
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
			if(d == null) {
				Printer.FieldReturn("RETURN " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", null): true");
			}
			else {
				Printer.FieldReturn("RETURN " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): true");
			}
			return true;
		}
	}
	
	/**
	 * Ki�rt�keli, hogy a lyukra l�phet-e a MovableThing (ami elvileg egy Worker lesz), �s ha igen, akkor megsemmis�ti, ha a lyuk nyitva van. 
	 * @param t MovableThing t�pus�, ez az objektum l�p a mez�re (�s adott esetben semmis�l meg).
	 * @param d Direction t�pus�, azaz ir�ny, amerre a MovableThing haladna.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(GetThing() != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			Printer.FieldCallThing("CALL: " + name, this, "boolean " + GetThing().GetName() , GetThing(), ".DirectPushedBy(" + d.toString() + ")");
			if(GetThing().DirectPushedBy(d)){
				Accepted(t);
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
				Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): true");
				return true;
			}
			else {
				Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): false");
				return false;
			}
		}
		else {
			Accepted(t);
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
			Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): true");
			return true;
		}
	}
}
