import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Lütfullah TÜRKER  141044050
 */

@SuppressWarnings("try")
public class ForLibraryUsers extends LibraryOperations {

    
    ForLibraryUsers(){
    }
    
    /**
     *
     * @param Borrower Kitap almak isteyen kişiyi polymorphic çağrı ile alıyoruz. ve içerde User mı Staff mı kontrolü yaparak polymorphizm uyguluyoruz.
     * @param BookNameOrAuthor Ödünç alınmak istenen kitabın Adı veya yazarını  alıp içerde bu kitap var mı yok mu diye kontrol ediyoruz.
     * @throws Exception Hata sonucu kitabı alamama gibi durumlarda kullanıcıyı bilgilendirerek Exception fırlatıyoruz.
     * Ve işlem başarılı olduğunda bunu kullanıcıya bildiyiroruz.
     */
    public void BorrowBook(LibrarySystemsUsers Borrower,String BookNameOrAuthor) throws Exception
    {
        boolean Found = false;
        int index = 0 ;
        if (Borrower.GetUserType() == 1)
            throw new Exception("Staffs cannot borrow a book.");
        for (int i=0 ;i<BookArr.size();++i)
            if (BookArr.get(i).GetAuthor().equals(BookNameOrAuthor) || BookArr.get(i).GetName().equals(BookNameOrAuthor)){
                Found = true;
                index = i ;
            }
        if (Found == false || BookArr.get(index).GetHowManyInLibrary() <= 0 ){
            System.out.println("Sorry,The Book you search is not found in library.");
            return ;
        }
        User BorrowerUser = (User) Borrower ;
        for (int i = 0;i<BorrowerUser.GetBorrowedBooks().size();++i)
            if (BorrowerUser.GetBorrowedBooks().get(i).GetName().equals(BookNameOrAuthor))
            {
                System.out.println("Sorry, You have already taken this book.");
                return;
            }
        BookArr.get(index).DecreaseHowManyInLibrary();
        BorrowerUser.AddBorrowedBook(BookArr.get(index));
        
        
        File  CSVFile = new File("BorrowedBooks.csv");
        if (!CSVFile.exists()) {
                CSVFile.createNewFile();
            try (FileWriter fileWriter = new FileWriter(CSVFile, true)) {
                fileWriter.write("USER ID;USER NAME;BOOK ID;BOOK NAME;");
                fileWriter.close();
            }
        }
        FileWriter fileWriter = new FileWriter(CSVFile, true);
        try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
            
            bWriter.write("\n"+BorrowerUser.GetUserID()+";"+BorrowerUser.GetName()+";"+BookArr.get(index).GetBookID()+";"+BookArr.get(index).GetName()+";");
            bWriter.close();
        }
        
        File  CSVFile2 = new File("Books.csv");
        if (!CSVFile2.exists()) {
                CSVFile2.createNewFile();
        }
        FileWriter fileWriter2 = new FileWriter(CSVFile2, false);
        try (BufferedWriter bWriter2 = new BufferedWriter(fileWriter2)) {
            bWriter2.write("BOOK ID;BOOK NAME;HOW MANY ARE IN LIBRARY");
             for (int i = 0;i<BookArr.size();++i)
                bWriter2.write("\n"+BookArr.get(i).GetBookID()+";"+BookArr.get(i).GetName()+";"+BookArr.get(i).GetHowManyInLibrary()+";");
            bWriter2.close();
        }
        
        System.out.println("The book borrowing process has been successfully completed.");
    }
    
    /**
     *
     * @param Returner Kitap geri vermek isteyen kişiyi polymorphic çağrı ile alıyoruz. ve içerde User mı Staff mı kontrolü yaparak polymorphizm uyguluyoruz.
     * @param BookNameOrAuthor Geri verilmek istenen kitabın Adı veya yazarını  alıp içerde bu kitap var mı yok mu diye kontrol ediyoruz.Varsa da bu kitap kullanıcının daha önceden almış olduğu bir kitap mı diye kontrol ederek ona göre işlemler uyguluyoruz.
     * @throws Exception Hata sonucu kitabı geri verememe gibi durumlarda kullanıcıyı bilgilendirerek Exception fırlatıyoruz.
     * Ve işlem başarılı olduğunda bunu kullanıcıya bildiyiroruz.
     */
    public void ReturnBook(LibrarySystemsUsers Returner,String BookNameOrAuthor) throws Exception
    {
        boolean Found = false;
        int index = 0 ;
        if (Returner.GetUserType() == 1)
            throw new Exception("Staffs cannot borrow or return a book.");
        for (int i=0 ;i<BookArr.size();++i)
            if (BookArr.get(i).GetAuthor().equals(BookNameOrAuthor) || BookArr.get(i).GetName().equals(BookNameOrAuthor)){
                Found = true;
                index = i ;
            }
        
        if (Found == false){
            System.out.println("Sorry,The Book you search is not found in library.");
            return ;
        }
        User ReturnerUser = (User) Returner ;
        Found = false ;
        for (int i =0;i<ReturnerUser.GetBorrowedBooks().size();++i)
            if (ReturnerUser.GetBorrowedBooks().get(i).GetName().equals(BookNameOrAuthor) || ReturnerUser.GetBorrowedBooks().get(i).GetAuthor().equals(BookNameOrAuthor))
                Found = true;
        if (Found == false)
        {
            System.out.println("Sorry,The book you have given does not belong to this library and if you want to donate this book We cannot accept book donation.");
            return ;
        }
        
        ForLibraryStaffs ForAddBook = new ForLibraryStaffs();
        Staff TakeBook = new Staff("Temp Staff");
        ForAddBook.AddBook(TakeBook,BookNameOrAuthor);
        
        ReturnerUser.ReturnBook(BookArr.get(index));
        
        File  CSVFile = new File("BorrowedBooks.csv");
        if (!CSVFile.exists()) {
                CSVFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(CSVFile, false);
        try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
            bWriter.write("USER ID;USER NAME;BOOK ID;BOOK NAME;");
             for (int i = 0;i<Staff.RegisteredUserNum;++i)
                 for(int j =0;j<Staff.UserArr[i].GetBorrowedBooks().size();++i)
                    bWriter.write("\n"+Staff.UserArr[i].GetUserID()+";"+Staff.UserArr[i].GetName()+";"+Staff.UserArr[i].GetBorrowedBooks().get(j).GetBookID()+";"+Staff.UserArr[i].GetBorrowedBooks().get(j).GetName() +";");
                bWriter.close();
        }
    }
}
