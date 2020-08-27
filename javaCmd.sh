rm -rf simulation.txt

javac $(find . -name "*.java")

java -cp . justine/simulator/Main scenario.txt 

rm $(find . -name "*.class")

# find -name *.java > sources.txt
# javac -sourcepath  @sources.txt
# java justine/simulator/Main scenario.txt