package mp.dao;

import mp.data.Month;
import mp.tables.Salary;
import mp.tables.Worker;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
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

    @Override
    public void addSalaryForMonth(Salary salary) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            final String validMonth = StringUtils.capitalize(salary.getMonth().getTitle().toLowerCase());
            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            String insert = "INSERT INTO job_salary(worker_passport, year, month_ID, month, hours, rate, ZUS, VAT, salary) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PREPARED_STATEMENT = CONNECTION.prepareStatement(insert);

            final Field[] declaredFields = salary.getClass().getDeclaredFields();

            int paramIndex = 1;
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.getName().equals("worker_passport")) {
                    PREPARED_STATEMENT.setString(paramIndex, salary.getWorker_passport());
                } else if (field.getName().equals("month")) {
                    PREPARED_STATEMENT.setString(paramIndex, validMonth);
                } else {
                    if (field.getType().getName().equals("float")) {
                        PREPARED_STATEMENT.setFloat(paramIndex, field.getFloat(salary));
                    } else {
                        PREPARED_STATEMENT.setInt(paramIndex, field.getInt(salary));
                    }
                }
                field.setAccessible(false);
                paramIndex++;
            }
            PREPARED_STATEMENT.execute();
        } catch (SQLException | IllegalAccessException e) {
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
        salary.setMonth_ID(resultSet.getInt("month_ID"));
        salary.setMonth(Month.valueOf(resultSet.getString("month").toUpperCase()));
        salary.setHours(resultSet.getInt("hours"));
        salary.setRate(resultSet.getFloat("rate"));
        salary.setZus(resultSet.getInt("ZUS"));
        salary.setVat(resultSet.getInt("VAT"));
        salary.setSalary(resultSet.getFloat("salary"));
    }
}
