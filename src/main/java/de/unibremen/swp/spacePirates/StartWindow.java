package de.unibremen.swp.spacePirates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow {

    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Spaceship Game");
        JButton loadButton = new JButton("Spiel laden");
        loadButton.setBounds(20,20,20,20);

        JButton newButton = new JButton("Neues Spiel");
        newButton.setBounds(20,20,20,20);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SpaceshipGame a = new SpaceshipGame();
                Controller c = new Controller(args);
                //a.startGame(args);
            }
        });
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(loadButton);
        loadButton.setEnabled(false);
        mainPanel.add(newButton);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}