ifeq ($(OS),Windows_NT)
	SEP=;
else
	SEP=:
endif

JFLAGS = -g
JCLASS = -cp "src$(SEP).$(SEP)../junit-4.5.jar"

JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $.java

CLASSES = \
	src/GameT.java \
	src/Display.java \
	src/Controller.java \
	src/TestGameT.java \
	src/Demo.java \

MAIN = TestGameT

default: classes

classes: $(CLASSES:.java=.class)

doc:
	doxygen doxConfig
	cd latex && $(MAKE)

test: src/$(MAIN).class
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore src.$(MAIN)

demo: src/Demo.java
	$(JC) $(JCLASS) $(JFLAGS) src/Demo.java
	$(JVM) src/Demo

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/.class