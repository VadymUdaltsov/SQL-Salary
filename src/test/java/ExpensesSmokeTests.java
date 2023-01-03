import mp.dao.ExpensesServiceMySQL;
import mp.data.Month;
import mp.tables.Expenses;
import mp.tables.Worker;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class ExpensesSmokeTests {
    private ExpensesServiceMySQL expensesServiceMySQL;

    private List<Expenses> year;

    @BeforeSuite
    public void conf() {
        expensesServiceMySQL = new ExpensesServiceMySQL();
    }

    @Test
    public void getExpensesForYear() {
        year = expensesServiceMySQL.getExpensesForYear(2022);

        Assert.assertFalse(year.isEmpty());
    }

    @Test
    public void getExpensesForMonth() {
        year = expensesServiceMySQL.getExpensesForYear(2022);

        final int randomIndex = RandomUtils.nextInt(0, year.size() - 1);
        final Expenses randomMonthExpenses = year.get(randomIndex);

        final String month = fixMonth(randomMonthExpenses.getMonth().getTitle());

        final Expenses december = expensesServiceMySQL.getExpensesForMonth(2022, month);

        Assert.assertTrue(year.contains(december));
    }

    @Test
    public void addExpensesForMonth() {
        Expenses february = new Expenses(2022, Month.FEBRUARY, 750, 210, 75,
                71, 1250, 140);
        expensesServiceMySQL.addExpensesMonth(february);

        year = expensesServiceMySQL.getExpensesForYear(2022);

        Assert.assertTrue(year.contains(february));
    }

    @Test
    public void deleteExpensesByMonth() {
        year = expensesServiceMySQL.getExpensesForYear(2022);
        Expenses lastExpenses;

        if (year.size() != 0) {
            lastExpenses = year.get(year.size() - 1);
        } else throw new NullPointerException("List with expenses is empty");

        expensesServiceMySQL.deleteExpensesMonth(2022, fixMonth(lastExpenses.getMonth().getTitle()));

        year = expensesServiceMySQL.getExpensesForYear(2022);

        Assert.assertFalse(year.contains(lastExpenses));
    }


    private String fixMonth(String month) {
        return StringUtils.capitalize(month.toLowerCase());
    }
}
