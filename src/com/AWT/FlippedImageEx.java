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

    // переменная для картинки
    private Image enotIMG;
    private BufferedImage bufimg;
    private final int SPACE = 10;

    public FlippedImageSurface() { // конструктор
        loadImage(); // загрузка
        createFlippedImage(); // создание фильтра
        setSurfaceSize(); // размеры
    }

    private void loadImage() { // создаём картинку из файла
        enotIMG = new ImageIcon("enot.jpeg").getImage();
    }

    private void createFlippedImage() { // создание маски
        bufimg = new BufferedImage(enotIMG.getWidth(null),
                enotIMG.getHeight(null), BufferedImage.TYPE_INT_RGB); // маска

        Graphics gb = bufimg.getGraphics(); // графика
        gb.drawImage(enotIMG, 0, 0, null); // прорисовка
        gb.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1); // трансформация
        tx.translate(-enotIMG.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufimg = op.filter(bufimg, null);
    }

    private void setSurfaceSize() { // установка размеров
        int w = bufimg.getWidth();
        int h = bufimg.getHeight();

        Dimension d = new Dimension(3*SPACE+2*w, h+2*SPACE);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) { // прорисовка эл.
        Graphics2D g2d = (Graphics2D) g; // графика

        g2d.drawImage(enotIMG, SPACE, SPACE, null); // картинка
        g2d.drawImage(bufimg, null, 2*SPACE + bufimg.getWidth(), SPACE); // из буффера обмена
    }

    @Override
    public void paintComponent(Graphics g) { // зарисовка компонента
        super.paintComponent(g);
        doDrawing(g); // вызов прорисовки компонента
    }
}

public class FlippedImageEx extends JFrame {

    public FlippedImageEx() {
        initUI();
    } // конструктор с инициализацией

    private void initUI() { // инициализация
        add(new FlippedImageSurface()); // наш объект
        pack();

        setTitle("Flipped image"); // имя
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выход
    }

    public static void main(String[] args) { // нглавный класс

        EventQueue.invokeLater(new Runnable() { // поток
            @Override
            public void run() { // запуск
                FlippedImageEx ex = new FlippedImageEx();// создание нашего класса
                ex.setVisible(true); // видимость
            }
        });
    }
}
