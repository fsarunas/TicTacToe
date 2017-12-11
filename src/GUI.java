import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI
{
    private Game game;
    private JFrame frame;
    private JButton[][] buttons;
    private JTextField txtField;
    private char letter;
    private boolean isGameFinished;

    public GUI()
    {
        game = new Game();
        makeFrame();
    }

    private void makeFrame()
    {
        frame = new JFrame("TTT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[3][3];

        JPanel panelTTT = new JPanel();
        panelTTT.setSize(400,400);
        panelTTT.setLayout(new GridLayout(3,3));

        JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    int i = 0;
                    while(i < 3) {
                        int j = 0;
                        while(j < 3) {
                            isGameFinished = false;
                            buttons[i][j].setEnabled(true);
                            buttons[i][j].setText("");
                            j++;
                        }
                        i++;
                    }
                }
            });

        int i = 0;
        while(i < 3) {
            int j = 0;
            while(j < 3) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ActionListener() { 
                        public void actionPerformed(ActionEvent e) { 
                            JButton btn = (JButton)e.getSource();
                            if(btn.getText().equals("")) {
                                char turn = game.playerTurn();
                                updateTurn(turn);

                                char letter = game.updateGame();
                                btn.setText(String.valueOf(letter));

                                if(isGameFinished() == true) {
                                    int i = 0;
                                    while(i < 3) {
                                        int j = 0;
                                        while(j < 3) {
                                            buttons[i][j].setEnabled(false);
                                            j++;
                                        }
                                        i++;
                                    }
                                }
                            }
                        }
                    });

                panelTTT.add(buttons[i][j]);
                j++;
            }
            i++;
        }


        txtField = new JTextField();
        txtField.setEditable(false);

        char turn = game.playerTurn();
        updateTurn(turn);
        game.updateGame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(txtField, BorderLayout.NORTH);
        panel.add(panelTTT, BorderLayout.CENTER);
        panel.add(clearButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    private void updateTurn(char letter)
    {
        this.letter = letter;
        txtField.setText("Player turn: " + letter);
    }

    private boolean isGameFinished()
    {
        String[] check;
        int i;
        int j;

        check = new String[3];
        i = 0;
        while(i < 3) {
            j = 0;
            while(j < 3) {
                if(!buttons[i][j].getText().equals("")) {
                    check[j] = buttons[i][j].getText();
                }
                j++;
            }
            if(check[0] != null && check[1] != null && check[2] != null) {
                if(check[0].equals(check[1]) && check[0].equals(check[2])) {
                    isGameFinished = true;
                    return isGameFinished;
                }
            }
            check = new String[3];
            i++;
        }

        check = new String[3];
        j = 0;
        while(j < 3) {
            i = 0;
            while(i < 3) {
                if(!buttons[i][j].getText().equals("")) {
                    check[i] = buttons[i][j].getText();
                }
                i++;
            }
            if(check[0] != null && check[1] != null && check[2] != null) {
                if(check[0].equals(check[1]) && check[0].equals(check[2])) {
                    isGameFinished = true;
                    return isGameFinished;
                }
            }
            check = new String[3];
            j++;
        }

        check = new String[3];
        i = 0;
        while(i < 3) {
            if(!buttons[i][i].getText().equals("")) {
                check[i] = buttons[i][i].getText();
            }
            i++;
        }
        if(check[0] != null && check[1] != null && check[2] != null) {
            if(check[0].equals(check[1]) && check[0].equals(check[2])) {
                isGameFinished = true;
                return isGameFinished;
            }
        }

        check = new String[3];
        i = 2;
        j = 0;
        while(j < 3) {
            if(!buttons[i][j].getText().equals("")) {
                check[i] = buttons[i][j].getText();
            }
            i--;
            j++;
        }
        if(check[0] != null && check[1] != null && check[2] != null) {
            if(check[0].equals(check[1]) && check[0].equals(check[2])) {
                isGameFinished = true;
                return isGameFinished;
            }
        }

        return isGameFinished;
    }
}