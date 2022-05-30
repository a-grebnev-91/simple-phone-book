package phonebook.model.data;

import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private PhoneNumber number;

    private String organization;

    public Person(String name, long number) {
        this.name = name;
        this.number = new PhoneNumber(number);
    }

    public Person(String name, String organization, long number) {
        this(name, number);
        this.organization = organization;
    }

    public Person(int id, String name, String organization, long number) {
        this(name, organization, number);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PhoneNumber getNumber() {
        return number;
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

    public void setNumber(PhoneNumber number) {
        this.number = number;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(number, person.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
