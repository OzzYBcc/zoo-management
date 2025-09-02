# Zoo Management

## Overview
This project is a Java application designed to manage a zoo's animal database using a file-based SQLite database ("firstDB.db"). Developed as part of a Java course at the University of Piraeus, it includes a GUI interface for adding, viewing, searching, editing, and deleting animal records. The application features two core classes (`AnimalInfo` and `DBManager`) and a `MainForm` GUI, with enhancements based on lecture examples and online resources.

## Technologies
- **Language**: Java
- **Database**: SQLite (v3.41.2.1)
- **Libraries**: Java SQL (jdbc:sqlite), Swing (for GUI)
- **IDE**: Likely supports form design (e.g., NetBeans)

## Features
- **AnimalInfo Class**: Represents an animal with attributes (hash, name, phylum, weight, max age, cell number) and getters/setters.
- **DBManager Class**: Handles database operations:
  - Creates the `ANIMALS` table if it doesn’t exist.
  - Inserts, updates, deletes, and retrieves animal records.
  - Checks for existing animals by hash.
  - Retrieves all animals or a specific animal by hash.
- **MainForm GUI**: Provides a user-friendly interface with buttons for:
  - Viewing all animals.
  - Adding new animals with user input.
  - Searching animals by name or hash.
  - Editing animal details.
  - Deleting animals by hash.
  - Exiting the application.
- **Error Handling**: Includes basic SQLException handling and user input validation.

## Installation
1. Clone the repository:
   ```
   git clone https://github.com/OzzYBcc/zoo-management.git
   ```
2. Ensure Java Development Kit (JDK) is installed.
3. Install SQLite JDBC driver (e.g., add `sqlite-jdbc-x.x.x.jar` to your project).
4. Compile and run the project using an IDE (e.g., NetBeans) or command line:
   ```
   javac com/unipi/p23229/bscplh2024/*.java
   java com.unipi.p23229.bscplh2024.MainForm
   ```
5. Ensure `firstDB.db` is in the project directory or adjust the connection string in `DBManager.java`.

## Usage
- **Sample GUI Interaction**:
  - Click "Προσθήκη νέου ζώου" to enter animal details (hash, name, etc.) via dialog boxes.
  - Click "Προβολή όλων των ζώων" to display all records in the text area.
  - Use "Αναζήτηση με κωδικό" or "Αναζήτηση με όνομα" to find specific animals.
  - Edit or delete animals using their hash codes.
- **Sample Code Snippet (insertAnimal Method)**:
  ```java
  public static void insertAnimal(AnimalInfo animal) {
      String sql = "INSERT INTO ANIMALS(hash, animalName, phylum, weight, maxAge, cellNumber) VALUES(?, ?, ?, ?, ?, ?)";
      try (Connection connection = DriverManager.getConnection(connectionString);
           PreparedStatement pstmt = connection.prepareStatement(sql)) {
          pstmt.setString(1, animal.getHash());
          pstmt.setString(2, animal.getAnimalName());
          // ... (other setters)
          pstmt.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  ```

## Contributing
Contributions are welcome! Enhance the GUI, add data validation, or improve database performance by forking the repository and submitting a pull request.

## License
MIT License