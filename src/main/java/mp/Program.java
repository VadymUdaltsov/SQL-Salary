package mp;

import mp.dao.ExpensesServiceMySQL;
import mp.dao.WorkerServiceMySQL;
import mp.data.Month;
import mp.tables.Expenses;
import mp.tables.Worker;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        WorkerServiceMySQL workerServiceMySQL = new WorkerServiceMySQL();
        final List<Worker> workers = workerServiceMySQL.getAllWorkers();
        System.out.println("Workers List: " + workers);
        System.out.println("*****************************************************");


        workerServiceMySQL.addWorker("Lebedynskaya", "Yaroslava");
        final List<Worker> updatedWorkersAfterAdded = workerServiceMySQL.getAllWorkers();
        System.out.println("After Added Worker: ");
        for (Worker worker : updatedWorkersAfterAdded) {
            System.out.println(worker);
        }
        System.out.println("*****************************************************");


        workerServiceMySQL.deleteWorker("UK3LY");
        final List<Worker> updatedWorkersAfterDeleted = workerServiceMySQL.getAllWorkers();
        System.out.println("After Deleted Worker: ");
        for (Worker worker : updatedWorkersAfterDeleted) {
            System.out.println(worker);
        }
        System.out.println("*****************************************************");


        final Worker firstWorker = workerServiceMySQL.getWorkerByPassport("UK1UV");
        System.out.println("Get worker by Passport");
        System.out.println(firstWorker);
        System.out.println("*****************************************************");

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
