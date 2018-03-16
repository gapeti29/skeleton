package sokoban;

import java.util.Scanner;

public final class Game {
	private static int round = 0;
	private static Warehouse map;
	private static Worker[] workers;
		
	/**
	 * Létrehoz egy új raktárépületet és generál hozzá egy pályát.
	 */
	public static void CreateMap() {
		map = new Warehouse();
		Printer.AddWarehouse(map);
		Printer.GameCreateWarehouse("CREATE: ", "Warehouse " + map.GetName(), map, "()");
	}
	
	/**
	 * A paraméterül kapott munkást eltárolja a workers tömbben.
	 * @param w Worker típusú, ez az objektum kerül a tömbbe.
	 */
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
	
	/**
	 * Egy pontot ad az aktuálisan körön lévõ játékosnak.
	 */
	public static void AddPointToWorker() {
		Printer.GameCallThing("CALL: ", "void " + workers[round].GetName(), workers[round], ".AddPoint()");
		workers[round].AddPoint();
		Printer.GameReturn("RETURN: ", ".AddPointToWorker()");
	}
	
	/**
	 * Az adott eset szimulálásának kiválasztása.
	 * @param input int típusú, a fõmenübõl választott opciót adja meg.
	 * @param choice String típusú, az almenübõl választott opciót adja meg.
	 * @param choice2H String típusú, a 2: H választásnál dönt, hogy az A, vagy a B rész fusson-e le.
	 */
	private static void SwitchCase(int input, String choice, String choice2H) {
		switch(input) {
		//A munkás lép
		case 1:
			switch(choice) {
			case "A":
				map.OneA();
				break;
			case "B":
				map.OneB();
				break;
			case "C":
				map.OneC();
				break;
			case "D":
				map.OneD();
				break;
			case "E":
				map.OneE();
				break;
			case "F":
				map.OneF();
				break;
			default:
				System.out.println("Nincs ilyen eset!");
				break;
			}
			break;
		//A munkás ládát tartalmazó mezõre lép
		case 2:
			switch(choice) {
			case "A":
				map.TwoA();
				break;
			case "B":
				map.TwoB();
				break;
			case "C":
				map.TwoC();
				break;
			case "D":
				map.TwoD();
				break;
			case "E":
				map.TwoE();
				break;
			case "F":
				map.TwoF();
				break;
			case "G":
				map.TwoG();
				break;
			case "H":
				switch(choice2H) {
				case "A":
					map.TwoHA();
					break;
				case "B":
					map.TwoHB();
					break;
				default:
					System.out.println("Nincs ilyen eset!");
					break;
				}
			}	
			break;
		}
	}
	
	/**
	 * Menü megjelenítése, plusz az inputok bekérése.
	 */
	public static void Menu() {
		System.out.println("Input '1': Új játék");
		Scanner Cin = new Scanner(System.in);
		int input;
		String choice;
		String choice2H = null; //Nincs rá mindig szükség.
		input = Cin.nextInt();
		if(input == 1) {
			while(input != 0) {
				System.out.println("\tInput '0': Kilépés");
				
				System.out.println("\tInput '1': A munkás lép");
					System.out.println("\t\tInput 'A': A munkás üres mezõre lép");
					System.out.println("\t\tInput 'B': A munkás kapcsolóra lép");
					System.out.println("\t\tInput 'C': A munkás célmezõre lép");
					System.out.println("\t\tInput 'D': A munkás oszlopra próbál lépni");
					System.out.println("\t\tInput 'E': A munkás lyukra lép");
					System.out.println("\t\tInput 'F': A munkás másik munkáshoz lép");
				
				System.out.println("\tInput '2': A munkás ládát tartalmazó mezõre lép");
					System.out.println("\t\tInput 'A': Láda kapcsolóra kerül");
					System.out.println("\t\tInput 'B': Láda célmezõre kerül");
					System.out.println("\t\tInput 'C': Láda oszlopnak ütközik");
					System.out.println("\t\tInput 'D': Láda lyukra kerül");
					System.out.println("\t\tInput 'E': Egy munkás két ládát tol");
					System.out.println("\t\tInput 'F': A láda egy másik munkást tol");
					System.out.println("\t\tInput 'G': A láda lekerül a kapcsolóról");
					System.out.println("\t\tInput 'H': A láda másik munkást tol");
						System.out.println("\t\t\tInput 'A': A tolt munkás oszlopnak megy");
						System.out.println("\t\t\tInput 'B': A tolt munkás nem mozduló ládának megy");
				input = Cin.nextInt(); //0, 1, vagy 2
				//Ha nem akarunk kilépni
				if(input != 0) {
					if(input == 1) System.out.println("Válssz az 1. menüpont A-F opciói közül!");
					if(input == 2) System.out.println("Válssz a 2. menüpont A-H opciói közül!");
					Cin = new Scanner(System.in);
					choice = Cin.nextLine(); //A - H
					if(choice.equals("H")) {
						System.out.println("Válssz az A és B opciók közül!");
						choice2H = Cin.nextLine();
					}
					CreateMap();
					SwitchCase(input, choice, choice2H);
					//Jobban nézzen ki...
					System.out.println("\n\n");
				}
			}
		}
		Cin.close();
	}
	
	public static void main(String[] args) {
		Menu();	
	}
}
