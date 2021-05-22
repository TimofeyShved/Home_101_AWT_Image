package com.AWT;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ReflectionSurface extends JPanel {

    // переменные
    private BufferedImage image;
    private BufferedImage refImage;
    private int img_w;
    private int img_h;
    private final int SPACE = 30;

    public ReflectionSurface() { // конструктор
        loadImage(); // загрузка
        getImageSize(); // размеры
        createReflectedImage(); // стили картинки
    }

    private void loadImage() { // загрузка
        try {
            image = ImageIO.read(new File("enot.jpeg")); // файл загрузки
        } catch (Exception ex) {
            Logger.getLogger(Surface.class.getName()).log( // лог ошибки
                    Level.WARNING, null, ex);
        }
    }

    private void getImageSize() { // устанока размеров
        img_w = image.getWidth();
        img_h = image.getHeight();
    }

    private void createReflectedImage() { // создание стилец
        float opacity = 0.4f; // будущая прозрачность
        float fadeHeight = 0.3f;

        refImage = new BufferedImage(img_w, img_h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D rg = refImage.createGraphics();// графика

        rg.drawImage(image, 0, 0, null); // прорисовка картинки
        rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN)); //наложение фигур

        rg.setPaint(new GradientPaint(0, img_h * fadeHeight, // заливка цветов (градиент) картинка
                new Color(0.0f, 0.0f, 0.0f, 0.0f), 0, img_h,
                new Color(0.0f, 0.0f, 0.0f, opacity)));

        rg.fillRect(0, 0, img_w, img_h); // заливка
        rg.dispose();
    }

    private void doDrawing(Graphics g) { // прорисовка
        Graphics2D g2d = (Graphics2D) g.create(); // графика

        int win_w = getWidth(); // размеры
        int win_h = getHeight();

        int gap = 20;

        g2d.setPaint(new GradientPaint(0, 0, Color.black, 0,
                win_h, Color.darkGray));                    // заливка задний фон

        g2d.fillRect(0, 0, win_w, win_h);               // прорисовка фигур
        g2d.translate((win_w - img_w) / 2, win_h / 2 - img_h);

        g2d.drawImage(image, 0, 0, null);       // картинку
        g2d.translate(0, 2 * img_h + gap);              // перемещение
        g2d.scale(1, -1);

        g2d.drawImage(refImage, 0, 0, null);    // картинку

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) { // зарисовка компонента
        super.paintComponent(g);
        doDrawing(g); // вызов прорисовки компонента
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(img_w + 2 * SPACE, 2 * img_h + 3 * SPACE);
    }
}

public class ReflectionEx extends JFrame {

    public ReflectionEx() {
        initUI();
    } // конструктор

    private void initUI() { // инициализация
        add(new ReflectionSurface()); // наш компонент
        pack();

        setTitle("Reflection"); // заголовок
        setLocationRelativeTo(null); // рамка по центру
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выход
    }

    public static void main(String[] args) { // главный класс

        EventQueue.invokeLater(new Runnable() {  // поток
            @Override
            public void run() { // запуск

                ReflectionEx ex = new ReflectionEx();// создание нашего класса
                ex.setVisible(true); // видимость
            }
        });
    }
}
