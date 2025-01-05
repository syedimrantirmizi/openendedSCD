package openended;

public class MathOperations {
    private SquareCalculator squareCalculator;
    public MathOperations() {
        squareCalculator = new SquareCalculator();
    }
	public void printSquares(int count) {
        for (int i = 1; i <= count; i++) {
            System.out.println("Square of " + i + " is: " + squareCalculator.calculateSquare(i));
        }
    }

    
    public static void main(String[] args) {
    	MathOperations mathOps = new MathOperations();
    	mathOps.printSquares(5);
	}
}