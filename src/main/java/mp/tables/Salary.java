package mp.tables;

import mp.data.Month;

import java.util.Objects;

public class Salary {

    private Month month;
    private Worker worker;
    private int hours;
    private float rate;
    private int zus;
    private int vat;
    private float salary;

    public Salary() {
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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
        return hours == salary1.hours && Float.compare(salary1.rate, rate) == 0 && zus == salary1.zus
                && vat == salary1.vat && Float.compare(salary1.salary, salary) == 0 && month == salary1.month
                && Objects.equals(worker, salary1.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, worker, hours, rate, zus, vat, salary);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "month=" + month +
                ", worker=" + worker +
                ", hours=" + hours +
                ", rate=" + rate +
                ", zus=" + zus +
                "%, vat=" + vat +
                "%, salary=" + salary +
                '}';
    }
}
