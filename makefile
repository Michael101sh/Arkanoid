compile:bin
	javac -d bin -cp biuoop-1.3.jar src/*/*.java src/*.java
bin:
	mkdir bin
run:
	java -jar ass6game.jar
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*/*.java src/*java
jar: 
	jar cfm ass6game.jar Manifest.mf -C bin . -C resources .
