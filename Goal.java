package sokoban;

public class Goal extends Field {
	public Goal() { name = "g"; }
	
	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot.
	 * Ha sikerült, akkor meghívja annak a célmezõre érkezést kezelõ függvényét.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		if(d == null) {
			Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", null)");
		}
		else {
			Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + ")");
		}
		if(super.Accept(t, d)) {
			//Meghívja a célmezõre érést kezelõ függvényét.
			Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".AtGoal(" + this.GetName() + Printer.GetFieldNumber(this) + ")");
			t.AtGoal(this);
			Printer.FieldReturn("RETURN: " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): true");
			return true;
		}
		else {
			Printer.FieldReturn("RETURN: " + name, this, ".Accept(" + t.GetName() + Printer.GetThingNumber(t) + ", " + d.toString() + "): false");
			return false;
		}
	}
	
	/**
	 * Az aktuálisan soron lévõ játékos betolt egy ládát a célmezõre, ezért õ kap egy plusz pontot.
	 */
	public void CrateDelivered() {
		Printer.FieldCallGame("CALL: " + name, this, "void ", ".AddPointToWorker()");
		Game.AddPointToWorker();
		Printer.FieldReturn("RETURN: " + name, this, ".CrateDelivered(): void");
	}
}
