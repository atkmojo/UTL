package org.utl.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.utl.core.impl.Workflow;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonHandler<T> {

    private static final Gson gson = new Gson();
    private final Class<T> type;

    public JsonHandler(Class<T> type) {
        this.type = type;
    }

    public T jsonFromFile(String filePath) throws FileNotFoundException {
        //Type genericListType = new TypeToken<ArrayList<T>>() {}.getType();
        JsonReader reader = new JsonReader(new FileReader(filePath));
        return gson.fromJson(reader, this.type);
    }
}
