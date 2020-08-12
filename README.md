# OCR - Projet 10

# Besoins du Client

Ticket#1 : Ajoutez un système de réservation d’ouvrages (par le client).

Ticket#2 : Corrigez un bug dans la gestion des prolongations de prêt (par le client).

Ticket#3 : Mettez en place une stratégie de tests (par le Tech Lead).



# Déploiement de l'application

## Préparation de la base de données
Les composants nécessaires lors du développement sont disponibles via des conteneurs docker. L'environnement de développement est assemblé
grâce à docker-compose (cf docker/dev/docker-compose.yml). 

Il comporte : 

une base de données PostgreSQL contenant un jeu de données de démo (postgresql://127.0.0.1:9032/) 

`Lancement` 

cd docker/dev

docker-compose up

`Arrêt` 

cd docker/dev

docker-compose stop

`Remise à zero`

cd docker/dev

docker-compose stop

docker-compose rm -v

docker-compose up
   
    
 
### Préchargement de données
Afin de permettre l'utilisation du système à l'issue de son démarrage, des données sont préchargées au démarrage du système.
Le framework Flyway se chargera de créer les tables de base de données et de les remplir de données au démarrage de chaque microservice.

Le préchargement des données se déroule en 2 étapes.

* La première étape est la création du modèle de persistance dans la Base de Données.
Ces modèles de persistances sont présentes sous forme de statements SQL dans le fichier _NOM_DE_MICROSERVICE/src/main/resources/db/migration/V1__Init_DB.sql_.

* La deuxième est préchargement de jeu de données de démo.
Ces données sont présentes dans le fichier _NOM_DE_MICROSERVICE/src/main/resources/db/migration/V3__Test_data.sql_
      
### Configuration du module "microservice-batch"
Le service doit être configuré pour pouvoir envoyer des lettres électroniques.
Pour ce faire, modifiez les lignes 

 _spring.mail.host=_
 
 _spring.mail.username=_
 
 _spring.mail.password=_
 
 _spring.mail.port=_
 
 _spring.mail.protocol=_
 
 dans le fichier _config-server/src/main/resources/shared/microservice-batch.properties_


##  Déploiement 

La procédure est la suivante :
1) cloner le projet gitlab
2) S'assurer que la version de Java utilisée est la version 11 et que Maven est installé
3) Exécuter la ligne de commande dans le répertoire racine de chaque module du projet : `mvn clean package spring-boot:run` 

    Respectez l'ordre de démarrage suivant :

    1. config-server
    2. eureka-server
    3. zuul-server
    4. microservice-document
    5. microservice-fileserver
    6. microservice-loan
    7. microservice-user
    8. microservice-reservation
    9. client
    10. microservice-batch

4) Ouvrir un browser web à l'adresse http://localhost:9099

    
## Jeu de donné de démo
Les utilisateurs enregistrés dans la base de données.

Email :`ocr.proj07@yandex.ru` Mot de pass :`user`
 
Email :`user2@user.com` Mot de pass :`user`

Email :`admin@admin.com` Mot de pass :`user`



# Description technique

L'application utilise les frameworks & projets suivants:

1) _SpringBoot 2.2.6.RELEASE_ 

2) _Spring MVC_ 

3) _Spring Data_ 

4) _Spring Security_ & _spring-cloud-starter-oauth2_ & _spring-cloud-starter-security_

5) _PostgreSQL_ 

6) _Flyway_ (

7) _Thymeleaf_ & _Thymeleaf Spring Security_ 

8) _Bootstrap_ 

9) _OpenFeign_ 

10) _Eureka_

11) _Ribbon_

12) _Zuul_

13) _Spring Cloud Starter Config_

14) _Spring Batch_

15) _QueryDsl_ 

