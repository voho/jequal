# jEqual

[![Travis](https://travis-ci.org/voho/jequal.svg?branch=master)](https://travis-ci.org/voho/jequal) [![codecov.io](https://codecov.io/github/voho/jequal/coverage.svg?branch=master)](https://codecov.io/github/voho/jequal?branch=master)

Making `equals` and `hashCode` nice and easy since **2016**!

```java
public class Person {
    private static final Equality<Person> EQUALITY = Equality
                .withInstancesOf(Person.class)
                .allowAnySubType()
                .checkEqualityOf(Person::getFirstName)
                .checkEqualityOf(Person::getLastName)
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
