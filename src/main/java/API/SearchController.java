package API;

import com.bigdatacompany.eticaret.KafkaProducerr;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class SearchController {
    @Autowired
    KafkaProducerr kafkaProducer;
    @GetMapping("/search")
    public void searchIndex(@RequestParam String term){
        List<String> cities= Arrays.asList("Ankara","İstanbul","Adana1","Mersin","Zonguldak","Malatya","Elazığ","Hakkari","Van","Antalya"
        ,"İzmir","Trabzon","Kütahya");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        Random random=new Random();
        int i= random.nextInt(cities.size());
        JSONObject jsonpObject=new JSONObject();
        jsonpObject.put("search",term);
        jsonpObject.put("timestamp",timestamp);
        jsonpObject.put("region",cities.get(i));
        kafkaProducer.send(jsonpObject.toJSONString());
    }
}
