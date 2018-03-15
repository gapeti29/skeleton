package sokoban;

public class Goal extends Field {
	public Goal() { name = "g"; }
	
	/**
	 * Megpr�b�lja elfogadni a mez�re �rkez� MovableThing t�pus� objektumot.
	 * Ha siker�lt, akkor megh�vja annak a c�lmez�re �rkez�st kezel� f�ggv�ny�t.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		Printer.FieldCallField("CALL: " + name, this, "boolean " + name, this, ".Accept(t, d)");
		if(super.Accept(t, d)) {
			//Megh�vja a c�lmez�re �r�st kezel� f�ggv�ny�t.
			Printer.FieldCallThing("CALL: " + name, this, "void " + t.GetName(), t, ".AtGoal(this)");
			t.AtGoal(this);
			Printer.FieldReturn("RETURN: " + name, this, ".Accept(t, d): true");
			return true;
		}
		else {
			Printer.FieldReturn("RETURN: " + name, this, ".Accept(t, d): false");
			return false;
		}
	}
	
	/**
	 * Az aktu�lisan soron l�v� j�t�kos betolt egy l�d�t a c�lmez�re, ez�rt � kap egy plusz pontot.
	 */
	public void CrateDelivered() {
		Printer.FieldCallGame("CALL: " + name, this, "void ", ".AddPointToWorker()");
		Game.AddPointToWorker();
		Printer.FieldReturn("RETURN: " + name, this, ".CrateDelivered(): void");
	}
}
