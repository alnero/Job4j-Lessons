package alnero;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TeacherXmlTest {
    @Test
    public void whenStringifyTeacherToFromXmlThenSameTeacherReturned() throws Exception {
        Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        Teacher teacher = new Teacher(true, 30, "Smith", contact, new String[]{"math", "history", "english"});

        JAXBContext context = JAXBContext.newInstance(Teacher.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String teacherXml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(teacher, writer);
            teacherXml = writer.getBuffer().toString();
            System.out.println(teacherXml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(teacherXml)) {
            Teacher teacherFromXml = (Teacher) unmarshaller.unmarshal(reader);
            System.out.println(teacherFromXml);
            assertThat(teacher, is(teacherFromXml));
        }
    }
}
