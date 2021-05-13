package com.AWT;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Test extends JPanel {

    public void paint(Graphics g) {
        Image img = createImageWithText(); // создаём картинку
        g.drawImage(img, 20,20,this); // рисуем
    }

    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB); // создаём буфер картинки
        Graphics g = bufferedImage.getGraphics(); // графика

        g.drawString("www.text.com", 20,20); // наш текст
        g.drawString("www.text.com", 20,40);
        g.drawString("www.text.com", 20,60);
        g.drawString("www.text.com", 20,80);
        g.drawString("www.text.com", 20,100);

        return bufferedImage; // возвращаем
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(); // панель
        frame.getContentPane().add(new Test());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выход
        frame.setSize(200, 200); // размеры
        frame.setVisible(true); // видимость
    }
}
