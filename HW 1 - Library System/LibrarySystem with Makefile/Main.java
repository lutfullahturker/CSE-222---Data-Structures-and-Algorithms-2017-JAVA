import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author Lütfullah TÜRKER  141044050
 */
public class Main {

    /**
     * @param args the command line arguments
     * <ul><li>Kütüphane Sistemi Programızın menüsü while döngüleri ve switchler ile main de yapıldı.
     * <li>Program çalışırken olası hata durumlarının hepsi düşünüldü Exception lar fırlatıldı ve mainde yakalanıp ekrana basıldı.</ul>
     */
    public static void main(String[] args) {
        try
        {
            Staff TestStaff = new Staff ("Test Staff");
            ForLibraryStaffs ForStaff = new ForLibraryStaffs();
            ForLibraryUsers ForUsers = new ForLibraryUsers();
            int input = 2;
            int UserType;
            int ID;
            int index = 0;
            boolean Found = false;
            Scanner read = new Scanner(System.in);
            BufferedReader readLine = new BufferedReader(new InputStreamReader(System.in));
            while (input != 1 && input != 0){
                System.out.println("Please log in.\nFor Users : Press '0'\nFor Staffs : Press '1'\nFor Exit : Press '-1'");
                input = read.nextInt();
                if(input != 1 && input != 0 && input != -1)
                    System.out.println("You have entered wrong number !");
                else if (input == -1)
                    exit(0);
                else{
                    System.out.println("Please Enter Your ID .(FOR TESTING : STAFF ID = '20001')");
                    ID = read.nextInt();
                    if (input == 0){
                        for (int i = 0;i<Staff.RegisteredUserNum;++i)
                            if(Staff.UserArr[i].GetUserID() == ID){
                                Found = true;
                                index = i;
                            }
                        if (Found == true)
                            break;
                        else{
                            System.out.println("The ID is not found in registered users list ! Please try again.");
                            input = 2;
                        }
                    }
                    else if (input == 1 && TestStaff.getStaffID() == ID)
                        break;
                    else{
                        System.out.println("The ID is not found in registered users list ! Please try again.");
                        input = 2;
                    }
                }
            }
            UserType = input;
            while (input != -1)
            {
                switch (UserType){
                    case 0:
                        System.out.println("Hello "+Staff.UserArr[index].GetName()+"\nFor learn books you borrowed : Press '1'\nFor borrow a book : Press '2'\nFor return a book : Press '3'\nFor exit : Press '-1'");
                        input = read.nextInt();
                        switch (input){
                            case 1:
                                for ( int i = 0;i<Staff.UserArr[index].GetBorrowedBooks().size();++i)
                                    System.out.println(Staff.UserArr[index].GetBorrowedBooks().get(i).GetName());
                                if (Staff.UserArr[index].GetBorrowedBooks().isEmpty())
                                    System.out.println("\nThere are no books you have borrowed from the library at the moment.\n");
                                else
                                    System.out.printf("\n");
                                break;
                            case 2:
                                System.out.println("Please enter a book name you want to borrow.");
                                String BookName ;
                                BookName = readLine.readLine();
                                System.out.println();
                                ForUsers.BorrowBook(Staff.UserArr[index], BookName);
                                System.out.println();
                                break;
                            case 3:
                                System.out.println("Please enter the book name you want to return.");
                                String BookName2 ;
                                BookName2 = readLine.readLine();
                                System.out.println();
                                ForUsers.ReturnBook(Staff.UserArr[index], BookName2);
                                System.out.println();
                                break;
                            case -1:
                                System.out.println("Thanks for using our library .");
                                exit(0);
				break;
                            default:
                                System.out.println("\nYou have entered wrong number !\n");
                        }
                        break;
                    case 1:
                        System.out.println("Hello "+TestStaff.GetName()+"\nFor show all registered users in the system : Press '1'\nFor add a new user : Press '2'\nFor add a book : Press '3'\nFor remove a book : Press '4'\nFor exit : Press '-1'");
                        input = read.nextInt();
                        switch(input){
                            case 1:
                                System.out.println();
                                for (int i = 0;i<Staff.RegisteredUserNum;++i)
                                    System.out.println("User ID : " + Staff.UserArr[i].GetUserID()+"   User Name : "+Staff.UserArr[i].GetName());
                                if (Staff.RegisteredUserNum ==0)
                                    System.out.println("Could not find any registered user on the system .Please add a new user .");
                                System.out.println();
                                break;
                            case 2:
                                System.out.println("\nPlease enter a Username for new user .");
                                String UserName ;
                                UserName = readLine.readLine();
                                TestStaff.AddUser(UserName);
                                System.out.println("\nAdding a new user operation is successfully completed.\nNew user's ID : "+Staff.UserArr[Staff.RegisteredUserNum-1].GetUserID()+"  New user's Name : "+Staff.UserArr[Staff.RegisteredUserNum-1].GetName()+"\n");
                                break;
                            case 3:
                                System.out.println("Please enter a Book Name for add a new book .");
                                String BookName ;
                                BookName = readLine.readLine();
                                System.out.println();
                                ForStaff.AddBook(TestStaff, BookName);
                                System.out.println();
                                break;
                            case 4:
                                System.out.println("Please enter a Book Name for remove a new book .");
                                String BookName2 ;
                                BookName2 = readLine.readLine();
                                System.out.println();
                                ForStaff.RemoveBook(TestStaff, BookName2);
                                System.out.println();
                                break;
                            case -1:
                                System.out.println("Logout was successfully completed .");
                                exit(0);
				break;
                            default:
                                System.out.println("\nYou have entered wrong number !\n");
                        }
                        break;
                    default:
                        throw new Exception("Unexpected error detected ! Please restart the program !");
                }
            }
        }
        catch (Exception e){
            System.out.println("Program Execution Failed !\n"+e);
        }
    }
}
