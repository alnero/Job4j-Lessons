package alnero.jsonCircularReference;

import org.json.JSONObject;
import org.junit.Test;

public class jsonCircularReferenceTest {
    @Test
    public void whenUseJsonPropertyIgnoreThenNoStackOverflowError() {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));
    }
}
