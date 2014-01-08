package org.andrescandal.controller;

import java.util.HashMap;
import java.util.Map;

import org.andrescandal.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    /**
     * Stores the students data for this demo. (use a datasource instead!)
     */
    private static Map<Integer, Student> studentsMap = new HashMap<Integer, Student>();

    static {
        // initializes Student's demo map.
        Student s1 = new Student(1, "Paul", "paul@email.com");
        Student s2 = new Student(2, "Richard", "richard@email.com");
        studentsMap.put(s1.getId(), s1);
        studentsMap.put(s2.getId(), s2);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/institute")
    public @ResponseBody
    String getInstituteName() {

        return "Spring HttpConverters Institute!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
    public @ResponseBody
    Student getStudent(@PathVariable String id) {
        Student s = studentsMap.get(Integer.parseInt(id));
        return s;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/student/{id}")
    public @ResponseBody
    Student updateStudent(@RequestBody Student s, @PathVariable String id) {
        studentsMap.put(s.getId(), s);
        return s;
    }

}
