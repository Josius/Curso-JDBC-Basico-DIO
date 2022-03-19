package part3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Classe especializada para acessar os dados do BD (DAO -> Data Access Object)
public class AlunoDAO {

    //consultar, retorna todos os alunos do BD
    public List<Aluno> list(){
        List<Aluno> alunos = new ArrayList<>();

        //acessando o BD
        try(Connection conn = ConnectionFactory.getConnection()){

            PreparedStatement prst = conn.prepareStatement("SELECT * FROM aluno");

            //na variável abaixo estará todos os registros recuperados do BD
            ResultSet rs = prst.executeQuery();

            //o ponteiro aponta para "void", com next() o ponteiro aponta para a próx linha da tabela e retorna um
            // booleano, então usamos com while
            while(rs.next()){

                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("estado"));

                alunos.add(aluno);
            }

        }catch (Exception e){
            System.out.println("Listagem de alunos FALHOU!!");
            e.printStackTrace();
        }

        return alunos;
    }

    //consultar com filtro
    public Aluno getById(int id){
        Aluno aluno = new Aluno();

        try(Connection conn = ConnectionFactory.getConnection()){
            //requisição sql
            //? -> isso vai ser parametrizado pelo PreparedStatement
            String sql = "SELECT * FROM aluno WHERE id = ?";

            //statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //1 -> 1ª ? que aparece
            //id -> aloca id na ?
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));

            }
        }catch (SQLException e){
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }
        return aluno;
    }
    //inserir
    public void create(Aluno aluno){
        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());

            //executa inserção e armazena o nº de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção bem sucedida. Foi add " + rowsAffected + " linha");
        }catch (SQLException e){
            System.out.println("Inserção Falhou!");
            e.printStackTrace();
        }
    }
    //deletar
    public void delete(int id){
        try(Connection conn = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM aluno WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete bem sucedido. Foi deletado " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Delete falhou!");
            e.printStackTrace();
        }
    }
    //atualizar
    public void update(Aluno aluno){
        try(Connection conn = ConnectionFactory.getConnection()) {

            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());
            stmt.setInt(4, aluno.getId());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização bem sucedida! Foi atualizada: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização falhou!");
            e.printStackTrace();
        }
    }

}
