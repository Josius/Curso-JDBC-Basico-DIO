package part3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
//    Construtor privado para evitar instâncias da fábrica.
    private ConnectionFactory(){
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection(){
        //objeto de conexão
        Connection connection = null;

        //carregamento de arquivo de propriedades para gerar parâmetros necessários para comunicação com BD
        //FORMA ATUAL COM try with resources
        //se falhar, a conexão é fechada automaticamente
        try(InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")){

            //        parâmetros para conexão com BD
            Properties prop = new Properties();
            prop.load(input);

            //necessário colocar nas strings abaixo os nomes corretos das variáveis do arquivo connection.properties
            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            //Construindo a string de conexao
            StringBuilder sb = new StringBuilder("jdbc:")
                    .append(driver).append("://")
                    .append(dataBaseAddress).append("/")
                    .append(dataBaseName);

            String urlConnection = sb.toString(); // -> "jdbc:mysql://localhost/digital_innovation_one"


            try{
                connection = DriverManager.getConnection(urlConnection, user, password);
            }catch (SQLException e){
                System.out.println("Falhou ao tentar criar uma conexão");
                throw new RuntimeException(e);
            }
        }catch(IOException e){
            System.out.println("Falhou ao tentar carregar arquivos de propriedades");
            e.printStackTrace();
        }
        return connection;
    }
}
