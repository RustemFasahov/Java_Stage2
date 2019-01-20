import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    List<Person> book = new ArrayList<>();

    public void addPerson (Person p){
        book.add(p);
    }

    public void getPerson (String name){
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getName().equals(name)){
                System.out.println(name + " telephone: " + book.get(i).getNumber());
            }
        }
    }
}
