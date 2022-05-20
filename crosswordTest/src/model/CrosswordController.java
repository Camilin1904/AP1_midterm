package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {
		crossword = new Cell[puzzle.length][puzzle[0].length];
		int number = 1;
		for (int counter=0; counter<puzzle.length; counter++){
			for (int counter2=0; counter2<puzzle[0].length; counter2++){
				if (puzzle[counter][counter2]==" "){
					crossword[counter][counter2] = new Cell(CellType.BLACK, puzzle[counter][counter2], -1);
				}
				else{
					crossword[counter][counter2] = new Cell(CellType.CLOSED, puzzle[counter][counter2], number);
					number++;
				}
			}
		}
		
	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {
		Cell wasFound = null;
		String message = "";
		for (int counter=0; counter<crossword.length&&wasFound==null; counter++){
			for (int counter2=0; counter2<crossword[0].length&&wasFound==null; counter2++){
				if(crossword[counter][counter2].getLetter().equals(letter)&&crossword[counter][counter2].getState().equals(CellType.CLOSED)){
					crossword[counter][counter2].setState(CellType.OPEN);
					wasFound = crossword[counter][counter2];
				}
			}
		}
		if (wasFound!=null){
			message = "There is a letter " + wasFound.getLetter() + ", in the square: " + wasFound.getNumber();
		}
		else{
			message = "there is no letter " + letter;
		}
		return message;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		Cell wasFound = null;
		boolean exists = false;
		String message = "";
		for (int counter=0; counter<crossword.length&&wasFound==null; counter++){
			for (int counter2=0; counter2<crossword[0].length&&wasFound==null; counter2++){
				if(crossword[counter][counter2].getNumber()==num){
					if(crossword[counter][counter2].getLetter().equals(letter)&&crossword[counter][counter2].getState().equals(CellType.CLOSED)){
						crossword[counter][counter2].setState(CellType.OPEN);
						wasFound = crossword[counter][counter2];
						exists = true;
					}
				}
			}
		}
		if(exists){
			if (wasFound!=null){
				message = "The letter " + wasFound.getLetter() + " was in the square " + wasFound.getNumber();
			}
			else{
				message = "The letter " + letter + " was not in the square " + num;
			}
		}
		else{
			message = "The square " + num + " does not exists";
		}
		return message;
	}
	/**
	 * Shows the game to with the closed spaces not shown
	 * @return
	 */
	public String showCrossword2() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}
					else if (actual.getState()==CellType.CLOSED){
						numbers += " "+actual.getNumber() +"   ";
						letters += "      ";
					}
					else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}
					else if (actual.getState()==CellType.CLOSED){
						numbers += " "+actual.getNumber() +"    ";
						letters += "      ";
					}
					else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}
	/**
	 * shows every space, meant for testing
	 * @return
	 */
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}
					else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}
					else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
