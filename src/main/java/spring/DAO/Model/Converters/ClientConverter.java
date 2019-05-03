package spring.DAO.Model.Converters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import spring.DAO.Model.Client;
import spring.DAO.Model.Worker;

import java.lang.reflect.Type;

public class ClientConverter implements JsonSerializer<Client>{//, JsonDeserializer<Worker> {
    public JsonElement serialize(Client client, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("id", client.getId().toString());
        object.addProperty("fio", client.getFio());
        object.addProperty("phoneNumber", client.getPhoneNumber());
        object.addProperty("address", client.getAddress());
        object.addProperty("email", client.getEmail());
        object.addProperty("bankId", client.getBank().getId());
        object.addProperty("bankName", client.getBank().getName());
        return object;
    }

//    public Worker deserialize(JsonElement json, Type type,
//                            JsonDeserializationContext context) throws JsonParseException {
//        JsonObject object = json.getAsJsonObject();
//        String fio = object.get("fio").getAsString();
//        return new Worker(fio);
//    }
}
