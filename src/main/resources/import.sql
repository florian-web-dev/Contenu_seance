
insert into INTERVENANT(type,id, adresse,ville, code_postal, email, mp, nom, prenom, tel) values ('formateur',1,'1 rue formateur','BobVille','94500','bob@email.com','1234','Bob','noblewomen','0145879745');
insert into INTERVENANT(type,id, adresse,ville, code_postal, email, mp, nom, prenom, tel) values ('coordinateur',2,'63 Avenue du Président Wilson','Cachan','94230','bob@coordinateur.fr','{bcrypt}$2y$10$aFR8WfPgj5BzIzftisBGR.a5nSrTrL1caJ3Ph/T3eJlDdlJq7aCEK','Cortot','Valérie','0145879745');

insert into INTERVENANT(type,id, adresse,ville, code_postal, email, mp, nom, prenom, tel) values ('coordinateur',3,'61 rue des bordes','Coordonateur','94510','chaplain@coordonateur.fr','{bcrypt}$2y$10$QPCKNHe0784vZC8DSbTH1Oo.bu/5NkIUFk3aTVOfdzso5bTSKuFlO','Sejourne','Eric','0145161919');
insert into INTERVENANT(type,id, adresse,ville, code_postal, email, mp, nom, prenom, tel) values ('formateur',4,'1 rue formateur','Londres','94500','ada@lovelace.fr','{bcrypt}$2a$10$4lQEHG5NnGvN942DfcEyAOSqLOgMei6CwrpESdFX9wtVBoGzPnFcS','Lovelace','Ada','0145879745');
insert into INTERVENANT(type,id, adresse,ville, code_postal, email, mp, nom, prenom, tel) values ('formateur',5,'1 rue formateur','UneVille','94500','timomoulin@msn.com','{bcrypt}$2a$10$id50JbO3K6L6GikHr1vqMOxJS7cTtNovRY1aCk10n6rnFhZzAIhaa','Moulin','Tim','0145879745');
insert into INTERVENANT(type,id, adresse,ville, code_postal, email, mp, nom, prenom, tel) values ('formateur',6,'1 rue formateur sup','DeletVille','94500','sup@moi.fr','{bcrypt}$2a$10$SkE5R47SqV451HrxAK5LkeyxZtxSRJV2VqaQ7EJfBN2aE.ZuMCMHy','Sup','Moi','0102030405');


insert into CENTRE(adresse, codes_postal, nom, ville) VALUES ( '61 des bordes','94430','Champlain','Chennevierre');
insert into CENTRE(adresse, codes_postal, nom, ville) VALUES ( '2 des bordes','94400','Cachan','Cachan');
insert into CENTRE_COORDINATEURS(centres_id, coordinateurs_id) VALUES ( 1,2 );
insert into CENTRE_COORDINATEURS(centres_id, coordinateurs_id) VALUES ( 2,3 );

insert into FORMATION(id,TYPE_FORMATION) values (1, 'CDA' );
insert into FORMATION(id,TYPE_FORMATION) values (2, 'DWWM' );


insert into SESSION(id,date_debut, date_fin,durer_total, centre_id, coordinateur_id, formation_id) VALUES (1, '2019-11-14','2020-07-06',693,1,3,1 );
insert into SESSION(id,date_debut, date_fin,durer_total, centre_id, coordinateur_id, formation_id) VALUES (2, '2020-11-20','2021-07-10',693,1,3,1 );
insert into SESSION(id,date_debut, date_fin,durer_total, centre_id, coordinateur_id, formation_id) VALUES (3, '2021-11-16','2022-07-13',700,1,3,1 );

insert into SESSION(id,date_debut, date_fin,durer_total, centre_id, coordinateur_id, formation_id) VALUES (4, '2020-9-22','2021-05-08',693,2,2,2 );
insert into SESSION(id,date_debut, date_fin,durer_total, centre_id, coordinateur_id, formation_id) VALUES (5, '2022-07-22','2023-03-08',700,2,2,2 );


insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id) VALUES ( 1,1 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 5,1 );

insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 1,1 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 4,2 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 5,2 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 5,2 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 4,3 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 4,3 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 4,4 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 1,4);
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 5,5 );
insert into INTERVENANT_SESSIONS(formateurs_id, sessions_id)VALUES ( 4,5 );


insert into REAC(nom,date_debut, durer, lien, formation_id) values ('CDA reac', '2019-01-01',5,'https://www.banque.di.afpa.fr/EspaceEmployeursCandidatsActeurs/EGPResultat.aspx?ct=01281m03&type=t',1 );
insert into REAC(nom,date_debut, durer, lien, formation_id) values ('DWWM reac', '2019-01-01',5,'https://www.banque.di.afpa.fr/EspaceEmployeursCandidatsActeurs/EGPResultat.aspx?ct=01280m03&type=t',2 );

insert into ACTIVITE(id, activites_types, num_odre, reac_id) VALUES ( 1,'Concevoir et developper Cda',10 ,1 );
insert into ACTIVITE(id, activites_types, num_odre, reac_id) VALUES ( 2,'Concevoir et developper la persistance DW',11 ,2 );
insert into ACTIVITE(id, activites_types, num_odre, reac_id) VALUES ( 3,'Concevoir et developper des composants CDA',10 ,1 );
insert into ACTIVITE(id, activites_types, num_odre, reac_id) VALUES ( 4,'Concevoir et developper la persistance des données ...',11 ,2 );
insert into ACTIVITE(id, activites_types, num_odre, reac_id) VALUES ( 5,'Concevoir et developper des composants DW',11 ,2 );
insert into ACTIVITE(id, activites_types, num_odre, reac_id) VALUES ( 6,'Concevoir et developper la persistance CDA',8 ,1 );

