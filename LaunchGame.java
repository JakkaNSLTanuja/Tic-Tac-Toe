import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	static char[][] board;
	
	public TicTacToe() {
		board = new char[3][3];
		initBoard();
	}
	
	void initBoard(){
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	static void displayBoard() {
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0; j<board[i].length;j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	static void placeMark(int row, int column, char mark) {
		if(row >= 0 && row <= 2 && column >= 0 && column <= 2) {
			board[row][column] = mark;
		}
		else {
			System.out.println("invalid position");
		}
	}
	
	static boolean checkColWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}
	
	static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDiagWin() {
		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}
	
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}

abstract class ParentPlayer{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValidMove(int row, int column) {
		if(row >= 0 && row <= 2 && column >= 0 && column <= 2) {
			if(TicTacToe.board[row][column] == ' ') {
				return true;
			}
		}
		return false;
	}
}

class Player extends ParentPlayer{
	
	Player(String name, char mark){
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove() {
		
		Scanner scan = new Scanner(System.in);
		
		int row,column;
		do {
			System.out.println("Enter the row and column");
			row = scan.nextInt();
			column = scan.nextInt();
		}while(!isValidMove(row,column));
		
		TicTacToe.placeMark(row, column, mark);
	}
	
}

class Computer extends ParentPlayer{
	
	Computer(String name, char mark){
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove() {
		
		Scanner scan = new Scanner(System.in);
		
		int row,column;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			column = r.nextInt(3);
		}while(!isValidMove(row,column));
		
		TicTacToe.placeMark(row, column, mark);
	}
	
}


public class LaunchGame {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		TicTacToe t = new TicTacToe();
		
		System.out.println("Choose options 1 or 2, option1 is for 2 players and option2 is for 1 player & computer");
		int choose = scanner.nextInt();
		scanner.nextLine();
		
		if(choose == 1) {
			System.out.println("Enter player1 Name: ");
			String Player1 = scanner.nextLine();
			System.out.println("Enter Player2 Name: ");
			String Player2 = scanner.nextLine();
			Player p1 = new Player("Player1" , 'X');
			Player p2 = new Player("Player2" , 'O');
			
			ParentPlayer cp;
			cp = p1;
			
			while(true) {
				System.out.println(cp.name + " turn");
				cp.makeMove();
				TicTacToe.displayBoard();
				if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
					System.out.println(cp.name + " has won");
					break;
				}
				else if(TicTacToe.checkDraw()) {
					System.out.println("Game is a Draw");
					break;
				}
				else {
					if(cp == p1) {
						cp = p2;
					}
					else {
						cp = p1;
					}
				}
			}
		}
		else if(choose == 2){
			System.out.println("Enter the Player's Name: ");
			String Player1 = scanner.nextLine();
			Player p1 = new Player("Player1" , 'X');
			Computer p2 = new Computer("AI" , 'O');
			
			ParentPlayer cp;
			cp = p1;
			
			while(true) {
				System.out.println(cp.name + " turn");
				cp.makeMove();
				TicTacToe.displayBoard();
				if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
					System.out.println(cp.name + " has won");
					break;
				}
				else if(TicTacToe.checkDraw()) {
					System.out.println("Game is a Draw");
					break;
				}
				else {
					if(cp == p1) {
						cp = p2;
					}
					else {
						cp = p1;
					}
				}
			}
		}
		else {
			System.out.println("Invalid mode of Selection");
		}
	}

}
