javac -d target -cp target:/Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home/jre/../lib/tools.jar src/test/*.java
javac -d target -cp target:/Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home/jre/../lib/tools.jar -processor test.CommentIsStringProcessor src/test/*.java
