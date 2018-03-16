package sokoban;

import java.util.Scanner;

public final class Game {
	private static int round = 0;
	private static Warehouse map;
	private static Worker[] workers;
		
	/**
	 * L�trehoz egy �j rakt�r�p�letet �s gener�l hozz� egy p�ly�t.
	 */
	public static void CreateMap() {
		map = new Warehouse();
		Printer.AddWarehouse(map);
		Printer.GameCreateWarehouse("CREATE: ", "Warehouse " + map.GetName(), map, "()");
	}
	
	/**
	 * A param�ter�l kapott munk�st elt�rolja a workers t�mbben.
	 * @param w Worker t�pus�, ez az objektum ker�l a t�mbbe.
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
	 * Egy pontot ad az aktu�lisan k�r�n l�v� j�t�kosnak.
	 */
	public static void AddPointToWorker() {
		Printer.GameCallThing("CALL: ", "void " + workers[round].GetName(), workers[round], ".AddPoint()");
		workers[round].AddPoint();
		Printer.GameReturn("RETURN: ", ".AddPointToWorker()");
	}
	
	/**
	 * Az adott eset szimul�l�s�nak kiv�laszt�sa.
	 * @param input int t�pus�, a f�men�b�l v�lasztott opci�t adja meg.
	 * @param choice String t�pus�, az almen�b�l v�lasztott opci�t adja meg.
	 * @param choice2H String t�pus�, a 2: H v�laszt�sn�l d�nt, hogy az A, vagy a B r�sz fusson-e le.
	 */
	private static void SwitchCase(int input, String choice, String choice2H) {
		switch(input) {
		//A munk�s l�p
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
		//A munk�s l�d�t tartalmaz� mez�re l�p
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
	 * Men� megjelen�t�se, plusz az inputok bek�r�se.
	 */
	public static void Menu() {
		System.out.println("Input '1': �j j�t�k");
		Scanner Cin = new Scanner(System.in);
		int input;
		String choice;
		String choice2H = null; //Nincs r� mindig sz�ks�g.
		input = Cin.nextInt();
		if(input == 1) {
			while(input != 0) {
				System.out.println("\tInput '0': Kil�p�s");
				
				System.out.println("\tInput '1': A munk�s l�p");
					System.out.println("\t\tInput 'A': A munk�s �res mez�re l�p");
					System.out.println("\t\tInput 'B': A munk�s kapcsol�ra l�p");
					System.out.println("\t\tInput 'C': A munk�s c�lmez�re l�p");
					System.out.println("\t\tInput 'D': A munk�s oszlopra pr�b�l l�pni");
					System.out.println("\t\tInput 'E': A munk�s lyukra l�p");
					System.out.println("\t\tInput 'F': A munk�s m�sik munk�shoz l�p");
				
				System.out.println("\tInput '2': A munk�s l�d�t tartalmaz� mez�re l�p");
					System.out.println("\t\tInput 'A': L�da kapcsol�ra ker�l");
					System.out.println("\t\tInput 'B': L�da c�lmez�re ker�l");
					System.out.println("\t\tInput 'C': L�da oszlopnak �tk�zik");
					System.out.println("\t\tInput 'D': L�da lyukra ker�l");
					System.out.println("\t\tInput 'E': Egy munk�s k�t l�d�t tol");
					System.out.println("\t\tInput 'F': A l�da egy m�sik munk�st tol");
					System.out.println("\t\tInput 'G': A l�da leker�l a kapcsol�r�l");
					System.out.println("\t\tInput 'H': A l�da m�sik munk�st tol");
						System.out.println("\t\t\tInput 'A': A tolt munk�s oszlopnak megy");
						System.out.println("\t\t\tInput 'B': A tolt munk�s nem mozdul� l�d�nak megy");
				input = Cin.nextInt(); //0, 1, vagy 2
				//Ha nem akarunk kil�pni
				if(input != 0) {
					if(input == 1) System.out.println("V�lssz az 1. men�pont A-F opci�i k�z�l!");
					if(input == 2) System.out.println("V�lssz a 2. men�pont A-H opci�i k�z�l!");
					Cin = new Scanner(System.in);
					choice = Cin.nextLine(); //A - H
					if(choice.equals("H")) {
						System.out.println("V�lssz az A �s B opci�k k�z�l!");
						choice2H = Cin.nextLine();
					}
					CreateMap();
					SwitchCase(input, choice, choice2H);
					//Jobban n�zzen ki...
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
