import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Disciplina {
    String nome;
    double nota;

    public Disciplina(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }
}

class Aluno {
    String rgm;
    List<Disciplina> disciplinas;

    public Aluno(String rgm) {
        this.rgm = rgm;
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }
}

public class GestaoAlunos {
    private static Aluno[] alunos = new Aluno[60];
    private static int quantidadeAlunos = 0;
    private static Scanner scanner = new Scanner(System.in);

    private static void adicionarAluno(String rgm) {
        if (quantidadeAlunos >= alunos.length) {
            System.out.println("Número máximo de alunos atingido.");
            return;
        }
        for (int i = 0; i < quantidadeAlunos; i++) {
            if (alunos[i].rgm.equals(rgm)) {
                System.out.println("Um aluno com este RGM já está cadastrado.");
                return;
            }
        }
        Aluno aluno = new Aluno(rgm);
        System.out.println("Digite as disciplinas e notas do aluno (digite 'fim' para terminar):");
        while (true) {
            System.out.print("Nome da disciplina: ");
            String nomeDisciplina = scanner.nextLine();
            if ("fim".equalsIgnoreCase(nomeDisciplina)) {
                break;
            }
            System.out.print("Nota da disciplina: ");
            double notaDisciplina = scanner.nextDouble();
            scanner.nextLine(); // Consumir nova linha
            aluno.adicionarDisciplina(new Disciplina(nomeDisciplina, notaDisciplina));
        }
        alunos[quantidadeAlunos++] = aluno;
    }

    private static Aluno buscarAluno(String rgm) {
        for (int i = 0; i < quantidadeAlunos; i++) {
            if (alunos[i].rgm.equals(rgm)) {
                return alunos[i];
            }
        }
        return null;
    }

    private static void removerAluno(String rgm) {
        int posicao = -1;
        for (int i = 0; i < quantidadeAlunos; i++) {
            if (alunos[i].rgm.equals(rgm)) {
                posicao = i;
                break;
            }
        }
        if (posicao != -1) {
            for (int i = posicao; i < quantidadeAlunos - 1; i++) {
                alunos[i] = alunos[i + 1];
            }
            alunos[--quantidadeAlunos] = null;
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void exibirAlunos() {
        if (quantidadeAlunos == 0) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }
        for (int i = 0; i < quantidadeAlunos; i++) {
            Aluno aluno = alunos[i];
            System.out.println("RGM: " + aluno.rgm);
            for (Disciplina disciplina : aluno.disciplinas) {
                System.out.println("\tDisciplina: " + disciplina.nome + ", Nota: " + disciplina.nota);
            }
            System.out.println("---");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Menu Gestão de Alunos ---");
            System.out.println("1 - Adicionar aluno");
            System.out.println("2 - Buscar aluno por RGM");
            System.out.println("3 - Remover aluno por RGM");
            System.out.println("4 - Exibir todos os alunos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Digite o RGM do aluno: ");
                    String rgm = scanner.nextLine();
                    adicionarAluno(rgm);
                    break;
                case 2:
                    System.out.print("Digite o RGM do aluno para busca: ");
                    rgm = scanner.nextLine();
                    Aluno aluno = buscarAluno(rgm);
                    if (aluno != null) {
                        System.out.println("RGM: " + aluno.rgm);
                        for (Disciplina disciplina : aluno.disciplinas) {
                            System.out.println("\tDisciplina: " + disciplina.nome + ", Nota: " + disciplina.nota);
                        }
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o RGM do aluno para remover: ");
                    rgm = scanner.nextLine();
                    removerAluno(rgm);
                    break;
                case 4:
                    exibirAlunos();
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
