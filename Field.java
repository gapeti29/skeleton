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
	 * Visszaadja a Field nevét.
	 * A kiírásnál jön jól.
	 * @return String típussal tér vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * A paraméterül kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a mezõ az objektumot.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		if(thing != null) {
			//Ha sikerült arrébb tolni a mezõn lévõ dolgot.
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
	 * A paraméterül kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a mezõ az objektumot.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(thing != null) {
			//Ha sikerült arrébb tolni a mezõn lévõ dolgot.
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
	 * A paraméterül kapott dolog rákerülhet a mezõre.
	 * @param t MovableThing típusú, ez az objektum kerül a mezõre.
	 */
	private void Accepted(MovableThing t) {
		//Régi mezõrõl törli az objektumot.
		try {
			Printer.FieldCallField("CALL: " + name, this, "void " + t.GetField().GetName(), t.GetField(), ".Remove(t)");
			t.GetField().Remove(t);
		} catch(NullPointerException e) {
			/*
			 * Nem csinálunk semmit.
			 * A kivétel azt jelzi, hogy a MovableThing t nem volt még egy mezõn se, most kerül a pályára.
			 */
		}
		//Kölcsönösen eltárolják egymást.
		thing = t;
		
		Printer.FieldCallThing("CALL: " + name, this, "void " + thing.GetName(), thing, ".SetField(this)");
		thing.SetField(this);
	}
	
	/**
	 * A paraméterül kapott dolog törlése a mezõrõl.
	 * @param t MovableThing típusú, törlõdik a things tömbbõl.
	 */
	public void Remove(MovableThing t) {
		if(thing == t)
			thing = null;
		
		Printer.FieldReturn("RETURN: " + name, this, ".Remove(t): void");
	}
	
	/**
	 * Visszaadja a paraméterül kapott irányban lévõ szomszédos mezõt.
	 * @param d Ebben az irányban helyezkedik el a szomszédos mezõ.
	 * @return	Field típust ad vissza.
	 */
	public Field GetNeighbour(Direction d) {
		Printer.FieldReturn("RETURN: " + name, this, ".GetNeighbour(d): Field");
		return neighbours.get(d);
	}
	
	/**
	 * Eltárolja a mezõ a paraméterül kapott másik mezõt, a paraméterül kapott irányban lévõ szomszédjaként.
	 * @param f Field típusú, ezt a mezõt tárolja szomszédként.
	 * @param d Direction típusú, ebben az irányban lesz a szomszéd.
	 */
	public void SetNeighbour(Field f, Direction d) {
		neighbours.put(d, f);
		Printer.FieldReturn("RETURN: " + name, this, ".SetNeighbour(f, d): void");
	}
	
	/**
	 * Visszaadja azt a raktárépületet, amelyben a mezõ van.
	 * @return Warehouse típussal tér vissza.
	 */
	public Warehouse GetWarehouse() { return warehouse; }
	
	/**
	 * Eltárolja a paraméterül kapott raktárépületet.
	 * @param w Warehouse típusú, ebben szerepel a mezõ.
	 */
	public void SetWarehouse(Warehouse w) { warehouse = w; }
}
