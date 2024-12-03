package definitions;

import definitions.hooks.DBHooks;
import io.cucumber.java.ru.Допустим;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Класс AddFoodDBDefinitions предоставляет методы для выполнения операций с базой данных
 * в контексте добавления, проверки и удаления товаров. Методы класса используют аннотации
 * для описания шагов тестирования, включая подключение к базе данных и выполнение SQL-запросов.
 */
public class AddFoodDBDefinitions {
    private DBHooks dbHooks;
    @Допустим("Выполнено подключение к базе данных")
    public void connectionToDatabase() throws SQLException {
        dbHooks = new DBHooks();
        dbHooks.setUp();
    }
    @Допустим("Добавление товара в базу данных: {string}, {string}, {string}")
    public void addFoodToDatabase(String productName, String foodType, String isExotic) throws SQLException {
        Statement statement = dbHooks.getStatement();
        String insertQuery = String.format("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC)" +
                " VALUES ('%s', '%s', %b)",  productName, foodType, Boolean.parseBoolean(isExotic));
        statement.executeUpdate(insertQuery);
    }
    @Допустим("Проверка корректного добавления товара в базе данных: {string}, {string}, {string}")
    public void checkFoodAdded(String productName, String foodType, String isExotic) throws SQLException {
        Statement statement = dbHooks.getStatement();
        String selectQuery = String.format("SELECT * FROM FOOD WHERE FOOD_NAME = '%s' AND FOOD_TYPE = '%s' AND FOOD_EXOTIC = %b",
                productName, foodType, Boolean.parseBoolean(isExotic));
        ResultSet resultSet = statement.executeQuery(selectQuery);
        if (!resultSet.next()) {
            throw new AssertionError("Товар не был добавлен в базу данных");
        }
    }
    @Допустим("Удаление товара из базы данных: {string}, {string}, {string}")
    public void deleteFoodFromDatabase(String productName, String foodType, String isExotic) throws SQLException {
        Statement statement = dbHooks.getStatement();
        String deleteQuery = String.format("DELETE FROM FOOD WHERE FOOD_NAME = '%s' AND FOOD_TYPE = '%s' AND FOOD_EXOTIC = %b",
                productName, foodType, Boolean.parseBoolean(isExotic));
        statement.executeUpdate(deleteQuery);
    }
    @Допустим("Проверка удаления товара из базы данных: {string}, {string}, {string}")
    public void checkFoodDeleted(String productName, String foodType, String isExotic) throws SQLException {
        Statement statement = dbHooks.getStatement();
        String selectQuery = String.format("SELECT * FROM FOOD WHERE FOOD_NAME = '%s' AND FOOD_TYPE = '%s' AND FOOD_EXOTIC = %b",
                productName, foodType, Boolean.parseBoolean(isExotic));
        ResultSet resultSet = statement.executeQuery(selectQuery);
        if (resultSet.next()) {
            throw new AssertionError("Товар все еще присутствует в базе данных");
        }
    }

}
