package part3;

import java.util.List;

public class QueriesExecution {

    public static void main(String[] args) {

        AlunoDAO alunoDAO = new AlunoDAO();

        //CONSULTA
        System.out.println("Consulta todo os dados cadastrais do Banco de Dados");
        List<Aluno> alunos = alunoDAO.list();
        alunos.forEach(System.out::println);

        //CONSULTA COM FILTRO - POR ID
        System.out.println("\nConsulta de dados cadastrais de aluno por id=2");
        Aluno alunoParaConsulta = alunoDAO.getById(2);
        System.out.println(alunoParaConsulta);

        //INSERÇÃO
        System.out.println("\nInserção de dados cadastrais um aluno");
        Aluno alunoInsercao = new Aluno(
                "Gustavp",
                24,
                "SP"
        );
        alunoDAO.create(alunoInsercao);
        //consultando alterações
        alunoDAO.list().forEach(System.out::println);

        //DELETAR
        System.out.println("\nDeletando dados cadastrais de um aluno");
        alunoDAO.delete(1);
        //consultando alterações
        alunoDAO.list().forEach(System.out::println);

        //ATUALIZAR
        System.out.println("\nAtualização de dados cadastrais de um aluno");
        Aluno alunoAtualizar = alunoDAO.getById(3);
        alunoAtualizar.setNome("Joaquis");
        alunoAtualizar.setIdade(18);
        alunoAtualizar.setEstado("RS");
        alunoDAO.update(alunoAtualizar);
        //consultando alterações
        alunoDAO.list().forEach(System.out::println);
    }
}
