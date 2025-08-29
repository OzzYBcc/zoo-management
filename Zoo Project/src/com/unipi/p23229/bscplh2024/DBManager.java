package com.unipi.p23229.bscplh2024;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// η κλαση DBManager διαχειριζεται τη συνδεση και τις ενεργειες στη βαση δεδομενων του συστηματος διαχειρισης ζωολογικου κηπου.
public class DBManager {
    static String connectionString = "jdbc:sqlite:firstDB.db";

    // δημιουργει τον πινακα ANIMALS στη βαση δεδομενων αν δεν υπαρχει ηδη.
    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS ANIMALS(" +
                    "hash TEXT PRIMARY KEY," +
                    "animalName TEXT," +
                    "phylum TEXT," +
                    "weight TEXT," +
                    "maxAge TEXT," +
                    "cellNumber TEXT)";
            statement.execute(createTableSQL);
            System.out.println("Η βάση δεδομένων λειτουργεί κανονικά!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // εισαγει ενα νεο ζωο στον πινακα ANIMALS.
    public static void insertAnimal(AnimalInfo animal) {
        String sql = "INSERT INTO ANIMALS(hash, animalName, phylum, weight, maxAge, cellNumber) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, animal.getHash());
            pstmt.setString(2, animal.getAnimalName());
            pstmt.setString(3, animal.getPhylum());
            pstmt.setString(4, animal.getWeight());
            pstmt.setString(5, animal.getMaxAge());
            pstmt.setString(6, animal.getCellNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ελεγχει αν ενα ζωο υπαρχει στη βαση δεδομενων βασει του κωδικου του (hash).
    public static boolean animalExists(String hash) {
        String sql = "SELECT 1 FROM ANIMALS WHERE hash = ?";
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, hash);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // επιστρεφει true αν βρεθηκε εγγραφη, αλλιως false
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // επιστρεφει μια λιστα με ολα τα ζωα που υπαρχουν στη βαση δεδομενων.
    public static List<AnimalInfo> getAllAnimals() {
        String sql = "SELECT * FROM ANIMALS";
        List<AnimalInfo> animals = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AnimalInfo animal = new AnimalInfo(
                        rs.getString("hash"),
                        rs.getString("animalName"),
                        rs.getString("phylum"),
                        rs.getString("weight"),
                        rs.getString("maxAge"),
                        rs.getString("cellNumber")
                );
                animals.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    // επιστρεφει τις πληροφοριες ενος ζωου βασει του κωδικου του (hash).
    public static AnimalInfo getAnimalByHash(String hash) {
        String sql = "SELECT * FROM ANIMALS WHERE hash = ?";
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, hash);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new AnimalInfo(
                        rs.getString("hash"),
                        rs.getString("animalName"),
                        rs.getString("phylum"),
                        rs.getString("weight"),
                        rs.getString("maxAge"),
                        rs.getString("cellNumber")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ενημερωνει τις πληροφοριες ενος ζωου στη βαση δεδομενων.
    public static void updateAnimal(AnimalInfo animal) {
        String sql = "UPDATE ANIMALS SET animalName = ?, phylum = ?, weight = ?, maxAge = ?, cellNumber = ? WHERE hash = ?";
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, animal.getAnimalName());
            pstmt.setString(2, animal.getPhylum());
            pstmt.setString(3, animal.getWeight());
            pstmt.setString(4, animal.getMaxAge());
            pstmt.setString(5, animal.getCellNumber());
            pstmt.setString(6, animal.getHash());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // διαγραφει ενα ζωο απο τη βαση δεδομενων βασει του κωδικου του (hash).
    public static void deleteAnimal(String hash) {
        String sql = "DELETE FROM ANIMALS WHERE hash = ?";
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, hash);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
