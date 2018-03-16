package sokoban;

public final class Printer {
	private static int tabs;
	//Az �sszes l�trehozott objektum t�rolva lesz.
	private static Field[] fields;
	private static Goal[] goals;
	private static Hole[] holes;
	private static Pillar[] pillars;
	private static Switch[] switches;
	private static Crate[] crates;
	private static Worker[] workers;
	private static Warehouse[] warehouses;
	
	/**
	 * Megkeresi, hogy melyik Field h�vott meg egy f�ggv�nyt �s azt ki�rja a console ablakra.
	 * @param f Field[] (vagy annak lesz�rmazottja) t�pus�, v�gig n�zi ebben a t�mbben, hogy benne van-e a caller.
	 * @param callerType String t�pus�, a f�ggv�nyh�v�s m�dj�t adja meg. Lehet CALL:, vagy CREATE:
	 * @param caller Field (vagy annak lesz�rmazottja) t�pus�, ez az objektum h�vott meg egy f�ggv�ny.
	 */
	private static void FieldCaller(Field[] f, String callerType, Field caller) {
		if(f != null) {
			for(int i = 0; i < f.length; ++i) {
				if(f[i] == caller) {
					for(int j = 0; j < tabs; ++j) {
						System.out.print("\t");
					}
					System.out.print(callerType + i + ": ");
				}
			}
		}
	}
	
	/**
	 * Megkeresi, hogy melyik Field f�ggv�nye h�v�dott meg�s azt ki�rja a console ablakra.
	 * @param f Field[] (vagy annak lesz�rmazottja) t�pus�, v�gig n�zi ebben a t�mbben, hogy benne van-e a called.
	 * @param calledType String t�pus�, megadja a megh�vott f�ggv�ny visszat�r�si �rt�k�t, plusz a megh�vott objektum nev�t (Pl:f, s, p, stb.).
	 * @param called Field (vagy annak lesz�rmazottja) t�pus�, ennek az objektumnak h�v�dott meg egy f�ggv�nye.
	 * @param function String t�pus�, a megh�vott f�ggv�nyt tartalmazza.
	 */
	private static void FieldCalled(Field[] f, String calledType, Field called, String function) {
		if(f != null) {
			for(int i = 0; i < f.length; ++i) {
				if(f[i] == called) {
					System.out.print(calledType + i + function + "\n");
				}
			}
		}
	}
	
	/*
	 * V�gig n�zik az �sszes Field[] t�pusra.
	 */
	private static void FieldCallerFinder(String callerType, Field caller) {
		FieldCaller(fields, callerType, caller);
		FieldCaller(goals, callerType, caller);
		FieldCaller(holes, callerType, caller);
		FieldCaller(pillars, callerType, caller);
		FieldCaller(switches, callerType, caller);
	}
	
	private static void FieldCalledFinder(String calledType, Field called, String function) {
		FieldCalled(fields, calledType, called, function);
		FieldCalled(goals, calledType, called, function);
		FieldCalled(holes, calledType, called, function);
		FieldCalled(pillars, calledType, called, function);
		FieldCalled(switches, calledType, called, function);
	}
	
