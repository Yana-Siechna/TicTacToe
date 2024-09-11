package window.programTicTacToe;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class TicTacToeInWindow extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isXturn = true;

    public TicTacToeInWindow(){
        setTitle("Хрестики-нулики");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("")){
            return;
        }
        if (isXturn) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }
        isXturn = !isXturn;
        checkForWinner();
    }

    private void checkForWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
        }
        if (checkRowColDiag(board, "X")){
            JOptionPane.showMessageDialog(this, "Х переміг!");
            resetGame();
        }
        if (checkRowColDiag(board, "O")){
            JOptionPane.showMessageDialog(this, "O переміг!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Нічия!");
            resetGame();
        }
    }

    private boolean checkRowColDiag(String[][] board, String player){
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)){
                return true;
            }
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)){
                return true;
            }
            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)){
                return true;
            }
            if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)){
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull(){
        for (JButton button : buttons){
            if (button.getText().equals("")){
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (JButton button : buttons){
            button.setText("");
        }
        isXturn = true;
    }
}