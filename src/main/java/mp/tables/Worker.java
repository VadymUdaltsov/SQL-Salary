package mp.tables;

import java.util.Objects;

public class Worker {
    private String passport;
    private String surname;
    private String name;

    public String getWorker_Passport() {
        return passport;
    }

    public void setWorker_Passport(String passport) {
        this.passport = passport;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return passport == worker.passport && Objects.equals(surname, worker.surname) && Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport, surname, name);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "passport=" + passport +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
