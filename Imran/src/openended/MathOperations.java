package openended;

public class MathOperations {
//    public void calculateAndPrint() {
//        for (int i = 1; i <= 5; i++) {
//            System.out.println("Square of " + i + " is: " + (i * i));
//        }
//    }
	public void printSquares(int count) {
        for (int i = 1; i <= count; i++) {
            System.out.println("Square of " + i + " is: " + calculateSquare(i));
        }
    }

    private int calculateSquare(int number) {
        return number * number;
    }
    public static void main(String[] args) {
    	MathOperations mathOps = new MathOperations();
    	mathOps.printSquares(5);
	}
}