package sokoban;

public final class Printer {
	private static int tabs;
	//Az összes létrehozott objektum tárolva lesz.
	private static Field[] fields;
	private static Goal[] goals;
	private static Hole[] holes;
	private static Pillar[] pillars;
	private static Switch[] switches;
	private static Crate[] crates;
	private static Worker[] workers;
	private static Warehouse[] warehouses;
	
	/**
	 * Megkeresi, hogy melyik Field hívott meg egy függvényt és azt kiírja a console ablakra.
	 * @param f Field[] (vagy annak leszármazottja) típusú, végig nézi ebben a tömbben, hogy benne van-e a caller.
	 * @param callerType String típusú, a függvényhívás módját adja meg. Lehet CALL:, vagy CREATE:
	 * @param caller Field (vagy annak leszármazottja) típusú, ez az objektum hívott meg egy függvény.
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
	 * Megkeresi, hogy melyik Field függvénye hívódott megés azt kiírja a console ablakra.
	 * @param f Field[] (vagy annak leszármazottja) típusú, végig nézi ebben a tömbben, hogy benne van-e a called.
	 * @param calledType String típusú, megadja a meghívott függvény visszatérési értékét, plusz a meghívott objektum nevét (Pl:f, s, p, stb.).
	 * @param called Field (vagy annak leszármazottja) típusú, ennek az objektumnak hívódott meg egy függvénye.
	 * @param function String típusú, a meghívott függvényt tartalmazza.
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
	 * Végig nézik az összes Field[] típusra.
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
	 * Egy Field hívta meg egy másik Field valamely függvényét.
	 */
	public static void FieldCallField(String callerType, Field caller,  String calledType, Field called, String function) {
		FieldCallerFinder(callerType, caller);
		FieldCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Egy Field függvénye visszatér.
	 */
	public static void FieldReturn(String calledType, Field called, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		
		FieldCalledFinder(calledType, called, function);
	}
	
	/**
	 * Megkeresi, hogy melyik MovableThing hívott meg egy függvényt, és azt kiírja a console ablakra.
	 * @param t MovableThing[] típusú, végig nézi ebben a tömbben, hogy benne van-e a caller.
	 * @param callerType String típusú, a függvényhívás típusát adja meg. Lehet CALL:, vagy CREATE:
	 * @param caller MovableThing típusú, ez az objektum hívott meg valamilyen függvényt.
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
	 * Megkeresi, hogy melyik MovableThing-nek hívódott meg egy függvénye, és azt kiírja a console ablakra.
	 * @param t MovableThing[] típusú, végig nézi ebben a tömbben, hogy benne van-e a caller.
	 * @param calledType String típusú, megadja a meghívott függvény visszatérési értékét, plusz a meghívott objektum nevét (Pl:w, c).
	 * @param called MovableThing típusú, ennek az objektumnak hívódott meg valamely függvénye.
	 * @param function String típusú, a meghívott függvényt adja meg.
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
	 * Végig nézik a MovableThing[] tömböket.
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
	 * MovableThing hívott meg egy Field függvényét.
	 */
	public static void ThingCallField(String callerType, MovableThing caller,  String calledType, Field called, String function) {
		ThingCallerFinder(callerType, caller);
		FieldCalledFinder(calledType, called, function);		
		++tabs;
	}
	
	/*
	 * Field hívott meg egy MovableThing függvényét.
	 */
	public static void FieldCallThing(String callerType, Field caller,  String calledType, MovableThing called, String function) {
		FieldCallerFinder(callerType, caller);
		ThingCalledFinder(calledType, called, function);		
		++tabs;
	}
	
	/*
	 * MovableThing hívott meg egy MovableThing függvényét.
	 */
	public static void ThingCallThing(String callerType, MovableThing caller,  String calledType, MovableThing called, String function) {
		ThingCallerFinder(callerType, caller);
		ThingCalledFinder(calledType, called, function);		
		++tabs;
	}
	
	/*
	 * Egy MovableThing függvénye visszatér.
	 */
	public static void ThingReturn(String calledType, MovableThing called, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		ThingCalledFinder(calledType, called, function);
	}
	
	/**
	 * Megkeresi, hogy melyik Warehouse hívott meg egy függvényt, és azt kiírja a console ablakra.
	 * @param callerType String típusú, a függvényhívás típusát adja meg. Lehet CALL:, vagy CREATE:
	 * @param caller Warehouse típusú, ez az objektum hívott meg egy függvényt.
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
	 * Megkeresi, hogy melyik Warehouse-nak hívódott meg egy függvénye, és azt kiírja a console ablakra.
	 * @param calledType String típusú, megadja a meghívott függvény visszatérési értékét, plusz a meghívott objektum nevét (Pl:wh).
	 * @param called Warehouse típusú, ennek az objektumnak hívodott meg egy függvénye.
	 * @param function String típusú, a meghívott függvényt adja meg. 
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
	 * MovableThing egy Warehouse függvényét hívta meg.
	 */
	public static void ThingCallWarehouse(String callerType, MovableThing caller, String calledType, Warehouse called, String function) {
		ThingCallerFinder(callerType, caller);
		WarehouseCalled(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Warehouse egy MovableThing függvényét hívta meg.
	 */
	public static void WarehouseCallThing(String callerType, Warehouse caller, String calledType, MovableThing called, String function) {
		WarehouseCaller(callerType, caller);
		ThingCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Field egy Warehouse függvényét hívta meg.
	 */
	public static void FieldCallWarehouse(String callerType, Field caller, String calledType, Warehouse called, String function) {
		FieldCallerFinder(callerType, caller);
		WarehouseCalled(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Warehouse egy Field függvényét hívta meg.
	 */
	public static void WarehouseCallField(String callerType, Warehouse caller, String calledType, Field called, String function) {
		WarehouseCaller(callerType, caller);
		FieldCalledFinder(calledType, called, function);
		++tabs;
	}
	
	/*
	 * Warehouse valamely függvénye visszatér.
	 */
	public static void WarehouseReturn(String calledType, Warehouse called, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		WarehouseCalled(calledType, called, function);
	}
	
	/*
	 * Game hívja meg egy MovableThing függvényét.
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
	 * Field hívja meg a Game valamely függvényét.
	 */
	public static void FieldCallGame(String callerType, Field caller, String calledType, String function) {
		FieldCallerFinder(callerType, caller);
		System.out.print(calledType + "Game" + function + "\n");
		++tabs;
	}
	
	/*
	 * Game valamely függvénye visszatér.
	 */
	public static void GameReturn(String calledType, String function) {
		--tabs;
		for(int j = 0; j < tabs; ++j) {
			System.out.print("\t");
		}
		System.out.print(calledType + "Game" + function + "\n");
	}
	
	/*
	 * Create hívásoknál nem változnak a tabulátorok, ezek ezért lettek külön véve.
	 * Warehouse létrehoz egy Field-et.
	 */
	public static void WarehouseCreateField(String callerType, Warehouse caller, String calledType, Field called, String function) {
		WarehouseCaller(callerType, caller);
		FieldCalledFinder(calledType, called, function);
	}
	
	/*
	 * Warehouse létrehoz egy MovableThing-et.
	 */
	public static void WarehouseCreateThing(String callerType, Warehouse caller, String calledType, MovableThing called, String function) {
		WarehouseCaller(callerType, caller);
		ThingCalledFinder(calledType, called, function);
	}
	
	/*
	 * Game létrehoz egy Warehouse-t.
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
	 * Hozzáadások...
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
	 * Minden kinullázása.
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
