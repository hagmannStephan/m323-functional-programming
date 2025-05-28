# Imperative to Functional Programming

**Imperative Programming:** Telling the computer exactly what to do in which order e.g. with loops, if-statements, etc.

**Functional (deklarative) Programming:** Treats computation as the evaluation of mathematical functions and avoid changing state or mutable data.
## Example
**Imperative Code Example:**
```java
public class ImperativeExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> squares = new ArrayList<>();
        
        for (Integer number : numbers) {
            squares.add(number * number);
        }
        
        System.out.println(squares);
    }
}
```
**Functional Code Example:**
```java
public class FunctionalExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        
        List<Integer> squares = numbers.stream()
                                       .map(number -> number * number)
                                       .collect(Collectors.toList());
        
        System.out.println(squares);
    }
}
```
# Interface `Comparator`
Allow to define custom comparison logic <span style="text-decoration: underline; font-weight: bold">outside the class</span> for which instances we want to sort.
### Example: Comparator Derived Class
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
### Example: Comparator as Anonymous Class
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
// Identical to attendees.sort(byDateOfBirth);
// Tip: write comparator as param of .sort
Collections.sort(attendees, byDateOfBirth);
```
Call like this:
```java
Collections.sort(attendees, new ch.bbw.util.AttendeeByMentorDateOfBirth());
```
### Example: Comparator as Lambda Expression
```java
// Sort by rank
attendees.sort((a1, a2) -> {
    return a1.getRank().compareTo(a2.getRank());
});
```
## Class Attribute of Comparator
```java
public void sortByName(List<Person> people) {
	// Person::getName = (Person p) -> p.getName()
	people.sort(Comparator.comparing(Person::getName));
}
```
## `Comparator Chaining`
Define what should happen if the first comparison returns an equal value:
```java
attendees.sort(
    Comparator
		    .comparing((Attendee a) -> a.getGuitar().getPrice())
            // Only possible if getName is directly a method from Attendee
            // Does the same as .thenComparing((Attendee a) -> a.getName())
            .thenComparing(Attendee::getName)
);
```
# Class `Comparable`
Used to define the natural order of objects for objects of a class. Has to be implemented in this class.
```java
// Implement the Comparable interface for comparing Attendee
public class Attendee implements Comparable<Atstendee> {
    private String name;
    private Date birthday;

    @Override
    public int compareTo(Attendee other) {
        // Default: sort by name (alphabetically)
        // Change to date of birth (oldest one first)
        return this.birthday.compareTo(other.birthday);
    }
}
```
# Natural / Reverse Order
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
# Lombok / Record
Automatically generates `getter`, `setter`, `toString()` etc.
```java
import lombok.Data;

@Data
public class Guitar {
    private GuitarBrand brand;
    private String model;
    private Integer price;
    private String color;
}
```
# Additional Competences
## Java Natural-Order and Reverse
If the `compareTo` method hasn’t been overridden, it uses the default (natural) ordering defined by the type.  
**Natural order always sorts ascending**. It behaves like this:

| Datatype                        | Natural Order (Ascending)                    |
| ------------------------------- | -------------------------------------------- |
| `String`                        | Word starting with `A` first, with `Z` last  |
| `Integer`                       | Smallest first, largest last                 |
| `Boolean`                       | `false` < `true`                             |
| `LocalDate` / `Date`            | Earliest first, latest last                  |
| `Double`                        | Lowest first, highest last                   |
| `Character`                     | `'a'` < `'b'` < `'z'`                        |
| `Enum`                          | By declaration order in the enum class       |
| Custom Object with `Comparable` | Based on the implementation of `compareTo()` |
**Reverse order always sorts descending** and can be achieved with:
- `Comparator.reverseOrder()` for natural-order types
- `Collections.reverseOrder(customComparator)` for custom comparators
- Options in chapter `Natural / Reverse Order`
## Java Sort Collections
Java provides built-in methods to sort collections. The actual behavior depends on whether the elements implement `Comparable` (natural order) or if a `Comparator` is provided.  
Sorting is **stable** and based on **TimSort** (a hybrid of MergeSort and InsertionSort).

### Methods to Sort

| Method                         | Description                                           |
| ------------------------------ | ----------------------------------------------------- |
| `Collections.sort(list)`       | Sorts using natural order (`compareTo`)               |
| `Collections.sort(list, comp)` | Sorts using the given `Comparator`                    |
| `list.sort(comp)`              | Java 8+ method; equivalent to above, more fluent      |
| `stream.sorted()`              | Returns a sorted stream (natural order)               |
| `stream.sorted(comp)`          | Returns a sorted stream using the provided comparator |
### Requirements:

- For natural order: elements must implement `Comparable<T>`
- For custom sort: pass a `Comparator<T>` (e.g. via lambdas or `Comparator.comparing()`)
- Sorting is **in-place** for lists (`Collections.sort` and `list.sort`), but **non-mutating** for streams
### TimSort

The default algorithm for sorting `List` instances in Java is **TimSort**, introduced in Java 7. It is (this is java solution to sorting arrays and similar data structures):
- A hybrid **stable sorting algorithm** derived from merge sort and insertion sort
- Optimized for **partially ordered datasets**, exploiting runs (ordered subsequences)

It is implemented in the package-private class:  
`java.util.TimSort`

To see how complex it is, checkout [this video](https://youtu.be/NVIjHj-lrT4?si=p7KT29YtuWnutsgX).