import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Lütfullah TÜRKER  141044050
 */
@SuppressWarnings("try")
public class Staff implements LibrarySystemsUsers{

    private String Name ;
    /**
     * UserArr Kullanıcıları tuttuğumuz arrayimiz 
     */
    protected static User UserArr[] = new User[10];
    private static int UserIDNum = 10001;
    /**
     * Kullanıcıları tuttuğumuz arrayin size'ı yani kayıtlı kullanıcı sayısı
     */
    protected static int RegisteredUserNum = 0 ;
    private static int CapacityOfUserArr = 10 ;
    private int StaffID;
    private static int StaffIDCount = 20001 ;
    private static boolean isFirst = true;
    
    /**
     * 
     * @param Name  İsimsiz Staff olmayacağı için sadece Name alan bir Constructor yazıyoruz.
     * @throws Exception  Program çalışmaya başladığında varolan CSV database dosyalarından okuma yapılıp 
     * User arrayi gibi gerekli bilgileri dosyadan alıp doldururken bir sorun oluşması,dosyanın okunamaması gibi
     * durumlarda exception fırlatılıyor.Main de yakalanması için constructora gelen exception maine tekrar fırlatılıyor.
     */
    Staff(String Name) throws Exception{
        this.Name = Name;
        StaffID = StaffIDCount++;
        try{
            if (isFirst == true)
            {
                isFirst = false;
                ReadCSVFile();
            }
        }
        catch (Exception e){
            throw e;
        }
    }
    
    /**
     *
     * @return Staff ın ID sini return eder.
     */
    public int getStaffID() {
        return StaffID;
    }
    /**
     * 
     * @return Users.csv dosyasını okurken dosyadaki en büyük ID yi bulup yeni kayıt gelirse bu ID den itibaren kaydedilmesi için bu fonksiyonu kullanıyor.
     * @throws Exception Dosyada arama yapılırken hata olması durumunda Exceptionları fırlatıyor.
     */
    private int FindMaxID() throws Exception
    {
        if (RegisteredUserNum == 0)
            return 10000;
        int max = UserArr[0].GetUserID();
        for (int i =0;i<RegisteredUserNum;++i)
        {
            if (UserArr[i].GetUserType() == 0)
            {
                if (UserArr[i].GetUserID() > max)
                    max = UserArr[i].GetUserID();
            }
            else {
                StaffIDCount = UserArr[i].GetUserID();
                StaffID = UserArr[i].GetUserID();
            }
        }
        return max;
    }
    /**
     * <ul><li>Program başlatıltığında bir kere çağrılıp database CSV dosyalarını okuyup programdaki gerekli Arrayleri ve dataları doldurmak için bu fonksiyonu kullanıyoruz.</ul>
     * @throws Exception Dosya okuma yapılırken hata ile karşılaşılması durumlarında Exception fırlatılıyor.
     */
    private void ReadCSVFile() throws Exception
    {
        File  CSVFile = new File("Users.csv"); 
        if (!CSVFile.exists()) {
            CSVFile.createNewFile();
            try (FileWriter fileWriter = new FileWriter(CSVFile, true)) {
                fileWriter.write("USER ID;USER NAME;USER TYPE;");
            
                try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
                    bWriter.write("\n"+this.StaffID+";"+this.GetName()+";"+this.GetUserType()+";");
                }
            }
            return ;
        }
        try{
            String Temp;
            String[] SplitArr;
            Scanner Read = new Scanner(CSVFile);
            Temp = Read.nextLine();
            while (Read.hasNextLine())
            {
                Temp = Read.nextLine();
                SplitArr = Temp.split(";");
                if (Integer.parseInt(SplitArr[2]) == 0)
                {
                    if (RegisteredUserNum >= CapacityOfUserArr)
                    {
                        User[] temp = new User[RegisteredUserNum];
                        for (int i=0;i<RegisteredUserNum;++i)
                            temp[i] = UserArr[i];
                        CapacityOfUserArr *= 2 ;
                        UserArr = new User[CapacityOfUserArr];
                        for (int i=0;i<RegisteredUserNum;++i)
                            UserArr[i] = temp[i];
                    }
                    UserArr[RegisteredUserNum] = new User(SplitArr[1]);
                    UserArr[RegisteredUserNum].UserID = Integer.parseInt(SplitArr[0]);
                    ++RegisteredUserNum;  
                }
                else if (Integer.parseInt(SplitArr[2]) == 1)
                {
                    StaffID = Integer.parseInt(SplitArr[0]);
                    Name = SplitArr[1];
                }
                else
                    throw new Exception(" Error ! The wrong user type was detected !");
            }
            UserIDNum = FindMaxID()+1;
        }
        catch (Exception e){
            throw new Exception ("Fatal Error !\nDatabase reading operation is failed !");
        }
    }
    
    
    private void SetUserID(User SetUser){
        SetUser.UserID = UserIDNum++;
    }
    
    /**
     *
     * @param UserName Eklenecek User ın ismini alıyoruz.
     * @throws Exception Ekleme sırasında hata durumunda fırlatıyoruz.
     */
    public void AddUser(String UserName) throws Exception{
        if (RegisteredUserNum >= CapacityOfUserArr)
        {
            User[] temp = new User[RegisteredUserNum];
            for (int i=0;i<RegisteredUserNum;++i)
                temp[i] = UserArr[i];
            CapacityOfUserArr *= 2 ;
            UserArr = new User[CapacityOfUserArr];
            for (int i=0;i<RegisteredUserNum;++i)
                UserArr[i] = temp[i];
        }
        UserArr[RegisteredUserNum] = new User(UserName);
        SetUserID(UserArr[RegisteredUserNum]);
        ++RegisteredUserNum;  
        
        File  CSVFile = new File("Users.csv");
        if (!CSVFile.exists()) {
                CSVFile.createNewFile();
            try (FileWriter fileWriter = new FileWriter(CSVFile, true)) {
                fileWriter.write("USER ID;USER NAME;USER TYPE;");
                fileWriter.close();
            }
        }
        FileWriter fileWriter = new FileWriter(CSVFile, true);
        try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
            bWriter.write("\n"+UserArr[RegisteredUserNum-1].GetUserID()+";"+UserArr[RegisteredUserNum-1].GetName()+";"+UserArr[RegisteredUserNum-1].GetUserType()+";");
            bWriter.close();
        }
    }
    
    @Override
    public int GetUserType() {
        /** If GetUserType  function returns 1  this user's Type is Staff.*/
        return 1;
    }

    @Override
    public String GetName() {
        return Name;
    }
    
    
    
}
