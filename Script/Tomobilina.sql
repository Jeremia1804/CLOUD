CREATE DATABASE tomobilina;

\c tomobilina;


CREATE SEQUENCE seq_paysmarque;
CREATE SEQUENCE seq_marque;
CREATE SEQUENCE seq_categorie;
CREATE SEQUENCE seq_modele;
CREATE SEQUENCE seq_energie;
CREATE SEQUENCE seq_boite_vitesse;
CREATE SEQUENCE seq_couleur;
CREATE SEQUENCE seq_consommation;
CREATE SEQUENCE seq_equipement;
CREATE SEQUENCE seq_voiture;


CREATE TABLE paysmarque (
    idPays VARCHAR(15) DEFAULT 'PAYS'||nextval('seq_paysmarque') PRIMARY KEY,
    nomPays VARCHAR(100) NOT NULL,
    etat INT DEFAULT 1
);

CREATE TABLE marque (
    idMarque VARCHAR(15) DEFAULT 'MRQ'||nextval('seq_marque') PRIMARY KEY,
    nomMarque VARCHAR(100) NOT NULL,
    idpays VARCHAR(15) REFERENCES paysmarque,
    photo VARCHAR(300),
    etat INT DEFAULT 1
);

-- Insert data into paysmarque table
INSERT INTO paysmarque (idPays, nomPays, etat) VALUES
('PAYS1', 'France', 1),
('PAYS2', 'Germany', 1),
('PAYS3', 'USA', 1);

-- Insert data into marque table
INSERT INTO marque (nomMarque, idpays, etat) VALUES
('Renault', 'PAYS1', 1),
('Volkswagen', 'PAYS2', 1),
('Ford', 'PAYS3', 1);

CREATE TABLE categorie (
    idCategorie VARCHAR(15) DEFAULT 'CAT'||nextval('seq_categorie') PRIMARY KEY,
    nomCategorie VARCHAR(100) NOT NULL,
    etat INT DEFAULT 1
);
INSERT INTO categorie (nomCategorie, etat) VALUES
('Compact Cars', 1),
('Sedans', 1),
('SUVs', 1),
('Trucks', 1),
('Luxury Cars', 1);

CREATE TABLE modele (
    idModele VARCHAR(15) DEFAULT 'MOD'||nextval('seq_modele') PRIMARY KEY,
    idmarque VARCHAR(15),
    idcategorie VARCHAR(15),
    nomModele VARCHAR(100) NOT NULL,
    annee INT NOT NULL,
    nbrPlaces INT NOT NULL,
    nbrPortes INT NOT NULL,
    photo VARCHAR(250),
    etat INT DEFAULT 1,
    FOREIGN KEY (idmarque) REFERENCES marque(idMarque),
    FOREIGN KEY (idcategorie) REFERENCES categorie(idCategorie)
);

insert into modele(idmarque, idcategorie, nomModele, annee, nbrPlaces, nbrPortes, photo, etat) VALUES('MRQ1', 'CAT1', 'Test Modele', 2010, 5, 4, null, 1);

CREATE TABLE energie (
    idEnergie VARCHAR(15) DEFAULT 'ENE'||nextval('seq_energie') PRIMARY KEY,
    nomEnergie VARCHAR(100) NOT NULL,
    etat INT DEFAULT 1
);
INSERT INTO energie (nomEnergie) VALUES 
    ('Essence'),
    ('Diesel'),
    ('Hybride'),
    ('Electrique'),
    ('GPL');

CREATE TABLE boite_vitesse (
    idBoitevitesse VARCHAR(15) DEFAULT 'BTV'||nextval('seq_boite_vitesse') PRIMARY KEY,
    nomBoite_vitesse VARCHAR(100) NOT NULL, 
    etat INT DEFAULT 1
);
insert into boite_vitesse(nomboite_vitesse, etat) values('automatique', 1);
insert into boite_vitesse(nomboite_vitesse, etat) values('manuel', 1);

CREATE TABLE couleurs (
    idCouleur VARCHAR(15) DEFAULT 'COL'||nextval('seq_couleur') PRIMARY KEY,
    nomCouleur VARCHAR(100) NOT NULL,
    etat INT DEFAULT 1
);
insert into couleurs(nomcouleur, etat) values('bleu', 1);
insert into couleurs(nomcouleur, etat) values('noir', 1);
insert into couleurs(nomcouleur, etat) values('blanc', 1);
insert into couleurs(nomcouleur, etat) values('rouge', 1);

CREATE TABLE equipement (
    idEquipement VARCHAR(15) DEFAULT 'EQP'||nextval('seq_equipement') PRIMARY KEY,  
    nomEquipement VARCHAR(100) NOT NULL,
    etat INT DEFAULT 1
);
insert into equipement(nomequipement, etat) values('Bluetooth', 1);

CREATE TABLE voiture (
    idVoiture VARCHAR(15) DEFAULT 'VTR'||nextval('seq_voiture') PRIMARY KEY,  
    idmodele VARCHAR(15),
    kilometrage FLOAT,
    idenergie VARCHAR(15),
    idboitevitesse VARCHAR(15),
    idcouleur VARCHAR(15) REFERENCES couleurs,
    consommation FLOAT,
    etat INT DEFAULT 1,
    FOREIGN KEY (idmodele) REFERENCES modele(idmodele),
    FOREIGN KEY (idenergie) REFERENCES energie(idenergie),
    FOREIGN KEY (idboitevitesse) REFERENCES boite_vitesse(idboitevitesse),
    FOREIGN KEY (idcouleur) REFERENCES couleurs(idcouleur)
);
INSERT INTO voiture(idmodele, kilometrage, idenergie, idboitevitesse, idcouleur, consommation, etat) VALUES('MOD1', 12000, 'ENE1', 'BTV2', 'COL2', 2.6, 1);

CREATE TABLE equipementVoiture (
    idvoiture VARCHAR(15) REFERENCES voiture,
    idequipement VARCHAR(15) REFERENCES equipement,
    etat INT DEFAULT 1,
    FOREIGN KEY (idequipement) REFERENCES equipement(idequipement)
);

insert into equipementvoiture(idvoiture, idequipement) VALUES('VTR1', 'EQP1');