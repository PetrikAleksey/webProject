package spring.DAO.Model.Converters;

import com.google.gson.*;
import spring.DAO.Model.Bank;

import java.lang.reflect.Type;

public class BankConverter implements JsonSerializer<Bank>, JsonDeserializer<Bank> {
    public JsonElement serialize(Bank bank, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("id", bank.getId().toString());
        object.addProperty("name", bank.getName());
        return object;
    }

    public Bank deserialize(JsonElement json, Type type,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String name = object.get("name").getAsString();
        return new Bank(name);
    }
}
