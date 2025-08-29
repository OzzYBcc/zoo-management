package com.unipi.p23229.bscplh2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {

    private final JPanel panel; // κεντρικος πινακας της εφαρμογης
    private final JTextArea outputTextArea; // πεδιο κειμενου για εμφανιση εξοδου
    private final JButton viewAllButton, addButton, searchByNameButton, searchByHashButton,
            editButton, deleteButton, exitButton; // κουμπια ενεργειων στην εφαρμογη

    public MainForm() {
        super("Εφαρμογή Διαχείρισης Ζωολογικού Κήπου");

        // δημιουργια του κυριου πινακα
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // δημιουργια πεδιου κειμενου για την εξοδο
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        // δημιουργια κουμπιων
        viewAllButton = new JButton("Προβολή όλων των ζώων");
        addButton = new JButton("Προσθήκη νέου ζώου");
        searchByNameButton = new JButton("Αναζήτηση με όνομα");
        searchByHashButton = new JButton("Αναζήτηση με κωδικό");
        editButton = new JButton("Επεξεργασία ζώου");
        deleteButton = new JButton("Διαγραφή ζώου");
        exitButton = new JButton("Έξοδος");

        // προσθηκη στοιχειων στον κυριο πινακα
        panel.add(viewAllButton);
        panel.add(addButton);
        panel.add(searchByNameButton);
        panel.add(searchByHashButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(exitButton);

        // προσθηκη λειτουργιων με βαση συναρτησεις στα κουμπιά
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllAnimals(); // Εμφάνιση όλων των ζώων
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewAnimal(); // προσθηκη νεου ζωου
            }
        });

        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByName(); // αναζητηση ζωου με βαση το όνομα
            }
        });

        searchByHashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByHash(); // αναζητηση ζωου με βαση τον κωδικο
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAnimal(); // επεξεργασια ζωου με βαση τον κωδικο
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAnimal(); // διαγραφη ζωου με βαση τον κωδικο
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication(); // εξοδος απο την εφαρμογη και αποθηκευση
            }
        });



        // ρυθμισεις της εφαρμογης GUI (μεγεθος, τοποθετηση κλπ)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(outputTextArea, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // το παραθυρο να εμφανιζεται στο κεντρο της οθονης
    }

    // μεθοδος για προβολη ολων των ζωων
    private void viewAllAnimals() {
        List<AnimalInfo> animals = DBManager.getAllAnimals();
        if (animals.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Δεν υπάρχουν διαθέσιμα ζώα."); // Μήνυμα αν δεν υπάρχουν ζώα
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Λίστα όλων των ζώων:\n\n");
            for (AnimalInfo animal : animals) {
                sb.append(animal).append("\n\n");
            }
            outputTextArea.setText(sb.toString());
        }
    }

    // μεθοδος για προσθηκη νεων ζωων
    private void addNewAnimal() {
        String hash = JOptionPane.showInputDialog(this, "Εισάγετε κωδικό ζώου:");
        if (hash == null) return; // ελεγχω αν ο χρηστης πατησε ακυρωση

        // ελεγχω αν το κλειδι υοπαρχει ηδη στην βαση δεδομενων
        if (DBManager.animalExists(hash)) {
            JOptionPane.showMessageDialog(this, "Ο κωδικός ζώου υπάρχει ήδη στη βάση δεδομένων. Παρακαλώ εισάγετε έναν διαφορετικό κωδικό.");
            return;
        }

        String animalName = JOptionPane.showInputDialog(this, "Εισάγετε όνομα ζώου:");
        if (animalName == null) return;
        String phylum = JOptionPane.showInputDialog(this, "Εισάγετε ομοταξία ζώου (π.χ. θηλαστικό):");
        if (phylum == null) return;
        String weight = JOptionPane.showInputDialog(this, "Εισάγετε βάρος ζώου:");
        if (weight == null) return;
        String maxAge = JOptionPane.showInputDialog(this, "Εισάγετε μέγιστη ηλικία του ζώου:");
        if (maxAge == null) return;
        String cellNumber = JOptionPane.showInputDialog(this, "Εισάγετε τον κωδικό κελιού που βρίσκεται το ζώο:");
        if (cellNumber == null) return;

        AnimalInfo animal = new AnimalInfo(hash, animalName, phylum, weight, maxAge, cellNumber);
        DBManager.insertAnimal(animal);

        JOptionPane.showMessageDialog(this, "Το ζώο προστέθηκε επιτυχώς.");
    }

    // μεθοδος για αναζητηση ζωου με βαση το ονομα του
    private void searchByName() {
        String name = JOptionPane.showInputDialog(this, "Εισάγετε το όνομα του ζώου προς αναζήτηση:");

        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε όνομα ζώου για αναζήτηση."); // αν ο χρηστης βαλει κενο ονομα, προειδοποιηση
            return;
        }

        List<AnimalInfo> foundAnimals = DBManager.getAllAnimals();
        foundAnimals.removeIf(animal -> !animal.getAnimalName().equalsIgnoreCase(name));

        if (foundAnimals.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Δεν βρέθηκαν ζώα με το όνομα: " + name);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Βρέθηκαν τα παρακάτω ζώα:\n\n");
            for (AnimalInfo animal : foundAnimals) {
                sb.append(animal).append("\n\n");
            }
            outputTextArea.setText(sb.toString());
        }
    }

    // μεθοδος για την αναζητηση ζωου με βαση τον κωδικο του
    private void searchByHash() {
        String hash = JOptionPane.showInputDialog(this, "Εισάγετε τον κωδικό του ζώου προς αναζήτηση:");
        if (hash == null) return; // ελεγχω αν ο χρηστης πατησε ακυρωση

        AnimalInfo animal = DBManager.getAnimalByHash(hash);

        if (animal != null) {
            outputTextArea.setText("Βρέθηκαν τα παρακάτω ζώα:\n\n" + animal);
        } else {
            JOptionPane.showMessageDialog(this, "Δεν βρέθηκε ζώο με αυτόν τον κωδικό.");
        }
    }

    // μεθοδος για επεξεργασια ενος ζωου
    private void editAnimal() {
        String hash = JOptionPane.showInputDialog(this, "Εισάγετε τον κωδικό ζώου προς επεξεργασία:");
        if (hash == null) return; // ελεγχω αν ο χρηστης πατησε ακυρωση

        AnimalInfo animalToUpdate = DBManager.getAnimalByHash(hash);

        // περιπτωσεις αν ο χρηστης δεν αλλαξε κατι, να μεινουν ιδιες οι τιμες και οχι κενες

        if (animalToUpdate != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Επεξεργασία στοιχείων για το ζώο:\n\n").append(animalToUpdate).append("\n\n");

            String newName = JOptionPane.showInputDialog(this, "Εισάγετε νέο όνομα για το ζώο (πατήστε Cancel για ακύρωση):");
            if (newName != null && !newName.isEmpty()) {
                animalToUpdate.setAnimalName(newName);
                sb.append("Το όνομα άλλαξε σε: ").append(newName).append("\n\n");
            }

            String newPhylum = JOptionPane.showInputDialog(this, "Εισάγετε νέα ομοταξία για το ζώο (πατήστε Cancel για ακύρωση):");
            if (newPhylum != null && !newPhylum.isEmpty()) {
                animalToUpdate.setPhylum(newPhylum);
                sb.append("Η ομοταξία άλλαξε σε: ").append(newPhylum).append("\n");
            }

            String newWeight = JOptionPane.showInputDialog(this, "Εισάγετε νέο βάρος για το ζώο (πατήστε Cancel για ακύρωση):");
            if (newWeight != null && !newWeight.isEmpty()) {
                animalToUpdate.setWeight(newWeight);
                sb.append("Το βάρος άλλαξε σε: ").append(newWeight).append("\n\n");
            }

            String newMaxAge = JOptionPane.showInputDialog(this, "Εισάγετε νέα μέγιστη ηλικία για το ζώο (πατήστε Cancel για ακύρωση):");
            if (newMaxAge != null && !newMaxAge.isEmpty()) {
                animalToUpdate.setMaxAge(newMaxAge);
                sb.append("Η μέγιστη ηλικία άλλαξε σε: ").append(newMaxAge).append("\n\n");
            }

            String newCellNumber = JOptionPane.showInputDialog(this, "Εισάγετε νέο κωδικό κελιού για το ζώο (πατήστε Cancel για ακύρωση):");
            if (newCellNumber != null && !newCellNumber.isEmpty()) {
                animalToUpdate.setCellNumber(newCellNumber);
                sb.append("Ο κωδικός κελιού άλλαξε σε: ").append(newCellNumber).append("\n\n");
            }

            //προσθηκη των αλλαγων μου στην sb

            DBManager.updateAnimal(animalToUpdate);
            sb.append("\nΤα στοιχεία του ζώου ενημερώθηκαν επιτυχώς.");

            JOptionPane.showMessageDialog(this, sb.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Δεν βρέθηκε ζώο με αυτόν τον κωδικό.");
        }
    }

    // μεθοδος για διαγραφη ζωου
    private void deleteAnimal() {
        String hash = JOptionPane.showInputDialog(this, "Εισάγετε τον κωδικό ζώου προς διαγραφή:");
        if (hash == null) return; // ελεγχω αν ο χρηστης πατησε ακυρωση

        DBManager.deleteAnimal(hash);
        JOptionPane.showMessageDialog(this, "Το ζώο διαγράφηκε επιτυχώς.");
    }

    // μεθοδος για τερματισμο της εφαρμογης
    private void exitApplication() {
        int option = JOptionPane.showConfirmDialog(this, "Είστε σίγουροι ότι θέλετε να εξέλθετε από την εφαρμογή;",
                "Έξοδος", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // εκκινηση εφαρμογης
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm app = new MainForm();
            app.setVisible(true);
        });
    }
}