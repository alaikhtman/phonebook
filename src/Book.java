import java.util.LinkedList;

/**
 * Created by Sasha on 16.04.15.
 */
public class Book {
    LinkedList<Contact> phoneBook;

    public Book()
    {
        phoneBook = new LinkedList<Contact>();
    }

    public void findName(String name)

    {
        boolean isNameFound = false;

        for (int i = 0; i < phoneBook.size(); i++) {
            if (name.equals(phoneBook.get(i).name)) {
                System.out.println(phoneBook.get(i).name + " " + phoneBook.get(i).phone + " " + phoneBook.get(i).email);
                isNameFound = true;
                break;
            }
        }
        if (isNameFound == false)
        {
            System.out.println("Not found");

        }

    }

    public void preview ()
    {
        for (int i = 0; i < phoneBook.size(); i++)
        {

            System.out.println(phoneBook.get(i).name + " " + phoneBook.get(i).phone + " " + phoneBook.get(i).email);
        }
    }

    public void removeName (String removeName)
    {
        boolean isUserFound = false;
        for (int i = 0; i < phoneBook.size() ; i++)
        {
            if (removeName.equals(phoneBook.get(i).name))
            {
                phoneBook.remove(i);
                System.out.println("Remove Succses");
                System.out.println(phoneBook.get(i).name + " " + phoneBook.get(i).phone + " " + phoneBook.get(i).email);
                isUserFound = true;
            }

        }
        if (isUserFound == false)
        {
            System.out.println("not found");
        }

    }



}