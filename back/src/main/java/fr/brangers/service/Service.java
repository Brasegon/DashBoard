package fr.brangers.service;

import fr.brangers.SqlConnector;

import java.sql.Connection;

public abstract class Service {
    protected Connection connection;

    protected Service() {
        this.connection = SqlConnector.getConnection();
    }

    public String launch() {
        return ("");
    }

    public Connection getConnection() {
        return connection;
    }

    /* Permet de crée des services via cette classe abstraite */

}
