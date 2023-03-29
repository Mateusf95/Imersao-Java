import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo{
    @Override
    public List<Conteudo> extraiConteudo(String json){

        //pegar só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();
        for (Map<String, String> atributos : listaDeAtributos){
            String titulo = atributos.get("title");
            String urlImage = atributos.get("url");
            var conteudo = new Conteudo(titulo, urlImage);
            conteudos.add(conteudo);
        }
        return conteudos;

    }
}
