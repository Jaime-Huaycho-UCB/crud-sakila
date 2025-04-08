package app.crud_sakila;

import app.database.MySql;
import app.views.FrameActors;

public class App {
    public static void main(String[] args) {
        MySql mysql = new MySql();
        mysql.connect();
        FrameActors frameActors = new FrameActors();
        frameActors.setLocationRelativeTo(null);
        frameActors.setVisible(true);
    }
}
