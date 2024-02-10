# ECSE428-PROJECT

NOTE: CHECK WHATSAPP GROUP DESCRIPTION FOR PASSWORD

### To access the database server locally via terminal (you need to have psql installed on your device first):

run $ PGSSLMODE=require PGPASSWORD=**** psql -U app -h adequately-complete-anchovy.a1.pgedge.io -d defaultdb

### To connect to the database in the backend:
        String host = "adequately-complete-anchovy-iad.a1.pgedge.io";
        String user = "app";
        String database = "defaultdb";
        String sslMode = "require";
        String password = "****";
        String url = String.format("jdbc:postgresql://%s/%s?sslmode=%s", host, database, sslMode);


