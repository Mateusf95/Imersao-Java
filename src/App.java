import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    private static JsonParser jsonParser;

    public static void main(String[] args) throws Exception {
        
        // https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        // System.out.println(body);

        
      
        //exibir e manipular os dados

        var extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudo(json);
        var geradora = new GeradorasDeFigurinhas();
        for(int i = 0; i < 10; i++){
            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";
            
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1m Título: \u001b[m"+conteudo.getTitulo());
            // System.out.println("\u001b[1m URL do poster: \u001b[m"+conteudo.get("image"));
            // System.out.print("\u001b[37m \u001b[45m Avaliação: \u001b[m");
            // var num = Double.parseDouble(conteudo.get("imDbRating"));
            // for(int i = 1; i<= (int)num; i++){
            //     System.out.print("\u001b[45m ⭐ \u001b[m");
            // }
            System.out.println();
        }
    }
}
