mvn clean compile
mvn exec:java -Dexec.executable="app.Main" -Dlog4j.configuration="file:log4j.properties"

