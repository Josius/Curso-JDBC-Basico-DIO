package part3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public List<Curso> list(){
        List<Curso> cursos = new ArrayList<>();

        try(Connection con = ConnectionFactory.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM curso");
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("duracao_horas")
                );

                cursos.add(curso);
            }

        } catch (Exception e) {
            System.out.println("Listagem dos cursos FALHOU! VERIFICAR!");
            e.printStackTrace();
        }

        return cursos;
    }

    public Curso getById(int id){
        Curso curso = new Curso();

        try(Connection con = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM curso WHERE id =?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDuracaoHoras(rs.getInt("duracao_horas"));
            }
        } catch (SQLException e) {
            System.out.println("Listagem falhou.");
            e.printStackTrace();
        }
        return curso;
    }

    public void create(Curso curso){
        try (Connection con = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO curso(nome, duracao_horas) VALUES(?, ?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, curso.getNome());
            pstm.setInt(2, curso.getDuracaoHoras());

            int rowsAffected = pstm.executeUpdate();

            System.out.println("Inserção bem sucedida. Foi add " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção falhou");
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try (Connection con = ConnectionFactory.getConnection()){
            String sql = "DELETE FROM curso WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();

            System.out.println("Deleção bem sucedida. Foi retirada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Deleção falhou");
            e.printStackTrace();
        }
    }

    public void update(Curso curso){
        try (Connection con = ConnectionFactory.getConnection()){
            String sql = "UPDATE curso SET nome = ?, duracao_horas = ? WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, curso.getNome());
            pstm.setInt(2, curso.getDuracaoHoras());
            pstm.setInt(3, curso.getId());

            int rowsAffected = pstm.executeUpdate();

            System.out.println("Atualização bem sucedida! Foi atualizada: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização falhou!");
            e.printStackTrace();
        }
    }
}
