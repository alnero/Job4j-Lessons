package alnero;

import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TeacherJSONObjectTest {
    @Test
    public void whenCreateJSONObjectThen() {
        /* JSONObject from json string */
        JSONObject jsonContact = new JSONObject("{\"zipCode\":123456,\"phone\":\"+7 (111) 111-11-11\"}");

        /* JSONArray from ArrayList */
        List<String> studies = new ArrayList<>();
        studies.add("math");
        studies.add("history");
        studies.add("english");
        JSONArray jsonStudies = new JSONArray(studies);

        /* JSONObject via put method */
        Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        Teacher teacher = new Teacher(true, 30, "Smith", contact, new String[]{"math", "history", "english"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", teacher.getName());
        jsonObject.put("available", teacher.isAvailable());
        jsonObject.put("age", teacher.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("studies", jsonStudies);
        System.out.println(jsonObject.toString());

        /* JSONObject directly via POJO */
        System.out.println(new JSONObject(teacher).toString());
        assertThat(jsonObject.toString(), is(new JSONObject(teacher).toString()));
    }
}
