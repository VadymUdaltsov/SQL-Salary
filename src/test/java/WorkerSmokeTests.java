import mp.dao.WorkerServiceMySQL;
import mp.tables.Worker;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class WorkerSmokeTests {
    private WorkerServiceMySQL workerService;

    private List<Worker> allWorkers;

    @BeforeSuite
    public void conf() {
        workerService = new WorkerServiceMySQL();
    }

    @Test
    public void getAllWorkers() {
        allWorkers = workerService.getAllWorkers();


        Assert.assertFalse(allWorkers.isEmpty());
    }

    @Test
    public void getWorkerByPassport() {
        allWorkers = workerService.getAllWorkers();
        final int randomIndex = RandomUtils.nextInt(0, allWorkers.size() - 1);
        final Worker randomWorker = allWorkers.get(randomIndex);

        final String passport = randomWorker.getPassport();
        final Worker workerByPassport = workerService.getWorkerByPassport(passport);

        Assert.assertTrue(allWorkers.contains(workerByPassport));
    }

    @Test
    public void addWorker() {
        Worker lebedynskayaYaroslava = new Worker("Lebedynskaya", "Yaroslava");
        workerService.addWorker(lebedynskayaYaroslava);

        allWorkers = workerService.getAllWorkers();

        Assert.assertTrue(allWorkers.contains(lebedynskayaYaroslava));
    }

    @Test
    public void updateWorker() {
        Worker testWorkerForUpdate = new Worker("Robert", "Malkovich");
        workerService.addWorker(testWorkerForUpdate);

        allWorkers = workerService.getAllWorkers();
        Assert.assertTrue(allWorkers.contains(testWorkerForUpdate));

        String testPassport = testWorkerForUpdate.getPassport();

        final Worker workerForUpdate = new Worker("Alex", "Rackij");
        workerService.updateWorkerData(testPassport, workerForUpdate);

        allWorkers = workerService.getAllWorkers();
        Assert.assertTrue(allWorkers.contains(workerForUpdate));

        workerService.deleteWorker(workerForUpdate.getPassport());

        allWorkers = workerService.getAllWorkers();
        Assert.assertFalse(allWorkers.contains(workerForUpdate));
    }

    @Test
    public void deleteWorker() {
        allWorkers = workerService.getAllWorkers();
        Worker lastWorker;

        if (allWorkers.size() != 0) {
            lastWorker = allWorkers.get(allWorkers.size() - 1);
        } else throw new NullPointerException("List with workers is empty");

        workerService.deleteWorker(lastWorker.getPassport());

        allWorkers = workerService.getAllWorkers();

        Assert.assertFalse(allWorkers.contains(lastWorker));
    }
}
