package mp.dao;

import mp.tables.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static mp.data.Const.*;
import static mp.features.StringMethod.getFirstLetter;

public class WorkerServiceMySQL implements WorkerDAO {
    @Override
    public List<Worker> getAllWorkers() {
        return getWorkerList();
    }

    @Override
    public Worker getWorkerByPassport(String passport) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            PREPARED_STATEMENT = CONNECTION.prepareStatement("SELECT * FROM worker WHERE passport = ?");
            PREPARED_STATEMENT.setString(1, passport);

            final ResultSet resultSet = PREPARED_STATEMENT.executeQuery();
            Worker worker = new Worker();
            while (resultSet.next()) {
                worker.setPassport(resultSet.getString("passport"));
                worker.setSurname(resultSet.getString("surname"));
                worker.setName(resultSet.getString("name"));
            }

            return worker;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                PREPARED_STATEMENT.close();
                CONNECTION.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Worker updateWorkerData(String passport, Worker newWorker) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            final String newPassport = generatePassport(newWorker.getSurname(), newWorker.getName());
            newWorker.setPassport(newPassport);

            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            PREPARED_STATEMENT = CONNECTION.prepareStatement("UPDATE worker SET passport = ?, surname = ?, name = ?" +
                    " WHERE passport = ?");
            PREPARED_STATEMENT.setString(1, newPassport);
            PREPARED_STATEMENT.setString(2, newWorker.getSurname());
            PREPARED_STATEMENT.setString(3, newWorker.getName());
            PREPARED_STATEMENT.setString(4, passport);
            PREPARED_STATEMENT.executeUpdate();

            return getWorkerByPassport(newPassport);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                PREPARED_STATEMENT.close();
                CONNECTION.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addWorker(Worker worker) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            String passport = generatePassport(worker.getSurname(), worker.getName());
            worker.setPassport(passport);

            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            String insert = "INSERT INTO worker(passport, surname, name) VALUES (?, ?, ?)";
            PREPARED_STATEMENT = CONNECTION.prepareStatement(insert);

            PREPARED_STATEMENT.setString(1, passport);
            PREPARED_STATEMENT.setString(2, worker.getSurname());
            PREPARED_STATEMENT.setString(3, worker.getName());
            PREPARED_STATEMENT.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                PREPARED_STATEMENT.close();
                CONNECTION.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteWorker(String passport) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            String delete = "DELETE FROM worker WHERE passport = ?";

            PREPARED_STATEMENT = CONNECTION.prepareStatement(delete);
            PREPARED_STATEMENT.setString(1, passport);
            PREPARED_STATEMENT.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                PREPARED_STATEMENT.close();
                CONNECTION.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void addWorkersInList(ResultSet resultSet, List<Worker> workers) {
        Worker worker = new Worker();
        try {
            worker.setPassport(resultSet.getString("passport"));
            worker.setSurname(resultSet.getString("surname"));
            worker.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        workers.add(worker);
    }

    private List<Worker> getWorkerList() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            PREPARED_STATEMENT = CONNECTION.prepareStatement("SELECT * FROM worker");
            final ResultSet resultSet = PREPARED_STATEMENT.executeQuery();
            List<Worker> workers = new ArrayList<>();

            while (resultSet.next()) {
                addWorkersInList(resultSet, workers);
            }
            return workers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                PREPARED_STATEMENT.close();
                CONNECTION.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String generatePassport(String surname, String name) {
        final List<Worker> workers = getWorkerList();
        final Worker lastWorker = workers.get(workers.size() - 1);
        final String lastPassport = lastWorker.getPassport();
        final String numberInLastPassport = getNumberInPassport(lastPassport);

        return "UK" + numberInLastPassport +
                getFirstLetter(surname) +
                getFirstLetter(name);
    }

    private String getNumberInPassport(String passport) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(passport);
        String newNumberToString = null;
        while (matcher.find()) {
            final String numberFromPassport = matcher.group();
            int newNumber = Integer.parseInt(numberFromPassport) + 1;
            newNumberToString = Integer.toString(newNumber);
        }
        return newNumberToString;
    }
}
