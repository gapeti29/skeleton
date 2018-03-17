package sokoban;

public class Switch extends Field{
	private Hole holes;
	
	public Switch() {
		name = "s";
	}
	
	/**
	 * Elt�rolja a param�ter�l kapott lyukat.
	 * @param h Hole t�pus�, ezt tudja majd kezelni.
	 */
	public void SetHole(Hole h) { 
		holes = h;
		Printer.FieldReturn("RETURN: " + name, this, ".SetHole(" + h.GetName() + Printer.GetFieldNumber(h) + ")");
	}
	
	/**
	 * Megpr�b�lja elfogadni a mez�re �rkez� MovableThing t�pus� objektumot, �s megh�vja annak a kapcsol� kezel� f�ggv�ny�t, ha elfogadta.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		if(GetThing() != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			Printer.FieldCallThing("CALL: " + name, this, "boolean " + GetThing().GetName() , GetThing(), ".PushedBy(" + d.toString() + ")");
			if(GetThing().PushedBy(d)){
				Accepted(t);
				Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".ControlSwitch(" + this.GetName() + Printer.GetFieldNumber(this) + ")");
				t.ControlSwitch(this);
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
			Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".ControlSwitch(" + this.GetName() + Printer.GetFieldNumber(this) + ")");
			t.ControlSwitch(this);
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
	 * Megpr�b�lja elfogadni a mez�re �rkez� MovableThing t�pus� objektumot, �s megh�vja annak a kapcsol� kezel� f�ggv�ny�t, ha elfogadta.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(GetThing() != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			Printer.FieldCallThing("CALL: " + name, this, "boolean " + GetThing().GetName() , GetThing(), ".DirectPushedBy(" + d.toString() + ")");
			if(GetThing().DirectPushedBy(d)){
				Accepted(t);
				Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".ControlSwitch(this)");
				t.ControlSwitch(this);
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
			Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".ControlSwitch(this)");
			t.ControlSwitch(this);
			Printer.FieldReturn("RETURN " + name, this, ".DirectAccept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): true");
			return true;
		}
	}
	
	/**
	 * Kinyitja a kapcsol�hoz tartoz� lyukat.
	 */
	public void TurnOn() { 
		Printer.FieldCallField("CALL: " + name, this, "void " + holes.GetName(), holes, ".Open()");
		holes.Open(); 
		Printer.FieldReturn("RETURN: " + name, this, ".TurnOn(): void");
	}
	
	/**
	 * Becsukja a kapcsol�hoz tartoz� lyukat.
	 */
	public void TurnOff() { 
		Printer.FieldCallField("CALL: " + name, this, "void " + holes.GetName(), holes, ".Close()");
		holes.Close();
		Printer.FieldReturn("RETURN: " + name, this, ".TurnOff(): void");
	}
}
