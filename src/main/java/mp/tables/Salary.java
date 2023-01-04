package mp.tables;

import mp.data.Month;

import java.util.Objects;

public class Salary {
    private String worker_passport;
    private int year;
    private int month_ID;
    private Month month;
    private int hours;
    private float rate;
    private int zus;
    private int vat;
    private float salary;

    public Salary() {
    }

    public Salary(String worker_passport, int year, int month_ID, Month month, int hours, float rate, int zus, int vat) {
        this.worker_passport = worker_passport;
        this.year = year;
        this.month_ID = month_ID;
        this.month = month;
        this.hours = hours;
        this.rate = rate;
        this.zus = zus;
        this.vat = vat;
        this.salary = calcSalary();
    }

    public String getWorker_passport() {
        return worker_passport;
    }

    public void setWorker_passport(String worker_passport) {
        this.worker_passport = worker_passport;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth_ID() {
        return month_ID;
    }

    public void setMonth_ID(int month_ID) {
        this.month_ID = month_ID;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getZus() {
        return zus;
    }

    public void setZus(int zus) {
        this.zus = zus;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary1 = (Salary) o;
        return year == salary1.year && month_ID == salary1.month_ID && hours == salary1.hours
                && Float.compare(salary1.rate, rate) == 0 && zus == salary1.zus && vat == salary1.vat
                && Float.compare(salary1.salary, salary) == 0
                && Objects.equals(worker_passport, salary1.worker_passport) && month == salary1.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker_passport, year, month_ID, month, hours, rate, zus, vat, salary);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "worker_passport='" + worker_passport + '\'' +
                ", year=" + year +
                ", month_ID=" + month_ID +
                ", month=" + month +
                ", hours=" + hours +
                ", rate=" + rate +
                ", zus=" + zus +
                ", vat=" + vat +
                ", salary=" + salary +
                '}';
    }

    private float calcSalary() {
        if ((hours * rate * 12) > 60000) {
            setVat(12);
            salary = (hours * rate) - (hours * rate * (zus + vat) / 100);
        } else {
            setVat(0);
            salary = (hours * rate) - (hours * rate * zus / 100);
        }
        return salary;
    }
}

