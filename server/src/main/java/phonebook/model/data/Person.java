package phonebook.model.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person {

    private int id;
    private String name;
    private final Set<Long> numbers;

    private String organization;

    public Person(String name, Long ... numbers) {
        this.name = name;
        this.numbers = new HashSet<>();
        this.numbers.addAll(Arrays.asList(numbers));
    }

    public Person(String name, String organization, Long ... numbers) {
        this(name, numbers);
        this.organization = organization;
    }

    public Person(int id, String name, String organization, Long ... numbers) {
        this(name, organization, numbers);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Long> getNumbers() {
        return numbers;
    }

    public String getOrganization() {
        return organization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNumber(Long number) {
        this.numbers.add(number);
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(numbers, person.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
