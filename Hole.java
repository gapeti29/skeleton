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
	 * Bezárja a lyukat.
	 */
	public void Close() {
		isOpen = false;
		Printer.FieldReturn("RETURN: " + name, this, ".Close(): void");
	}
	
	/**
	 * Kiértékeli, hogy a lyukra léphet-e a MovableThing, és ha igen, akkor megsemmisíti, ha a lyuk nyitva van. 
	 * @param t MovableThing típusú, ez az objektum lép a mezõre (és adott esetben semmisül meg).
	 * @param d Direction típusú, azaz irány, amerre a MovableThing haladna.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
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
	 * Kiértékeli, hogy a lyukra léphet-e a MovableThing (ami elvileg egy Worker lesz), és ha igen, akkor megsemmisíti, ha a lyuk nyitva van. 
	 * @param t MovableThing típusú, ez az objektum lép a mezõre (és adott esetben semmisül meg).
	 * @param d Direction típusú, azaz irány, amerre a MovableThing haladna.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
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
