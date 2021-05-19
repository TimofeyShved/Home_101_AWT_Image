package com.AWT;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BlurredImageSurface extends JPanel {

    private BufferedImage enotIMG;
    private BufferedImage bluri;

    public BlurredImageSurface() {
        loadImage();
        createBlurredImage();
        setSurfaceSize();
    }

    private void loadImage() {
        try {
            enotIMG = ImageIO.read(new File("enot.jpeg"));
        } catch (IOException ex) {
            Logger.getLogger(Surface.class.getName()).log(
                    Level.WARNING, null, ex);
        }
    }

    private void createBlurredImage() {
        float[] blurKernel = {
                1 / 9f, 1 / 9f, 1 / 9f,
                1 / 9f, 1 / 9f, 1 / 9f,
                1 / 9f, 1 / 9f, 1 / 9f
        };

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        bluri = blur.filter(enotIMG, new BufferedImage(enotIMG.getWidth(),
                enotIMG.getHeight(), enotIMG.getType()));
    }

    private void setSurfaceSize() {
        Dimension d = new Dimension();
        d.width = enotIMG.getWidth(null);
        d.height = enotIMG.getHeight(null);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bluri, null, 0, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class BlurredImageEx extends JFrame {

    public BlurredImageEx() {
        setTitle("Blurred image");
        add(new BlurredImageSurface());

        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                BlurredImageEx ex = new BlurredImageEx();
                ex.setVisible(true);
            }
        });
    }
}
