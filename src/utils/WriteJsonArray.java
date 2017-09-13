package utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.*;

/**
 * Created by Miguel on 08/09/2017.
 */
public class WriteJsonArray <T> {
    private Gson gsonObject;
    private JsonWriter jsonWriterObject;

    public WriteJsonArray(String file, String indent) throws Exception {
        gsonObject = new Gson();
        jsonWriterObject = new JsonWriter(new FileWriter(file));
        jsonWriterObject.setIndent(indent);
    }

    public void writeStart() throws IOException {
        jsonWriterObject.beginArray();
    }

    @SuppressWarnings("unchecked")
    public void writeObject(T t, Class<?> resultClass) throws IOException {
        //((TypeAdapter<Object>) gsonObject.getAdapter(resultClass)).write(jsonWriterObject, t);
        gsonObject.toJson(t, resultClass, jsonWriterObject);
    }

    public void writeEnd() throws IOException {
        jsonWriterObject.endArray();
    }

    public void close() throws IOException {
        jsonWriterObject.close();
    }
}
