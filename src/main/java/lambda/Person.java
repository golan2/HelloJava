package lambda;

/**
* <pre>
* <B>Copyright:</B>   HP Software IL
* <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
* <B>Creation:</B>    02/05/14 17:23
* <B>Since:</B>       BSM 9.21
* <B>Description:</B>
*
* </pre>
*/
public class Person {
    private final String name;
    private final int    age;
    private final String mail;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.mail = name.replaceFirst(" ", ".") + "@gmail.com";
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }

    public void printPerson() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", mail='" + mail + '\'' +
            '}';
    }
}
