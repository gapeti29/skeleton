# Skeleton

Printer osztály működése:
Az osztály minden létrejövő objektumot eltárol, tömbbökben. A program futása során, mielőtt meghívódna egy függvény, azelőtt meghívja a számára kompatibilis függvényét a Printer-nek, mely kiírja a console ablakra, hogy melyik objektum, melyik objektumnak, milyen függvényét hívja meg. Ez alól kivétel az új objektum létrehozása, az előbb történik, mint a kiírása.

Printer osztály függvényei:
Mindegyik függvénye statikus.
A különböző fajta objektumok különböző kiírató függvényt tudnak meghívni. A Field-ek a FieldXXX() függvényeket, a MovableThing-ek a MovableThingXXX() függvényeket, stb.
Példa erre:
  public static void ThingCallField(String callerType, MovableThing caller,  String calledType, Field called, String function);
  
  A fenti függvényt egy MovableThing tudja meghívni, ha egy Field valamely függvényét hívná meg a játékban. A függvény a következő paramétereket várja:
  String callerType: A hívás típusát adja meg, plusz a hívó nevét.
  MovableThing caller: Ez az objektum hív meg egy függvényt.
  String calledType: A hívott függvény visszatérési értékét, valamint a hívott objektum nevét tartalmazza.
  Field called: Ennek a mezőnek hívják meg egy függvényét.
  String function: A meghívott függvényt tartalmazza, szövegesen.
  
  Használatára egy konkrét példa:  //MovableThing, Move() függvényéből
  Printer.ThingCallField("CALL: " + name, this, "boolean " + temp.GetName(), temp, ".Accept(this, d)");
  
  A kiírásnál az objektumok még kapnak egy számot is, a Printer osztályon belül tömbbeli sorszámukat kapják meg. Így a console ablakon végül ez jelenik meg:  
  CALL: c0: boolean f2.Accept(this, d)
  
Skeleton működése:
A Game osztály hozza létre a menüt és olvassa be az inputokat, ezután meghívja a Warehouse-nak azt a függvényét, ami kiválasztásra került. Minden szimuláció kezdetekor teljesen új objektumok keletkeznek, melyek a szimuláció lefutása után törlődnek.

Megjegyzés:
A forrásfájlok közé feltöltöttem a Wall-t is, de az egyáltalán nem lett használva, úgyhogy lehet azt nem kéne leadni.
