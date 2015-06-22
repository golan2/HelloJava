package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    02/05/2014 00:28
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class HelloLambda {

    private static List<Person> persons = generatePersons();

    private static List<Person> generatePersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("A first",  10));
        persons.add(new Person("A second", 20));
        persons.add(new Person("A third",  30));
        persons.add(new Person("B first",  40));
        persons.add(new Person("B second", 50));
        persons.add(new Person("B third",  60));
        persons.add(new Person("C first",  70));
        persons.add(new Person("C second", 80));
        persons.add(new Person("C third",  90));
        return persons;
    }

    public static void main(String[] args) {


        Predicate<Person> beginsWithA = (Person person) -> person.getName().startsWith("A");
        Predicate<Person> beginsWithB = (Person person) -> person.getName().startsWith("B");
        Predicate<Person> post40      = (Person person) -> person.getAge() > 40;

        persons.stream().filter(beginsWithB).forEach(Person::printPerson);
        persons.stream().filter(beginsWithB.negate()).map(Person::getMail).forEach(System.out::println);



        //printPersonsWithPredicate(persons, beginsWithA);
        //processPersons(persons, beginsWithA, Person::printPerson);
        //processPersonsWithFunction(persons, beginsWithA.and(post40.negate()), Person::getName, System.out::println);

        //
        //String[] strArray = {"412","413","414"};
        //List<String> list = Arrays.asList(strArray);
        //Double average = list.stream().filter(s -> s.startsWith("4")).collect(Collectors.averagingDouble(Double::parseDouble));
        //long count = list.stream().collect(Collectors.counting());


    }

    /**
     * Print only the matching persons
     * @param allPersons all persons
     * @param isMatch which persons to print
     */
    public static void printPersonsWithPredicate(List<Person> allPersons, Predicate<Person> isMatch) {
        for (Person p : allPersons) {
            if (isMatch.test(p)) {
                p.printPerson();
            }
        }
    }


    /**
     * Do something to matching persons
     * Iterate all persons and find the matching ones. Do something to every person that match
     * @param allPersons all persons
     * @param isMatch which persons to print
     * @param doSomething what to do for each matching person
     */
    public static void processPersons(List<Person> allPersons, Predicate<Person> isMatch, Consumer<Person> doSomething) {
        allPersons.stream().filter(isMatch).forEach(doSomething);
        //for (Person p : allPersons) {
        //    if (isMatch.test(p)) {
        //        doSomething.accept(p);
        //    }
        //}
    }

    /**
     * Extract some data from each matching person and do something with this data
     * @param allPersons all persons
     * @param isMatch which persons to print
     * @param extractor how to extract the date from the person
     * @param doSomething what to do with the extracted data
     */
    public static void processPersonsWithFunction(List<Person> allPersons, Predicate<Person> isMatch, Function<Person, String> extractor, Consumer<String> doSomething) {
        allPersons.stream().filter(isMatch).map(extractor).forEach(doSomething);
        //for (Person p : allPersons) {
        //    if (isMatch.test(p)) {
        //        String data = extractor.apply(p);
        //        doSomething.accept(data);
        //    }
        //}
    }

}
