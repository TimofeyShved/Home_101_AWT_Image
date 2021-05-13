package com.AWT;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException {
	    File file = new File("1.jpg"); // получение файла
        BufferedImage image = ImageIO.read(file); // получение картинки, передел из файла
        ImageIO.write(image, "png", new File("1.png")); // чтение файла и сохранение его в png

        String[] extension = ImageIO.getReaderFileSuffixes(); // список всеъ поддерживаемых форматов
        for (String str:extension){
            System.out.println(str); // выводим на экран
        }

        // что бы не падало приложение, при неправельном формате картинок
        ImageReader reader = null; // создаём ридер
        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("JPEG"); // формат ридера
        if (iterator.hasNext()) reader = iterator.next();

        InputStream inputStream = new FileInputStream(new File("1.jpg")); // получем стрим данных
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream); // переводим в картинку
        ImageInputStream imageInputStream1 = ImageIO.createImageInputStream(new File("1.jpg"));
        reader.setInput(imageInputStream, true); // передаем в ридер
        BufferedImage image1 = reader.read(reader.getNumImages(true)); // из ридера читаем картинки (вдруг гифка)

        // получем маленькие иконки для картинок
        int count = reader.getNumThumbnails(0); // кол-во картинок
        BufferedImage image2 = reader.readThumbnail(0, count); // получем саму картинку
        System.out.println(reader.getHeight(0)); // выводим размер
        reader.getWidth(0);

        // для того что-бы сохранить картинку
        ImageWriter writer = null; // создаём врайтер
        Iterator<ImageWriter> iterator1 = ImageIO.getImageWritersByFormatName("JPEG"); // формат картинки
        if(iterator1.hasNext()) writer = iterator1.next();

        // запись картинки в папку
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(new File("1.png"));
        writer.setOutput(imageOutputStream);
    }
}
