package sokoban;

public class Worker extends MovableThing{
	private int points;
	
	public Worker() { name = "w"; }
	
	/**
	 * A munkás pontszámának eggyel növelése.
	 */
	public void AddPoint() { 
		++points;
		Printer.ThingReturn("RETURN: " + name, this, ".AddPoint(): void");
	}
	
	/**
	 * A munkás megpróbál az adott irányba lépni (direktben, nem tolják).
	 * @param d Direction típusú, ebbe az irányba haladna.
	 * @return boolean típussal tér vissza, mely akkor true, ha sikerült a mozgás.
	 */
	public boolean DirectMove(Direction d) {
		Printer.ThingCallField("CALL: " + name, this, "Field " + GetField().GetName(), GetField(), ".GetNeighbour(d)");
		Field temp = GetField().GetNeighbour(d);
		Printer.ThingCallField("CALL: " + name, this, "boolean " + temp.GetName(), temp, ".DirectAccept(this, d)");
		boolean b = temp.DirectAccept(this, d);
		if(b){
			Printer.ThingReturn("RETURN: " + name, this, ".DirectMove(d): true");
			return true;
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, ".DirectMove(d): false");
			return false;
		}
	}
	
	/**
	 * Törli magát az aktuális mezõrõl.
	 * null értékre állítja a tárolt mezõ értékét.
	 */
	public void Disappear() {
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".Remove(this)");
		GetField().Remove(this);
		
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".SetField(null)");
		SetField(null);
		
		Printer.ThingReturn("RETURN: " + name, this, ".Disappear(): void");
	}
	
	/**
	 * Kikapcsolja a paraméterül kapott kapcsolót.
	 */
	public void ControlSwitch(Switch s) {
		Printer.ThingCallField("CALL: " + name, this, "void " + s.GetName(), s, ".TurnOff()");
		s.TurnOff();
		Printer.ThingReturn("RETURN: " + name, this, ".ControlSwitch(s): void");
	}
	
	/**
	 * A munkást nem közvetlen próbálják meg arrébb tolni, így megpróbál az adott irányba tolódni.
	 * Ha nem sikerül arrébb tolódnia, akkor meghal.
	 * @param d Direction típusú, ebbe az irányba mozogna.
	 * @return boolean típussal tér vissza, mely különbözõ okokból, de mindig true lesz.
	 */
	public boolean PushedBy(Direction d) {
		Printer.ThingCallThing("CALL: " + name, this, "boolean " + name, this, ".Move(d)");
		if(!Move(d)) {
			Printer.ThingCallThing("CALL: " + name, this, "void " + name, this, ".Disappear()");
			Disappear();
		}
		Printer.ThingReturn("RETURN: " + name, this, ".PushedBy(d): true");
		return true;
	}
	
	/**
	 * A munkást nem lehet direktben eltolni, ezért mindig false értékkel tér vissza.
	 * @return boolean típussal tér vissza.
	 */
	public boolean DirectPushedBy(Direction d) {
		Printer.ThingReturn("RETURN: " + name, this, ".DirectPushedBy(d): false");
		return false;
	}
}
