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
		if(GetThing() != null) {
			//Ha sikerült arrébb tolni a mezõn lévõ dolgot.
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
	 * Kiértékeli, hogy a lyukra léphet-e a MovableThing (ami elvileg egy Worker lesz), és ha igen, akkor megsemmisíti, ha a lyuk nyitva van. 
	 * @param t MovableThing típusú, ez az objektum lép a mezõre (és adott esetben semmisül meg).
	 * @param d Direction típusú, azaz irány, amerre a MovableThing haladna.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(GetThing() != null) {
			//Ha sikerült arrébb tolni a mezõn lévõ dolgot.
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
