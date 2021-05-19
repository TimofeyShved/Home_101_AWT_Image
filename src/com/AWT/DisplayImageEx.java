package com.AWT;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

    private Image enotIMG;

    public Surface() {
        loadImage();
        setSurfaceSize();
    }

    private void loadImage() {
        enotIMG = new ImageIcon("enot.jpeg").getImage();
    }

    private void setSurfaceSize() {

        Dimension d = new Dimension();
        d.width = enotIMG.getWidth(null);
        d.height = enotIMG.getHeight(null);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(enotIMG, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class DisplayImageEx extends JFrame {

    public DisplayImageEx() {
        initUI();
    }

    private void initUI() {
        add(new Surface());
        pack();
        setTitle("enot");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DisplayImageEx ex = new DisplayImageEx();
                ex.setVisible(true);
            }
        });
    }
}
