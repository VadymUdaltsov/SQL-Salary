package mp.tables;

import java.util.Objects;

public class FullModel {
    private Salary salary;
    private Expenses expenses;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullModel fullModel = (FullModel) o;
        return Objects.equals(salary, fullModel.salary) && Objects.equals(expenses, fullModel.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, expenses);
    }

    @Override
    public String toString() {
        return "FullModel{ " + "passport: " + salary.getWorker_passport() + ", year: " +
                salary.getYear() + ", month_ID: " + salary.getMonth_ID() + ", month: " + salary.getMonth() +
                ", hours: " + salary.getHours() + ", rate: " + salary.getRate() + "Zł" + ", ZUS: "
                + salary.getZus() + ", VAT: " + salary.getVat() + ", salary: " + salary.getSalary() + "Zł" + ", food: " +
                expenses.getFood() + ", accountant: " + expenses.getAccountant() + ", phone: " + expenses.getPhone() +
                ", internet: "  + expenses.getInternet() + ", house: " + expenses.getHouse() + ", cat: " + expenses.getCat()
                + " }";
    }
}
