package toyproject.annonymouschat.web.controller.href;

import org.junit.jupiter.api.Test;
import toyproject.annonymouschat.config.controller.controller.ControllerWithTwoMap;

import static org.junit.jupiter.api.Assertions.*;

class IndexServletTest {
    @Test
    void instance() throws InstantiationException, IllegalAccessException {
//        IndexServlet indexServlet = new IndexServlet();
//        System.out.println(indexServlet instanceof ControllerWithTwoMap); // true
//
//        Object indexServletClass = IndexServlet.class;
//        System.out.println(indexServletClass instanceof ControllerWithTwoMap); //false

        Object indexServlet = IndexServlet.class.newInstance();
        System.out.println(indexServlet);
        System.out.println(indexServlet instanceof ControllerWithTwoMap);
    }
}