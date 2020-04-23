import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

//import static java.awt.DefaultKeyboardFocusManager.sendMessage;
public class JavaPostgreSqlPrepared {

    private static String url = "jdbc:postgresql://ec2-54-157-78-113.compute-1.amazonaws.com:5432/d4qb1euqb2hibb";
    private static String user = "hwrdniijtmimul";
    private static String password = "162bee697eda890e5b9bf10f45d1893405b08b7a42295f2521cfde070297a146";

    private static Connection con;
    private static PreparedStatement preparedStatement = null;

    public static void main(String[] args) {

    }
    //SELECT value_toast FROM toasts ORDER BY RANDOM() limit 1
    public static String ChoiceProfession(int id_profession)
    {
        try
        {
            PreparedStatement pst = con.prepareStatement("SELECT value_toast FROM toasts WHERE id_toast =?");
            pst.setInt(1, id_profession);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSqlPrepared.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
}
