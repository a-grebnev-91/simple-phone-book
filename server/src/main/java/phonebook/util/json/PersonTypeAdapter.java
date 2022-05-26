package phonebook.util.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import phonebook.data.Person;

import java.io.IOException;

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
        jsonWriter.name("number").value(person.getNumber().getNumber());
        jsonWriter.endObject();
    }

    @Override
    public Person read(JsonReader jsonReader) throws IOException {
        int id = 0;
        String name = null;
        String organization = null;
        long number = 0;

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String jsonName = jsonReader.nextName();
            if (jsonName.equals("id"))
                id = jsonReader.nextInt();
            else if (jsonName.equals("name"))
                name = jsonReader.nextString();
            else if (jsonName.equals("organization"))
                organization = jsonReader.nextString();
            else if (jsonName.equals("number"))
                number = jsonReader.nextLong();
            else
                jsonReader.skipValue();
        }
        jsonReader.endObject();

        return new Person(id, name, organization, number);
    }
}

