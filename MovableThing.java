package sokoban;

public abstract class MovableThing {
	Field field;
	protected String name;
	
	public abstract void Disappear();
	public abstract void ControlSwitch(Switch s);
	public abstract boolean PushedBy(Direction d);
	public abstract boolean DirectPushedBy(Direction d);
	
	/**
	 * Visszatér az objektum nevével.
	 * A kiírásnál hasznos.
	 * @return String típussal tér vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * Az objektum a megadott irányba próbál meg elmozdulni.
	 * @param d Direction típusú, ebbe az irányba mozogna.
	 * @return boolean típusú, mely akkor true, ha sikeres volt a mozgás.
	 */
	public boolean Move(Direction d) {
		Printer.ThingCallField("CALL: " + name, this, "Field " + GetField().GetName(), GetField(), ".GetNeighbour(" + d.toString()+  ")");
		Field temp = GetField().GetNeighbour(d);
		Printer.ThingCallField("CALL: " + name, this, "boolean " + temp.GetName(), temp, ".Accept(" + this.GetName() + Printer.GetThingNumber(this) + ", " + d.toString() + ")");
		boolean b = temp.Accept(this, d);
		
		if(b) {
			Printer.ThingReturn("RETURN: " + name, this, ".Move(" + d.toString() + "): true");
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, ".Move(" + d.toString() + "): false");
		}
		return b;
	}
	
	/**
	 * Ha célmezõre ér a MovableThing, akkor ez a függvénye hívódik. Alapból nem történik semmi.
	 * @param g Erre a célmezõre lépett az objektum.
	 */
	public void AtGoal(Goal g) {
		Printer.ThingReturn("RETURN: " + name, this, ".AtGoal(" + g.GetName() + Printer.GetFieldNumber(g) + "): void");
	}
	
	/**
	 * Visszaadja azt a mezõt, amelyen aktuálisan az objektum áll.
	 * @return	Field típussal tér vissza.
	 */
	public Field GetField() { return field; }
	
	/**
	 * A paraméterül kapott mezõt tárolja, ezen fog az objektum állni.
	 * @param f Field típusú.
	 */
	public void SetField(Field f) {
		field = f;
		if(f == null) {
			Printer.ThingReturn("RETURN: " + name, this, ".SetField(null): void");
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, ".SetField("+ f.GetName() + Printer.GetFieldNumber(f) + "): void");
		}
	}
}
