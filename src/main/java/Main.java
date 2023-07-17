import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;




public class Main {

    static WebDriver driver;


    public static void main(String[] args) {
        System.setProperty("webdriver.chromedriver.driver", "D:\\projects\\test-selemium\\drivers\\chromedriver.exe");



        //Работа с окнами
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//Ожидание 30 секунд


        driver.manage().window().setSize(new Dimension(900, 500));//определенное разрешение
        driver.manage().window().maximize();//Окно браузера на весь экран


        //Работа со страницами
        driver.get("https://www.google.com");//Открыть страницу гугла
        driver.get("https://e.mail.ru/");//То же, что и get - .navigate.to
        driver.navigate().back();//Назад
        driver.navigate().forward();//Вперед
        driver.navigate().back();
        driver.navigate().refresh();//перезагрузить страницу

        System.out.println(driver.getTitle());//вывод в консоль Title(Элемент заголовка)
        System.out.println(driver.getCurrentUrl());//Вывод в консоль url страницы

        driver.navigate().forward();
        System.out.println(" ");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        System.out.println(" ");


        //Поиск веб-элементов
        driver.get("https://ru.wikipedia.org/wiki/%D0%91%D0%B8%D1%82%D0%B2%D0%B0_%D0%BF%D1%80%D0%B8_%D0%A7%D0%B8%D0%BA%D0%B0%D0%BC%D0%BE%D0%B3%D0%B5");//Открыл страницу
        driver.findElement(By.linkText("Войти"));//Нашел веб-элемент по тексту-ссылки
        driver.findElement(By.partialLinkText("Кершоу"));//Нашел ссылку по части текста
        driver.findElement(By.name("search"));//Поиск элемента по имени
        driver.findElement(By.className("vector-search-box-input"));//Поиск по названию класса
        driver.findElement(By.id("p-search"));//Поиск по id элемента
        driver.findElement(By.cssSelector("#searchButton"));//нашел элемент по css локатору
        driver.findElement(By.xpath("//*[@id=\"ca-history\"]/a/span"));//поиск элемента по XPath


        //Работа с кнопками
        WebElement touchButton = driver.findElement(By.xpath("//*[@id=\"searchButton\"]"));
        touchButton.click();//метод клик(кликнуть по кнопке)
        WebElement searchTextButton = driver.findElement(By.xpath("//button[@type=\"submit\"]/span[2]"));
        System.out.println("Текст кнопки: \"" + searchTextButton.getText() + "\"");//Вывести текст кнопки
        System.out.println(driver.getCurrentUrl());
        WebElement buttonSearch2 = driver.findElement(By.xpath("//span[@class=\"oo-ui-actionFieldLayout-button\"]//button"));
        buttonSearch2.submit();//Если тип submit, так же клик

        WebElement buttonSerch3 = driver.findElement(By.xpath("//button[@type=\"submit\"]/span[2]"));
        if (buttonSerch3.getText().equals("Найти")) {
            System.out.println("Кнопка найти присутствует на странице!");
        } else
            System.out.println("Нет кнопки с таким текстом!");


        //Работа с текстовыми полями
        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Владимир Путин");//Ввел текст в текстовое поле;
        driver.findElement(By.xpath("//*[@id=\"searchButton\"]")).click();//Кликнул по пнопке поиска
        System.out.println(driver.getTitle());//Вывел в консоль заголовок
        driver.navigate().back();
        WebElement textImput =  driver.findElement(By.xpath("//*[@id=\"ooui-php-1\"]"));//Нашел элемент
        textImput.sendKeys("Selenium");//Ввел текст в поле
        driver.findElement(By.xpath("//span[@class=\"oo-ui-actionFieldLayout-button\"]")).click();
        System.out.println("В поле поиска введен текст: \"" + driver.findElement(By.xpath("//*[@id=\"ooui-php-1\"]")).getAttribute("value") + "\"");//Поиск текста в поле
        driver.findElement(By.xpath("//*[@id=\"ooui-php-1\"]")).clear();//Очистил поле


        //Работа с ссылками
        WebElement linkSelenium =  driver.findElement(By.xpath("//div[@class=\"mw-search-result-heading\"]//span"));//Нашел эл. ссылки
        System.out.println(linkSelenium.getText());//Вывел в консоль текст ссылки

        linkSelenium.click();//Кликнул на ссылку
        System.out.println(" ");
        System.out.println(driver.getTitle());//Вывел заголовок страницы
        driver.findElement(By.xpath("//li[@id=\"pt-login\"]//span")).click();//Кликнул на ссылку "Войти"
        System.out.println(" ");
        System.out.println(driver.getTitle());//Вывел заголовок страницы
        driver.findElement(By.xpath("//input[@id=\"wpName1\"]")).sendKeys("testLogin1");//Заполнил поле логин
        driver.findElement(By.xpath("//input[@id=\"wpPassword1\"]")).sendKeys("testPassword1");//Заполнил поле пароль


        //Работа с радиобаттонами и чек-боксами
        driver.findElement(By.xpath("//input[@name=\"wpRemember\"]")).click();//Кликнул по чекбоксу
        System.out.println("Проставил чек-бокс: \"" + driver.findElement(By.xpath("//label[@for=\"wpRemember\"]")).getText() + "\"");//Вывел в консоль текст чек-бокса
        System.out.println("Кликнул по кнопке: \"" + driver.findElement(By.xpath("//*[@id=\"wpLoginAttempt\"]")).getText() + "\"");
        driver.findElement(By.xpath("//*[@id=\"wpLoginAttempt\"]")).click();//Кликнул по кнопке "войти"
        System.out.println(driver.findElement(By.xpath("//*[@id=\"userloginForm\"]/form/div[1]")).getText());
        driver.findElement(By.xpath("//input[@id=\"wpPassword1\"]")).sendKeys("testPassword1");//Заполнил поле пароль

        if (driver.findElement(By.xpath("//input[@name=\"wpRemember\"]")).isSelected()) {
            System.out.println("Состояние чек-бокса: " + driver.findElement(By.xpath("//input[@name=\"wpRemember\"]")).isSelected());//Проверка состояния чек-бокса
            System.out.println("Убрал чек-бокс: \"" + driver.findElement(By.xpath("//label[@for=\"wpRemember\"]")).getText() + "\"");
            driver.findElement(By.xpath("//input[@name=\"wpRemember\"]")).click();//Кликнул по чекбоксу
            System.out.println("Состояние чек-бокса после клика: " + driver.findElement(By.xpath("//input[@name=\"wpRemember\"]")).isSelected());//Проверка состояния чек-бокса
        } else
            System.out.println("Состояние чек-бокса: " + driver.findElement(By.xpath("//input[@name=\"wpRemember\"]")).isSelected());


        //Работа с выпадающими списками(перенес в метод listData)
        driver.get("https://ya.ru/");
        captcha();
        waitElements("//a[text()=\"Войти\"]");
        clickElement("//a[text()=\"Войти\"]");
        String mainWindow1 = driver.getWindowHandle();
        clickElement("//span[@class=\"AuthFooter-item\"][2]");

        //Цикл перебирает все вкладки(возвращает их количество)
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);//и переключаемся на последнюю
        }

        String mainWindowOne = driver.getWindowHandle();//Запомнил вкладку
        clickElement("//*[@class=\"document-menu__inner\"]/div/div/div[8]/a");
        listData("Персональные данные");
        listData("Документы");
        listData("Адреса");
        System.out.println(" ");

        //Работа со списком элементов(перенес в метод listDataTouch)
        listDataTouch(7);
        listDataTouch(3);
        listDataTouch(1);
        System.out.println(" ");

        //Цикл пройтись по всем элементам списка "Данные"
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class=\"document-menu__inner\"]/div/div/div[8]/div")).size(); i++) {
            listDataTouch(i);
        }
        driver.switchTo().window(mainWindow1);//Перешел на первую вкладку
        driver.close();//Закрыл ее
        driver.switchTo().window(mainWindowOne);//Перешел на оставшуюся

        //Работа с таблицами(класс Table)
        driver.get("https://ru.wikipedia.org/");
        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Таблица");
        driver.findElement(By.xpath("//*[@id=\"searchButton\"]")).click();

        Table one = new Table(2, 2);//Выбрал таблицу 2, строку 2
        Table two = new Table(3);//Выбрал таблицу 3
        Table myTable = new Table(50);//Выбрал таблицу 50
        System.out.println();
        Table name = new Table();
        name.tablesNames();//вывод названий всех таблиц
        name.num = 1;//Выбрал таблицу 1
        System.out.println();
        name.tablename();//вывод название таблицы
        name.num = 2;//Выбрал таблицу 2
        name.tablename();//вывод название таблицы


        //Ожидания(implicitlyWait - в разделе работа с окнами)
        //implicitlyWait - при каждом поиске элемента
        //waitElements - явное ожидание

        driver.get("https://market.yandex.ru/");
        captcha();
        waitElements("//*[@id=\"header-search\"]");//Явное ожидание
        System.out.println(" ");
        System.out.println("Нахожусь на странице: " + driver.getTitle());



        driver.findElement(By.xpath("//*[@placeholder=\"Искать товары\"]")).sendKeys("Samsung tv 65");//Заполнил поле поиска
        System.out.println("Заполнил поле поиска");
        driver.findElement(By.xpath("//*[@placeholder=\"Искать товары\"]")).click();
        //waitElements("//ul[@role=\"listbox\"]");//Явное ожидание
        driver.findElement(By.xpath("//*[@placeholder=\"Искать товары\"]")).sendKeys(Keys.ENTER);//Кликнул Enter
        waitElements("//*[@class=\"nXZ_7\"]");//Явное ожидание
        onCheckBox("120 Гц");
        onCheckBox("58\"-69\"");


        //Дополнительные действия(Drag&Drops,RightClick,doubleClick..)
        driver.findElement(By.xpath("//*[text()=\"Каталог\"]")).click();
        waitElements("//*[text()=\"Выгодно\"]");

        WebElement elementElectronics = driver.findElement(By.xpath("//a[@class=\"_1010X\"]//*[text()=\"Электроника\"]"));

        Actions actions = new Actions(driver);//переменная типа Actions
        // Навести курсор на элемент. Метод build() используется для создания цепочки действий. Метод perform() запускает выполнение всех действий:
        actions.moveToElement(elementElectronics).build().perform();
        waitElements("//*[text()=\"Ноутбуки\"]");
        driver.findElement(By.xpath("//*[text()=\"Ноутбуки\"]")).click();
        System.out.println(driver.getTitle());

        WebElement oneProduct = driver.findElement(By.xpath("//div[@class=\"VFWBy uG_0s\"]/ul/div[1]/div"));//Нашел первый товар
        WebElement searchPanel = driver.findElement(By.xpath("//*[@placeholder=\"Искать товары\"]"));//Нашел строку поиска
        //Метод перемещения объекта
        actions.dragAndDrop(oneProduct, searchPanel).build().perform();
        //Кликнуть и зажать. Наводим на панель поиска. Отпускаем кнопку мыши. Собираем. Запускаем.
        actions.clickAndHold(oneProduct).moveToElement(searchPanel).release().build().perform();
        //Метод клика правой кнопкой мыши
        actions.contextClick(oneProduct).build().perform();


        //Выполнение JavaScript(JavaScriptExecutor - это интерфейс, который позволяет выполнять JavaScript код на веб-странице)
        JavascriptExecutor jse = (JavascriptExecutor) driver;//создали переменную, для выполнения скриптов

        driver.get("https://ru.wikipedia.org");
        //Скролл страницы вниз/вверх
        jse.executeScript("window.scrollBy(0, 1000)");
        jse.executeScript("window.scrollBy(0, -500)");

        //Взаимодействие с alert
        jse.executeScript("alert('Hello World')");//выполнили алерт-скрипт

        pause(3000);
        driver.switchTo().alert().accept();//Переключились.на алерт.ок


        //Работа с несколькими вкладками и окнами браузера(метод windowWork)
        driver.get("https://ya.ru/");
        captcha();
        clickElement("//a[text()=\"Войти\"]");
        clickElement("//span[@class=\"AuthFooter-item\"][2]");
        String mainWindow = driver.getWindowHandle();//Запомнил имя вкладки
        System.out.println("Нахожусь на странице: " + driver.getTitle());

        //Цикл перебирает все вкладки(возвращает их количество)
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);//и переключаемся на последнюю
        }
        System.out.println("Нахожусь на странице: " + driver.getTitle());
        String mainWindow2 = driver.getWindowHandle();//Запомнил вкладку 2
        driver.switchTo().window(mainWindow);//Переключился в окно, которое запомнили ранее
        System.out.println("Нахожусь на странице: \"" + driver.getTitle() + "\" 3 секунды");

        pause(3000);

        driver.switchTo().window(mainWindow2);//Переключился в окно2, которое запомнил ранее
        System.out.println("Нахожусь на странице: \"" + driver.getTitle() + "\". Через 2 секунды вкладка будет закрыта");

        //Пауза 2 сек
        pause(2000);

        driver.close();//Закрыть вкладку
        driver.switchTo().window(mainWindow);//Переключился на оставшуюся вкладку
        System.out.println("Вкладка закрыта, нахожусь на странице: \"" + driver.getTitle() + "\"");
        System.out.println(" ");

        //Методы состояний элементов
        //isEnabled(enabled/disabled)(false/true) - Активная/неактивная
        WebElement button1 = driver.findElement(By.xpath("//button[@type=\"submit\"]"));//Нашел кнопку

        if (!button1.isEnabled())
            System.out.println("Кнопка \"" + button1.getText() + "\" неактивна");
        else
            System.out.println("Кнопка \"" + button1.getText() + "\" активна");

        driver.findElement(By.xpath("//a[@id=\"passp:exp-register\"]")).click();
        driver.findElement(By.xpath("//button[text()=\"Для себя\"]")).click();
        System.out.println("Нахожусь на странице: " + driver.getTitle());
        System.out.println(" ");


        WebElement registrationButton = driver.findElement(By.xpath("//button[@data-t=\"button:action\"]"));
        if(registrationButton.isEnabled()){
            System.out.println("Кнопка \"" + registrationButton.getText() + "\" активна, кликаю по активному чек-боксу!" );
            driver.findElement(By.xpath("//div[@class=\"form__eula\"]//span[@class=\"Checkbox-Box\"]")).click();
        }


        //isSelected - проставлен чек-бокс или нет
        WebElement checkBox2 = driver.findElement(By.xpath("//div[@class=\"PermissionCheckbox\"]//span[@class=\"Checkbox-Box\"]"));

        if (!checkBox2.isSelected()){
            System.out.println("Чек-бокс 2 не проставлен, кликаю");
            checkBox2.click();

        } else
            System.out.println("Чек-бокс 2 проставлен");


        //isDisplayed - отображение элемента
        WebElement eye = driver.findElement(By.xpath("//*[contains(text(), 'Показать текст пароля')]"));

        if(!eye.isDisplayed())
            System.out.println("Элемент глаза не отображается");
        else
            System.out.println("Элемент глаза отображается");

        if(!registrationButton.isEnabled()) System.out.println("Кнопка \"" + registrationButton.getText() + "\" неактивна");//Проверил активность кнопки

        //Имитация нажатия на клавиатуру
        actions.sendKeys(Keys.ESCAPE).perform();//Нажал Esc, чтобы убрать контекстное меню

        //Проверить наличие элемента на странице
        driver.get("https://github.com/");
        System.out.println(" ");
        System.out.println("Нахожусь на странице: \"" + driver.getCurrentUrl() + "\"");
        System.out.println("Найдено элементов: " + driver.findElements(By.xpath("//a[@href=\"/login\"]")).size());
        System.out.println("Проверка на несуществующий элемент(возможна задержка!)");
        //Проверка на несуществующий элемент(возможна задержка)
        if (driver.findElements(By.xpath("//a[text()='Log in']")).size() > 0) System.out.println("Есть такие элементы");
        else System.out.println("Таких элементов нет!");
        driver.findElement(By.xpath("//*[contains(text(), \"Search or\")]")).click();
        WebElement searchGit = driver.findElement(By.xpath("//input[@id=\"query-builder-test\"]"));
        //Сочетание клавиш
        searchGit.sendKeys("ТЕКСТ Для теСта");
        searchGit.sendKeys(Keys.chord(Keys.CONTROL + "a"));//Имитация Ctrl+a(для выделения)
        screenshot("Выделение текста");
        pause(5000);
        searchGit.sendKeys(Keys.chord(Keys.CONTROL + "x"));//Имитация Ctrl+x(вырезать)
        pause(3000);
        searchGit.sendKeys(Keys.chord(Keys.CONTROL + "v"));//Имитация Ctrl+v(вставка)
        pause(2000);
        searchGit.sendKeys(Keys.ENTER);
        screenshot("Конец теста");

