import mp.dao.SalaryServiceMySQL;
import mp.dao.WorkerServiceMySQL;
import mp.data.Month;
import mp.tables.Salary;
import mp.tables.Worker;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

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

        Assert.assertFalse(Objects.isNull(salary));
    }

    @Test
    public void addSalaryForMonth() {
        Salary february = new Salary("UK1UV", 2022, 2, Month.FEBRUARY,
                174, 33.45F, 9, 12);
        salaryServiceMySQL.addSalaryForMonth(february);

        final Salary salary = salaryServiceMySQL.getSalaryByPassport("UK1UV");

        Assert.assertFalse(Objects.isNull(salary));
    }


}
