CREATE DATABASE IF NOT EXISTS estbm_db;

USE estbm_db;


CREATE TABLE IF NOT EXISTS Enseignants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telephone VARCHAR(20) NOT NULL
);

--------------------------------------
CREATE TABLE IF NOT EXISTS Etudiants (
    cin INT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    email VARCHAR(255),
    telephone VARCHAR(15),
    notebac DECIMAL(4, 2),
    nomFiliere VARCHAR(255), 
    FOREIGN KEY (nomFiliere) REFERENCES Filieres(nomFiliere)
);

--------------------------------------------

CREATE TABLE CoursEnseignant (
    idCours INT AUTO_INCREMENT PRIMARY KEY,
    nomCours VARCHAR(255),
    idEnseignant INT,
    FOREIGN KEY (idEnseignant) REFERENCES Enseignants(id)
);

----------------------------------------------
CREATE TABLE Filieres (
    idFiliere INT AUTO_INCREMENT PRIMARY KEY,
    nomFiliere VARCHAR(255) NOT NULL UNIQUE,
    responsable INT,
    description TEXT,
    FOREIGN KEY (responsable) REFERENCES Enseignants(id)
);

-------------------------------------------
CREATE TABLE EmploisDuTemps (
    ID_EmploiDuTemps INT AUTO_INCREMENT PRIMARY KEY,
    ID_Cours INT,
    ID_Enseignant INT,
    ID_Salle VARCHAR(30),
    ID_Filiere INT,
    Jour VARCHAR(255),
    Heure_debut VARCHAR(255),
    Heure_fin VARCHAR(255),
    FOREIGN KEY (ID_Cours) REFERENCES coursenseignant(idCours),
    FOREIGN KEY (ID_Enseignant) REFERENCES Enseignants(id),
    FOREIGN KEY (ID_Filiere) REFERENCES filieres(idFiliere)
);

-----------------------------------------


CREATE TABLE IF NOT EXISTS Notes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ID_Etudiant VARCHAR(255),
    ID_Examen INT,
    Note DECIMAL(4, 2),
    FOREIGN KEY (ID_Etudiant) REFERENCES Etudiants(cin),
    FOREIGN KEY (ID_Examen) REFERENCES examens(ID_Examen)
);
