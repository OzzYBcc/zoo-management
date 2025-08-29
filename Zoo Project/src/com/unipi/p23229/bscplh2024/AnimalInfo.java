package com.unipi.p23229.bscplh2024;

import java.io.*;

// η κλαση AnimalInfo αναπαριστα τις πληροφοριες ενος ζωου.
class AnimalInfo {
    // η μεταβλητη hash αναπαριστα τον κωδικο του καθε ζωου.
    protected String hash;
    // η μεταβλητη animalName αναπαριστα το ονομα του ζωου.
    protected String animalName;
    // η μεταβλητη phylum αναπαριστα την ομογενεια του ζωου.
    protected String phylum;
    // η μεταβλητη weight αναπαριστα το βαρος του ζωου.
    protected String weight;
    // η μεταβλητη maxAge αναπαριστα τη μεγιστη ηλικια που μπορει να φτασει το ζωο.
    protected String maxAge;
    // η μεταβλητη cellNumber αναπαριστα τον αριθμο κελιου που βρισκεται το ζωο.
    protected String cellNumber;

    // κατασκευαστης της κλασης AnimalInfo.
    public AnimalInfo(String hash, String animalName, String phylum, String weight, String maxAge, String cellNumber) {
        this.hash = hash;
        this.animalName = animalName;
        this.phylum = phylum;
        this.weight = weight;
        this.maxAge = maxAge;
        this.cellNumber = cellNumber;
    }

    // μεθοδος για την επιστροφη του κωδικου του ζωου.
    public String getHash() {
        return hash;
    }

    // μεθοδος για την ορισμο του κωδικου του ζωου.
    public void setHash(String hash) {
        this.hash = hash;
    }

    // μεθοδος για την επιστροφη του ονοματος του ζωου.
    public String getAnimalName() {
        return animalName;
    }

    // μεθοδος για την ορισμο του ονοματος του ζωου.
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    // μεθοδος για την επιστροφη της ομογενειας του ζωου.
    public String getPhylum() {
        return phylum;
    }

    // μεθοδος για την ορισμο της ομογενειας του ζωου.
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    // μεθοδος για την επιστροφη του βαρους του ζωου.
    public String getWeight() {
        return weight;
    }

    // μεθοδος για την ορισμο του βαρους του ζωου.
    public void setWeight(String weight) {
        this.weight = weight;
    }

    // μεθοδος για την επιστροφη της μεγιστης ηλικιας του ζωου.
    public String getMaxAge() {
        return maxAge;
    }

    // μεθοδος για την ορισμο της μεγιστης ηλικιας του ζωου.
    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    // μεθοδος για την επιστροφη του αριθμου κελιου του ζωου.
    public String getCellNumber() {
        return cellNumber;
    }

    // μεθοδος για την ορισμο του αριθμου κελιου του ζωου.
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    // μεθοδος που επιστρεφει αληθες αν το ζωο δεν εχει συμπληρωμενες πληροφοριες.
    public boolean isEmpty() {
        return hash == null && animalName == null && phylum == null && weight == null && maxAge == null && cellNumber == null;
    }

    // μεθοδος που επιστρεφει μια συμπληρωμενη περιγραφη του μελους.
    @Override
    public String toString() {
        return "Κωδικός Ζώου: " + hash + ", Όνομα Ζώου: " + animalName + ", Ομοταξία: " + phylum + ", Βάρος Ζώου: " + weight + ", Μέγιστη Ηλικία Ζώου: " + maxAge + ", Αριθμός Κελιού Ζώου: " + cellNumber;
    }
}
