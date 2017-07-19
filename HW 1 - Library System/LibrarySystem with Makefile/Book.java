/**
 *
 * @author Lütfullah TÜRKER  141044050
 * <ul><li><b>Sistemimizin reusable olması için Book sınıfı yazıldı.İstenildiği takdirde
     * her bir Book için Yazar adı Yayın evi gibi bilgiler için bir fonksiyon yazılması yeterlidir.</b></ul>
 */
public class Book {
    
    private final String Name;
    private String Author;
    private int HowManyInLibrary;
    /**
     * Book ID leri eklendikçe artırmak için static bir varaible tutuyoruz.Her Book eklendiğinde bunu artırıyoruz.
     */
    protected static int BookIDCount = 1001;
    protected int BookID ;
    
    /**
     * 
     * @param Name  İsimsiz kitap olmayacağı için sadece isim alan constructor yapıyoruz.
     * 
     */
    Book(String Name){
        this.Name = Name;
        Author = "No Input" ;
        HowManyInLibrary = 1 ;
        BookID = BookIDCount++;
    }
    
    /**
     *
     * @return BookID yi return eder.
     */
    public int GetBookID(){
        return BookID;
    }
    
    /**
     *
     * @return Kitabın adını return eder.
     */
    public String GetName(){
        return Name;
    }

    /**
     *
     * @return verildiyse kitabın yazarını return eder.
     */
    public String GetAuthor(){
        return Author;
    }

    /**
     *
     * @param Author <ul><li><b>Sistemimizin reusable ve geliştirilebilir olması için Book sınıfı yazıldı.İstenildiği takdirde
     * her bir Book için Yazar adı, Yayın evi gibi bilgiler için bu şekilde bir fonksiyon yazılması yeterlidir.</b></ul>
     */
    public void SetAuthor(String Author)
    {
        this.Author = Author;
    }

    /**
     *
     * @return O kitaptan o an kütüphanede kaç tane bulunduğunu return eder.
     */
    public int GetHowManyInLibrary(){
        return HowManyInLibrary;
    }

    /**
     * Bir kitabın kullanıcı tarafından geri getirilmesi veya staff tarafından eklenmesi durumunda kütüphanedeki o kitabın sayısını 1 arttıran fonksiyonumuz
     */
    public void IncreaseHowManyInLibrary()
    {
        ++HowManyInLibrary ;
    }

    /**
     * Bir kitabın kullanıcı tarafından ödünç alınması veya staff tarafından silinmesi durumunda kütüphanedeki o kitabın sayısını 1 azaltan fonksiyonumuz
     */
    public void DecreaseHowManyInLibrary()
    {
        --HowManyInLibrary ;
    }
    
}
