package mp.dao;

import mp.tables.Expenses;

import java.util.List;

public interface ExpensesDAO {

    Expenses getExpensesForMonth(int year, String month, String passport);

    List<Expenses> getExpensesForYear(int year, String passport);

    void addExpensesMonth(Expenses expenses, String passport);

    void deleteExpensesMonth(int year, String month);

}
