package mp.dao;

import mp.tables.Worker;

import java.util.List;

public interface WorkerDAO {
    List<Worker> getAllWorkers();

    void addWorker(Worker worker);

    void deleteWorker(String passport);

    Worker getWorkerByPassport(String passport);

    Worker updateWorkerData(String passport, Worker newWorker);
}
