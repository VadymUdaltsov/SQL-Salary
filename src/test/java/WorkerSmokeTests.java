import mp.dao.WorkerServiceMySQL;
import mp.tables.Worker;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class WorkerSmokeTests {
    private WorkerServiceMySQL workerService;

    @BeforeSuite
    public void conf() {
        workerService = new WorkerServiceMySQL();
    }

    @Test
    public void addWorker() {
        Worker lebedynskayaYaroslava = new Worker("Lebedynskaya", "Yaroslava");
        workerService.addWorker(lebedynskayaYaroslava);

        final List<Worker> allWorkers = workerService.getAllWorkers();

        Assert.assertTrue(allWorkers.contains(lebedynskayaYaroslava));
    }

    @Test
    public void deleteWorker() {
        List<Worker> allWorkers = workerService.getAllWorkers();
        Worker lastWorker;

        if (allWorkers.size() != 0) {
            lastWorker = allWorkers.get(allWorkers.size() - 1);
        } else throw new NullPointerException("List with workers is empty");

        workerService.deleteWorker(lastWorker.getWorker_Passport());

        final List<Worker> updatedList = workerService.getAllWorkers();

        Assert.assertFalse(updatedList.contains(lastWorker));
    }
}
