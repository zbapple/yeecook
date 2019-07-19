import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {

        Map<String,String> map= new HashMap();
        map.put("b","4");
        map.put("a","5");
        map.put("c","3");
        map.put("d","5");
        map.put("","");



        for(String key : map.keySet()){
            String value = map.get(key);
            System.out.println(key+"  "+value);
        }
    }
}
