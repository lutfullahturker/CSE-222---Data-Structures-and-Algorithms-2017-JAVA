

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Lütfullah TÜRKER  141044050
 */
public class ForLibraryStaffs extends LibraryOperations{
    
    
    private static boolean isFirst = true;

    /**
     * Program açıldığında daha önceden CSV dosyalarına yazılmış olan Books.csv deki ve BorrowedBooks.csv deki bilgileri okuyup BookArrayimiz gibi gerekli olan
     * tüm data member larımıza dosyadan okuyarak yazıyoruz.
     * @throws Exception Dosya okurken veya okuyup arrayleri doldururken hata ile karşılaşılması durumunda Exception fırlatıyoruz.
     */
    ForLibraryStaffs() throws Exception{
        if (isFirst == true)
        {
            isFirst = false;
            ReadBookFiles();
        }
    }
    
    private void ReadBookFiles() throws Exception {
        File  BooksFile = new File("Books.csv"); 
        File BorrowedBooksFile = new File("BorrowedBooks.csv");
        if (!BooksFile.exists())
            return ;
        try{
            int count = 0;
            String Temp;
            String[] SplitArr;
            Scanner Read = new Scanner(BooksFile);
            Temp = Read.nextLine();
            while (Read.hasNextLine())
            {
                Temp = Read.nextLine();
                SplitArr = Temp.split(";");
                    BookArr.add(new Book (SplitArr[1]));
                    BookArr.get(count++).BookID = Integer.parseInt(SplitArr[0]);
                    int HowMany = Integer.parseInt(SplitArr[2]);
                    while(BookArr.get(count-1).GetHowManyInLibrary() < HowMany)
                        BookArr.get(count-1).IncreaseHowManyInLibrary();
                    if (HowMany == 0)
                        BookArr.get(count-1).DecreaseHowManyInLibrary();
                    if (HowMany != BookArr.get(count-1).GetHowManyInLibrary())
                        throw new Exception ("Error ! The database and the program could not be synchronized.");
            }
            int MaxBookID = BookArr.get(0).GetBookID();
            for (int i = 0;i<BookArr.size();++i)
                if (BookArr.get(i).GetBookID() > MaxBookID)
                    MaxBookID = BookArr.get(i).GetBookID() ;
            Book.BookIDCount = MaxBookID+1 ;
            
            
            if (!BorrowedBooksFile.exists())
                return;
            Scanner Read2 = new Scanner(BorrowedBooksFile);
            String Temp2;
            String[] SplitArr2;
            Temp2 = Read2.nextLine();
            while (Read2.hasNextLine())
            {
                Temp2 = Read2.nextLine();
                SplitArr2 = Temp2.split(";");
                int IndexofUserID = -1;
                int IndexofBookID = -1;
                for (int i =0;i<Staff.RegisteredUserNum;++i)
                    if (Staff.UserArr.get(i).GetUserID() == Integer.parseInt(SplitArr2[0]))
                        IndexofUserID = i;
                for (int j = 0;j<BookArr.size();++j)
                    if (BookArr.get(j).GetBookID() == Integer.parseInt(SplitArr2[2]))
                        IndexofBookID = j ;
                if (IndexofBookID == -1 || IndexofUserID == -1)
                    throw new Exception ("Sorry ,Unexpected Error Detected !");
                Staff.UserArr.get(IndexofUserID).AddBorrowedBook(BookArr.get(IndexofBookID));
            }
        }
        catch (Exception e){
            throw new Exception ("Fatal Error !\nDatabase reading operation is failed !");
        }
    }
    
