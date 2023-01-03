package mp;

import mp.dao.ExpensesServiceMySQL;
import mp.data.Month;
import mp.tables.Expenses;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        ExpensesServiceMySQL expensesServiceMySQL = new ExpensesServiceMySQL();

        final Expenses december = expensesServiceMySQL.getExpensesForMonth(2022, "December");
        System.out.println("Expenses for Month: " + december);
        System.out.println("*****************************************************");


        Expenses january = new Expenses(2022, Month.JANUARY, 800, 210, 75,
                72, 1250, 120);

        expensesServiceMySQL.deleteExpensesMonth(2022, "January");

        expensesServiceMySQL.addExpensesMonth(january);

        final Expenses month = expensesServiceMySQL.getExpensesForMonth(2022, "January");
        System.out.println("new month: " + month);

        final List<Expenses> expensesForYear = expensesServiceMySQL.getExpensesForYear(2022);
        System.out.println("All expenses for Year: " + expensesForYear);
        System.out.println("*****************************************************");
    }
}
