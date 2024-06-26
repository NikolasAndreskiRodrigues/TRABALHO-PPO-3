import java.util.Scanner;

public class Menu {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Writer writer = new Writer();
        Reader reader = new Reader();
        reader.UpdateList();
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Criar Chamado");
            System.out.println("2. Ver Chamado");
            System.out.println("3. Apagar Chamado");
            System.out.println("4. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Digite o título do chamado:");
                    String title = scanner.nextLine();
                    System.out.println("Digite a descrição do chamado:");
                    String description = scanner.nextLine();
                    writer.Createchamado(title, description);
                    break;
                case 2:
                    System.out.println("Digite o ID do chamado que você quer ver:");
                    long idVer = scanner.nextLong();
                    reader.Getchamado(idVer);
                    break;
                case 3:
                    System.out.println("Digite o ID do chamado que você quer apagar:");
                    long idApagar = scanner.nextLong();
                    writer.DeleteChamado(idApagar);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
