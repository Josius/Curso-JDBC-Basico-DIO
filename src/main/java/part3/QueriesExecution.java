package part3;

import java.util.ArrayList;
import java.util.Arrays;
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

        //Banco de Dados Curso
        CursoDAO cursoDAO = new CursoDAO();
        //Cursos

        List<String> cursos = new ArrayList<>(Arrays.asList(
                "Biotecnologia",
                "Ciências da Natureza",
                "Educação Física e Saúde",
                "Gerontologia",
                "Gestão Ambiental",
                "Gestão de Políticas Públicas",
                "Lazer e Turismo",
                "Marketing",
                "Obstetrícia",
                "Sistemas de Informação",
                "Têxtil e Moda"
        ));

        List<Integer> horas = new ArrayList<>(Arrays.asList(
                new Integer(3360),
                new Integer(3665),
                new Integer(3900),
                new Integer(4740),
                new Integer(3660),
                new Integer(2730),
                new Integer(3150),
                new Integer(3090),
                new Integer(4170),
                new Integer(10000),
                new Integer(3480)
        ));


        //INSERÇÃO

        System.out.println("\nInserção de dados cadastrais de um curso");
        Curso cursoInsercao;
        for (int i=0; i < cursos.size(); i++){
            cursoInsercao = new Curso(cursos.get(i), horas.get(i));
            cursoDAO.create(cursoInsercao);
        }


        //CONSULTA
//        cursoDAO.list().forEach(System.out::println);

        //CONSULTA COM FILTRO - POR ID

        System.out.println("\nConsulta de dados cadastrais por id=2");
        Curso cursoParaConsulta = cursoDAO.getById(2);
        System.out.println(cursoParaConsulta);


        //DELETAR

        System.out.println("\nDeletando dados cadastrais de um aluno");
        cursoDAO.delete(1);
        //consultando alterações
        cursoDAO.list().forEach(System.out::println);


        //ATUALIZAR
        System.out.println("\nAtualização de dados cadastrais");
        Curso cursoAtualizar = cursoDAO.getById(3);
        cursoAtualizar.setNome("Teste");
        cursoAtualizar.setDuracaoHoras(2000);
        cursoDAO.update(cursoAtualizar);
        //consultando alterações
        cursoDAO.list().forEach(System.out::println);

    }
}
