package by.bsac.lab2.main;

import by.bsac.lab2.exception.ConnectionPoolException;
import by.bsac.lab2.menu.Menu;
import by.bsac.lab2.pool.ConnectionPool;

public class Main {
    public static void main(String[] args) throws ConnectionPoolException {
        ConnectionPool.getInstance().initializeConnectionPool(1, "jdbc:mysql://localhost:3306/flowers?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
        Menu menu = new Menu();
        menu.chooseMenuPoint();
    }
}
