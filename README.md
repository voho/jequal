# jEqual

[![Travis](https://travis-ci.org/voho/jequal.svg?branch=master)](https://travis-ci.org/voho/jequal) [![codecov.io](https://codecov.io/github/voho/jequal/coverage.svg?branch=master)](https://codecov.io/github/voho/jequal?branch=master)
[![Jitpack](https://jitpack.io/v/voho/jequal.svg)](https://jitpack.io/#voho/jequal)

Making `equals` and `hashCode` nice and easy to understand since **2016**!

## Introduction

Implementing `equals` and `hashCode` properly is quite a challenge. The IDE can usually generate the code for you, but the result is often a little bit cryptic, and that remains true even after the recent additions in JDK 8.
If implemented improperly, the crippled equality can lead to difficult Heisenbugs when using objects in various equality-based collections, like sets.
In my opinion, definition of an **equality** of an object with another object is something that has to be defined very clearly using the right concepts and at the correct level of abstraction.
Simply comparing value by value, checking for *null*s and stuff... my patience is already gone.

## Example

This is how you can do it using the jEqual library!

```java
public class Person {
    private static final Equality<Person> EQUALITY = Equality
            .onType(Person.class)
            .allowingSubType()
            .checkingEqualityOf(Person::getFirstName)
            .checkingEqualityOf(Person::getLastName)
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

## Usage

You can include this library in your Maven project simply using the Jitpack service.

This has two steps. Step one, include this repository:

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Step two, add this dependency (you can find the latest version in `pom.xml` file):

```
<dependency>
    <groupId>com.github.voho</groupId>
    <artifactId>jequal</artifactId>
    <version>{SPECIFY_VERSION_HERE}</version>
</dependency>
```
