package sokoban;

public class Switch extends Field{
	private Hole holes;
	
	public Switch() {
		name = "s";
	}
	
	/**
	 * Eltárolja a paraméterül kapott lyukat.
	 * @param h Hole típusú, ezt tudja majd kezelni.
	 */
	public void SetHole(Hole h) { 
		holes = h;
		Printer.FieldReturn("RETURN: " + name, this, ".SetHole(h)");
	}
	
	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot, és meghívja annak a kapcsoló kezelõ függvényét, ha elfogadta.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".Accept(t, d)");
		if(super.Accept(t, d)){
			Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".ControlSwitch(this)");
			t.ControlSwitch(this);
			Printer.FieldReturn("RETURN: " + name, this, ".Accept(t, d): true");
			return true;
		}
		else {
			Printer.FieldReturn("RETURN: " + name, this, ".Accept(t, d): false");
			return false;
		}
	}
	
	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot, és meghívja annak a kapcsoló kezelõ függvényét, ha elfogadta.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".DirectAccept(t, d)");
		if(super.DirectAccept(t, d)){
			Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".ControlSwitch(this)");
			t.ControlSwitch(this);
			Printer.FieldReturn("RETURN: " + name, this, ".DirectAccept(t, d): true");
			return true;
		}
		else {
			Printer.FieldReturn("RETURN: " + name, this, ".DirectAccept(t, d): false");
			return false;
		}
	}
	
	/**
	 * Kinyitja a kapcsolóhoz tartozó lyukat.
	 */
	public void TurnOn() { 
		Printer.FieldCallField("CALL: " + name, this, "void " + holes.GetName(), holes, ".Open()");
		holes.Open(); 
		Printer.FieldReturn("RETURN: " + name, this, ".TurnOn(): void");
	}
	
	/**
	 * Becsukja a kapcsolóhoz tartozó lyukat.
	 */
	public void TurnOff() { 
		Printer.FieldCallField("CALL: " + name, this, "void " + holes.GetName(), holes, ".Close()");
		holes.Close();
		Printer.FieldReturn("RETURN: " + name, this, ".TurnOff(): void");
	}
}
