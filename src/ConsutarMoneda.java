import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsutarMoneda {

    public Moneda buscaMoneda(int tipoDeMoneda) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/b5d8bed6b2dcc9fd92f9ab53/latest/USD" + tipoDeMoneda + "/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}