    /**
     *
     * @param Officer Kitap eklemek isteyen kişiyi polymorphic çağrı ile alıyoruz. ve içerde User mı Staff mı kontrolü yaparak polymorphizm uyguluyoruz.
     * @param BookName  Eklenmek istenen kitabın Adını  alıp içerde bu kitap var mı yok mu diye kontrol ediyoruz.Varsa Bu kitabı Arrayimizde bulup kütüphanedeki sayısını 1 arttıran fonksiyonumuzu çağrıyoruz.Yoksa da Arraye yeni Book elemanı ekliyoruz.
     * @throws Exception Hata sonucu kitabı ekleyememe gibi durumlarda kullanıcıyı bilgilendirerek Exception fırlatıyoruz.
     * Ve işlem başarılı olduğunda bunu kullanıcıya bildiyiroruz.
     */
    public void AddBook(LibrarySystemsUsers Officer,String BookName) throws Exception
    {
        boolean Found = false;
        int index = 0 ;
        if (Officer.GetUserType() == 0)
            throw new Exception("Users cannot Add or Remove a book.");
        Staff LibStaff = (Staff) Officer;
        for (int i=0 ;i<BookArr.size();++i)
            if (BookArr.get(i).GetName().equals(BookName)){
                Found = true;
                index = i ;
            }
        if (Found == true)
        {
            BookArr.get(index).IncreaseHowManyInLibrary();
            File  CSVFile = new File("Books.csv");
            if (!CSVFile.exists()) {
                    CSVFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(CSVFile, false);
            try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
                bWriter.write("BOOK ID;BOOK NAME;HOW MANY ARE IN LIBRARY");
                 for (int i = 0;i<BookArr.size();++i)
                    bWriter.write("\n"+BookArr.get(i).GetBookID()+";"+BookArr.get(i).GetName()+";"+BookArr.get(i).GetHowManyInLibrary()+";");
                bWriter.close();
            }
        }
        
        else
        {
        Book newBook = new Book(BookName);
        BookArr.add(newBook);
        
        File  CSVFile = new File("Books.csv");
        if (!CSVFile.exists()) {
                CSVFile.createNewFile();
            try (FileWriter fileWriter = new FileWriter(CSVFile, true)) {
                fileWriter.write("BOOK ID;BOOK NAME;HOW MANY ARE IN LIBRARY");
                fileWriter.close();
            }
        }
        FileWriter fileWriter = new FileWriter(CSVFile, true);
        try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
            
            bWriter.write("\n"+newBook.GetBookID()+";"+newBook.GetName()+";"+newBook.GetHowManyInLibrary()+";");
            bWriter.close();
        }
            
        }
        System.out.println("The Book has been added in the library.");
    
    }

    /**
     *
     * @param Officer Kitap silmek isteyen kişiyi polymorphic çağrı ile alıyoruz. ve içerde User mı Staff mı kontrolü yaparak polymorphizm uyguluyoruz.
     * @param BookName  Kaldırılmak istenen kitabın Adını  alıp içerde bu kitap var mı yok mu diye kontrol ediyoruz.Varsa o an o kitap kütüphanede mi yoksa bir User da mı diye kontrol ediyoruz.Eğer kitap bir User daysa kitap gelmeden kitabı kaldıramassınız şeklinde uyarı veriyoruz.Yoksa da ona göre işlemler yapıyoruz.
     * @throws Exception Hata sonucu kitabı kaldıramama gibi durumlarda kullanıcıyı bilgilendirerek Exception fırlatıyoruz.
     * Ve işlem başarılı olduğunda bunu kullanıcıya bildiyiroruz.
     */
    public void RemoveBook(LibrarySystemsUsers Officer,String BookName) throws Exception
    {
        boolean Found = false;
        int index = 0 ;
        if (Officer.GetUserType() == 0)
            throw new Exception("Users cannot Add or Remove a book.");
        Staff LibStaff = (Staff) Officer;
        for (int i=0 ;i<BookArr.size();++i)
            if (BookArr.get(i).GetName().equals(BookName)){
                Found = true;
                index = i ;
            }
        if (Found == false)
        {
            System.out.println("Sorry,The Book you wanted to remove is not found in library.");
            return;
        }
        for (int i =0;i<Staff.RegisteredUserNum;++i)
            for(int j = 0;j<Staff.UserArr.get(i).GetBorrowedBooks().size();++j)
                if (Staff.UserArr.get(i).GetBorrowedBooks().get(j).GetName().equals(BookName))
                {
                    System.out.println("Sorry,You are trying to remove a given book. You can not remove this book before the book is brought back.");
                    return ;
                }
        BookArr.remove(index);
        File  CSVFile = new File("Books.csv");
        if (!CSVFile.exists()) {
                CSVFile.createNewFile(); 
        }
        FileWriter fileWriter = new FileWriter(CSVFile, false);
        try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
            bWriter.write("BOOK ID;BOOK NAME;HOW MANY ARE IN LIBRARY");
             for (int i = 0;i<BookArr.size();++i)
                bWriter.write("\n"+BookArr.get(i).GetBookID()+";"+BookArr.get(i).GetName()+";"+BookArr.get(i).GetHowManyInLibrary()+";");
            bWriter.close();
        }
        
        System.out.println("The book was successfully removed from the library.");
    }

    
}
