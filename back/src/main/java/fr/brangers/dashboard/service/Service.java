package fr.brangers.dashboard.service;

import fr.brangers.SqlConnector;
import fr.brangers.dashboard.message.IResponse;

import java.sql.Connection;

public abstract class Service {
    protected Connection connection;

    protected Service() {
        this.connection = SqlConnector.getConnection();
    }

    public IResponse launch() {
        return (null);
    }

    public Connection getConnection() {
        return connection;
    }

    /* Permet de crée des services via cette classe abstraite */

}
