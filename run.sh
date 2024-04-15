#!/bin/bash
mvn compile
mvn package

java --module-path ~/eclipse-workspace/javafx-sdk-22/lib --add-modules javafx.controls,javafx.fxml -jar target/gestion-de-stock-1.0-SNAPSHOT-jar-with-dependencies.jar
