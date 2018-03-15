package sokoban;

import java.util.HashMap;
import java.util.Map;

public class Field {
	private MovableThing thing;
	private Map<Direction, Field> neighbours = new HashMap<Direction, Field>();
	private Warehouse warehouse;
	
	protected String name;
	
	public Field() {
		name = "f";
	}
	
	/**
	 * Visszaadja a Field nev�t.
	 * A ki�r�sn�l j�n j�l.
	 * @return String t�pussal t�r vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * A param�ter�l kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a mez� az objektumot.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		if(thing != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			Printer.FieldCallThing("CALL: " + name, this, "boolean " + thing.GetName() , thing, ".PushedBy(d)");
			if(thing.PushedBy(d)){
				Accepted(t);
				Printer.FieldReturn("RETURN " + name, this, ".Accept(t, d): true");
				return true;
			}
			else {
				Printer.FieldReturn("RETURN " + name, this, ".Accept(t, d): false");
				return false;
			}
		}
		else {
			Accepted(t);
			Printer.FieldReturn("RETURN " + name, this, ".Accept(t, d): true");
			return true;
		}
	}
	
	/**
	 * A param�ter�l kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a mez� az objektumot.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(thing != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			Printer.FieldCallThing("CALL: " + name, this, "boolean " + thing.GetName() , thing, ".DirectPushedBy(d)");
			if(thing.DirectPushedBy(d)){
				Accepted(t);
				Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(t, d): true");
				return true;
			}
			else {
				Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(t, d): false");
				return false;
			}
		}
		else {
			Accepted(t);
			Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(t, d): true");
			return true;
		}
	}
	
	/**
	 * A param�ter�l kapott dolog r�ker�lhet a mez�re.
	 * @param t MovableThing t�pus�, ez az objektum ker�l a mez�re.
	 */
	private void Accepted(MovableThing t) {
		//R�gi mez�r�l t�rli az objektumot.
		try {
			Printer.FieldCallField("CALL: " + name, this, "void " + t.GetField().GetName(), t.GetField(), ".Remove(t)");
			t.GetField().Remove(t);
		} catch(NullPointerException e) {
			/*
			 * Nem csin�lunk semmit.
			 * A kiv�tel azt jelzi, hogy a MovableThing t nem volt m�g egy mez�n se, most ker�l a p�ly�ra.
			 */
		}
		//K�lcs�n�sen elt�rolj�k egym�st.
		thing = t;
		
		Printer.FieldCallThing("CALL: " + name, this, "void " + thing.GetName(), thing, ".SetField(this)");
		thing.SetField(this);
	}
	
	/**
	 * A param�ter�l kapott dolog t�rl�se a mez�r�l.
	 * @param t MovableThing t�pus�, t�rl�dik a things t�mbb�l.
	 */
	public void Remove(MovableThing t) {
		if(thing == t)
			thing = null;
		
		Printer.FieldReturn("RETURN: " + name, this, ".Remove(t): void");
	}
	
	/**
	 * Visszaadja a param�ter�l kapott ir�nyban l�v� szomsz�dos mez�t.
	 * @param d Ebben az ir�nyban helyezkedik el a szomsz�dos mez�.
	 * @return	Field t�pust ad vissza.
	 */
	public Field GetNeighbour(Direction d) {
		Printer.FieldReturn("RETURN: " + name, this, ".GetNeighbour(d): Field");
		return neighbours.get(d);
	}
	
	/**
	 * Elt�rolja a mez� a param�ter�l kapott m�sik mez�t, a param�ter�l kapott ir�nyban l�v� szomsz�djak�nt.
	 * @param f Field t�pus�, ezt a mez�t t�rolja szomsz�dk�nt.
	 * @param d Direction t�pus�, ebben az ir�nyban lesz a szomsz�d.
	 */
	public void SetNeighbour(Field f, Direction d) {
		neighbours.put(d, f);
		Printer.FieldReturn("RETURN: " + name, this, ".SetNeighbour(f, d): void");
	}
	
	/**
	 * Visszaadja azt a rakt�r�p�letet, amelyben a mez� van.
	 * @return Warehouse t�pussal t�r vissza.
	 */
	public Warehouse GetWarehouse() { return warehouse; }
	
	/**
	 * Elt�rolja a param�ter�l kapott rakt�r�p�letet.
	 * @param w Warehouse t�pus�, ebben szerepel a mez�.
	 */
	public void SetWarehouse(Warehouse w) { warehouse = w; }
}
