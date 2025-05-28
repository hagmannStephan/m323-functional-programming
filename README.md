# Functional Sorting of Structured Data
## 1. Design Data Model
According to the requirements, I created this model:

<img src="https://github.com/user-attachments/assets/8aae9d75-14d3-4186-b9c8-43f6a414431e" alt="data model" width="400"/>


## 2. Implement Models
I created the model's in my Java Project in the [models folder](src/main/java/ch/bbw/models).

## 3. Generate Data
I decided to generate the test data with the website [generedata.com](https://generatedata.com/). Here is a screenshot of how I generated the mock data:

<img src="https://github.com/user-attachments/assets/ac4208e2-31cc-48df-b4d7-bec9e6395426" alt="screenshot generatedata" width="800"/>


When I run the project, I will parse the JSON and create objects of it with the Class [DataLoader](src\main\java\ch\bbw\util\DataLoader.java).

## 4. Implement Functional Sorting

**For a better structured version of the part below, checkout my CheatSheet!**

### 4.1 `Comparable`
Can **compare itself** to another object of the same type. Use it for **default sorting** like name or age. Implement it like this:
```java
// Implement the Comparable interface for comparing Attendee
public class Attendee implements Comparable<Atstendee> {
    private String name;
    private Date birthday;
    private int rank;
    private Guitar guitar;
    private Mentor mentor;

    @Override
    public int compareTo(Attendee other) {
        // Default: sort by name (alphabetically)
        return this.name.compareTo(other.name);
    }
}
```

Now it gets used in basic sort operations like the following:
```java
Collections.sort(attendeeList); // uses compareTo
```

### 4.2 `Comparator`

#### 4.2.1 `Comparator` derived Class
Allow to define custom comparison logic <span style="text-decoration: underline; font-weight: bold">outside the class</span> for which instances we want to sort:
```java
public class AttendeeByMentorDateOfBirth implements Comparator<Attendee> {

    @Override
    public int compare(Attendee a1, Attendee a2) {
        if (a1 == null || a2 == null) {
            return 0; // Handle null cases as needed
        }
        return a1.getMentor().getDateOfBirth().compareTo(a2.getMentor().getDateOfBirth());
    }
}
```
You can call it like this in the code:
```java
Collections.sort(attendees, new ch.bbw.util.AttendeeByMentorDateOfBirth());
```

#### 4.2.2 `Comparator` as anonymous Class
Write the Comparator class directly in the sort method:
```java
Comparator<Attendee> byDateOfBirth = new Comparator<Attendee>() {
    @Override
    public int compare(Attendee a1, Attendee a2) {
    if (a1 == null || a2 == null) {
        return 0; // Handle null cases as needed
    }
    return a1.getDateOfBirth().compareTo(a2.getDateOfBirth());
    }
};
// Does the same as Collections.sort(attendees, byDateOfBirth);, however would recommend usage as in example above
// Also possible to write the Comparator directly as sort param
attendees.sort(byDateOfBirth);
```

### 4.2.3 `Comparator` as lambda expression
Now the same with lamda expression:
```java
attendees.sort((a1, a2) -> {
    return a1.getRank().compareTo(a2.getRank());
});
```

### 4.2.4 `Comparator Chaining`
With Comparator Chaining you can define what should be compared if the first comparison returns an equal value:
```java
attendees.sort(
    Comparator.comparing((Attendee a) -> a.getGuitar().getPrice())
            // Only possible if getName is directly a method from Attendee
            .thenComparing(Attendee::getName)
);
```

# 4.3 Natural / Reverse Order
**Natural Order**: Ascending (e.g. oldest date first, youngest age first, ...)
**Reverse Order**: Descending (e.G. names with z first, true first, ...)
## Option 1: Change Comparison
```java
    return a1.getRank().compareTo(a2.getRank());
    // becomes (for last rank, first)
    return a2.getRank().compareTo(a1.getRank());
```
## Option 2: Use `reversed()`
Needs to be applied directly to the comparison, can't just be appended to, e.g. a lambda expression.
```java
	// attendees.sort((a1, a2) -> {
		// return a1.getRank().compareTo(a2.getRank());
	// });
	// becomes
	attendees.sort(Comparator.comparing(Attendee::getRank).reversed());
```