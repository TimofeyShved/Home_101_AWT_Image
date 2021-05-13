# AWT_Image

Класс Java BufferedImage является подклассом класса Image. Он используется для обработки и управления данными изображения. BufferedImage сделан из ColorModel данных изображения. Все объекты BufferedImage имеют координату верхнего левого угла (0, 0).

Конструкторы
Этот класс поддерживает три типа конструкторов.

Первый конструктор создает новый BufferedImage с указанными ColorModel и Raster.
---
BufferedImage(ColorModel cm, WritableRaster raster, 
boolean isRasterPremultiplied, Hashtable<?,?> properties)

Второй конструктор создает BufferedImage одного из предопределенных типов изображений.
---

BufferedImage(int width, int height, int imageType)

Третий конструктор создает BufferedImage одного из предопределенных типов изображений: TYPE_BYTE_BINARY или TYPE_BYTE_INDEXED.
---

BufferedImage(int width, int height, int imageType, IndexColorModel cm)

-------------

# Метод и описание


copyData (WritableRaster outRaster)
---

Он вычисляет произвольную прямоугольную область BufferedImage и копирует ее в указанный WritableRaster.

getColorModel ()
---

Возвращает объект класса ColorModel изображения.

получить данные()

Возвращает изображение как одну большую плитку.

getData (прямоугольник прямоугольник)
---

Он вычисляет и возвращает произвольную область BufferedImage .

GetGraphics ()
---

Этот метод возвращает Graphics2D, сохраняет обратную совместимость.

GetHeight ()
---

Возвращает высоту BufferedImage .

getMinX ()
---

Возвращает минимальную координату x этого BufferedImage .

getMinY ()
---

Возвращает минимальную координату y этого BufferedImage .

getRGB (int x, int y)
---

Возвращает целочисленный пиксель в стандартной цветовой модели RGB (TYPE_INT_ARGB) и цветовом пространстве sRGB по умолчанию.

GetType ()
---

Возвращает тип изображения.
