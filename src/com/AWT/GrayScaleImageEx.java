package com.AWT;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GrayScaleImageSurface extends JPanel {

    // переменная для картинки
    private Image enotIMG;
    private BufferedImage bufimg;
    private Dimension d;

    public GrayScaleImageSurface() { // конструктор
        loadImage(); // загрузка
        determineAndSetSize(); // размеры
        createGrayImage(); // создание серого оттенка
    }

    private void determineAndSetSize() { // установка размеров
        d = new Dimension();
        d.width = enotIMG.getWidth(null);
        d.height = enotIMG.getHeight(null);
        setPreferredSize(d);
    }

    private void createGrayImage() { // создание маски
        bufimg = new BufferedImage(d.width, d.height,
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2d = bufimg.createGraphics(); // прорисовка графики
        g2d.drawImage(enotIMG, 0, 0, null);
        g2d.dispose();
    }

    private void loadImage() {
        enotIMG = new ImageIcon("enot.jpeg").getImage();
    } // создаём картинку из файла

    private void doDrawing(Graphics g) { // прорисовка эл.
        Graphics2D g2d = (Graphics2D) g; // графика
        g2d.drawImage(bufimg, null, 0, 0); // картинка
    }

    @Override
    public void paintComponent(Graphics g) { // зарисовка компонента
        super.paintComponent(g);
        doDrawing(g); // вызов прорисовки компонента
    }
}

public class GrayScaleImageEx extends JFrame {

    public GrayScaleImageEx() {
        initUI();
    }// конструктор с инициализацией

    private void initUI() { // инициализация

        GrayScaleImageSurface dpnl = new GrayScaleImageSurface(); // наш объект
        add(dpnl);

        pack();

        setTitle("GrayScale image"); // имя
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выход
    }

    public static void main(String[] args) {// главный класс

        EventQueue.invokeLater(new Runnable() { // поток
            @Override
            public void run() { // запуск
                GrayScaleImageEx ex = new GrayScaleImageEx();// создание нашего класса
                ex.setVisible(true); // видимость
            }
        });
    }
}
