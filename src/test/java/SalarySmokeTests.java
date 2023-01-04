import mp.dao.SalaryServiceMySQL;
import mp.dao.WorkerServiceMySQL;
import mp.tables.Salary;
import mp.tables.Worker;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class SalarySmokeTests {

    private SalaryServiceMySQL salaryServiceMySQL;
    private WorkerServiceMySQL workerServiceMySQL;

    @BeforeSuite
    public void conf() {
        salaryServiceMySQL = new SalaryServiceMySQL();
        workerServiceMySQL = new WorkerServiceMySQL();
    }

    @Test
    public void getSalaryByMonth() {
        final List<Worker> allWorkers = workerServiceMySQL.getAllWorkers();
        final int randomIndex = RandomUtils.nextInt(0, allWorkers.size() - 1);
        final String randomPassport = allWorkers.get(randomIndex).getPassport();

        final Worker workerByPassport = workerServiceMySQL.getWorkerByPassport(randomPassport);

        final Salary salary = salaryServiceMySQL.getSalaryByPassport(workerByPassport.getPassport());
        System.out.println("");
    }
}
