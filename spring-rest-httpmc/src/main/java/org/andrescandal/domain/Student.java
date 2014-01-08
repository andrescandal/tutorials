package org.andrescandal.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Student's entity class.
 * @author ACandal
 *
 */
@XStreamAlias("Student")
public class Student {

    private Integer id;
    private String firstName;
    private String email;

    public Student() {

    }

    public Student(Integer id, String firstName, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
