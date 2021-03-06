package HundirFlota;

public class Player {
	private String Nombre;
	private int turno;
	private Board tablero;
	private String [][] tableroInfo;

	
	public Player() {
		this.iniciarTableroInfo();
		this.tablero=new Board();

	}
	public Board getTablero() {
		return this.tablero;
	}
	public void setNombre(String nombre) {
		this.Nombre=nombre;
	}
	public String getNombre() {
		return this.Nombre;
	}
	public void setTurno(int turno) {
		this.turno=turno;
	}
	public int getTurno() {
		return this.turno;
	}
	public String [][] getTableroInfo() {
		return this.tableroInfo;
	}
	
	public void iniciarTableroInfo() {
		tableroInfo=new String[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				tableroInfo[i][j]="[-]";
			}
		}
	}

	
	public String mostrarTableroInfo() {
		String tablero="\n     0:  1:  2:  3:  4:  5:  6:  7:  8:  9:\n\n";

		for(int i=0;i<10;i++) {
			tablero=tablero+i+":  ";
			for(int j=0; j<10;j++) {
				tablero = tablero+tableroInfo[i][j]+" ";
			}
			tablero=tablero+"\n";
		}
		return tablero;
	}
	
	public boolean disparar(Board tablero,int fila,int columna) {
		boolean hit=false;

		if(fila>tablero.getFilas() || columna>tablero.getColumnas() || fila<0 || columna<0){
			hit=false;
			System.out.println("WRONG COORDS: "+fila+" "+columna);
			
		}else if(this.tableroInfo[fila][columna]=="[X]") {
			System.out.println("MISSED SHOOT, TAKE CARE BECAUSE YOU ALREADY SHOT THIS POSTION ON A PREVIOUS TURN");
			return false;
		}
		else if(tablero.getPosicion(fila, columna) == 1) {
			hit=true;	
			System.out.println("HIT SHOOT on: "+fila+" "+columna);
			this.tableroInfo[fila][columna]="[X]";
			tablero.getBarco(fila, columna).ifHit(fila, columna);
			if(tablero.getBarco(fila, columna).isSunk()) {
				System.out.println("YOU SUNK ONE "+tablero.getBarco(fila, columna).getTypeOfBoat());
			}
			
		}else {
			hit=false;
			System.out.println("MISSED SHOOT on "+fila+" "+columna);
			this.tableroInfo[fila][columna]="[O]";
		}
		return hit;
	}

}
