import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	  int startX;
	  int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
    	pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
  	pieces = new JLabel( new ImageIcon("BlackPawn.png") );
		panels = (JPanel)chessBoard.getComponent(i);
      panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
  	private Boolean piecePresent(int x, int y){
  		Component c = chessBoard.findComponentAt(x, y);
  		if(c instanceof JPanel){
  			return false;
  		}
  		else{
  			return true;
  		}
  	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
  	private Boolean checkWhiteOponent(int newX, int newY){
  		Boolean oponent;
  		Component c1 = chessBoard.findComponentAt(newX, newY);
  		JLabel awaitingPiece = (JLabel)c1;
  		String tmp1 = awaitingPiece.getIcon().toString();
  		if(((tmp1.contains("Black")))){
  			oponent = true;
  		}
  		else{
  			oponent = false;
  		}
  		return oponent;
  	}

    private Boolean checkBlackOponent(int newX, int newY){
      Boolean oponent;
      Component c1 = chessBoard.findComponentAt(newX, newY);
      JLabel awaitingPiece = (JLabel)c1;
      String tmp1 = awaitingPiece.getIcon().toString();
      if(((tmp1.contains("White")))){
        oponent = true;
      }
      else{
        oponent = false;
      }
      return oponent;
    }

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			     return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		    initialX = e.getX();
		    initialY = e.getY();
        startX = (e.getX()/75);
        startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		    Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		    String tmp = chessPiece.getIcon().toString();
		    String pieceName = tmp.substring(0, (tmp.length()-4));
		    Boolean validMove = false;

        int landingX = (e.getX()/75);
        int landingY = (e.getY()/75);
        int xMovement = Math.abs((e.getX()/75)-startX);
        int yMovement = Math.abs((e.getY()/75)-startY);
        System.out.println("=============================================");
        System.out.println("The piece that is being moved is : "+pieceName);
        System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
        System.out.println("The xMovement is : "+xMovement);
        System.out.println("The yMovement is : "+yMovement);
        System.out.println("The landing coordinates are : "+"("+landingX+","+landingY+")");
        System.out.println("=============================================");




		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.

			So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/

        if(pieceName.contains("King")){
          /*
            the king can move in any direction as long as its being placed back onto the board and its only
            moving one square at a time....

            if(the king is being placed back onto the board){

            if(xMovement == 1)||(yMovement == 1).... and all other combinations of moving
            only one square at a time... essentially just like a restricted Queen

            we cant take our own piece and there must be one square between both kings after
            completing the move...
           }
          */
        }
        else if(pieceName.equals("WhiteQueen")){
          /*
          if(the queen is being placed back onto the board){
            if(the movement is like a bishop){

            test for a valid move...
              we have a valid bishop movement if the xMovement == yMovement
              if there is something in the way
              if there is a piece on the expected landing square, if so make sure that
              its an enemy piece...

            }
            else if(the movement is like the rook){

            test for a valid rook movement
              if there is an xMovement there cant be a yMovement
              if there is a yMovement there cant be an xMovement

              if one of the above conditions are true we simply make sure that there is
              nothing in the way and that we not take our own piece.

            }
          }

          */
          validMove = true;
        }
        else if(pieceName.equals("BlackQueen")){
          validMove = true;
        }
        else if(pieceName.contains("Knight")){

          /*
            We know knight can move in L direction. It means that if xMovement == 1 then yMovement
            must equal 2 and also the other way around

            We need to check the square that we are movig to and make sure that if there is a piece piecePresent
            that its not our own piece...
          */
          if(((xMovement == 1)&&(yMovement == 2))||((xMovement == 2)&&(yMovement == 1))){
            if(!piecePresent(e.getX(), e.getY())){
              validMove = true;
            }
            else{
              if(pieceName.contains("White")){
                if(checkWhiteOponent(e.getX(), e.getY())){
                  validMove = true;
                }
              }
              else{
                if(checkBlackOponent(e.getX(), e.getY())){
                  validMove = true;
                }
              }
            }
          }
        }
        //black pawn
        else if(pieceName.equals("BlackPawn")){
          /* The pawn can move either two or one squares */
          if(startY == 6){//first move
            /*
              the pawn is making its first move....
              the pawn can move one or two squares... in the y direction
              as long as we are moving up the board and also there is no movement in the x direction
              startY > landingY in english is just the piece moving up the board
            */
            if(((yMovement==1)||(yMovement == 2))&&(startY > landingY)&&(xMovement == 0)){
              if(yMovement == 2){
                if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()+75)))){
                  validMove = true;
                }
                else{
                  validMove = false;
                }
              }
              else{
                if(!piecePresent(e.getX(), e.getY())){
                validMove = true;
                }
                else{
                  validMove = false;
                }
              }
            }
            else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
              if(piecePresent(e.getX(), e.getY())){
                if(checkBlackOponent(e.getX(), e.getY())){
                  validMove = true;
                }
                else{
                  validMove = false;
                }
              }
            }
          }
            else{//all other moves
              if(((yMovement==1))&&(startY > landingY)&&(xMovement == 0)){
                if(!piecePresent(e.getX(), e.getY())){
                  validMove = true;
                  }
                }
                else{
                  validMove = false;
                }
              }
               if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
                if(piecePresent(e.getX(), e.getY())){
                  if(checkBlackOponent(e.getX(), e.getY())){
                    validMove = true;
                  }
                  else{
                    validMove = false;
                  }
                }
              }
          }
        //white pawn
    		else if(pieceName.equals("WhitePawn")){
          //Starting position
    			if(startY == 1)
    			{
            /*
            if start x is equal to its end column, and the new y has moved one or two squares we may have a valid move.
            Basically saying that if the first move was a valid move...
    				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
            */
              if(((yMovement==1)||(yMovement == 2))&&(startY < landingY)&&(xMovement == 0))
    				{
              //To check if the first move was one or two spaces
              //changing the / to -  allows the white pawn to move two spaces when there is a piece in front of it
    					if((((e.getY()-75)-startY)==2)){
                //checks if there is a piece on the square player is moving to, if yes = true, if false = no
    						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()-75)))){
    							validMove = true;
    						}
                else{
                  validMove = false;
                }
    					}
              /*
              This is where the code says if the move was not 2 spaces and was just one space, then the first move was made,
              and the pawn can only move one square at a time thereafter.
              */
    					else{
    						if((!piecePresent(e.getX(), (e.getY()))))
    						{
    							validMove = true;
    						}
    					}
    				}
    			}
    			else{
    				int newY = e.getY()/75;
    				int newX = e.getX()/75;
            /*this line makes it possible for pawn to take pieces across the board
    				if((startX-1 >=)||(startX +1 <=7))
            */
            if((startX-1 >=1)||(startX +1 <=1))
    				{
    					if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=1)))||((newX == (startX-1))&&(startX-1 >=1)))))
    					{
    						if(checkWhiteOponent(e.getX(), e.getY())){
    							validMove = true;
    							if(startY == 6){
    								success = true;
    							}
    						}
    						else{
    							validMove = false;
    						}
    					}
    					else{
    						if(!piecePresent(e.getX(), (e.getY()))){
    							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
    								if(startY == 6){
    									success = true;
    								}
    								validMove = true;
    							}
    							else{
    								validMove = false;
    							}
    						}
    						else{
    							validMove = false;
    						}
    					}
    				}
    				else{
    					validMove = false;
    				}
    			}
    		}
    		if(!validMove){
    			int location=0;
    			if(startY ==0){
    				location = startX;
    			}
    			else{
    				location  = (startY*8)+startX;
    			}
    			String pieceLocation = pieceName+".png";
    			pieces = new JLabel( new ImageIcon(pieceLocation) );
    			panels = (JPanel)chessBoard.getComponent(location);
    		  panels.add(pieces);
    		}
    		else{
    			if(success){
    				int location = 56 + (e.getX()/75);
    				if (c instanceof JLabel){
    	       	Container parent = c.getParent();
    	        parent.remove(0);
    					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
    					parent = (JPanel)chessBoard.getComponent(location);
    			    parent.add(pieces);
    				}
    				else{
    					Container parent = (Container)c;
    	        pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
    					parent = (JPanel)chessBoard.getComponent(location);
    			    parent.add(pieces);
    				}
    			}
    			else{
    				if (c instanceof JLabel){
    	            	Container parent = c.getParent();
    	            	parent.remove(0);
    	            	parent.add( chessPiece );
    	        	}
    	        	else {
    	            	Container parent = (Container)c;
    	            	parent.add( chessPiece );
    	        	}
    	    		chessPiece.setVisible(true);
    			}
    		}
        }

        public void mouseClicked(MouseEvent e) {

        }
        public void mouseMoved(MouseEvent e) {
       }
        public void mouseEntered(MouseEvent e){

        }
        public void mouseExited(MouseEvent e) {

        }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
