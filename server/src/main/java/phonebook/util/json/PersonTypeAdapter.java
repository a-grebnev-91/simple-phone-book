package phonebook.util.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import phonebook.model.data.Person;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PersonTypeAdapter extends TypeAdapter<Person> {

    @Override
    public void write(JsonWriter jsonWriter, Person person) throws IOException {
        if (person == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        jsonWriter.name("id").value(person.getId());
        jsonWriter.name("name").value(person.getName());
        jsonWriter.name("organization").value(person.getOrganization());
        jsonWriter.name("numbers");
        writeLongsArray(jsonWriter, person.getNumbers());
        jsonWriter.endObject();
    }

    private void writeLongsArray(JsonWriter jsonWriter, Set<Long> numbers) throws IOException {
        jsonWriter.beginArray();
        for (Long number : numbers) {
            jsonWriter.value(number);
        }
        jsonWriter.endArray();
    }

    @Override
    public Person read(JsonReader jsonReader) throws IOException {
        int id = 0;
        String name = null;
        String organization = null;
        Set<Long> numbers = null;

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String jsonName = jsonReader.nextName();
            switch (jsonName) {
                case "id":
                    id = jsonReader.nextInt();
                    break;
                case "name":
                    name = jsonReader.nextString();
                    break;
                case "organization":
                    organization = jsonReader.nextString();
                    break;
                case "numbers":
                    numbers = readLongsArray(jsonReader);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();

        return new Person(id, name, organization, numbers.toArray(new Long[0]));
    }

    private Set<Long> readLongsArray(JsonReader jsonReader) throws IOException {
        Set<Long> numbers = new HashSet<>();
        jsonReader.beginArray();
        while(jsonReader.hasNext()) {
            numbers.add(jsonReader.nextLong());
        }
        jsonReader.endArray();
        return numbers;
    }
}

