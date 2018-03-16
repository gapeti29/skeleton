package sokoban;

public class Crate extends MovableThing{
	
	public Crate() { name = "c"; }
	
	/**
	 * Értesíti a raktárépületet, hogy megsemmisült.
	 * Törli magát az aktuális mezõrõl.
	 * null értékre állítja a tárolt mezõ értékét.
	 */
	public void Disappear() {
		Printer.ThingCallWarehouse("CALL: " + name, this, "void " + GetField().GetWarehouse().GetName(), GetField().GetWarehouse(), ".CrateRemoved(this)");
		GetField().GetWarehouse().CrateRemoved(this);
		
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".Remove(" + this.GetName() + Printer.GetThingNumber(this) + ")");
		GetField().Remove(this);
		
		Printer.ThingCallField("CALL: " + name, this, "void " + GetField().GetName(), GetField(), ".SetField(null)");
		SetField(null);
		
		Printer.ThingReturn("RETURN: " + name, this, ".Disappear(): void");
	}
	
	/**
	 * Bekapcsolja a paraméterül kapott kapcsolót.
	 * @param s Switch típusú, ezt a kapcsolót fogja bekapcsolni.
	 */
	public void ControlSwitch(Switch s) {
		Printer.ThingCallField("CALL: " + name, this, "void " + s.GetName(), s, Printer.GetFieldNumber(s) + ".TurnOn()");
		s.TurnOn();
		
		Printer.ThingReturn("RETURN: " + name, this, ".ControlSwitch(" + s.GetName() + Printer.GetFieldNumber(s) + "): void");
	}
	
	/**
	 * A ládát arrébb akarják tolni, ezért megpróbál az adott irányban arrébb tolódni.
	 * @param d Direction típusú, ebbe az irányba tolódna a láda.
	 * @return boolean típusú, true értékkel tér vissza, ha sikeres volt a mozgás.
	 */
	public boolean PushedBy(Direction d)  {
		Printer.ThingCallThing("CALL: " + name, this, "boolean " + name, this, ".Move(" + d.toString() + ")");
		boolean temp = Move(d);
		
		if(temp) {
			Printer.ThingReturn("RETURN: " + name, this, "PushedBy(" + d.toString() + "): true");
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, "PushedBy(" + d.toString() + "): false");
		}
		
		return temp;
	}
	
	/**
	 * A ládát lehet direktben tolni, ezért megpróbál az adott irányban arrébb tolódni.
	 * @param d Direction típusú, ebbe az irányba tolódna a láda.
	 * @return boolean típusú, true értékkel tér vissza, ha sikeres volt a mozgás.
	 */
	public boolean DirectPushedBy(Direction d) {
		Printer.ThingCallThing("CALL: " + name, this, "boolean " + name, this, ".Move(" + d.toString() + ")");
		boolean temp = Move(d);
		
		if(temp) {
			Printer.ThingReturn("RETURN: " + name, this, "DirectPushedBy(" + d.toString() + "): true");
		}
		else {
			Printer.ThingReturn("RETURN: " + name, this, "DirectPushedBy(" + d.toString() + "): false");
		}
		
		return temp;
	}
	
	/**
	 * A láda célmezõre ért, errõl értesíti a paraméterül kapott célmezõt, majd törli magát.
	 * @param g Goal típusú, erre a célmezõre ért a láda.
	 */
	public void AtGoal(Goal g) {
		Printer.ThingCallField("CALL: " + name, this, "void " + g.GetName(), g, ".CrateDelivered()");
		g.CrateDelivered();
		
		Printer.ThingCallThing("CALL: " + name, this, "void " + name, this, "Disappear()");
		Disappear();
		
		Printer.ThingReturn("RETURN: " + name, this, ".AtGoal(" + g.GetName() + Printer.GetFieldNumber(g) + "): void");
	}
}
