import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reader {
    private static ArrayList<Chamado> list = new ArrayList<>();
    public void UpdateList() {
        list.clear();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("bancochamados.txt"));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 4) {
                    long id = Long.parseLong(partes[0]);
                        String title = partes[1];
                        String description = partes[2];
                        String data = partes[3];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        Chamado chamado = new Chamado();
                        chamado.setTitle(title);
                        chamado.setDescription(description);
                        chamado.setData(LocalDate.parse(data, formatter));
                        chamado.setId(id);
                        list.add(chamado);
                }
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Getchamado(long idProcurado) {
        try {
            for (Chamado chamado : list) {
                if (chamado.getId() == idProcurado) {
                    System.out.println("Chamado" + "\n" +
                            "id = " + chamado.getId() + "\n" +
                            "title = " + chamado.getTitle() + "\n" +
                            "description = " + chamado.getDescription() + "\n" +
                            "data = " + chamado.getData() + "\n");
                    return;
                }
            }
            System.out.println("Chamado com id " + idProcurado + " não encontrado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

