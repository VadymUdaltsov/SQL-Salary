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
        List<Worker> workers = workerServiceMySQL.getAllWorkers();
        System.out.println("Workers List: " + workers);
        System.out.println("*****************************************************");


        Worker lebedynskayaYaroslava = new Worker("Lebedynskaya", "Yaroslava");
        workerServiceMySQL.addWorker(lebedynskayaYaroslava);
        workers = workerServiceMySQL.getAllWorkers();
        System.out.println("Workers List After Added: " + workers);
        System.out.println("*****************************************************");


        workerServiceMySQL.deleteWorker(lebedynskayaYaroslava.getWorker_Passport());
        workers = workerServiceMySQL.getAllWorkers();
        System.out.println("Workers List After Deleted: " + workers);
        System.out.println("*****************************************************");

        Worker testWorkerForUpdate = new Worker("Robert", "Malkovich");
        workerServiceMySQL.addWorker(testWorkerForUpdate);

        String testPassport = testWorkerForUpdate.getWorker_Passport();
        workerServiceMySQL.getWorkerByPassport(testPassport);
        System.out.println("Before update Robert:  " + workerServiceMySQL.getAllWorkers());

        final Worker updatedWorker = new Worker("Alex", "Rackij");
        workerServiceMySQL.updateWorkerData(testPassport, updatedWorker);
        System.out.println("After update Robert:  " + workerServiceMySQL.getAllWorkers());
        workerServiceMySQL.deleteWorker(updatedWorker.getWorker_Passport());
        System.out.println("Updated test Worker was deleted!");
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
