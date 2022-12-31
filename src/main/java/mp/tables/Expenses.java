package mp.tables;

import mp.data.Month;

import java.util.Objects;

public class Expenses {
    private int year;
    private Month month;
    private int food;
    private int accountant;
    private int phone;
    private int internet;
    private int house;
    private int cat;

    public Expenses() {
    }

    public Expenses(int year, Month month, int food, int accountant, int phone, int internet, int house, int cat) {
        this.year = year;
        this.month = month;
        this.food = food;
        this.accountant = accountant;
        this.phone = phone;
        this.internet = internet;
        this.house = house;
        this.cat = cat;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return year == expenses.year && food == expenses.food && accountant == expenses.accountant
                && phone == expenses.phone && internet == expenses.internet && house == expenses.house
                && cat == expenses.cat && month == expenses.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, food, accountant, phone, internet, house, cat);
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "year=" + year +
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
