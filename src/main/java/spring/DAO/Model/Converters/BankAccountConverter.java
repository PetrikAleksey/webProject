package spring.DAO.Model.Converters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import spring.DAO.Model.BankAccount;
import spring.DAO.Model.Client;

import java.lang.reflect.Type;

public class BankAccountConverter implements JsonSerializer<BankAccount>{//, JsonDeserializer<Worker> {
    public JsonElement serialize(BankAccount bankAccount, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("id", bankAccount.getId().toString());
        object.addProperty("login", bankAccount.getLogin());
        object.addProperty("password", bankAccount.getPassword());
        object.addProperty("currency", bankAccount.getCurrency());
        object.addProperty("clientId", bankAccount.getClient().getId());
        object.addProperty("clientFIO", bankAccount.getClient().getFio());
        return object;
    }

//    public Worker deserialize(JsonElement json, Type type,
//                            JsonDeserializationContext context) throws JsonParseException {
//        JsonObject object = json.getAsJsonObject();
//        String fio = object.get("fio").getAsString();
//        return new Worker(fio);
//    }
}
