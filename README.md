# Strengths Service
----------------------

## About

A service for exploring the [StrengthsFinder](http://strengths.gallup.com/110440/About-StrengthsFinder-20.aspx) strengths of a team.
    
## Developer getting Started

### Build the application

Use the provided gradle wrapper to build a fat jar

    > ./gradlew shadowJar

### Setup the database

Before running the app for the first time, you'll need to set up the
database schema

1. Create an empty PostgreSQL database
2. Update config.yml to reference your postgres instance and rebuild the application
    
    > ./gradlew shadowJar
    
3. Run the database migrations

    > java -jar build/libs/strengths-service-1.0.0-SNAPSHOT-all.jar db migrate build/resources/main/config.yml

### Run the application

With gradle

    > ./gradlew run
    
With java

    > java -jar build/libs/strengths-service-1.0.0-SNAPSHOT-all.jar server build/resources/main/config.yml
    
### Environment specific overrides

You can override any config by passing a java system property when running

    > java
        -Ddw.database.url=jdbc:postgresql://proddb/strengths \
        -Ddw.database.user=produser \
        -Ddw.database.password=secret \    
        -jar build/libs/strengths-service-1.0.0-SNAPSHOT-all.jar server build/resources/main/config.yml
