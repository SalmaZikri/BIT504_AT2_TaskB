import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMain extends JPanel implements ActionListener, MouseListener{
	 
	
	public static final int ROWS = 3;     
	public static final int COLS = 3;  
	public static final String TITLE = "Tic Tac Toe";
	public static final int CELL_SIZE = 100;
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	public static final int CELL_PADDING = CELL_SIZE / 6;    
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;    
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	private Board board;
	private GameState currentState;
	private Player currentPlayer; 
	private JLabel statusBar;
	
	
	public GameMain() {
		
		this.addMouseListener(this);
		
		
		  statusBar = new JLabel("         ");
	      statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
	      statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
	      statusBar.setOpaque(true);
	      statusBar.setBackground(Color.LIGHT_GRAY);
	      
	      setLayout(new BorderLayout());
	      add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
	      setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));
	            // account for statusBar in height
	 
	      board = new Board();   // allocate the game-board
	      initGame();  // Initialize the game variables
}
	public static void main(String[] args) {
		//new GameMain();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	           public void run() {
	        	   
	        	   JFrame frame = new JFrame(TITLE);
	               // Set the content-pane of the JFrame to an instance of main JPanel
	        	   frame.add(new GameMain());
	               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	               frame.pack();
	               frame.setLocationRelativeTo(null); // center the application window
	               frame.setVisible(true);
	           }
	         });
	     }
	
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			setBackground(Color.gray);
			
			board.paint(g);
			
			if (currentState == GameState.Playing) {          
				statusBar.setForeground(Color.BLACK);          
				if (currentPlayer == Player.Cross) {   
					statusBar.setText("X's Turn");
				} else {    
					 statusBar.setText("O's Turn");
				}       
				} else if (currentState == GameState.Draw) {          
					statusBar.setForeground(Color.RED);          
					statusBar.setText("It's a Draw! Click to play again.");       
				} else if (currentState == GameState.Cross_won) {          
					statusBar.setForeground(Color.RED);          
					statusBar.setText("'X' Won! Click to play again.");       
				} else if (currentState == GameState.Nought_won) {          
					statusBar.setForeground(Color.RED);          
					statusBar.setText("'O' Won! Click to play again.");       
				}
			}
		public void initGame() {
			for (int row = 0; row < ROWS; ++row) {          
				for (int col = 0; col < COLS; ++col) {  
					// empty cells
					board.cells[row][col].content = Player.Empty;           
				}
			}
			 currentState = GameState.Playing;
			 currentPlayer = Player.Cross;
		}
		public void updateGame(Player thePlayer, int row, int col) {
			//check for win after play
			if(board.hasWon(thePlayer, row, col)) {
				//currentState = (thePlayer == Player.Cross) ? GameState.Cross_won : GameState.Nought_won;
				// TODO: check which player has won and update the currentstate to the appropriate gamestate for the winner
				//FOR ALL ??? REFER TO GameState enumerators
				if(thePlayer == Player.Cross)
					currentState = GameState.Cross_won;
				else
					currentState = GameState.Nought_won;
				} else 
				if (board.isDraw ()) {
					currentState = GameState.Draw;
					
					 
			}
			//otherwise no change to current state of playing
		}
		
		public void mouseClicked(MouseEvent e) {  
		    // get the coordinates of where the click event happened            
			int mouseX = e.getX();             
			int mouseY = e.getY();             
			// Get the row and column clicked             
			int rowSelected = mouseY / CELL_SIZE;             
			int colSelected = mouseX / CELL_SIZE;               			
			if (currentState == GameState.Playing) {                
				if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.Empty) {
					// move  
					board.cells[rowSelected][colSelected].content = currentPlayer; 
					// update currentState                  
					updateGame(currentPlayer, rowSelected, colSelected); 
					// Switch player
					if (currentPlayer == Player.Cross) {
						currentPlayer =  Player.Nought;
					}
					else {
						currentPlayer = Player.Cross;
					}
				}             
			} else {        
				// game over and restart              
				initGame();            
			}   
			repaint();
		}
		

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}


		
}
		
		
		
		
		



