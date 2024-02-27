# ECSE428-PROJECT

NOTE: CHECK WHATSAPP GROUP DESCRIPTION FOR PASSWORD

### To access the database server locally via terminal (you need to have psql installed on your device first):

run $ PGSSLMODE=require PGPASSWORD=***** psql -U admin -h arguably-massive-yeti.a1.pgedge.io -d defaultdbb

### To connect to the database in the backend:
        String host = "arguably-massive-yeti.a1.pgedge.io";
        String user = "admin";
        String database = "defaultdb";
        String sslMode = "require";
        String password = "****";
        String url = String.format("jdbc:postgresql://%s/%s?sslmode=%s", host, database, sslMode);


### To run the frontend, go to the frontend directory on terminal and run:
        npm start



