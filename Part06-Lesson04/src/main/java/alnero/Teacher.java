package alnero;

import java.util.Arrays;
import java.util.Objects;

public class Teacher {
    private final boolean isAvailable;
    private final int age;
    private final String name;
    private final Contact contact;
    private final String[] studies;

    public Teacher(boolean isAvailable, int age, String name, Contact contact, String[] studies) {
        this.isAvailable = isAvailable;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.studies = studies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return isAvailable == teacher.isAvailable && age == teacher.age && Objects.equals(name, teacher.name) && Objects.equals(contact, teacher.contact) && Arrays.equals(studies, teacher.studies);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isAvailable, age, name, contact);
        result = 31 * result + Arrays.hashCode(studies);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "available=" + isAvailable +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                ", studies=" + Arrays.toString(studies) +
                '}';
    }
}
