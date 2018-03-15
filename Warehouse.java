package sokoban;

public class Warehouse {
	private int remainingCrates;
	private Field[] fields;
	private Crate[] crates;
	private String name = "wh";
	
	/**
	 * Visszatér az objektum nevével.
	 * A kiírásnál hasznos.
	 * @return String típussal tér vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * Egy láda megszûnt valamilyen okból.
	 * @param c Crate típusú, ez a láda szûnt meg.
	 */
	public void CrateRemoved(Crate c) {
		for(int i = 0; i < remainingCrates; ++i) {
			if(crates[i] == c) {
				Crate[] temp = new Crate[remainingCrates-1];
				for(int j = 0; j < remainingCrates-1; ++j) {
					if(i != j) {
						temp[j] = crates[j];
					}
				}
				crates = temp;
			}
		}
		
		--remainingCrates;
		Printer.WarehouseReturn("RETURN: " + name, this, ".CrateRemoved(c): void");
	}
		
	/**
	 * A paraméterül kapott mezõt beilleszti a pályába.
	 * @param f Field típusú, ez a mezõ kerül a pályába.
	 */
	public void AddField(Field f) {
		if(fields == null) {
			fields = new Field[1];
			fields[0] = f;
		}
		else {
			Field[] temp = new Field[fields.length+1];
			for(int i = 0; i < fields.length; ++i)
				temp[i] = fields[i];
			temp[fields.length] = f;
			fields = temp;
		}
			
	}
	
	/**
	 * A paraméterül kapott ládát eltárolja.
	 * @param c Crate típusú, a crates[] tömb végére szúrja.
	 */
	public void AddCrate(Crate c) {
		if(crates == null) {
			crates = new Crate[1];
			crates[0] = c;
		}
		else {
			Crate[] temp = new Crate[crates.length+1];
			for(int i = 0; i < crates.length; ++i)
				temp[i] = crates[i];
			temp[crates.length] = c;
			crates = temp;
		}
	}
	
	/*
	 * Az objektumok létrehozásakor szügséges beállítások, illetve a létrehozás kiírása.
	 */
	
	private void CreateField(Field f) {
		AddField(f);
		Printer.AddField(f);
		Printer.WarehouseCreateField("CREATE: " + name, this, "Field " + f.GetName(), f, "()");
		f.SetWarehouse(this);
	}
	
	private void CreateSwitch(Switch s) {
		AddField(s);
		Printer.AddSwitch(s);
		Printer.WarehouseCreateField("CREATE: " + name, this, "Switch " + s.GetName(), s, "()");
		s.SetWarehouse(this);
	}
	
	private void CreateHole(Hole h) {
		AddField(h);
		Printer.AddHole(h);
		Printer.WarehouseCreateField("CREATE: " + name, this, "Hole " + h.GetName(), h, "()");
		h.SetWarehouse(this);
	}
	
	private void CreateGoal(Goal g) {
		AddField(g);
		Printer.AddGoal(g);
		Printer.WarehouseCreateField("CREATE: " + name, this, "Goal " + g.GetName(), g, "()");
		g.SetWarehouse(this);
	}
	
	private void CreatePillar(Pillar p) {
		AddField(p);
		Printer.AddPillar(p);
		Printer.WarehouseCreateField("CREATE: " + name, this, "Pillar " + p.GetName(), p, "()");
		p.SetWarehouse(this);
	}
	
	private void CreateWorker(Worker w) {
		Game.AddWorker(w);
		Printer.AddWorker(w);
		Printer.WarehouseCreateThing("CREATE: " + name, this, "Worker " + w.GetName(), w, "()");
	}
	
	private void CreateCrate(Crate c) {
		AddCrate(c);
		Printer.AddCrate(c);
		Printer.WarehouseCreateThing("CREATE: " + name, this, "Crate " + c.GetName(), c, "()");
	}
	
	/*
	 * A szimulációs esetek függvényei.
	 */
	
