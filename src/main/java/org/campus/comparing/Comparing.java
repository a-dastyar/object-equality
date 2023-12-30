package org.campus.comparing;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Alireza Dastyar
 */
public class Comparing {

    public static void main(String[] args) {
        /**
         * 
         * Since objects are passed by reference and not by value we can not 
         * use `==` operator to check equality hence there is a special method called equals() 
         * which every object has inherited, default implementation also compares 
         * the references and we need to override it to check equality as per our
         * definition of equality.
         * For example we might call two objects equal if the have same value at 
         * a certain fields: people object are equals if they have same national id for example.
         *
         */

        // Student class doesn't override the equals
        var student1 = new Student(1);
        var student2 = new Student(1);
        System.out.println("student1.quauls(student2): " + student1.equals(student2)); // false
        
        // Customer class does override the equals method and uses both fields to check equality.
        var customer1 = new Customer("User1",1);
        var customer2 = new Customer("User1",1);
        var customer3 = new Customer("User3",1);

        System.out.println("customer1.eqauls(customer2): "+customer1.equals(customer2)); // true 
        System.out.println("customer1.eqauls(customer3): "+customer1.equals(customer3)); // false, don't have same name.


        /**
         * Another special method is `hashCode()` which is very useful in context of hash-based data structures
         * Lets say we have a HashMap we know that HashMap uses a hash value of the key object to have O(1) access time.
         * How does HashMap know use which fields of the key object to generate the hash code?
         * Yes, it calls `hashCode()` on the key object and it's responsibility of the class author to provide the `hashCode()`
         * implementation.
         * It also uses the equals method incases of collision.
         */
        
        var studentAges=new HashMap<>();
        studentAges.put(new Student(1), 10);
        studentAges.put(new Student(2), 20);

        // returns null since it compare the references while they have same state they have different reference
        System.out.println("student1 age:"+studentAges.get(new Student(1))); 
        // returns null since it compare the references while they have same state they have different reference
        System.out.println("student2 age:"+studentAges.get(new Student(2))); 

        System.out.println("studentAges size:"+studentAges.size()); // 2

        /**
         * `toString()` is yet another inherited method which objects have.
         * It's used to represent objects as string and also has a useless default implementation
         * so we have to override it to be useful
         */

        // Student class doesn't override the `toString()` method.
        System.out.println(new Student(1));

        // Student class does override the `toString()` method.
        System.out.println(new Customer("User1",2023));
    }
}

class Student {

    public int id;

    public Student(int id) {
        this.id = id;
    }

}

class Customer {

    private String name;
    private long nationalId;

    public Customer(String name, long nationalId) {
        this.name = name;
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + (int) (this.nationalId ^ (this.nationalId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.nationalId != other.nationalId) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{");
        sb.append("name=").append(name);
        sb.append(", nationalId=").append(nationalId);
        sb.append('}');
        return sb.toString();
    }

}