//        //Загрузка файлов
//        driver.get("https://www.google.com/?hl=RU");
//        driver.findElement(By.xpath("//div[@jscontroller=\"lpsUAf\"]")).click();
//        //С помощью sendKeys и ссылки на файл можно загружать файлы(чаще всего: //input[@type="file"])
//        driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys("C:\\Users\\nikita.tormin\\Pictures\\Screenpresso\\Основные методы WebDriver.png");


        //Запуск тестов без графики



//        driver.quit();//Закрыть браузер
    }



    //Метод клика по элементу(потом понял, что не актуально, проще прописывать вручную)
    public static void clickElement(String xPath) {
        driver.findElement(By.xpath(xPath)).click();
    }

    //Метод проверки на капчу(работа с yandex)
    public static void captcha(){
      if (driver.getCurrentUrl().contains("showcaptcha")){
            System.out.println("Введите в поле капчу!");
            waitElements("//input[@type=\"submit\"]");
            driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
      }
    }

    //Метод работы с выпадающим списком
    public static void listData(String textData) {
        clickElement("//*[text()=\"" + textData + "\"]");
        System.out.println("Выбрал в списке: \"" + textData + "\"");
    }

    //Метод работы со списком "Данные"
    public static void listDataTouch(int listNumber) {
        List<WebElement> dataDocuments = driver.findElements(By.xpath("//*[@class=\"document-menu__inner\"]/div/div/div[8]/div"));//Нашел xPath всех эллементов списка "Данные"
        if (listNumber < dataDocuments.size()) {//Условие, что номер элемента в списке меньше, чем размер списка
            if (listNumber == 5){
                driver.findElement(By.xpath("//*[@class=\"document-menu__inner\"]/div/div/div[8]/div[6]")).click();
                System.out.println("Выбрал в списке: " + listNumber + ")\"Публичные данные\"");
            } else if (listNumber == 6) {
                driver.findElement(By.xpath("//*[@class=\"document-menu__inner\"]/div/div/div[8]/div[6]")).click();
                driver.findElement(By.xpath("//*[@class=\"document-menu__inner\"]/div/div/div[8]/div[7]")).click();
                System.out.println("Выбрал в списке: " + listNumber + ")\"Управление данными\"");
            } else {
                dataDocuments.get(listNumber).click();//Перешел на элемент под указанным номером, кликнул
                System.out.println("Выбрал в списке: " + listNumber + ")\"" + dataDocuments.get(listNumber).getText() + "\"");//Вывел в консоль выбранный элемент в списке
            }
        } else
            System.out.println("Ввели значение больше, чем элементов в списке(" + (dataDocuments.size() - 1) + ")");
    }

    //Метод явного ожидания(10sec)
    public static void waitElements(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));//Явное ожидание
    }

    //Метод включения чек-бокса фильтра на яндекс-маркете
    public static void onCheckBox(String text) {
        WebElement checkboxInch = driver.findElement(By.xpath("//span[contains(text(), '" + text + "')]"));
        if (!checkboxInch.isSelected()) {
            checkboxInch.click();
            System.out.println("Чек-бокс " + text + " включен");
            waitElements("//span[contains(text(), 'Найдено')]");
            System.out.println(driver.findElement(By.xpath("//span[@class=\"_20WYq _2WBB4\"]")).getText());
        } else {
            System.out.println("Чек-бокс " + text + " включен");
        }
    }

    public static void pause(int mls){
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Метод скриншотов
    public static void screenshot(String name){
        //Делаю скриншоты и сохраняю их
        Date dateNow = new Date();//Переменная типа Date
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");//Отформатируем, чтобы получить дату в виде строки(шаблон)
        String fileName = "(" + format.format(dateNow) + ").png";//Имя, в котором указана дата + формат

        //Делаю сам скриншот
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Users\\nikita.tormin\\Pictures\\Saved Pictures\\" + name + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}