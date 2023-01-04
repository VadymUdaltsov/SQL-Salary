package mp.dao;

import mp.data.Month;
import mp.tables.Salary;
import mp.tables.Worker;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static mp.data.Const.*;

public class SalaryServiceMySQL implements SalaryDAO {

    @Override
    public Salary getSalaryByPassport(String passport) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            final Worker worker = new WorkerServiceMySQL().getWorkerByPassport(passport);

            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            PREPARED_STATEMENT = CONNECTION.prepareStatement("SELECT * FROM job_salary WHERE worker_passport = ?");
            PREPARED_STATEMENT.setString(1, passport);

            final ResultSet resultSet = PREPARED_STATEMENT.executeQuery();
            Salary salary = new Salary();

            while (resultSet.next()) {
                initSalary(resultSet, salary, worker);
            }

            return salary;
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

    private void initSalary(ResultSet resultSet, Salary salary, Worker worker) throws SQLException {
        salary.setWorker_passport(worker.getPassport());
        salary.setYear(resultSet.getInt("year"));
        salary.setMonth_ID( resultSet.getInt("month_ID"));
        salary.setMonth( Month.valueOf(resultSet.getString("month").toUpperCase()));
        salary.setHours(resultSet.getInt("hours"));
        salary.setRate(resultSet.getFloat("rate"));
        salary.setZus(resultSet.getInt("ZUS"));
        salary.setVat(resultSet.getInt("VAT"));
        salary.setSalary(resultSet.getFloat("salary"));
    }
}
