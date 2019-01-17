import java.util.Arrays;

public class Test {

    public static void main(String[] args)  {
        String[][] array = new String[4][4];
        addNumberForArray(array);
        array[2][2] = "D";
        printArray(array);
        try {
            addArray(array);
        } catch (MyArrayDataException e) {
            e.cellNumber();
        } catch (MyArraySizeException e) {
            System.out.println("Длинна массива больше 4");;
        }
    }

    public static void addNumberForArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int number = (int) (Math.random() * 10);
                array[i][j] = String.valueOf(number);
            }
        }
    }

    public static void printArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void addArray(String[][] array) throws MyArrayDataException, MyArraySizeException {
        if (array.length > 4) {
            throw new MyArraySizeException();
        }
            int number = 0;
            int number1 = 0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if(array[i][j].matches("[A-Z]+") == true){
                        throw new MyArrayDataException(i,j);
                    }
                    number1 = Integer.parseInt(array[i][j]);
                    number += number1;
                }
            }
        }
}


