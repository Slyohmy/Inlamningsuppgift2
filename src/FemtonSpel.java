import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FemtonSpel extends JFrame {

    JButton[][] board = new JButton[4][4];
    int tile1;
    int tile2;

    public FemtonSpel(){

        Container container = getContentPane();
        container.setLayout(new GridLayout(4,4));
        buttonListener pushed = new buttonListener();

        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 4; j++) {

                JButton n = new JButton();
                board[i][j] = n;
                board[i][j].addActionListener(pushed);
                board[i][j].setBackground(Color.lightGray);
                container.add(board[i][j]);
            }
        }
    }

    public static void main(String[] args) {

        FemtonSpel game = new FemtonSpel();
        game.shuffle();
        game.setTitle("15-Spel");
        game.setSize(400,400);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void shuffle(){

        boolean[] shuffleValues = new boolean[16];
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                int val = (int)(16*Math.random());
                
                while(shuffleValues[val]){
                    val = (int)(16*Math.random());
                }
                shuffleValues[val] = true;

                if(val!=0)
                    board[i][j].setText(""+val);
                else {
                    board[i][j].setBackground(Color.darkGray);
                    tile1 = i; tile2 = j;
                }
            }
        }
    }

    class buttonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Object square = e.getSource();
            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 4; j++) {

                    if (board[i][j] == square)
                    {
                        moves(i,j);
                    }
                }
            }
        }
    }
    public void moves(int i, int j){

        if ((i+1 == tile1 && j == tile2) || (i-1 == tile1 && j == tile2) || (i == tile1 && j+1 == tile2) || (i == tile1 && j-1 == tile2)){

            board[tile1][tile2].setText(board[i][j].getText());
            board[tile1][tile2].setBackground(Color.lightGray);
            board[i][j].setText("");
            board[i][j].setBackground(Color.darkGray);
            tile1 = i; tile2 = j;
        }
    }
}
