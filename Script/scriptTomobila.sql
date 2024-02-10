CREATE SEQUENCE seq_roles;
CREATE TABLE roles (
    id int DEFAULT nextval('seq_roles') PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

CREATE SEQUENCE seq_user;
CREATE TABLE users(
    id int DEFAULT nextval('seq_user') PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    dtn DATE,
    sexe int,
    password VARCHAR(100) NOT NULL,
    dateheure TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, email, dtn, sexe, password, dateheure)
VALUES
  ('admin', 'admin@gmail.com', '1990-01-01', 1, 'admin', '2024-01-25T12:34:56');
INSERT INTO users (username, email, dtn, sexe, password, dateheure)
VALUES
  ('rakoto', 'rakoto@gmail.com', '1980-01-05', 1, 'rakoto', '2024-01-25T12:34:56');
INSERT INTO users (username, email, dtn, sexe, password, dateheure)
VALUES
  ('jean', 'jean@gmail.com', '1995-05-08', 1, 'jean', '2024-12-31T12:34:56');

CREATE TABLE user_roles (
    user_id int,
    role_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

insert into user_roles(user_id, role_id) VALUES(1,2);
insert into user_roles(user_id, role_id) VALUES(2,1);
insert into user_roles(user_id, role_id) VALUES(3,1);

CREATE SEQUENCE seq_contact_user;
CREATE TABLE Contact_user (
    idContact_user VARCHAR(17) DEFAULT 'CCT_USER'||nextval('seq_contact_user') PRIMARY KEY,
    idUser int,
    contact VARCHAR(20),
    type VARCHAR(10),
    dateheure TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES users(id)
);

insert into contact_user(idUser, contact, type, dateheure) VALUES(2, '034 56 123 89', 'numero', '2024-12-31T12:34:56');
insert into contact_user(idUser, contact, type, dateheure) VALUES(3, '034 58 178 56', 'numero', '2024-12-31T12:34:56');

CREATE SEQUENCE seq_annonce;
CREATE TABLE annonce(
    idAnnonce VARCHAR(17) DEFAULT 'ANONC'||nextval('seq_annonce') PRIMARY KEY,
    titre VARCHAR(30),
    descriptions TEXT,
    idUser int,
    idVoiture VARCHAR(17),
    prix FLOAT,
    photo VARCHAR(300),
    dateheure TIMESTAMP,
    etat INT,
    FOREIGN KEY (idUser) REFERENCES users(id),
    FOREIGN KEY (idVoiture) REFERENCES voiture (idVoiture)
);

CREATE SEQUENCE seq_photo_annonce;
CREATE TABLE photo_annonce (
    id int DEFAULT nextval('seq_photo_annonce') PRIMARY KEY,
    idAnnonce VARCHAR(17),
    image VARCHAR(250),
    FOREIGN KEY (idAnnonce) REFERENCES annonce (idAnnonce)
);


INSERT INTO annonce (titre,descriptions,idUser,idVoiture,prix,dateheure,etat) VALUES 
('Annonce 1',  'Bien',1,'VTR1', 5000,'2024-01-29T12:34:56',3),
('Annonce 2',  'Bien',2,'VTR2', 5600,'2024-01-20T08:34:56',3),
('Annonce 3',  'Bien',3,'VTR3', 9000,'2024-01-22T12:34:56',3);

CREATE SEQUENCE seq_favori;
CREATE TABLE favori(
    idFavori VARCHAR(17) DEFAULT 'FAVORI'||nextval('seq_favori') PRIMARY KEY,
    idUser int,
    idAnnonce VARCHAR(17),
    dateheure TIMESTAMP,
    etat int,
    FOREIGN KEY (idUser) REFERENCES users(id),
    FOREIGN KEY (idAnnonce) REFERENCES annonce (idAnnonce)
);


CREATE SEQUENCE seq_notif;
CREATE TABLE notif (
    idNotif VARCHAR(17) DEFAULT 'NOTIF'||nextval('seq_notif') PRIMARY KEY,
    idUser int,
    idAnnonce VARCHAR(17),
    descriptions VARCHAR(60),
    dateheure TIMESTAMP,
    etat int DEFAULT 0,
    FOREIGN KEY (idUser) REFERENCES users(id)
);

CREATE SEQUENCE seq_vente;
CREATE TABLE vente (
    idVente VARCHAR(17) DEFAULT 'VENTE'||nextval('seq_vente') PRIMARY KEY,
    idAnnonce VARCHAR(17) UNIQUE,
    idUser int,
    prix double precision,
    dateheure TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES users(id),
    FOREIGN KEY (idAnnonce) REFERENCES annonce (idAnnonce)
);

CREATE SEQUENCE seq_commission;
CREATE TABLE COMMISSION (
    idCommission VARCHAR(17) DEFAULT 'COMI'||nextval('seq_commission') PRIMARY KEY,
    commission double precision,
    dateheure TIMESTAMP
);

INSERT INTO commission (dateheure,commission) VALUES 
('2024-01-25T01:34:56',20),
('2024-01-28T01:34:56',25.5),
('2024-01-29T03:34:56',43);


CREATE SEQUENCE seq_negociation;
CREATE TABLE negociation (
    idNegociation VARCHAR(17) DEFAULT 'NEGOC'||nextval('seq_negociation') PRIMARY KEY,
    idAnnonce VARCHAR(17) ,
    acheteur int,
    etat int default 0,
    dateheure TIMESTAMP,
    FOREIGN KEY (acheteur) REFERENCES users(id),
    FOREIGN KEY (idAnnonce) REFERENCES annonce (idAnnonce)
);

INSERT INTO commission (dateheure,commission) VALUES 
('2024-01-25T01:34:56',20);


INSERT INTO VENTE (idAnnonce,idUser,prix,dateheure) VALUES 
('ANONC6',2,2000, '2024-01-29T01:34:56');


INSERT INTO users (id, username, email, dtn, sexe, password, dateheure)
VALUES
  (1, 'utilisateur1', 'utilisateur1@example.com', '1990-01-01', 1, 'motdepasse1', '2024-01-25T12:34:56');


INSERT INTO users (id, username, email, dtn, sexe, password, dateheure)
VALUES
  (2, 'utilisateur2', 'utilisateur1@example.com', '1990-01-01', 1, 'motdepasse2', '2024-01-25T12:34:56');

INSERT INTO users (id, username, email, dtn, sexe, password, dateheure)
VALUES
  (3, 'utilisateur3', 'utilisateur1@example.com', '1990-01-01', 1, 'motdepasse3', '2024-12-31T12:34:56');

create SEQUENCE seq_mois;
CREATE TABLE Mois (
    idMois VARCHAR(15) DEFAULT 'MOIS'||nextval('seq_mois') PRIMARY KEY,
    libelle VARCHAR(15) NOT NULL,
    numero int not null
);

-- Utilisateur cette semaine et lastweek
CREATE VIEW nbUsersLastWeek AS (
SELECT
  COUNT(*) AS nombre_inscrits
FROM users
WHERE TO_CHAR(dateheure + INTERVAL '1 week', 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);


CREATE OR REPLACE VIEW nbUsersWeek AS (
SELECT
  COUNT(*) AS nombre_inscrits,
  (select nombre_inscrits from nbUsersLastWeek) as last
FROM users
WHERE TO_CHAR(dateheure, 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);



-- Vente cette semaine et lastweek
CREATE OR REPLACE VIEW VenteLastWeek AS (
SELECT
  case when sum(a.prix) is null then 0 else sum(v.prix) end as totalVente
FROM vente v
JOIN annonce a on a.idAnnonce = v.idAnnonce
WHERE TO_CHAR(v.dateheure + INTERVAL '1 week', 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);

CREATE OR REPLACE VIEW VenteWeek AS (
SELECT
   case when sum(a.prix) is null then 0 else sum(v.prix) end as totalVente,
  (select totalVente from vENTELastWeek) as last
FROM vente v
JOIN annonce a on a.idAnnonce = v.idAnnonce
WHERE TO_CHAR(v.dateheure, 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);

--Annonce Ajoute cette semaine et lastweek
CREATE VIEW nbAnnonceLastWeek AS (
SELECT
  COUNT(*) AS nombre
FROM annonce
WHERE TO_CHAR(dateheure + INTERVAL '1 week', 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);


CREATE OR REPLACE VIEW nbAnnonceWeek AS (
SELECT
  COUNT(*) AS nombre,
  (select nombre from nbAnnonceLastWeek) as last
FROM annonce
WHERE TO_CHAR(dateheure, 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);


-- Commision et  VENTE
CREATE OR REPLACE VIEW V_vente_Commission as ( 
SELECT
  v.idVente,v.prix,c.commission, v.dateheure,(v.prix*c.commission/100) as benefice
FROM vente v
JOIN commission c on v.dateheure>c.dateheure
JOIN
    (SELECT
    v.idVente,max(c.dateheure) as dateheure
    FROM vente v
    JOIN commission c on v.dateheure>c.dateheure
    GROUP BY v.idvente) VU
ON vu.idvente = v.idvente and vu.dateheure = c.dateheure
);

-- COMMISION CETte semaine et la semaine dereniere
CREATE OR REPLACE VIEW CommissionLastWeek AS (
SELECT
  case when sum(benefice) is null then 0 else sum(benefice) end as benefice
FROM V_vente_Commission
WHERE TO_CHAR(dateheure + INTERVAL '1 week', 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);

CREATE OR REPLACE VIEW CommissionWeek AS (
SELECT
  case when sum(benefice) is null then 0 else sum(benefice) end as benefice,
  (select benefice from CommissionLastWeek) as last
FROM V_vente_Commission
WHERE TO_CHAR(dateheure, 'IYYY-IW') = TO_CHAR(CURRENT_DATE, 'IYYY-IW')
);

-- lisTE DES MEILLEURS VENTE

CREATE OR REPLACE VIEW BESTVENTE AS (
SELECT v.idvente,a.idAnnonce,u.username,m.nomModele,v.prix,RANK() OVER (ORDER BY a.prix desc) AS rank_num FROM V_vente_Commission v
JOIN vente ve on ve.idvente = v.idvente
JOIN Annonce a on a.idAnnonce = ve.idAnnonce
JOIN users u on a.iduser = u.id
JOIN voiture vo on vo.idvoiture = a.idvoiture
JOIN modele m on vo.idmodele = m.idmodele
LIMIT 5
);

INSERT INTO Mois (libelle,numero) VALUES
    ('Janvier',1),
    ('Fevrier',2),
    ('Mars',3),
    ('Avril',4),
    ('Mai',5),
    ('Juin',6),
    ('Juillet',7),
    ('Aout',8),
    ('Septembre',9),
    ('Octobre',10),
    ('Novembre',11),
    ('Decembre',12);

-- View moi
create view v_mois as (
select mois.libelle,mois.numero as mois,EXTRACT(YEAR FROM CURRENT_DATE) as annee from mois 
);

-- Prix de vente et commission par mois
create view stat_PrixCommission as ( 
select vu.libelle,vu.mois,sum(prixvente) as prixvente ,sum(commission) as commission,RANK() OVER (ORDER BY vu.mois asc) AS rank_num from 
(select v.libelle,v.mois,case when ve.idvente is null then 0 else ve.prix end as prixvente, case when ve.idvente is null then 0 else ve.benefice end as commission from v_mois v
left join 
V_vente_Commission ve 
on EXTRACT(YEAR FROM ve.dateheure) = v.annee and EXTRACT(MONTH FROM ve.dateheure) = v.mois
) vu
group by vu.libelle,vu.mois
);

-- Prix de utilisateur et annonce par mois

CREATE VIEW STAT_user AS (
select vu.libelle,vu.mois,sum(nb) as nombre,RANK() OVER (ORDER BY vu.mois asc) AS rank_num from 
(select v.libelle,v.mois,case when u.id is null then 0 else 1 end as nb from v_mois v
left join 
users u
on EXTRACT(YEAR FROM u.dateheure) = v.annee and EXTRACT(MONTH FROM u.dateheure) = v.mois
) vu
group by vu.libelle,vu.mois
);

CREATE VIEW STAT_aNNONCE AS (
select vu.libelle,vu.mois,sum(nb) as nombre,RANK() OVER (ORDER BY vu.mois asc) AS rank_num from 
(select v.libelle,v.mois,case when u.iDaNNONCE is null then 0 else 1 end as nb from v_mois v
left join 
ANNONCE u
on EXTRACT(YEAR FROM u.dateheure) = v.annee and EXTRACT(MONTH FROM u.dateheure) = v.mois
) vu
group by vu.libelle,vu.mois
);

create or replace view stat_AnnonceUser as ( 
select su.libelle,su.mois,su.nombre as user,sa.nombre as annonce, rank() OVER (ORDER BY su.mois asc) AS rank_num from stat_user su
join stat_annonce sa on sa.mois = su.mois
);

-- LEs plus vendu par category, marques, modele

create or replace view total_vendu as (
  select count(*) as total,sum(prix) as totalPrix from V_vente_Commission
);

create or replace view vendu_tous as (
select m.idmodele,c.idcategorie,ma.idmarque,v.prix from V_vente_Commission v
join vente ve on ve.idvente = v.idvente
join annonce a on a.idannonce = ve.idAnnonce
join voiture vo on vo.idvoiture = a.idvoiture
join modele m on m.idmodele = vo.idmodele
join marque ma on ma.idmarque = m.idmarque
join categorie c on c.idcategorie = m.idcategorie
);


create view v_StatModele as (
select vu.*,(100*vu.nombre/vu.total) as taux_nombre,(100*vu.prix/vu.totalPrix) as taux_prix from (
select c.idmodele,c.nomModele,
case when vu.idmodele is null then 0 else vu.nombre end as nombre,
case when vu.idmodele is null then 0 else vu.prix end as prix,
t.total,
t.totalPrix
from modele c
left join 
(select idmodele,count(idmodele) as nombre,sum(prix) as prix from vendu_tous v
group by idmodele) vu on vu.idmodele = c.idmodele
cross join total_vendu t
) vu
);

create view v_Statcategorie as (
select vu.*,(100*vu.nombre/vu.total) as taux_nombre,(100*vu.prix/vu.totalPrix) as taux_prix from (
select c.idcategorie,c.nomcategorie,
case when vu.idcategorie is null then 0 else vu.nombre end as nombre,
case when vu.idcategorie is null then 0 else vu.prix end as prix,
t.total,
t.totalPrix
from categorie c
left join 
(select idcategorie,count(idcategorie) as nombre,sum(prix) as prix from vendu_tous v
group by idcategorie) vu on vu.idcategorie = c.idcategorie
cross join total_vendu t
) vu
);

create view v_Statmarque as (
select vu.*,(100*vu.nombre/vu.total) as taux_nombre,(100*vu.prix/vu.totalPrix) as taux_prix from (
select c.idmarque,c.nommarque,
case when vu.idmarque is null then 0 else vu.nombre end as nombre,
case when vu.idmarque is null then 0 else vu.prix end as prix,
t.total,
t.totalPrix
from marque c
left join 
(select idmarque,count(idmarque) as nombre,sum(prix) as prix from vendu_tous v
group by idmarque) vu on vu.idmarque = c.idmarque
cross join total_vendu t
) vu
);

-- View des favories

create view v_favori as (
select vu.* from 
(
select iduser,idAnnonce,sum(etat) as etat from favori
group by iduser,idAnnonce
) vu
where etat = 1
);

