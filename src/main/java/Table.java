import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.lang.model.element.Element;
import java.util.List;

public class Table extends Main {
    private int tablesSize = driver.findElements(By.xpath(".//table")).size();//количество таблиц на странице
    int num;

    //Метод по работе с таблицами на странице(3 конструктора)
    public Table(int num, int numStr){
        this.num = num;
        if (num > tablesSize) {
            System.out.println("Неверный номер таблицы(Всего на странице таблиц: " + tablesSize);
            return;
        }
        WebElement table = driver.findElement(By.xpath("//table[@class=\"wikitable\"][" + num + "]"));//Нашел таблицу
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));//Нашел строки
        rows.remove(0);//Удалил первую строку т.к. это заголовки
        System.out.println("Выбрана таблица: " + num + ") " + driver.findElement(By.xpath("//table[@class=\"wikitable\"][" + num + "]/caption")).getText());
        System.out.println("Строка " + numStr + " содержит: " + rows.get(numStr).getText());
    }

    public Table(int num){
        this.num = num;
        if (num > tablesSize) {
            System.out.println("Неверный номер таблицы(Всего на странице таблиц: " + tablesSize);
            return;
        }
        WebElement table = driver.findElement(By.xpath("//table[@class=\"wikitable\"][" + num + "]"));//Нашел таблицу
        System.out.println("Выбрана таблица: " + num + ") " + driver.findElement(By.xpath("//table[@class=\"wikitable\"][" + num + "]/caption")).getText());
    }

    public Table(){

    }

    //Метод вывода названия таблицы
    public void tablename(){
        WebElement table = driver.findElement(By.xpath(".//table[" + num + "]/caption"));
        System.out.println(table.getText());
    }

    //Метод вывода названий всех таблиц
    public void tablesNames(){
        for (int i = 1; i <= tablesSize; i++) {
            WebElement table = driver.findElement(By.xpath(".//table[" + i + "]/caption"));
            System.out.println(table.getText());
        }
    }
}

