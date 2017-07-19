/**
 *
 * @author Lütfullah TÜRKER  141044050
 */
public interface LibrarySystemsUsers {

    /**
     *
     * @return UserType return ediyor.Eğer Kütüphane Kullanıcısıysa 0 , Kütüphane Görevlisiyse 1 return ediyor.
     */
    public int GetUserType();

    /**
     *
     * @return Name ,   Kullanıcının adını return ediyor.
     */
    public String GetName();
}