	/*
	 * Egy Field h�vta meg egy m�sik Field valamely f�ggv�ny�t.
	 */
	public static void FieldCallField(String callerType, Field caller,  String calledType, Field called, String function) {
		FieldCallerFinder(callerType, caller);
		FieldCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Egy Field f�ggv�nye visszat�r.
	 */
	public static void FieldReturn(String calledType, Field called, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		
		FieldCalledFinder(calledType, called, function);
	}
	
	/**
	 * Megkeresi, hogy melyik MovableThing h�vott meg egy f�ggv�nyt, �s azt ki�rja a console ablakra.
	 * @param t MovableThing[] t�pus�, v�gig n�zi ebben a t�mbben, hogy benne van-e a caller.
	 * @param callerType String t�pus�, a f�ggv�nyh�v�s t�pus�t adja meg. Lehet CALL:, vagy CREATE:
	 * @param caller MovableThing t�pus�, ez az objektum h�vott meg valamilyen f�ggv�nyt.
	 */
	private static void ThingCaller(MovableThing[] t, String callerType, MovableThing caller) {
		if(t != null) {
			for(int i = 0; i < t.length; ++i) {
				if(t[i] == caller) {
					for(int j = 0; j < tabs; ++j) {
						System.out.print("\t");
					}
					System.out.print(callerType + i + ": ");
				}
			}
		}
	}
	
	/**
	 * Megkeresi, hogy melyik MovableThing-nek h�v�dott meg egy f�ggv�nye, �s azt ki�rja a console ablakra.
	 * @param t MovableThing[] t�pus�, v�gig n�zi ebben a t�mbben, hogy benne van-e a caller.
	 * @param calledType String t�pus�, megadja a megh�vott f�ggv�ny visszat�r�si �rt�k�t, plusz a megh�vott objektum nev�t (Pl:w, c).
	 * @param called MovableThing t�pus�, ennek az objektumnak h�v�dott meg valamely f�ggv�nye.
	 * @param function String t�pus�, a megh�vott f�ggv�nyt adja meg.
	 */
	private static void ThingCalled(MovableThing[] t, String calledType, MovableThing called, String function) {
		if(t != null) {
			for(int i = 0; i < t.length; ++i) {
				if(t[i] == called) {
					System.out.print(calledType + i + function + "\n");
				}
			}
		}
	}
	
	/*
	 * V�gig n�zik a MovableThing[] t�mb�ket.
	 */
	private static void ThingCallerFinder(String callerType, MovableThing caller){
		ThingCaller(crates, callerType, caller);
		ThingCaller(workers, callerType, caller);
	}
	
	private static void ThingCalledFinder(String calledType, MovableThing called, String function) {
		ThingCalled(crates, calledType, called, function);
		ThingCalled(workers, calledType, called, function);
	}
	
	/*
	 * MovableThing h�vott meg egy Field f�ggv�ny�t.
	 */
	public static void ThingCallField(String callerType, MovableThing caller,  String calledType, Field called, String function) {
		ThingCallerFinder(callerType, caller);
		FieldCalledFinder(calledType, called, function);		
		++tabs;
	}
	
	/*
	 * Field h�vott meg egy MovableThing f�ggv�ny�t.
	 */
	public static void FieldCallThing(String callerType, Field caller,  String calledType, MovableThing called, String function) {
		FieldCallerFinder(callerType, caller);
		ThingCalledFinder(calledType, called, function);		
		++tabs;
	}
	
	/*
	 * MovableThing h�vott meg egy MovableThing f�ggv�ny�t.
	 */
	public static void ThingCallThing(String callerType, MovableThing caller,  String calledType, MovableThing called, String function) {
		ThingCallerFinder(callerType, caller);
		ThingCalledFinder(calledType, called, function);		
		++tabs;
	}
	
	/*
	 * Egy MovableThing f�ggv�nye visszat�r.
	 */
	public static void ThingReturn(String calledType, MovableThing called, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		ThingCalledFinder(calledType, called, function);
	}
	
	/**
	 * Megkeresi, hogy melyik Warehouse h�vott meg egy f�ggv�nyt, �s azt ki�rja a console ablakra.
	 * @param callerType String t�pus�, a f�ggv�nyh�v�s t�pus�t adja meg. Lehet CALL:, vagy CREATE:
	 * @param caller Warehouse t�pus�, ez az objektum h�vott meg egy f�ggv�nyt.
	 */
	private static void WarehouseCaller(String callerType, Warehouse caller) {
		if(warehouses != null) {
			for(int i = 0; i < warehouses.length; ++i) {
				if(warehouses[i] == caller) {
					for(int j = 0; j < tabs; ++j) {
						System.out.print("\t");
					}
					System.out.print(callerType + i + ": ");
				}
			}
		}
	}
	
	/**
	 * Megkeresi, hogy melyik Warehouse-nak h�v�dott meg egy f�ggv�nye, �s azt ki�rja a console ablakra.
	 * @param calledType String t�pus�, megadja a megh�vott f�ggv�ny visszat�r�si �rt�k�t, plusz a megh�vott objektum nev�t (Pl:wh).
	 * @param called Warehouse t�pus�, ennek az objektumnak h�vodott meg egy f�ggv�nye.
	 * @param function String t�pus�, a megh�vott f�ggv�nyt adja meg. 
	 */
	private static void WarehouseCalled(String calledType, Warehouse called, String function) {
		if(warehouses != null) {
			for(int i = 0; i < warehouses.length; ++i) {
				if(warehouses[i] == called) {
					System.out.print(calledType + i + function + "\n");
				}
			}
		}
	}
	
	/*
	 * MovableThing egy Warehouse f�ggv�ny�t h�vta meg.
	 */
	public static void ThingCallWarehouse(String callerType, MovableThing caller, String calledType, Warehouse called, String function) {
		ThingCallerFinder(callerType, caller);
		WarehouseCalled(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Warehouse egy MovableThing f�ggv�ny�t h�vta meg.
	 */
	public static void WarehouseCallThing(String callerType, Warehouse caller, String calledType, MovableThing called, String function) {
		WarehouseCaller(callerType, caller);
		ThingCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Field egy Warehouse f�ggv�ny�t h�vta meg.
	 */
	public static void FieldCallWarehouse(String callerType, Field caller, String calledType, Warehouse called, String function) {
		FieldCallerFinder(callerType, caller);
		WarehouseCalled(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Warehouse egy Field f�ggv�ny�t h�vta meg.
	 */
	public static void WarehouseCallField(String callerType, Warehouse caller, String calledType, Field called, String function) {
		WarehouseCaller(callerType, caller);
		FieldCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Warehouse valamely f�ggv�nye visszat�r.
	 */
	public static void WarehouseReturn(String calledType, Warehouse called, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		WarehouseCalled(calledType, called, function);
	}
	
	/*
	 * Game h�vja meg egy MovableThing f�ggv�ny�t.
	 */
	public static void GameCallThing(String callerType, String calledType, MovableThing called, String function) {
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		System.out.print(callerType + "Game: ");
		
		ThingCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Field h�vja meg a Game valamely f�ggv�ny�t.
	 */
	public static void FieldCallGame(String callerType, Field caller, String calledType, String function) {
		FieldCallerFinder(callerType, caller);
		System.out.print(calledType + "Game" + function + "\n");
		++tabs;
	}
	
	/*
	 * Game valamely f�ggv�nye visszat�r.
	 */
	public static void GameReturn(String calledType, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		System.out.print(calledType + "Game" + function + "\n");
	}
	
	/*
	 * Create h�v�sokn�l nem v�ltoznak a tabul�torok, ezek ez�rt lettek k�l�n v�ve.
	 * Warehouse l�trehoz egy Field-et.
	 */
	public static void WarehouseCreateField(String callerType, Warehouse caller, String calledType, Field called, String function) {
		WarehouseCaller(callerType, caller);
		FieldCalledFinder(calledType, called, function);
	}
	
	/*
	 * Warehouse l�trehoz egy MovableThing-et.
	 */
	public static void WarehouseCreateThing(String callerType, Warehouse caller, String calledType, MovableThing called, String function) {
		WarehouseCaller(callerType, caller);
		ThingCalledFinder(calledType, called, function);
	}
	
	/*
	 * Game l�trehoz egy Warehouse-t.
	 */
	public static void GameCreateWarehouse(String callerType, String calledType, Warehouse called, String function) {
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		System.out.print(callerType + "Game: ");
		WarehouseCalled(calledType, called, function);
	}
	
	public static int GetFieldNumber(Field f) {
		if(fields != null) {
			for(int i = 0; i < fields.length; ++i) {
				if(fields[i] == f)
					return i;
			}
		}
		if(goals != null) {
			for(int i = 0; i < goals.length; ++i) {
				if(goals[i] == f)
					return i;
			}
		}
		if(holes != null) {
			for(int i = 0; i < holes.length; ++i) {
				if(holes[i] == f)
					return i;
			}
		}
		if(pillars != null) {
			for(int i = 0; i < pillars.length; ++i) {
				if(pillars[i] == f)
					return i;
			}
		}
		if(switches != null) {
			for(int i = 0; i < switches.length; ++i) {
				if(switches[i] == f)
					return i;
			}
		}
		return -1;
	}
	
	public static int GetThingNumber(MovableThing t) {
		if(crates != null) {
			for(int i = 0; i < crates.length; ++i) {
				if(crates[i] == t)
					return i;
			}
		}
		if(workers != null) {
			for(int i = 0; i < workers.length; ++i) {
				if(workers[i] == t)
					return i;
			}
		}
		return -1;
	}
	
	public static int GetWarehouseNumber(Warehouse wh) {
		if(warehouses != null) {
			for(int i = 0; i < warehouses.length; ++i) {
				if(warehouses[i] == wh)
					return i;
			}
		}
		return -1;
	}
	
	/*
	 * Hozz�ad�sok...
	 */
	
	public static void AddField(Field f) {
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
	
	public static void AddGoal(Goal g) {
		if(goals == null) {
			goals = new Goal[1];
			goals[0] = g;
		}
		else {
			Goal[] temp = new Goal[goals.length+1];
			for(int i = 0; i < goals.length; ++i)
				temp[i] = goals[i];
			temp[goals.length] = g;
			goals = temp;
		}
	}
	
	public static void AddHole(Hole h) {
		if(holes == null) {
			holes = new Hole[1];
			holes[0] = h;
		}
		else {
			Hole[] temp = new Hole[holes.length+1];
			for(int i = 0; i < holes.length; ++i)
				temp[i] = holes[i];
			temp[holes.length] = h;
			holes = temp;
		}
	}
	
	public static void AddPillar(Pillar p) {
		if(pillars == null) {
			pillars = new Pillar[1];
			pillars[0] = p;
		}
		else {
			Pillar[] temp = new Pillar[pillars.length+1];
			for(int i = 0; i < pillars.length; ++i)
				temp[i] = pillars[i];
			temp[pillars.length] = p;
			pillars = temp;
		}
	}
	
	public static void AddSwitch(Switch s) {
		if(switches == null) {
			switches = new Switch[1];
			switches[0] = s;
		}
		else {
			Switch[] temp = new Switch[switches.length+1];
			for(int i = 0; i < switches.length; ++i)
				temp[i] = switches[i];
			temp[switches.length] = s;
			switches = temp;
		}
	}
	
	public static void AddCrate(Crate c) {
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
	
	public static void AddWorker(Worker w) {
		if(workers == null) {
			workers = new Worker[1];
			workers[0] = w;
		}
		else {
			Worker[] temp = new Worker[workers.length+1];
			for(int i = 0; i < workers.length; ++i)
				temp[i] = workers[i];
			temp[workers.length] = w;
			workers = temp;
		}
	}
	
	public static void AddWarehouse(Warehouse w) {
		if(warehouses == null) {
			warehouses = new Warehouse[1];
			warehouses[0] = w;
		}
		else {
			Warehouse[] temp = new Warehouse[warehouses.length+1];
			for(int i = 0; i < warehouses.length; ++i)
				temp[i] = warehouses[i];
			temp[warehouses.length] = w;
			warehouses = temp;
		}
	}
	
	/**
	 * Minden kinull�z�sa.
	 */
	public static void Remove() {
		fields = null;
		goals = null;
		holes = null;
		pillars = null;
		switches = null;
		crates = null;
		workers = null;
		warehouses = null;
		tabs = 0;
	}
}
