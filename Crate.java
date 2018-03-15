package sokoban;

public class Crate extends MovableThing{
	
	public Crate() { name = "c"; }
	
	/**
	 * �rtes�ti a rakt�r�p�letet, hogy megsemmis�lt.
	 * T�rli mag�t az aktu�lis mez�r�l.
	 * null �rt�kre �ll�tja a t�rolt mez� �rt�k�t.
	 */
	public void Disappear() {
		Printer.ThingCallWarehouse("CALL: " + name, this, "void " + GetField().GetWarehouse().GetName(), GetField().GetWarehouse(), ".CrateRemoved(this)");
		GetField().GetWarehouse().CrateRemoved(this);
		
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".Remove(this)");
		GetField().Remove(this);
		
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".SetField(null)");
		SetField(null);
		
		Printer.ThingReturn("RETURN: " + name, this, ".Disappear(): void");
	}
	
	/**
	 * Bekapcsolja a param�ter�l kapott kapcsol�t.
	 * @param s Switch t�pus�, ezt a kapcsol�t fogja bekapcsolni.
	 */
	public void ControlSwitch(Switch s) {
		Printer.ThingCallField("CALL: " + name, this, "void " + s.GetName(), s, ".TurnOn()");
		s.TurnOn();
		
		Printer.ThingReturn("RETURN: " + name, this, ".ControlSwitch(s): void");
	}
	
	/**
	 * A l�d�t arr�bb akarj�k tolni, ez�rt megpr�b�l az adott ir�nyban arr�bb tol�dni.
	 * @param d Direction t�pus�, ebbe az ir�nyba tol�dna a l�da.
	 * @return boolean t�pus�, true �rt�kkel t�r vissza, ha sikeres volt a mozg�s.
	 */
	public boolean PushedBy(Direction d)  {
		Printer.ThingCallThing("CALL: " + name, this, "boolean " + name, this, ".Move(d)");
		boolean temp = Move(d);
		
		if(temp) {
			Printer.ThingReturn("RETURN: " + name, this, "PushedBy(d): true");
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, "PushedBy(d): false");
		}
		
		return temp;
	}
	
	/**
	 * A l�d�t lehet direktben tolni, ez�rt megpr�b�l az adott ir�nyban arr�bb tol�dni.
	 * @param d Direction t�pus�, ebbe az ir�nyba tol�dna a l�da.
	 * @return boolean t�pus�, true �rt�kkel t�r vissza, ha sikeres volt a mozg�s.
	 */
	public boolean DirectPushedBy(Direction d) {
		Printer.ThingCallThing("CALL: " + name, this, "boolean " + name, this, ".Move(d)");
		boolean temp = Move(d);
		
		if(temp) {
			Printer.ThingReturn("RETURN: " + name, this, "DirectPushedBy(d): true");
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, "DirectPushedBy(d): false");
		}
		
		return temp;
	}
	
	/**
	 * A l�da c�lmez�re �rt, err�l �rtes�ti a param�ter�l kapott c�lmez�t, majd t�rli mag�t.
	 * @param g Goal t�pus�, erre a c�lmez�re �rt a l�da.
	 */
	public void AtGoal(Goal g) {
		Printer.ThingCallField("CALL: " + name, this, "void " + g.GetName(), g, ".CrateDelivered()");
		g.CrateDelivered();
		
		Printer.ThingCallThing("CALL: " + name, this, "void " + name, this, "Disappear()");
		Disappear();
		
		Printer.ThingReturn("RETURN: " + name, this, ".AtGoal(g): void");
	}
}
