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

    public BlurredImageSurface() { // конструктор
        loadImage(); // загрузка
        createBlurredImage(); // создание фильтра
        setSurfaceSize(); // размеры
    }

    private void loadImage() { // создаём картинку из файла
        try {
            enotIMG = ImageIO.read(new File("enot.jpeg"));
        } catch (IOException ex) {
            Logger.getLogger(Surface.class.getName()).log(
                    Level.WARNING, null, ex);
        }
    }

    private void createBlurredImage() { // создание маски
        float[] blurKernel = {
                1 / 9f, 1 / 9f, 1 / 9f,
                1 / 9f, 1 / 9f, 1 / 9f,
                1 / 9f, 1 / 9f, 1 / 9f
        };

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        bluri = blur.filter(enotIMG, new BufferedImage(enotIMG.getWidth(),
                enotIMG.getHeight(), enotIMG.getType()));
    }

    private void setSurfaceSize() { // установка размеров
        Dimension d = new Dimension();
        d.width = enotIMG.getWidth(null);
        d.height = enotIMG.getHeight(null);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) { // прорисовка эл.
        Graphics2D g2d = (Graphics2D) g; // графика
        g2d.drawImage(bluri, null, 0, 0); // картинка
    }

    @Override
    public void paintComponent(Graphics g) { // зарисовка компонента
        super.paintComponent(g);
        doDrawing(g); // вызов прорисовки компонента
    }
}

public class BlurredImageEx extends JFrame {

    public BlurredImageEx() { // конструктор
        setTitle("Blurred image");
        add(new BlurredImageSurface()); // наш объект

        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выход
    }

    public static void main(String[] args) { // нглавный класс

        EventQueue.invokeLater(new Runnable() {  // поток
            @Override
            public void run() {  // запуск

                BlurredImageEx ex = new BlurredImageEx();// создание нашего класса
                ex.setVisible(true); // видимость
            }
        });
    }
}
