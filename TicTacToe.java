import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;



public class TicTacToe extends JPanel{

	JButton[] gameButton; //9 buttons for game
	JButton restart;
	JPanel board;
	public int turns = 1;
	//public int no =0;
	
	char[][]  game = new char[3][3]; //2d array for keep game board
	boolean cross = false;  //cross win
	boolean nought = false;   //noughts win
	boolean isNewGame = true;   //new game

 public TicTacToe() {
 	 super(new BorderLayout());
 	 	gameButton = new JButton[9];   
 	
 		JPanel board = new JPanel();

 		board.setLayout(new GridLayout(3, 3));  //set board panel lay out to grid with 9 cells

        for (int i = 0; i < 9; i++) {
            gameButton[i] = new JButton();   //declare new game buttons
            gameButton[i].setPreferredSize(new Dimension(100, 100));
            gameButton[i].setBackground(Color.WHITE);
            board.add(gameButton[i]);
            //no = i;
            gameButton[i].addActionListener(new ClickListener(i));  //call action listner with click listner, give button no as event id

}
 
		restart = new JButton("RESTART GAME");    // restart button
		restart.addActionListener(new ClickListener(-1));  //call action listner , and give -1 as event id
		JPanel newGame = new JPanel();
		newGame.setPreferredSize(new Dimension(300, 50));
		newGame.add(restart, BorderLayout.CENTER);

		add(board, BorderLayout.CENTER);   //add both board panel and newgame panel
		add(newGame ,BorderLayout.SOUTH);


    }


    class ClickListener extends JButton implements ActionListener {

    int eventId; 
    public ClickListener(int eventId) {   //set eventid
	this.eventId = eventId; 
    }

     public void actionPerformed(ActionEvent e) { 
 if(eventId>=0){    //if event id is positive , it is a game button
 	if(isNewGame){   //if it is newGame set turn number as 1
 		turns=1;
 		isNewGame=false;  //and newGame is false
 	}

     	
     	
     	
                        if(turns < 10)  {  //for 9 turns
                        	String letter = ((JButton)e.getSource()).getText();
                            if (!(turns % 2 == 0)){   //if turn no is a even no

                            if(!(letter.equals("O")) && !(letter.equals("X")) ){  //and if button text is not 'O' or 'X'  (to ignor selected buttons)
                                ((JButton)e.getSource()).setText("X");   //set button text to 'X'
                                ((JButton)e.getSource()).setFont(new Font("Arial", Font.PLAIN, 55));
                                ((JButton)e.getSource()).setForeground(Color.RED);
                                
                                 
                                 game[eventId/3][eventId%3] = 'X';  //put 'X' to 2d game array,  (if it is button no 4, 'X' goes to game[1][1])
                                 hasWon(game);  //check is there a winner
                                
                                turns++; 
                                if(!isNewGame) //if its is not a new game, call cpu move
                                 cpuMove();    
                            }}
                            
                             
                        
                        
                        }

               
}
if (eventId==-1) {   //if eventId is negative it is restart button
	resetGame();
	
}

     }

}


public void cpuMove(){

int[] preferredMove = {4,0,2,6,8,1,3,5,7}; //most preferred moves (button numbers)
String letter;
for(int no=0; no<9; no++){
letter = gameButton[preferredMove[no]].getText();  //get the text in most preferred button

if(!(letter.equals("O")) && !(letter.equals("X")) ){  //if it is free button
                                gameButton[preferredMove[no]].setText("O");  //set it is as O
                                gameButton[preferredMove[no]].setFont(new Font("Arial", Font.PLAIN, 55));
                                gameButton[preferredMove[no]].setForeground(Color.BLUE);
                                
                                 game[preferredMove[no]/3][preferredMove[no]%3] = 'O';  //put 'O' to 2d game array,  (if it is button no 4, 'O' goes to game[1][1])
                                 hasWon(game);
                                 
                                turns++;
                                break; //break the loop
                            }

}

}

public void hasWon(char[][] array){
int x,y;
int count=0;
for(x=0;x<3;x++){
	for(y=0;y<3;y++){
		if(array[x][y]=='X') continue;   //check possibilities to win
		else break; 
	}                                    
	if(y==3)
		cross = true;
}
//----------------------------------------

for(x=0;x<3;x++){
	for(y=0;y<3;y++){
		if(array[x][y]=='O') continue; 
		else break; 
	}
	if(y==3)
		nought = true;
}
//------------------------------------------
for(y=0;y<3;y++){
	for(x=0;x<3;x++){
		if(array[x][y]=='X') continue; 
		else break; 
	}
	if(x==3)
		cross = true;
}
//-------------------------------------------
for(y=0;y<3;y++){
	for(x=0;x<3;x++){
		if(array[x][y]=='O') continue; 
		else break; 
	}
	if(x==3)
		nought = true;
}
//---------------------------------------------
count=0;
	for(x=0;x<3;x++) {
		
		for(y=x;y<=x;y++) {         //this check diagonal lef upper to reight bottom
			
			if(array[x][y]=='X')
			count=count+1; //if checking elements are X's , then count increase by 1
				
		else break;
		}
		}
	if(count==3) cross = true;
//-----------------------------------------------
count=0;
	for(x=0;x<3;x++) {
		
		for(y=(3-1-x);y>=(3-1-x);y--) {  //this check diagonal right upper to left bottom
			
			if(array[x][y]=='X') count=count+1;   //if checking elements are X's , then count increase by 1
				
		else break;
		}
		}
	if(count==3) cross = true;
//-------------------------
	count=0;
	for(x=0;x<3;x++) {
		
		for(y=x;y<=x;y++) {         //this check diagonal lef upper to reight bottom
			
			if(array[x][y]=='O')
			count=count+1; //if checking elements are X's , then count increase by 1
				
		else break;
		}
		}
	if(count==3) nought = true;

	count=0;
	for(x=0;x<3;x++) {
		
		for(y=(3-1-x);y>=(3-1-x);y--) {  //this check diagonal right upper to left bottom
			
			if(array[x][y]=='O') count=count+1;   //if checking elements are X's , then count increase by 1
				
		else break;
		}
		}
	if(count==3) nought = true;


if(cross || nought){    //if there is a winner call winner method to print winner, and reset game
	winner(cross,nought);
	resetGame();

}
else if(!(cross || nought) && turns==9){  //if there is no winner

	JOptionPane.showMessageDialog(null, "There is no winner! Game is drawn.","Winner", JOptionPane.PLAIN_MESSAGE);  //print message
	resetGame();
}
}

//------------------------------------------------------------------------

public void winner(boolean cross,boolean nought){
	
	if(cross && !nought)  //if cross wins
	JOptionPane.showMessageDialog(null, "Cross won the game!","Winner", JOptionPane.PLAIN_MESSAGE);
   
else if(!cross && nought)  //if noughts win
	JOptionPane.showMessageDialog(null, "Nought won the game!","Winner", JOptionPane.PLAIN_MESSAGE);
   
}


//-------------------------------------------------------------------------
public void resetGame(){
	cross = false;     //reset game
	nought = false;
	isNewGame = true;  

	for(int i=0 ;i<9;i++){

		gameButton[i].setText("");   //set all text in game button to no text
		
	}

	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++)    //set game array to ' '
			game[i][j] = ' ';

}

//-------------------------------------------------------------------------
public static void main(String[] args){
	
	JFrame frame = new JFrame("TicTacToe");  //create new jframe
    	
  		frame.setSize(400, 400);       
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  		
  		JComponent newContentPane = new TicTacToe();  
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        frame.setVisible(true);

}
}