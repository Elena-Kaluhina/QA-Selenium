import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindElementsTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://ilcarro.web.app");
        driver.manage().window().setPosition(new Point(2500, 0)); // Подвинуть окно браузера на 2500 пикселей вправо, чтобы он запускался на втором мониторе
        driver.manage().window().maximize(); // На весь экран развернуть браузер
        // Неявное ожидание локатора. Если элемент появится до истечения времени, Selenium сразу продолжит выполнение, не дожидаясь окончания таймера.
        // Устанавливает глобальное ожидание на все элементы, которые вы пытаетесь найти с помощью методов findElement или findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //************************************************* Ищем локатор элемента по тэгу
    @Test
    public void testFindElementsTagName() {
        //? Локатор с тегом <h1>
        //* driver.findElement(By.tagName("h1")); // Создадим локальную переменную
        WebElement h1 = driver.findElement(By.tagName("h1"));
        //! System.out.println(h1); // получаем ерунду
        System.out.println("Заголовок h1 имеет текст: " + h1.getText());

        //? Локатор с тегом <а> это картинка в этом случае логотип сайта
        WebElement a = driver.findElement(By.tagName("a"));
        System.out.println("Текст элемента с тегом 'а': [" + a.getText() + "] -> текста нет потому что это картинка");
        System.out.println("Размер элемента с тегом 'а': " + a.getSize()); // Элементов несколько, а первый - картинка. Поэтому у неё нет текста, но есть разрешение.

        //? Локатор с тегом <img> это картинка внутри тега <a>
        WebElement img = driver.findElement(By.tagName("img"));
        String imgSrc = img.getAttribute("src"); // Получить ссылку на изображение
        System.out.println("Ссылка на изображение: " + imgSrc);

        //? Массив элементов с тегом <а>
        List<WebElement> elements_a = driver.findElements(By.tagName("a"));
        //! System.out.println("elements_a" + elements_a); так как массив то нужен цикл для распечатки
        if (elements_a.size() > 2) {
            WebElement thirdElement = elements_a.get(2); // Получаем 3-й элемент (индекс 2)
            System.out.println("Текст 3-го элемента с тегом <a> в массиве: " + thirdElement.getText());
        } else {
            System.out.println("Список содержит менее 3 элементов.");
        }
        System.out.println("**********************************");
        System.out.println("Размер массива элементов с тегом <а>: [" + elements_a.size() + "]");
        System.out.println("Текст элемента в массиве под индексом 2: [" + elements_a.get(2).getText() + "]");
        for (WebElement element : elements_a) { // for-each, для перебора всех элементов в списке elements_a
            System.out.println("Аттрибут <href>: " + element.getAttribute("href"));
            System.out.println("Текст элемента: " + element.getText());  // Для вывода текста элемента
        }
    }

    @Test
    public void FindElementsByLocator() {
        //? By.id
        //! #value
        //* #city
        driver.findElement(By.id("city"));
        driver.findElement(By.cssSelector("#city"));
        driver.findElement(By.cssSelector("input#city"));
        driver.findElement(By.xpath("//*[@id='city']")); // дольше
        driver.findElement(By.xpath("//input[@id='city']"));  // ищем именно среди h1

        //? By.cssSelector
        //! [attr="value"] // Среда не любит двойные кавычки
        driver.findElement(By.cssSelector("[ng-reflect-range-mode='true']"));
        driver.findElement(By.cssSelector("[ng-reflect-range-mode=\"true\"]"));//! Среда не любит двойные кавычки
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.quit();
    }
}
