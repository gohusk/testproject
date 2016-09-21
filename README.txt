Simple wordchecker app. To build, you will need maven and 1.8 jdk
installed.

INSTALLATION INSTRUCTIONS
The app can be run by running main method in Puzzle.java using the traditional method in whatever IDE you are using.
You can also build from the command line with:
mvn clean compile assembly:single
this will create an executagble jar file in the target directory named: wordchecker-{version}.jar. To run from the
executable jar type: java -jar wordchecker-{version}.jar. That will run the application

mvn clean install from the root of the project will execute all tests and build.
