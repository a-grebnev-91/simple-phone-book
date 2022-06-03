package phonebook.util.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import phonebook.model.data.Person;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        jsonWriter.name("numbers").value(convertArrayToString(person.getNumbers()));
        jsonWriter.endObject();
    }

    @Override
    public Person read(JsonReader jsonReader) throws IOException {
        int id = 0;
        String name = null;
        String organization = null;
        Long[] numbers = null;
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
                    numbers = convertStringToArray(jsonReader.nextString());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();

        return new Person(id, name, organization, numbers);
    }

    private String convertArrayToString(Set<Long> numbers) {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private Long[] convertStringToArray(String numbers) {
        String[] nums = numbers.split(",");
        return Arrays.stream(nums).map(String::trim).map(Long::parseLong).toArray(Long[]::new);
    }




    //TODO del all below
    private Set<Long> readLongsArray(JsonReader jsonReader) throws IOException {
        Set<Long> numbers = new HashSet<>();
        jsonReader.beginArray();
        while(jsonReader.hasNext()) {
            numbers.add(jsonReader.nextLong());
        }
        jsonReader.endArray();
        return numbers;
    }

    private void writeLongsArray(JsonWriter jsonWriter, Set<Long> numbers) throws IOException {
        jsonWriter.beginArray();
        for (Long number : numbers) {
            jsonWriter.value(number);
        }
        jsonWriter.endArray();
    }
}

