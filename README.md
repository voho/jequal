# jEqual

[![Travis](https://travis-ci.org/voho/jequal.svg?branch=master)](https://travis-ci.org/voho/jequal) [![codecov.io](https://codecov.io/github/voho/jequal/coverage.svg?branch=master)](https://codecov.io/github/voho/jequal?branch=master)

Making `equals` and `hashCode` nice and easy since **2016**!

Implementing `equals` and `hashCode` properly is sometimes a challenge. The IDE can generate code for you, but the result is often a little cryptic, and so even after the recent additions in JDK 8.
I like my code to be as clear as possible and thus, defining an "equality" of an object should implement in some higher-level manner than just comparing value by value by hand.
So, this is how it looks like my way:

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
