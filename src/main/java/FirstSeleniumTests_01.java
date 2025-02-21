import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumTests_01 {
    WebDriver driver;


    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver(); // этим шагом мы открываем браузер
        // driver.manage().window().setPosition(new Point(2500, 0)); // смещает экран вправо на 2500 Px
        // пиксели определяем методом тыка
        driver.get("https://google.com/"); // очищает историю, как-будто мы с нуля открыли страницу
//        driver.navigate().to("https://google.com/page1"); // открывает чётко ту страницу которая нам нужна
//        driver.navigate().to("https://google.com/page2"); // сохраняет историю
        driver.navigate().to("https://google.com/page3"); // сохраняет историю
//        driver.navigate().back(); // возвращает на шаг назад и проверяет перешли ли мы на страницу 2
//        driver.navigate().forward(); // возвращает на шаг вперёд
//        driver.navigate().refresh(); // обновляет страницу
        driver.manage().window().maximize(); // разворачивает на весь экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // время задержки 10 секунд (неявное ожидание)
        System.out.println("Step 1");

    }

    @Test
    public void FirstSeleniumTest(){
        System.out.println("Step 2");

    }

    @AfterMethod(enabled = true)
    public void tearDown(){
        driver.quit(); // завершает работу всего браузера
       // driver.close(); // завершает работу вкладки в браузере
        System.out.println("Step 3");

    }
}
