import MyFirstPackage.*;

// javac MyFirstProgram.java
// java MyFirstClass

// -d <путь к папке>			где будут распологаться файлы .class
// -classpath <путь к папке>	расположение файлов .java

class MyFirstClass {

	public static void main(String[] args) {

		MyFirstPackage secondClass = new MyFirstPackage(10);

		secondClass.setItem(0, 666);
		double avr = secondClass.average();

		System.out.println(avr);

		secondClass.print();
    }
}