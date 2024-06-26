import java.io.*;
import java.time.LocalDate;

public class Writer {
        public void Savechamado(Chamado chamado) {
        String dados = chamado.getId() + "," + chamado.getTitle() + "," + chamado.getDescription() + "," + chamado.getData();
        String nomeArquivo = "bancochamados.txt";

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo, true));

            escritor.write(dados);
            escritor.newLine();

            escritor.close();
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public void Createchamado(String title, String description) {
        long highestId = Idgerador();
        Chamado chamado = new Chamado();
        Writer writer = new Writer();
        Reader reader = new Reader();
        chamado.setId(highestId + 1);
        chamado.setData(LocalDate.now());
        chamado.setTitle(title);
        chamado.setDescription(description);
        writer.Savechamado(chamado);
        System.out.println("O ID do seu chamado é: " + chamado.getId());
        reader.UpdateList();
    }
    public void DeleteChamado(long idProcurado) {
        Reader reader = new Reader();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("bancochamados.txt"));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length > 0) {
                    long id = Long.parseLong(partes[0]);
                    if (id != idProcurado) {
                        conteudo.append(linha).append(System.lineSeparator());
                    }
                }
            }
            leitor.close();

            BufferedWriter escritor = new BufferedWriter(new FileWriter("bancochamados.txt"));
            escritor.write(conteudo.toString());
            escritor.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        reader.UpdateList();
    }
    private long Idgerador() {
        long highestId = 0;
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("bancochamados.txt"));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.isEmpty()) {
                    String[] partes = linha.split(",");
                    if (partes.length > 0) {
                        long id = Long.parseLong(partes[0]);
                        highestId = Math.max(highestId, id);
                    }
                }
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highestId;
    }
}