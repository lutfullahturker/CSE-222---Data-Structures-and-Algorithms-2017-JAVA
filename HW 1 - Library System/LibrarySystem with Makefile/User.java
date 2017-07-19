import java.util.ArrayList;

/**
 *
 * @author Lütfullah TÜRKER  141044050
 */
public class User implements LibrarySystemsUsers {

    private final String Name ;
    protected int UserID = 0;
    private final ArrayList<Book> BorrowedBooks;
    /**
     * 
     * @param Name İsimsiz bir kullanıcı kayıt edemeyeceğimiz için sadece Name alan bir constructor yazıldı. 
     */
    User(String Name){
        this.Name = Name;
        BorrowedBooks = new ArrayList<>();
    }
    
    /**
     *
     * @return Kullanıcının aldığı ve  şuan kendisinde bulunan tüm kitapları içeren bir Book arraylist i return ediyor.
     */
    public ArrayList<Book> GetBorrowedBooks(){
        return BorrowedBooks;
    }
    
    /**
     *
     * @param AddBook <ul><li>BorrowedBook arraylistine kullanıcı kitap ödünç aldıkça kitabı arraye eklemek için çağrılan fonksiyon.</ul>
     */
    public void AddBorrowedBook(Book AddBook){
        BorrowedBooks.add(AddBook);
    }
    
    /**
     *
     * @param ReturnedBook   <ul><li> Kullanıcı kitabı geri getirdiğinde çağrılır.Bir Book alır ve kullanıcının BorrowedBook arrayinden bu kitabı geir verdiği için bu kitap silinir.</ul>
     * @throws Exception     Gelen kitabın kullanıcının aldığı kitaplar arasında olmaması gibi durumlar için hata kontrolü yapılıp Exception fırlatılır.
     */
    public void ReturnBook(Book ReturnedBook) throws Exception{
            boolean check = BorrowedBooks.remove(ReturnedBook);
            if (check == false)
                throw new Exception("The book you have entered could not find on the system !");
        
    }
    
    /**
     *
     * @return UserID  return eder.(Kullanıcının ID si)
     * @throws Exception Olası hata durumunda Exception fırlatılır.
     */
    public int GetUserID() throws Exception{
    /** 
     * Kullanıcı kendi ID sini görebilir.Fakat kendi ID sini değiştiremez.
     * Bu yüzden SetUserID fonksiyonunu Sadece Staff değiştirebildiği için Staff Class'ına yazdık.
     */
        if (UserID == 0)
            throw new Exception("Unregistered User Found ! Please Register with a Staff");
        return UserID ;
    }
    
    @Override
    public int GetUserType() {
        /** If GetUserType  function returns 0  this user's Type is User.*/
        return 0 ;
    }

    @Override
    public String GetName() {
        return Name;
    }
    
    
}
