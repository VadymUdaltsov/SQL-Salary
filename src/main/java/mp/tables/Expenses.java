package mp.tables;

import mp.data.Month;

import java.util.Objects;

public class Expenses {

    private String worker_passport;
    private int year;
    private int month_ID;
    private Month month;
    private int food;
    private int accountant;
    private int phone;
    private int internet;
    private int house;
    private int cat;

    public Expenses() {
    }

    public Expenses(String worker_passport, int year, int month_ID, Month month, int food, int accountant, int phone,
                    int internet, int house, int cat) {
        this.worker_passport = worker_passport;
        this.year = year;
        this.month_ID = month_ID;
        this.month = month;
        this.food = food;
        this.accountant = accountant;
        this.phone = phone;
        this.internet = internet;
        this.house = house;
        this.cat = cat;
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

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getAccountant() {
        return accountant;
    }

    public void setAccountant(int accountant) {
        this.accountant = accountant;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenses expenses = (Expenses) o;
        return year == expenses.year && month_ID == expenses.month_ID && food == expenses.food
                && accountant == expenses.accountant && phone == expenses.phone && internet == expenses.internet
                && house == expenses.house && cat == expenses.cat
                && Objects.equals(worker_passport, expenses.worker_passport) && month == expenses.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker_passport, year, month_ID, month, food, accountant, phone, internet, house, cat);
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "worker_passport='" + worker_passport + '\'' +
                ", year=" + year +
                ", month_ID=" + month_ID +
                ", month=" + month +
                ", food=" + food +
                ", accountant=" + accountant +
                ", phone=" + phone +
                ", internet=" + internet +
                ", house=" + house +
                ", cat=" + cat +
                '}';
    }
}
