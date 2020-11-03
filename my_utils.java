import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;

public class my_utils {
  public static void saveJson(int number){
    JSONObject obj = JSONObject();
    obj.put("name", "My Name");
    obj.put("you", "you Name");

    JSONArray list = JSONArray();
    list.add("name");
    list.add("you");
    list.add(Integer.toString(number));

    obj.put("Things", list);

    try(FileWriter file = new FileWriter("myJSON.json")){
      file.write(obj.toString());
      file.flush();
    }catch(IOException e){
      e.printStackTrace();
    }

    System.out.println(obj);
  }
}