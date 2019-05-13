package spring.DAO.Model.Converters;

import com.google.gson.*;
import spring.DAO.Model.Worker;

import java.lang.reflect.Type;

public class WorkerConverter implements JsonSerializer<Worker>{//, JsonDeserializer<Worker> {
    public JsonElement serialize(Worker worker, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("id", worker.getId().toString());
        object.addProperty("fio", worker.getFio());
        object.addProperty("position", worker.getPosition().toString());
        object.addProperty("positionObjName", worker.getPosition().ordinal());
        object.addProperty("phone", worker.getPhone());
        object.addProperty("bankId", worker.getBank().getId());
        object.addProperty("bankName", worker.getBank().getName());
        return object;
    }

//    public Worker deserialize(JsonElement json, Type type,
//                            JsonDeserializationContext context) throws JsonParseException {
//        JsonObject object = json.getAsJsonObject();
//        String fio = object.get("fio").getAsString();
//        return new Worker(fio);
//    }
}
