package alnero;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "teacher")
@XmlAccessorType(XmlAccessType.FIELD)
public class Teacher {
    /** Teacher availability. **/
    @XmlAttribute
    private boolean isAvailable;

    /** Teacher age. **/
    @XmlAttribute
    private int age;

    /** Teacher name. **/
    @XmlAttribute
    private String name;

    /** Teacher contact. **/
    private Contact contact;

    /** Teacher's lessons. **/
    @XmlElementWrapper(name = "studies")
    @XmlElement(name = "study")
    private String[] studies;

    public Teacher() {

    }

    public Teacher(boolean isAvailable, int age, String name, Contact contact, String[] studies) {
        this.isAvailable = isAvailable;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.studies = studies;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStudies() {
        return studies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Teacher)) {
            return false;
        }
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
        return "Teacher{"
                + "available=" + isAvailable
                + ", age=" + age
                + ", name='" + name + '\''
                + ", contact=" + contact
                + ", studies=" + Arrays.toString(studies)
                + '}';
    }
}