	public void OneA() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}
	
	public void OneB() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//s0 létrehozása
		Switch s0 = new Switch();
		CreateSwitch(s0);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(s0, Left)");
		f0.SetNeighbour(s0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f0, Right)");
		s0.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//h0 létrehozása
		Hole h0 = new Hole();
		CreateHole(h0);
		//Lyuk kapcsolóhoz adása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetHole(h0)");
		s0.SetHole(h0);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}
	
	public void OneC() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//g0 létrehozása
		Goal g0 = new Goal();
		CreateGoal(g0);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(g0, Left)");
		f0.SetNeighbour(g0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + g0.GetName(), g0, ".SetNeighbour(f0, Right)");
		g0.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}
	
	public void OneD() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//p0 létrehozása
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(p0, Left)");
		f0.SetNeighbour(p0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f0, Right)");
		p0.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}

	public void OneE() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//h0 létrehozása
		Hole h0 = new Hole();
		CreateHole(h0);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(h0, Left)");
		f0.SetNeighbour(h0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + h0.GetName(), h0, ".SetNeighbour(f0, Right)");
		h0.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}

	public void OneF() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Munkás létrehozása
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(w1, null)");
		f1.Accept(w1, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}

	public void TwoA() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//s0 létrehozása
		Switch s0 = new Switch();
		CreateSwitch(s0);
		//Szomszéd beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(s0, Left)");
		f1.SetNeighbour(s0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f1, Right)");
		s0.SetNeighbour(f1, Direction.Right);
		//h0 létrehozása
		Hole h0 = new Hole();
		CreateHole(h0);
		//Lyuk kapcsolóhoz adása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetHole(h0)");
		s0.SetHole(h0);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}
	
	public void TwoB() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//f0 menti f1-et
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		//g0 létrehozása
		Goal g0 = new Goal();
		CreateGoal(g0);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Többi szomszéd elmentése
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(g0, Left)");
		f1.SetNeighbour(g0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + g0.GetName(), g0, ".SetNeighbour(f1, Right)");
		g0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}

	public void TwoC() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//p0 létrehozása
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(p0, Left)");
		f1.SetNeighbour(p0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f1, Right)");
		p0.SetNeighbour(f1, Direction.Right);
		//Munkás ezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
						
		Printer.Remove();
	}

	public void TwoD() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//h0 létrehozása
		Hole h0 = new Hole();
		CreateHole(h0);
		//Balra lévõ szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(h0, Left)");
		f1.SetNeighbour(h0, Direction.Left);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Jobbra lévõ szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + h0.GetName(), h0, ".SetNeighbour(f1, Right)");
		h0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
								
		Printer.Remove();
	}

	public void TwoE() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//f2 létrehozása
		Field f2 = new Field();
		CreateField(f2);
		//f3 létrehozása
		Field f3 = new Field();
		CreateField(f3);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Láda létrehozása
		Crate c1 = new Crate();
		CreateCrate(c1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Right)");
		f0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Left)");
		f1.SetNeighbour(f0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f2, Right)");
		f1.SetNeighbour(f2, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f1, Left)");
		f2.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f3, Right)");
		f2.SetNeighbour(f3, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f3.GetName(), f3, ".SetNeighbour(f2, Left)");
		f3.SetNeighbour(f2, Direction.Left);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(c1, null)");
		f2.Accept(c1, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
										
		Printer.Remove();
	}
	
	public void TwoF() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Right)");
		f0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Left)");
		f1.SetNeighbour(f0, Direction.Left);
		//f3 létrehozása
		Field f3 = new Field();
		CreateField(f3);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munkás létrehozása
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);		
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//f2 létrehozása
		Field f2 = new Field();
		CreateField(f2);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f3, Right)");
		f2.SetNeighbour(f3, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f1, Left)");
		f2.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f2, Right)");
		f1.SetNeighbour(f2, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f3.GetName(), f3, ".SetNeighbour(f2, Left)");
		f3.SetNeighbour(f2, Direction.Left);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(w1, null)");
		f2.Accept(w1, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
											
		Printer.Remove();
	}

	public void TwoG() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//s0 létrehozása
		Switch s0 = new Switch();
		CreateSwitch(s0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(s0, Left)");
		f0.SetNeighbour(s0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(s0, Right)");
		f1.SetNeighbour(s0, Direction.Right);
		//h0 létrehozása
		Hole h0 = new Hole();
		CreateHole(h0);
		//Lyuk kapcsolóhoz adása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetHole(h0)");
		s0.SetHole(h0);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Mezõre helyezés
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + s0.GetName(), s0, ".Accept(c0, null)");
		s0.Accept(c0, null);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f0, Right)");
		s0.SetNeighbour(f0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f1, Left)");
		s0.SetNeighbour(f1, Direction.Left);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
						
		Printer.Remove();
	}
	
	public void TwoHA() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Right)");
		f0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Left)");
		f1.SetNeighbour(f0, Direction.Left);
		//p0 létrehozása
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//f2 létrehozása
		Field f2 = new Field();
		CreateField(f2);
		//Munkás létrehozása
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(w1, null)");
		f2.Accept(w1, null);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(p0, Right)");
		f2.SetNeighbour(p0, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f1, Left)");
		f2.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f2, Right)");
		f1.SetNeighbour(f2, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f2, Left)");
		p0.SetNeighbour(f2, Direction.Left);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
													
		Printer.Remove();
	}
	
	public void TwoHB() {
		//f0 létrehozása
		Field f0 = new Field();
		CreateField(f0);
		//f1 létrehozása
		Field f1 = new Field();
		CreateField(f1);
		//f2 létrehozása
		Field f2 = new Field();
		CreateField(f2);
		//f3 létrehozása
		Field f3 = new Field();
		CreateField(f3);
		//p0 létrehozása
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Szomszédok beállítása
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Right)");
		f0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Left)");
		f1.SetNeighbour(f0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f2, Right)");
		f1.SetNeighbour(f2, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f1, Left)");
		f2.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f3, Right)");
		f2.SetNeighbour(f3, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f3.GetName(), f3, ".SetNeighbour(f2, Left)");
		f3.SetNeighbour(f2, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f3.GetName(), f3, ".SetNeighbour(p0, Right)");
		f3.SetNeighbour(p0, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f3, Left)");
		p0.SetNeighbour(f3, Direction.Left);
		//Munkás létrehozása
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munkás létrehozása
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Láda létrehozása
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Láda létrehozása
		Crate c1 = new Crate();
		CreateCrate(c1);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Munkás mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(w1, null)");
		f2.Accept(w1, null);
		//Láda mezõre helyezése
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f3.GetName(), f3, ".Accept(c1, null)");
		f3.Accept(c1, null);
		//Szimuláció
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
														
		Printer.Remove();
	}
}
