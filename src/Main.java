import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main

{
    Book  book;

    private static final int PORT = 7022;
    private static final String IP = "localhost";
    private Socket socket;
    private PrintWriter writerToServer;
    private BufferedReader readerFromServer;



    public static void main(String[] args)

    {
        Main program = new Main ();
        program.start ();
    }

    public void start ()
    {
        Scanner scanner  = new Scanner (System. in);

        book = new Book ();

        try
        {
            socket = new Socket (IP, PORT);
            writerToServer= new PrintWriter(socket.getOutputStream());
            readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        while (true)
        {
            System.out.println("1 -add");
            System.out.println("2- delete");
            System.out.println("3 -all");
            System.out.println("4 -find");
            System.out.println("5 - edit");
            System.out.println("6 -exit");

//            try
//            {
//                //contactReader = new BufferedReader(new InputStreamReader(new FileInputStream("contact.txt", true)));
//                String text;
//                text = contactReader.readLine();
//                while ((text = contactReader.readLine())!= null)
//            {
//
//                System.out.println(text);
//
//            }
//
//            }
//            catch (IOException e)
//            {
//                System.err.println("Error");
//            }


            int answer = scanner.nextInt();

            switch (answer)
            {
                case 1:
                    System.out.println("name?");
                    String name = scanner.next();

                    System.out.println("phone?");
                    String phone = scanner.next();

                    System.out.println("email?");
                    String email = scanner.next();

                    Contact contact = new Contact(name, phone, email);
                    book.phoneBook.add(contact);

                    String query = "add:" + contact.name + ":" + contact.phone + ":" + contact.email;



                    writerToServer.println(query);


                    writerToServer.flush();


                    book.preview();


                    break;

                case 2:

                    System.out.println("Remove with Name?");
                    String removeName = scanner.next();

                    book.removeName(removeName);

                    break;

                case 3:

//                    try
//                    {
                        //writerToServer = new PrintWriter(socket.getOutputStream());
                        writerToServer.println("getAllContact");
                        writerToServer.flush();

//                        readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        String dataFromServer;
//
//                        dataFromServer = readerFromServer.readLine();
//
//
//                        while (dataFromServer != null)
//                        {
//                            System.out.println(dataFromServer);
//                        }




//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }






                    break;

                case 4:


                    System.out.println("Find with Name?");
                    String findName = scanner.next();
                    book.findName(findName);

                    break;

                case 5:
                    System.out.println("What Name edit?");
                    String editName = scanner.next();

                    boolean isNameFound = false;
                    for (int i = 0; i < book.phoneBook.size(); i++)
                    {
                        if (editName.equals(book.phoneBook.get(i).name))
                        {
                            System.out.println(book.phoneBook.get(i).name + " " + book.phoneBook.get(i).phone + " " + book.phoneBook.get(i).email);
                            isNameFound = true;
                            System.out.println("What edit?");
                            System.out.println("1-Name, 2 - Phone, 3- Email, 4 - all");
                            int field = scanner.nextInt();

                            if (field == 1) {
                                System.out.println("New name:");
                                String newName = scanner.next();
                                book.phoneBook.get(i).name = newName;
                                System.out.println(book.phoneBook.get(i).name + " " + book.phoneBook.get(i).phone + " " + book.phoneBook.get(i).email);
                                break;
                            } else if (field == 2)

                            {
                                System.out.println("New phone:");
                                String newPhone = scanner.next();
                                book.phoneBook.get(i).phone = newPhone;
                                System.out.println(book.phoneBook.get(i).name + " " + book.phoneBook.get(i).phone + " " + book.phoneBook.get(i).email);
                                break;
                            } else if (field == 3) {
                                System.out.println("New email:");
                                String newEmail = scanner.next();
                                book.phoneBook.get(i).email = newEmail;
                                System.out.println(book.phoneBook.get(i).name + " " + book.phoneBook.get(i).phone + " " + book.phoneBook.get(i).email);
                                break;
                            } else if (field == 4) {
                                System.out.println("New name:");
                                String newName = scanner.next();
                                System.out.println("New phone:");
                                String newPhone = scanner.next();
                                System.out.println("New email:");
                                String newEmail = scanner.next();
                                book.phoneBook.get(i).name = newName;
                                book.phoneBook.get(i).phone = newPhone;
                                book.phoneBook.get(i).email = newEmail;
                                System.out.println(book.phoneBook.get(i).name + " " + book.phoneBook.get(i).phone + " " + book.phoneBook.get(i).email);
                                break;
                            } else {
                                System.out.println("Erorr field");
                            }
                        }
                    }
                    if (isNameFound ==false)
                    {
                        System.out.println("not found");
                    }
                    break;

                case 6:
                    try
                    {

                        //writerToServer = new PrintWriter(socket.getOutputStream());
                        writerToServer.print("stop");


                        writerToServer.flush();
                        writerToServer.close();
                        socket.close();

                    }

                    catch (IOException e)
                    {
                        e.printStackTrace();
                        writerToServer.flush();
                        writerToServer.close();
                        //socket.close();
                    }
                    System.exit(0);
                    break;

                default:
                    System.out.println("Error");


            }

        }
    }
}