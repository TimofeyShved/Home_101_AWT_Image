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

    private Image enotIMG; // переменная для картинки

    public Surface() { // конструктор
        loadImage(); // загрузка
        setSurfaceSize(); // размеры
    }

    private void loadImage() {
        enotIMG = new ImageIcon("enot.jpeg").getImage();
    } // создаём картинку из файла

    private void setSurfaceSize() {
        // установка размеров
        Dimension d = new Dimension();
        d.width = enotIMG.getWidth(null);
        d.height = enotIMG.getHeight(null);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) { // прорисовка эл.
        Graphics2D g2d = (Graphics2D) g; // графика
        g2d.drawImage(enotIMG, 0, 0, null); // картинка
    }

    @Override
    public void paintComponent(Graphics g) { // зарисовка компонента
        super.paintComponent(g);
        doDrawing(g); // вызов прорисовки компонента
    }
}

public class DisplayImageEx extends JFrame {

    public DisplayImageEx() {
        initUI();
    } // конструктор с инициализацией

    private void initUI() { // инициализация
        add(new Surface()); // наш объект
        pack();
        setTitle("enot"); // имя
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выход
    }

    public static void main(String[] args) { // главный класс

        EventQueue.invokeLater(new Runnable() { // поток
            @Override
            public void run() { // запуск
                DisplayImageEx ex = new DisplayImageEx(); // создание нашего класса
                ex.setVisible(true); // видимость
            }
        });
    }
}
