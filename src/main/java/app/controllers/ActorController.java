package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.database.MySql;
import app.models.Actor;

public class ActorController {
    public MySql mySql;
    public ActorController(){
        this.mySql = new MySql();
    }
    public ArrayList<Actor> getAll(){
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            Statement statement = MySql.connection.createStatement();
            String query = "SELECT * FROM actor";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("actor_id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String lastUpdate = result.getString("last_update");
                Actor actor = new Actor(id, firstName, lastName, lastUpdate);
                actors.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public boolean createActor(String firstName,String lastName){
        try {
            String query = "INSERT INTO actor(first_name,last_name) values (?,?)";
            PreparedStatement statement = MySql.connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}