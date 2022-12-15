package com.pboproject.game;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window() {
        setTitle("Dungeon Escape 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
