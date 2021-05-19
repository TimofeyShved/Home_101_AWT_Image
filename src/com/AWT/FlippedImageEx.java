package com.AWT;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class FlippedImageSurface extends JPanel {

    private Image enotIMG;
    private BufferedImage bufimg;
    private final int SPACE = 10;

    public FlippedImageSurface() {
        loadImage();
        createFlippedImage();
        setSurfaceSize();
    }

    private void loadImage() {
        enotIMG = new ImageIcon("enot.jpeg").getImage();
    }

    private void createFlippedImage() {
        bufimg = new BufferedImage(enotIMG.getWidth(null),
                enotIMG.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics gb = bufimg.getGraphics();
        gb.drawImage(enotIMG, 0, 0, null);
        gb.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-enotIMG.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufimg = op.filter(bufimg, null);
    }

    private void setSurfaceSize() {
        int w = bufimg.getWidth();
        int h = bufimg.getHeight();

        Dimension d = new Dimension(3*SPACE+2*w, h+2*SPACE);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(enotIMG, SPACE, SPACE, null);
        g2d.drawImage(bufimg, null, 2*SPACE + bufimg.getWidth(), SPACE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class FlippedImageEx extends JFrame {

    public FlippedImageEx() {
        initUI();
    }

    private void initUI() {
        add(new FlippedImageSurface());
        pack();

        setTitle("Flipped image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlippedImageEx ex = new FlippedImageEx();
                ex.setVisible(true);
            }
        });
    }
}
