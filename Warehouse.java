package sokoban;

public class Warehouse {
	private int remainingCrates;
	private Field[] fields;
	private Crate[] crates;
	private String name = "wh";
	
	/**
	 * Visszat�r az objektum nev�vel.
	 * A ki�r�sn�l hasznos.
	 * @return String t�pussal t�r vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * Egy l�da megsz�nt valamilyen okb�l.
	 * @param c Crate t�pus�, ez a l�da sz�nt meg.
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
	 * A param�ter�l kapott mez�t beilleszti a p�ly�ba.
	 * @param f Field t�pus�, ez a mez� ker�l a p�ly�ba.
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
	 * A param�ter�l kapott l�d�t elt�rolja.
	 * @param c Crate t�pus�, a crates[] t�mb v�g�re sz�rja.
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
	 * Az objektumok l�trehoz�sakor sz�gs�ges be�ll�t�sok, illetve a l�trehoz�s ki�r�sa.
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
	 * A szimul�ci�s esetek f�ggv�nyei.
	 */
	
	public void OneA() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}
	
	public void OneB() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//s0 l�trehoz�sa
		Switch s0 = new Switch();
		CreateSwitch(s0);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(s0, Left)");
		f0.SetNeighbour(s0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f0, Right)");
		s0.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//h0 l�trehoz�sa
		Hole h0 = new Hole();
		CreateHole(h0);
		//Lyuk kapcsol�hoz ad�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetHole(h0)");
		s0.SetHole(h0);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}
	
	public void OneC() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//g0 l�trehoz�sa
		Goal g0 = new Goal();
		CreateGoal(g0);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(g0, Left)");
		f0.SetNeighbour(g0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + g0.GetName(), g0, ".SetNeighbour(f0, Right)");
		g0.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}
	
	public void OneD() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//p0 l�trehoz�sa
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(p0, Left)");
		f0.SetNeighbour(p0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f0, Right)");
		p0.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}

	public void OneE() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//h0 l�trehoz�sa
		Hole h0 = new Hole();
		CreateHole(h0);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(h0, Left)");
		f0.SetNeighbour(h0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + h0.GetName(), h0, ".SetNeighbour(f0, Right)");
		h0.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}

	public void OneF() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//Munk�s l�trehoz�sa
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(w1, null)");
		f1.Accept(w1, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
		
		Printer.Remove();
	}

	public void TwoA() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//s0 l�trehoz�sa
		Switch s0 = new Switch();
		CreateSwitch(s0);
		//Szomsz�d be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(s0, Left)");
		f1.SetNeighbour(s0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f1, Right)");
		s0.SetNeighbour(f1, Direction.Right);
		//h0 l�trehoz�sa
		Hole h0 = new Hole();
		CreateHole(h0);
		//Lyuk kapcsol�hoz ad�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetHole(h0)");
		s0.SetHole(h0);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}
	
	public void TwoB() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//f0 menti f1-et
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		//g0 l�trehoz�sa
		Goal g0 = new Goal();
		CreateGoal(g0);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//T�bbi szomsz�d elment�se
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(g0, Left)");
		f1.SetNeighbour(g0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + g0.GetName(), g0, ".SetNeighbour(f1, Right)");
		g0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
				
		Printer.Remove();
	}

	public void TwoC() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//p0 l�trehoz�sa
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(p0, Left)");
		f1.SetNeighbour(p0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f1, Right)");
		p0.SetNeighbour(f1, Direction.Right);
		//Munk�s ez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
						
		Printer.Remove();
	}

	public void TwoD() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//h0 l�trehoz�sa
		Hole h0 = new Hole();
		CreateHole(h0);
		//Balra l�v� szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Left)");
		f0.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(h0, Left)");
		f1.SetNeighbour(h0, Direction.Left);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Jobbra l�v� szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + h0.GetName(), h0, ".SetNeighbour(f1, Right)");
		h0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Right)");
		f1.SetNeighbour(f0, Direction.Right);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
								
		Printer.Remove();
	}

	public void TwoE() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//f2 l�trehoz�sa
		Field f2 = new Field();
		CreateField(f2);
		//f3 l�trehoz�sa
		Field f3 = new Field();
		CreateField(f3);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//L�da l�trehoz�sa
		Crate c1 = new Crate();
		CreateCrate(c1);
		//Szomsz�dok be�ll�t�sa
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
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(c1, null)");
		f2.Accept(c1, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
										
		Printer.Remove();
	}
	
	public void TwoF() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Right)");
		f0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Left)");
		f1.SetNeighbour(f0, Direction.Left);
		//f3 l�trehoz�sa
		Field f3 = new Field();
		CreateField(f3);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munk�s l�trehoz�sa
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);		
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//f2 l�trehoz�sa
		Field f2 = new Field();
		CreateField(f2);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f3, Right)");
		f2.SetNeighbour(f3, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f1, Left)");
		f2.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f2, Right)");
		f1.SetNeighbour(f2, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f3.GetName(), f3, ".SetNeighbour(f2, Left)");
		f3.SetNeighbour(f2, Direction.Left);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(w1, null)");
		f2.Accept(w1, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
											
		Printer.Remove();
	}

	public void TwoG() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//s0 l�trehoz�sa
		Switch s0 = new Switch();
		CreateSwitch(s0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(s0, Left)");
		f0.SetNeighbour(s0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(s0, Right)");
		f1.SetNeighbour(s0, Direction.Right);
		//h0 l�trehoz�sa
		Hole h0 = new Hole();
		CreateHole(h0);
		//Lyuk kapcsol�hoz ad�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetHole(h0)");
		s0.SetHole(h0);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//Mez�re helyez�s
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + s0.GetName(), s0, ".Accept(c0, null)");
		s0.Accept(c0, null);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f0, Right)");
		s0.SetNeighbour(f0, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + s0.GetName(), s0, ".SetNeighbour(f1, Left)");
		s0.SetNeighbour(f1, Direction.Left);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Left)");
		w0.DirectMove(Direction.Left);
						
		Printer.Remove();
	}
	
	public void TwoHA() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f0.GetName(), f0, ".SetNeighbour(f1, Right)");
		f0.SetNeighbour(f1, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f0, Left)");
		f1.SetNeighbour(f0, Direction.Left);
		//p0 l�trehoz�sa
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//f2 l�trehoz�sa
		Field f2 = new Field();
		CreateField(f2);
		//Munk�s l�trehoz�sa
		Worker w1 = new Worker();
		CreateWorker(w1);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(w1, null)");
		f2.Accept(w1, null);
		//Szomsz�dok be�ll�t�sa
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(p0, Right)");
		f2.SetNeighbour(p0, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f2.GetName(), f2, ".SetNeighbour(f1, Left)");
		f2.SetNeighbour(f1, Direction.Left);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + f1.GetName(), f1, ".SetNeighbour(f2, Right)");
		f1.SetNeighbour(f2, Direction.Right);
		Printer.WarehouseCallField("CALL: " + name, this, "void " + p0.GetName(), p0, ".SetNeighbour(f2, Left)");
		p0.SetNeighbour(f2, Direction.Left);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
													
		Printer.Remove();
	}
	
	public void TwoHB() {
		//f0 l�trehoz�sa
		Field f0 = new Field();
		CreateField(f0);
		//f1 l�trehoz�sa
		Field f1 = new Field();
		CreateField(f1);
		//f2 l�trehoz�sa
		Field f2 = new Field();
		CreateField(f2);
		//f3 l�trehoz�sa
		Field f3 = new Field();
		CreateField(f3);
		//p0 l�trehoz�sa
		Pillar p0 = new Pillar();
		CreatePillar(p0);
		//Szomsz�dok be�ll�t�sa
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
		//Munk�s l�trehoz�sa
		Worker w0 = new Worker();
		CreateWorker(w0);
		//Munk�s l�trehoz�sa
		Worker w1 = new Worker();
		CreateWorker(w1);
		//L�da l�trehoz�sa
		Crate c0 = new Crate();
		CreateCrate(c0);
		//L�da l�trehoz�sa
		Crate c1 = new Crate();
		CreateCrate(c1);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f0.GetName(), f0, ".Accept(w0, null)");
		f0.Accept(w0, null);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f1.GetName(), f1, ".Accept(c0, null)");
		f1.Accept(c0, null);
		//Munk�s mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f2.GetName(), f2, ".Accept(w1, null)");
		f2.Accept(w1, null);
		//L�da mez�re helyez�se
		Printer.WarehouseCallField("CALL: " + name, this, "boolean " + f3.GetName(), f3, ".Accept(c1, null)");
		f3.Accept(c1, null);
		//Szimul�ci�
		Printer.WarehouseCallThing("CALL: " + name, this, "boolean " + w0.GetName(), w0, ".DirectMove(Right)");
		w0.DirectMove(Direction.Right);
														
		Printer.Remove();
	}
}