--
insert into COMPETENCE(id,nom,num_odre, activite_id) values (1, 'Maquetter une aplication cda',1,1 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (2, 'Developper une interface cda',2,3 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (3, 'Mettre en place une base de données',7,1 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (4, 'Developper une interface Dw',4,5 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (5, 'Concevoir une application',10,3 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (6, 'Concevoir une base de donneées DW',5,2 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (7, 'Concevoir une base de donneées CDA',3,6 );
insert into COMPETENCE(id,nom,num_odre, activite_id) values (8, 'Concevoir une base de donneées autre',12,4 );




insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (1, 'environnement de développement DW',4 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (2, 'l’architecture du web et des standards de son organisme de normalisation W3C DW ',5 );

insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (3, 'environnement de développement CDA',2 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (4, 'l’architecture du web et des standards de son organisme de normalisation W3C CDA',3 );

insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (5, 'systèmes de gestion de base de données DW',6 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (6, 'langage de requête pour la base utilisée DW',6 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (7, 'vulnérabilités et des attaques classiques sur les bases de données DW',6 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (8, 'protocoles d’accès et ports utilisés DW',6 );

insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (9, 'Règles orthographiques et grammaticales du francais CDA',7 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (10, 'Conception du modèle entité association CDA',7 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (11, 'Outil de conception entité association de type atelier de génie logitiel CDA',7 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (12, 'Recenser les information du domaine metier CDA',7 );

insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (13, 'environnement de développement cda',1 );
insert into SAVOIR_FAIRE(id,nom, competence_id) VALUES (14, 'environnement de développement autre', 8);


insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (1, '2021-02-08','deroulement commentaire bob formateur',4, 'Diaporama Exercices TD et mini-Projet','Objectif','vsCode','modalite eval DW 1',1,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (2, '2021-03-09','deroulement commentaire DW',4, 'Diaporama Exercices TD et mini-Projet','Objectif','vsCode','modalite eval DW 2',5,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (3, '2021-03-09','deroulement commentaire',4, 'Diaporama Exercices TD et mini-Projet','Objectif','vsCode','modalite eval autre 1',5,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (4, '2021-03-10','deroulement commentaire eval autre  Session CDA',3, 'Démonstrative participative','Objectif ','Rétroprojecteur','modalite eval autre 1',5,1 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (5, '2020-03-10','deroulement com',4, 'Diaporama Exercices TD et mini-Projet','Objectif','vsCode','modalite eval CDA 2',5,1 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (6, '2020-03-10','deroulement com',4, 'Diaporama Exercices TD et mini-Projet','Objectif','vsCode','modalite eval autre 2',4,1 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (7, '2020-03-11','deroulement commentaire',3, 'Diaporama Exercices TD et mini-Projet','Objectif peda','vsCode','modalite eval autre 1',4,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (8, '2020-03-12','deroulement commentaire session 3',3, 'Diaporama Exercices TD et mini-Projet','Objectif peda','vsCode','',5,3 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (9, '2020-03-12','deroulement commentaire',3, 'Diaporama Exercices TD et mini-Projet ','Objectif peda CDA','vsCode','modalite eval CDA 1',4,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (10, '2020-03-12','deroulement commentaire',3, 'Diaporama Exercices TD et mini-Projet ','Objectif peda CDA','vsCode','modalite eval CDA 2',4,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (11, '2020-03-12','deroulement commentaire',3, 'Diaporama Exercices TD et mini-Projet ','Objectif peda DW','vsCode','modalite eval DW 1',4,4 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (12, '2020-03-12','deroulement commentaire',3, 'Diaporama Exercices TD et mini-Projet ','Objectif peda DW','vsCode','modalite eval DW 2',4,4 );

insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (13, '2020-03-01','deroulement commentaire',3, 'Diaporama Exercices TD et mini-Projet ','Objectif peda DW','vsCode','',4,4 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (14, '2020-03-02','',3, 'Diaporama Exercices TD et mini-Projet','Objectif peda CDA','Cour + TD','un champ vide',4,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (15, '2020-03-03','',3, '','','','',4,3 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (16, '2020-03-04','',4, '','','','',4,4 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (17, '2020-03-05','',3, '','','','',4,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (18, '2020-03-08','',4, '','','','',null,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (19, '2020-03-09','',3, '','','','',5,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (20, '2020-03-10','',0, '','','','',null,5 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (21, '2020-03-11','',0, '','','','',null,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (22, '2020-03-12','',0, '','','','',null,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (23, '2020-03-15','',0, '','','','',null,2 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (24, '2020-03-16','',0, '','','','',null,4 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (25, '2020-03-17','',0, '','','','',null,4 );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (26, '2020-03-18','',0, '','','','',null,null );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (27, '2020-03-19','',0, '','','','',null,null );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (28, '2020-03-22','',0, '','','','',null,null );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (29, '2020-03-23','',0, '','','','',null,null );
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (30, '2020-03-24','',0, '','','','',null,null );

insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (31, '2020-03-25','deroulement',3, 'Diaporama','Objectif','Cour + TD','eval',5,1);
insert into SEANCE(id,date_du_jour, deroulement, durer, methode_envisagee, objectif_peda, support_use, evaluation, formateur_id, session_id) VALUES (32, '2020-03-26','deroulement',4, 'Diaporama','Objectif','Cour + TD','eval',5,1);


---------------------------- DWWM ------------------------------

insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 1,1 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 2,2 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 3,1 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 11,6 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 12,7 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 12,13 );

------------------------------ CDA ------------------------------
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 4,3 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 5,4 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 6,3 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 7,9 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 8,9 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 9,10 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 10,12 );

insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 31,14 );
insert into SEANCE_SAVOIR_FAIRES(seances_id, savoir_faires_id) VALUES ( 32,14 );


----------------------------------------------------------------------------------------------

