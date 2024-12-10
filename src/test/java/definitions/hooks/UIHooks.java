package definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static utils.PropertyConstants.PAGE_LOAD_TIMEOUT;

/**
 * Класс UIHooks управляет настройкой и завершением работы WebDriver для тестирования UI.
 * Он включает методы для инициализации драйвера перед тестами и для его завершения после тестов,
 * а также предоставляет методы для открытия страниц и получения текущего экземпляра драйвера.
 */
public class UIHooks {
    private static WebDriver driver;
    /**
     * Метод, который выполняется перед тестами, помеченными аннотацией @AddFoodUI.
     * Инициализирует WebDriver, устанавливает таймауты загрузки страницы и максимизирует окно браузера.
     */
    @Before("@AddFoodUI")
    @Step("Инициализируем драйвер браузера")
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
//        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.navigate().refresh();
    }
    /**
     * Метод, который выполняется после тестов, помеченных аннотацией @AddFoodUI.
     * Завершает работу WebDriver, если он был инициализирован.
     */
    @After("@AddFoodUI")
    @Step("Закрываем браузер")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    /**
     * Открывает указанную страницу по заданному URL.
     *
     * @param url URL страницы, которую необходимо открыть.
     * @throws IllegalStateException если WebDriver не инициализирован.
     */
    public static void openPage(String url) {
        if (driver != null) {
            driver.get(url);
        }
    }
    /**
     * Возвращает текущий экземпляр WebDriver. Если он не инициализирован, создается новый экземпляр.
     *
     * @return текущий экземпляр WebDriver.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
            driver.manage().window().maximize();
            driver.navigate().refresh();
        }
        return driver;
    }

}
