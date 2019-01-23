public class Main {
    public static void main(String[] args) {

        String[] words = {"apple","avocado","grape","banana","orange","apple", "fig","avocado", "pear", "plum", "orange","orange"};
        controllerPrintArray(words);

        PhoneBook book = new PhoneBook();
        Person per1 = new Person("Ivan", "89080");
        Person per2 = new Person("Petr", "75786");
        Person per3 = new Person("Petr", "90993786");
        book.addPerson(per1);
        book.addPerson(per2);
        book.addPerson(per3);
        book.getPerson("Petr");
    }
    public static void controllerPrintArray(String[] array){
        int [] controllerNumber = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int number = 0;
            if(controllerNumber[i] == 1){
                continue;
            }
            for (int j = i; j < array.length; j++) {
                if(array[i].equals(array[j])){
                    number += 1;
                    controllerNumber[j] = 1;
                }
            }
            System.out.println(array[i] + "-" + number);
        }
    }
}
