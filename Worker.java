package sokoban;

public class Worker extends MovableThing{
	private int points;
	
	public Worker() { name = "w"; }
	
	/**
	 * A munk�s pontsz�m�nak eggyel n�vel�se.
	 */
	public void AddPoint() { 
		++points;
		Printer.ThingReturn("RETURN: " + name, this, ".AddPoint(): void");
	}
	
	/**
	 * A munk�s megpr�b�l az adott ir�nyba l�pni (direktben, nem tolj�k).
	 * @param d Direction t�pus�, ebbe az ir�nyba haladna.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha siker�lt a mozg�s.
	 */
	public boolean DirectMove(Direction d) {
		Printer.ThingCallField("CALL: " + name, this, "Field " + GetField().GetName(), GetField(), ".GetNeighbour("+d.toString()+")");
		Field temp = GetField().GetNeighbour(d);
		Printer.ThingCallField("CALL: " + name, this, "boolean " + temp.GetName(), temp, ".DirectAccept(" + this.GetName() + Printer.GetThingNumber(this) + ", " +d.toString()+")");
		boolean b = temp.DirectAccept(this, d);
		if(b){
			Printer.ThingReturn("RETURN: " + name, this, ".DirectMove("+d.toString()+"): true");
			return true;
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, ".DirectMove("+d.toString()+"): false");
			return false;
		}
	}
	
	/**
	 * T�rli mag�t az aktu�lis mez�r�l.
	 * null �rt�kre �ll�tja a t�rolt mez� �rt�k�t.
	 */
	public void Disappear() {
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".Remove(" + this.GetName() + Printer.GetThingNumber(this) + ")");
		GetField().Remove(this);
		
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".SetField(null)");
		SetField(null);
		
		Printer.ThingReturn("RETURN: " + name, this, ".Disappear(): void");
	}
	
	/**
	 * Kikapcsolja a param�ter�l kapott kapcsol�t.
	 */
	public void ControlSwitch(Switch s) {
		Printer.ThingCallField("CALL: " + name, this, "void " + s.GetName(), s, ".TurnOff()");
		s.TurnOff();
		Printer.ThingReturn("RETURN: " + name, this, ".ControlSwitch(" + s.GetName() + Printer.GetFieldNumber(s) + "): void");
	}
	
	/**
	 * A munk�st nem k�zvetlen pr�b�lj�k meg arr�bb tolni, �gy megpr�b�l az adott ir�nyba tol�dni.
	 * Ha nem siker�l arr�bb tol�dnia, akkor meghal.
	 * @param d Direction t�pus�, ebbe az ir�nyba mozogna.
	 * @return boolean t�pussal t�r vissza, mely k�l�nb�z� okokb�l, de mindig true lesz.
	 */
	public boolean PushedBy(Direction d) {
		Printer.ThingCallThing("CALL: " + name, this, "boolean " + name, this, ".Move(" + d.toString() + ")");
		if(!Move(d)) {
			Printer.ThingCallThing("CALL: " + name, this, "void " + name, this, ".Disappear()");
			Disappear();
		}
		Printer.ThingReturn("RETURN: " + name, this, ".PushedBy(" + d.toString() + "): true");
		return true;
	}
	
	/**
	 * A munk�st nem lehet direktben eltolni, ez�rt mindig false �rt�kkel t�r vissza.
	 * @return boolean t�pussal t�r vissza.
	 */
	public boolean DirectPushedBy(Direction d) {
		Printer.ThingReturn("RETURN: " + name, this, ".DirectPushedBy(" + d.toString() + "): false");
		return false;
	}
}
