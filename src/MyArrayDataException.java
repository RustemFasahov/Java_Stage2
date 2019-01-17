
public class MyArrayDataException extends Exception {
    private int number1;
    private int number2;

    public MyArrayDataException (int number1, int number2){
        this.number1 = number1;
        this.number2 = number2;
    }
    public void cellNumber(){
        System.out.printf("В ячейке массива %d, %d, не число.", number1, number2);
    }
}
