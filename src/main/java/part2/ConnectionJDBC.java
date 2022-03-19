package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static void main(String[] args) throws SQLException {
//        CRIANDO UMA CONEXÃO COM O BANCO
//        Parametros para uma conexao
        String driver = "mysql ou postgres";
        String dataBaseAddress = "IP do BD";
        String dataBaseName = "nome do BD";
        String user = "nome usuario do BD";
        String password = "password";

//        Construindo a string de conexao
        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String urlConnection = sb.toString(); // -> "jdbc:mysql://localhost/digital_innovation_one"
//        Connection conn = null; // para usar com a forma antiga

//      FORMA ATUAL COM try with resources
//      se falhar, a conexão é fechada automaticamente
        try(Connection conn = DriverManager.getConnection(urlConnection,"nome usuario", "password");){
            System.out.println("Deu Certo!");
        }catch (Exception e){
            System.out.println("Falhou!!!");
        }
//        FORMA ANTIGA DE CONEXÃO
        /*try {
            conn = DriverManager.getConnection(urlConnection,"root", "souZa123");
            System.out.println("SUCESSO");
        }catch (Exception e){
            System.out.println("FALHA");
        }finally {
            conn.close();
        }*/

    }
}
