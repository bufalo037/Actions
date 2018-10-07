.PHONY: build clean run

build: tema2

run:
	#java -Xmx1G -jar Tema2.jar  
	java -Xmx1G Tema2
# ${ARGS}
	
tema2:
	javac *.java
#	jar cfe Tema2.jar Tema2 *.class
	jar cfe Tema2.jar Tema2 Tema2.class

clean:
	rm -f *.class