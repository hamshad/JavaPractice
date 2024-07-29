import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import DTO.Data;

public class CompletableFuturePrac {

    List<Data> dataList = new ArrayList<>();

    CompletableFuturePrac() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Processing..");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONParser parser = new JSONParser();
            JSONArray array = null;
            try {
                array = (JSONArray) parser.parse(new FileReader("data.json"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (array != null) {
                for (Object o : array) {
                    JSONObject obj = (JSONObject) o;
                    Long id = (Long) obj.get("Id");
                    String name = (String) obj.get("Name");
                    String desc = (String) obj.get("Desc");
                    Data d = new Data(id, name, desc);
                    dataList.add(d);
                }
            }

            return dataList;
        }).thenApplyAsync(list -> {
            return list.stream().filter(d -> d.getId() > 1 && d.getId() <= 4).collect(Collectors.toList());
        }).thenApplyAsync(list -> {
            return list.stream().map(s -> new Data(s.getId(), s.getName(), s.getDesc())).collect(Collectors.toList());
        }).thenAcceptAsync(
                str -> str.forEach(
                        list -> System.out.println("Name: " + list.getName() + "\nHe is " + list.getDesc() + "\n")));

        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
