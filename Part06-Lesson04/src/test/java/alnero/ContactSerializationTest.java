package alnero;

import org.junit.Test;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContactSerializationTest {
    @Test
    public void whenSerializeContactObjectThenDeserializedContactObjectIsTheSame() throws IOException, ClassNotFoundException {
        Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        System.out.println("Serialized object: " + contact);

        File tempFile = Files.createTempFile(null, null).toFile();
        /* Serialize */
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        /* De-serialize */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Contact contactFromFile = (Contact) ois.readObject();
            System.out.println("Deserialized object: " + contactFromFile);
            assertThat(contact, is(contactFromFile));
        }
    }
}
