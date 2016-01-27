# jEqual

Making `equals` and `hashCode` nice and easy since **2016**!

```java
public class Person {
    private static final Equality<Person> EQUALITY = Equality
                .withOtherObjectsOfType(Person.class)
                .byComparing(Person::getFirstName)
                .byComparing(Person::getLastName)
                .define();

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return EQUALITY.hashCode(this);
    }

    @Override
    public boolean equals(final Object o) {
        return EQUALITY.equals(this, o);
    }
}
```
