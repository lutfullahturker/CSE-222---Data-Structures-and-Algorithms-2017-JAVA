
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
        /**  Çalışma süresini hesaplamak için Test amaçlı olduğundan dolayı
         * ilk ödevdeki mainimi çok değiştirmeden kullanıcıdan input almak yerine
         * hazır testler girdim.Programın başında milisaniye yi kaydettim ve sonunda
         * da tekrar hesaplayıp ilkinden çıkarınca çalışma süresini bulmuş oldum.
         * */
            long start,finish ;
            start = System.currentTimeMillis() ;
            Staff TestStaff = new Staff ("Test Staff");
            ForLibraryStaffs ForStaff = new ForLibraryStaffs();
            ForLibraryUsers ForUsers = new ForLibraryUsers();
            int input = 2;
            int UserType;
            int ID;
            int index = 0;
            
            
            
            boolean Found = false;
            //while (input != 1 && input != 0){
                System.out.println("Please log in.\nFor Users : Press '0'\nFor Staffs : Press '1'\nFor Exit : Press '-1'");
                input = 1;
                System.out.println(input);
                if(input != 1 && input != 0 && input != -1)
                    System.out.println("You have entered wrong number !");
                else if (input == -1)
                    exit(0);
                else{
                    System.out.println("Please Enter Your ID .(FOR TESTING : STAFF ID = '20001')");
                    ID = 20001;
                    System.out.println(ID);
                    if (input == 0){
                        for (int i = 0;i<Staff.RegisteredUserNum;++i)
                            if(Staff.UserArr.get(i).GetUserID() == ID){
                                Found = true;
                                index = i;
                            }
                        if (Found == true){}
                        //    break;
                        else{
                            System.out.println("The ID is not found in registered users list ! Please try again.");
                            input = 2;
                        }
                    }
                    else if (input == 1 && TestStaff.getStaffID() == ID){}
                     //   break;
                    else{
                        System.out.println("The ID is not found in registered users list ! Please try again.");
                        input = 2;
                    }
                }
           // }
            UserType = input;
            //while (input != -1)
            {
                switch (UserType){
                    case 0:
                        System.out.println("Hello "+Staff.UserArr.get(index).GetName()+"\nFor learn books you borrowed : Press '1'\nFor borrow a book : Press '2'\nFor return a book : Press '3'\nFor exit : Press '-1'");
                        input = 2;
                        System.out.println(input);
                        switch (input){
                            case 1:
                                for ( int i = 0;i<Staff.UserArr.get(index).GetBorrowedBooks().size();++i)
                                    System.out.println(Staff.UserArr.get(index).GetBorrowedBooks().get(i).GetName());
                                if (Staff.UserArr.get(index).GetBorrowedBooks().isEmpty())
                                    System.out.println("\nThere are no books you have borrowed from the library at the moment.\n");
                                else
                                    System.out.printf("\n");
                                break;
                            case 2:
                                System.out.println("Please enter a book name you want to borrow.");
                                String BookName ;
                                BookName = "Sefiller";
                                System.out.println(BookName);
                                System.out.println();
                                ForUsers.BorrowBook(Staff.UserArr.get(index), BookName);
                                System.out.println();
                                break;
                            case 3:
                                System.out.println("Please enter the book name you want to return.");
                                String BookName2 ;
                                BookName2 = "Sefiller";
                                System.out.println(BookName2);
                                System.out.println();
                                ForUsers.ReturnBook(Staff.UserArr.get(index), BookName2);
                                System.out.println();
                                break;
                            case -1:
                                System.out.println("Thanks for using our library .");
                                exit(0);
                            default:
                                System.out.println("\nYou have entered wrong number !\n");
                        }
                        break;
                    case 1:
                        System.out.println("Hello "+TestStaff.GetName()+"\nFor show all registered users in the system : Press '1'\nFor add a new user : Press '2'\nFor add a book : Press '3'\nFor remove a book : Press '4'\nFor exit : Press '-1'");
                        input = 2;
                        System.out.println(input);
                        switch(input){
                            case 1:
                                System.out.println();
                                for (int i = 0;i<Staff.RegisteredUserNum;++i)
                                    System.out.println("User ID : " + Staff.UserArr.get(i).GetUserID()+"   User Name : "+Staff.UserArr.get(i).GetName());
                                if (Staff.RegisteredUserNum ==0)
                                    System.out.println("Could not find any registered user on the system .Please add a new user .");
                                System.out.println();
                                break;
                            case 2:
                                System.out.println("\nPlease enter a Username for new user .");
                                String UserName ;
                                UserName = "Deneme";
                                System.out.println(UserName);
                                TestStaff.AddUser(UserName);
                                System.out.println("\nAdding a new user operation is successfully completed.\nNew user's ID : "+Staff.UserArr.get(Staff.RegisteredUserNum-1).GetUserID()+"  New user's Name : "+Staff.UserArr.get(Staff.RegisteredUserNum-1).GetName()+"\n");
                                break;
                            case 3:
                                System.out.println("Please enter a Book Name for add a new book .");
                                String BookName ;
                                BookName = "Sefiller";
                                System.out.println(BookName);
                                System.out.println();
                                ForStaff.AddBook(TestStaff, BookName);
                                System.out.println();
                                break;
                            case 4:
                                System.out.println("Please enter a Book Name for remove a new book .");
                                String BookName2 ;
                                BookName2 = "Sefiller";
                                System.out.println();
                                ForStaff.RemoveBook(TestStaff, BookName2);
                                System.out.println();
                                break;
                            case -1:
                                System.out.println("Logout was successfully completed .");
                                exit(0);
                            default:
                                System.out.println("\nYou have entered wrong number !\n");
                        }
                        break;
                    default:
                        throw new Exception("Unexpected error detected ! Please restart the program !");
                }
            }
            
            ForStaff.AddBook(TestStaff, "Sefiller");
            System.out.println("Please log in.\nFor Users : Press '0'\nFor Staffs : Press '1'\nFor Exit : Press '-1'");
            System.out.println("0");
            System.out.println("Please Enter Your ID .(FOR TESTING : STAFF ID = '20001')");
            System.out.println("10001");
            
            System.out.println("For learn books you borrowed : Press '1'\nFor borrow a book : Press '2'\nFor return a book : Press '3'\nFor exit : Press '-1'");
            System.out.println("2");
            
            System.out.println("Please enter a book name you want to borrow.");
            System.out.println("Sefiller");
            ForUsers.BorrowBook(Staff.UserArr.get(0), "Sefiller");
            finish = System.currentTimeMillis() - start ;
            System.out.println("\nThe Program Running Time in Millisecond is "+finish);
        }
        catch (Exception e){
            System.out.println("Program Execution Failed !\n"+e);
        }
    }
}
