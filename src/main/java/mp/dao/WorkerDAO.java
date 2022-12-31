package mp.dao;

import mp.tables.Worker;

import java.util.List;

public interface WorkerDAO {
    List<Worker> getAllWorkers();

    void addWorker(String surname, String name);

    void deleteWorker(String passport);

    Worker getWorkerByPassport(String passport);
}
