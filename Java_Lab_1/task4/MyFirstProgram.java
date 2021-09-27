// javac MyFirstProgram.java
// java MyFirstClass

class MyFirstClass {
    public static void main(String[] args) {

		MySecondClass secondClass = new MySecondClass(10);

		secondClass.setItem(0, 666);
		
		double avr = secondClass.average();
		System.out.println(avr);

		secondClass.print();
    }
}


class MySecondClass {
	private int mass[];

	public int getItem (int i){
		return mass[i];
	}

	public void setItem(int i, int value){
		mass[i] = value;
	}

	public double average(){
		double avr = 0;
		for (int i = 0; i < mass.length; i++){
			avr += mass[i];
		}
		avr /= mass.length;
		return avr;
	}

	public void print(){
		for (int i = 0; i < mass.length; i++){
			System.out.println(mass[i]);
		}
	}

	public MySecondClass (int i){
		mass = new int[i];

		for(int j = 0; j < i; j++){
			mass[j] = (int)(Math.random() * 10);
		}
	}
}