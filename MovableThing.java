package sokoban;

public abstract class MovableThing {
	Field field;
	protected String name;
	
	public abstract void Disappear();
	public abstract void ControlSwitch(Switch s);
	public abstract boolean PushedBy(Direction d);
	public abstract boolean DirectPushedBy(Direction d);
	
	/**
	 * Visszat�r az objektum nev�vel.
	 * A ki�r�sn�l hasznos.
	 * @return String t�pussal t�r vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * Az objektum a megadott ir�nyba pr�b�l meg elmozdulni.
	 * @param d Direction t�pus�, ebbe az ir�nyba mozogna.
	 * @return boolean t�pus�, mely akkor true, ha sikeres volt a mozg�s.
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
	 * Ha c�lmez�re �r a MovableThing, akkor ez a f�ggv�nye h�v�dik. Alapb�l nem t�rt�nik semmi.
	 * @param g Erre a c�lmez�re l�pett az objektum.
	 */
	public void AtGoal(Goal g) {
		Printer.ThingReturn("RETURN: " + name, this, ".AtGoal(" + g.GetName() + Printer.GetFieldNumber(g) + "): void");
	}
	
	/**
	 * Visszaadja azt a mez�t, amelyen aktu�lisan az objektum �ll.
	 * @return	Field t�pussal t�r vissza.
	 */
	public Field GetField() { return field; }
	
	/**
	 * A param�ter�l kapott mez�t t�rolja, ezen fog az objektum �llni.
	 * @param f Field t�pus�.
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
