import java.awt.Color;
import java.awt.Graphics;

public class Board {
	
	
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	Cell [][] cells;
	int currentRow, currentCol;
	
	public Board() {
		
		cells = new Cell[GameMain.ROWS][GameMain.COLS];
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
		
	}
	
	public boolean isDraw() {
	      for (int row = 0; row < GameMain.ROWS; ++row) {
	         for (int col = 0; col < GameMain.COLS; ++col) {
	            if (cells[row][col].content == Player.Empty) {
	               return false; // an empty seed found, not a draw, exit
	            }
	         }
	      }
	      return true; // no empty cell, it's a draw
	   }
	public boolean hasWon(Player thePlayer) {
	      return (cells[currentRow][0].content == thePlayer         // 3-in-the-row
	                   && cells[currentRow][1].content == thePlayer
	                   && cells[currentRow][2].content == thePlayer
	              || cells[0][currentCol].content == thePlayer      // 3-in-the-column
	                   && cells[1][currentCol].content == thePlayer
	                   && cells[2][currentCol].content == thePlayer
	              || currentRow == currentCol            // 3-in-the-diagonal
	                   && cells[0][0].content == thePlayer
	                   && cells[1][1].content == thePlayer
	                   && cells[2][2].content == thePlayer
	              || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
	                   && cells[0][2].content == thePlayer
	                   && cells[1][1].content == thePlayer
	                   && cells[2][0].content == thePlayer);
	   }
	
	 
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
				if (col < GameMain.COLS - 1) System.out.print("|");
			}
			System.out.println();
	         if (row < GameMain.ROWS - 1) {
	            System.out.println("-----------");
	         }
		}
	}
	
		// TODO Auto-generated method stub
		
	}


