--
-- Fichier généré par SQLiteStudio v3.2.1 sur sam. févr. 27 13:47:03 2021
--
-- Encodage texte utilisé : UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table : KPI_ROLE
CREATE TABLE KPI_ROLE 
   (	EMAIL character varying(100), 
	PASSWORD character varying(100), 
	ROLE character varying(20)
   );
INSERT INTO KPI_ROLE (EMAIL, PASSWORD, ROLE) VALUES ('SEBASTIEN.VIAL@BOBST.COM', '$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy', 'ROLE_ADMIN');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
