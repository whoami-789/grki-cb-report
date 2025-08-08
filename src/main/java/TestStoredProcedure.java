import java.security.Security;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestStoredProcedure {
    public static void main(String[] args) {
        // Настройка TLS перед созданием подключения
        String disabledAlgorithms = Security.getProperty("jdk.tls.disabledAlgorithms");
        disabledAlgorithms = disabledAlgorithms.replace("TLSv1, TLSv1.1,", "");
        Security.setProperty("jdk.tls.disabledAlgorithms", disabledAlgorithms);

        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=mikrokredit;"
                + "user=sa;"
                + "password=1;"
                + "encrypt=false;";  // Временное отключение шифрования

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             CallableStatement cs = conn.prepareCall("{call analiz_schet(@dats=?, @bal=?)}")) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse("2024-12-31");
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            cs.setDate(1, sqlDate);
            cs.setString(2, "16307");

            cs.execute();
            System.out.println("Процедура выполнена успешно!");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}