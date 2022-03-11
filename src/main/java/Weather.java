import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=6fff53a641b9b9a799cfd6b079f5cd4e");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

        if(model.getMain().equals("Clear")) {
            return "Город\uD83C\uDF32: " + model.getName() + "\n" +
                    "Температура\uD83C\uDF21: " + model.getTemp() + "C" + "\n" +
                    "Влажность\uD83D\uDCA6: " + model.getHumidity() + "%" + "\n" +
                    "Облачность: " + "Солнечено" + "\nТы видишь в небе солнце? Так знай, твоя улыбка светит гораздо ярче!";
        } else if (model.getMain().equals("Clouds")) {
            return "Город\uD83C\uDF32: " + model.getName() + "\n" +
                    "Температура\uD83C\uDF21: " + model.getTemp() + "C" + "\n" +
                    "Влажность\uD83D\uDCA6: " + model.getHumidity() + "%" + "\n" +
                    "Облачность: " + "Облачно" + "\nТы видишь в небе солнце? Так знай, твоя улыбка светит гораздо ярче!";
        }

        return "Город\uD83C\uDF32: " + model.getName() + "\n" +
                "Температура\uD83C\uDF21: " + model.getTemp() + "C" + "\n" +
                "Влажность\uD83D\uDCA6: " + model.getHumidity() + "%" + "\n" +
                "Облачность: " + model.getMain();
    }
}
