package alnero;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TeacherJsonTest {
    @Test
    public void whenStringifyTeacherToFromJsonThenSameTeacherReturned() {
        Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        Teacher teacher = new Teacher(true, 30, "Smith", contact, new String[]{"math", "history", "english"});

        /* Stringify to json */
        Gson gson = new GsonBuilder().create();
        System.out.println("JSON string: " + gson.toJson(teacher));

        /* From json to object */
        String teacherJson =
                "{"
                   + "\"isAvailable\":true,"
                   + "\"age\":30,"
                   + "\"name\":\"Smith\","
                   + "\"contact\":{"
                       + "\"zipCode\":123456,"
                       + "\"phone\":\"+7 (111) 111-11-11\""
                   + "},"
                   + "\"studies\":[\"math\",\"history\",\"english\"]"
               + "}";
        Teacher teacherFromJson = gson.fromJson(teacherJson, Teacher.class);
        System.out.println("Object from JSON string: " + teacherFromJson);
        assertThat(teacher, is(teacherFromJson));
    }
}
