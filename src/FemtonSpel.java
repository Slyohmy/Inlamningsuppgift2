import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FemtonSpel extends JFrame {

    JButton[][] board = new JButton[4][4];
    JButton resetButton = new JButton("New Game");
    JButton winButton = new JButton("Click to Win");
    JPanel tilePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    buttonListener pushed = new buttonListener();

    int tile1;
    int tile2;

    public FemtonSpel(){

        tilePanel.setLayout(new GridLayout(4,4));
        bottomPanel.add(resetButton);
        bottomPanel.add(winButton);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tilePanel,BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        resetButton.addActionListener(reset);
        winButton.addActionListener(win);
        add(mainPanel);

        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 4; j++) {

                JButton n = new JButton();
                board[i][j] = n;
                board[i][j].addActionListener(pushed);
                board[i][j].setBackground(Color.lightGray);
                tilePanel.add(board[i][j]);
            }
        }
        isWin();
    }
    public boolean isWin(){

        int count = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 3 && j == 3){
                    break;
                }
                if (!board[i][j].getText().equals(String.valueOf(count))) {
                    return false;
                } count++;
            }
        }
        JOptionPane.showMessageDialog(null, "Grattis - du klarade spelet!");
        return true;
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
            isWin();
        }
    }

    ActionListener reset = e -> {
        if (e.getSource() == resetButton) {

            tilePanel.removeAll();

                for (int i = 0; i < 4 ; i++) {

                    for (int j = 0; j < 4; j++) {

                        JButton n = new JButton();
                        board[i][j] = n;
                        board[i][j].setBackground(Color.lightGray);
                        board[i][j].addActionListener(pushed);
                        tilePanel.add(board[i][j]);}}

                shuffle();
        }
    };

    ActionListener win = e -> {
        if (e.getSource() == winButton) {
            tilePanel.removeAll();
            gameWin();
            JOptionPane.showMessageDialog(null, "Grattis!! Du klarade 15-Spelet!");

        }
    };

    public void gameWin(){

        boolean[] tileValues = new boolean[17];

        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 4; j++) {

                JButton n = new JButton();
                board[i][j] = n;
                board[i][j].setBackground(Color.lightGray);
                board[i][j].addActionListener(pushed);
                tilePanel.add(board[i][j]);
                int val = 1;

                while(tileValues[val]){
                    val = ++val;
                }
                tileValues[val] = true;

                if(val!=16)
                    board[i][j].setText(""+val);
                else {
                    board[i][j].setBackground(Color.darkGray);
                    tile1 = i;
                    tile2 = j;
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
