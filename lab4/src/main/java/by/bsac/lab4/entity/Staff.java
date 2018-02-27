package by.bsac.lab4.entity;

import java.time.LocalDate;

public class Staff {
    private long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String role;
    private int filmCount;

    public Staff(long id, String name, String lastName, LocalDate birthDate, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
    }

    public Staff(long id, String name, String lastName, LocalDate birthDate, String role, int filmCount) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.filmCount = filmCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getFilmCount() {
        return filmCount;
    }

    public void setFilmCount(int filmCount) {
        this.filmCount = filmCount;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Staff) ) return false;

        Staff stuff = (Staff) o;

        if ( id != stuff.id ) return false;
        if ( filmCount != stuff.filmCount ) return false;
        if ( name != null ? !name.equals(stuff.name) : stuff.name != null ) return false;
        if ( lastName != null ? !lastName.equals(stuff.lastName) : stuff.lastName != null ) return false;
        if ( birthDate != null ? !birthDate.equals(stuff.birthDate) : stuff.birthDate != null ) return false;
        return role != null ? role.equals(stuff.role) : stuff.role == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + filmCount;
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", role='" + role + '\'' +
                ", filmCount=" + filmCount +
                '}';
    }
}
