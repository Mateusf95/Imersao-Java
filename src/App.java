
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    private static JsonParser jsonParser;

    public static void main(String[] args) throws Exception {
        
        // https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request =  HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //pegar só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";
            var geradora = new GeradorasDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1m Título: \u001b[m"+filme.get("title"));
            System.out.println("\u001b[1m URL do poster: \u001b[m"+filme.get("image"));
            System.out.print("\u001b[37m \u001b[45m Avaliação: \u001b[m");
            var num = Double.parseDouble(filme.get("imDbRating"));
            for(int i = 1; i<= (int)num; i++){
                System.out.print("\u001b[45m ⭐ \u001b[m");
            }
            System.out.println();
        }
    }
}
