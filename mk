javac -d out -cp /Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home/jre/../lib/tools.jar src/anno/*.java
javac -d out -cp out:/Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home/jre/../lib/tools.jar -processor anno.CommentIsStringProcessor testing/src/sample/*.java
