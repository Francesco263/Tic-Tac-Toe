import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
    int cntr = 0;
    JLabel title = new JLabel("Tic-Tac-Toe");
    JLabel info = new JLabel("Playing...");
    JButton reset = new JButton("Reset");
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel mainPanel = new JPanel(new GridLayout(3,3));
    ArrayList<JButton> buttons = new ArrayList<>(9);
    ImageIcon icon1 = new ImageIcon("files/x.png");
    ImageIcon icon2 = new ImageIcon("files/o.png");
    public TicTacToe(){
        reset.setEnabled(false);
        reset.setBackground(Color.white);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++){
                    buttons.get(i).setIcon(null);
                    buttons.get(i).setEnabled(true);
                    info.setText("Playing...");
                    cntr = 0;
                    reset.setEnabled(false);
                }
            }
        });
        title.setFont(new Font("Serif", Font.BOLD, 25));
        info.setFont(new Font("Serif", Font.BOLD, 19));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(info, BorderLayout.WEST);
        bottomPanel.add(reset, BorderLayout.EAST);
        for (int i = 1; i <= 9; i++){
            JButton button = new JButton();
            button.setBackground(Color.white);
            button.setName(Integer.toString(i));
            mainPanel.add(button);
            buttons.add(button);
            button.addActionListener(new Listener());
        }
        setTitle("v1.03");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setResizable(true);
    }
    class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setEnabled(false);
            cntr++;
            if (cntr%2 == 0){
                button.setIcon(icon1);
                if (validateGame()){
                    info.setText("X won!");
                }
            }
            else{
                button.setIcon(icon2);
                if (validateGame()){
                    info.setText("O won!");
                }
                else if (cntr == 9){
                    info.setText("Tie!");
                    reset.setEnabled(true);
                }
            }
        }
    }
    public boolean validateGame(){
        int[] board = new int[9];
        for (int i = 0; i < 9; i++){
            if (buttons.get(i).getIcon() == icon1){
                board[i] = 1;
            }
            else if (buttons.get(i).getIcon() == icon2){
                board[i] = 2;
            }
            else{
                board[i] = 0;
            }
        }
        return valScore(board);
    }
    public boolean valScore(int[] board){
        int[][] val = new int[][]{
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6},
        };
        for (int i = 0; i < 8; i++){
            int a = val[i][0];
            int b = val[i][1];
            int c = val[i][2];
            if (board[a] == board[b] && board[b] == board[c] && board[a] != 0){
                for (int f = 0; f < 9; f++){
                    buttons.get(f).setEnabled(false);
                    reset.setEnabled(true);
                }
                return true;
            }
        }
        return false;
    }
}

