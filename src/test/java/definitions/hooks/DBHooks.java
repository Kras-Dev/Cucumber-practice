package definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.DataBaseConstants.*;

/**
 * Класс DBHooks содержит методы, которые выполняются перед и после
 * выполнения сценариев Cucumber, связанных с базой данных.
 * Этот класс управляет соединением с базой данных и выполняет
 * необходимые действия для настройки и очистки.
 */
public class DBHooks {

    private Connection connection;

    private Statement statement;
    /**
     * Метод, который выполняется перед тестами, помеченными аннотацией
     * @AddFoodDB. Устанавливает соединение с базой данных и создает
     * объект Statement для выполнения SQL-запросов.
     *
     * @throws SQLException если не удалось установить соединение с базой данных
     */
    @Before("@AddFoodDB")
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER_NAME, PASS);
        statement = connection.createStatement();
    }
    /**
     * Метод, который выполняется после тестов, помеченных аннотацией
     * @AddFoodDB. Закрывает объект Statement и соединение с базой данных
     * для освобождения ресурсов.
     *
     * @throws SQLException если не удалось закрыть соединение с базой данных
     */
    @After("@AddFoodDB")
    public void tearDown() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    /**
     * Получает текущее соединение с базой данных.
     *
     * @return текущее соединение
     */
    public Connection getConnection() {
        return connection;
    }
    /**
     * Получает текущий объект Statement для выполнения SQL-запросов.
     *
     * @return текущий объект Statement
     */
    public Statement getStatement(){
        return statement;
    }
}
