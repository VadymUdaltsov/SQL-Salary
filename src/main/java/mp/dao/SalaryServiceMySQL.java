package mp.dao;

import mp.data.Month;
import mp.tables.Expenses;
import mp.tables.Salary;
import mp.tables.Worker;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            PREPARED_STATEMENT = CONNECTION.prepareStatement("SELECT * FROM job_salary WHERE worker_passport = ?");
            PREPARED_STATEMENT.setString(1, passport);

            final ResultSet resultSet = PREPARED_STATEMENT.executeQuery();
            Salary salary = new Salary();

            while (resultSet.next()) {
                initSalary(resultSet, salary);
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

    private void initSalary(ResultSet resultSet, Salary salary) throws SQLException {
        salary.setMonth(Month.valueOf(resultSet.getString("month").toUpperCase()));
        salary.setWorker(new Worker(resultSet.getString("worker_passport"),
                resultSet.getString("surname"),
                resultSet.getString("name")));
        salary.setHours(resultSet.getInt("hours"));
        salary.setRate(resultSet.getFloat("rate"));
        salary.setZus(resultSet.getInt("ZUS"));
        salary.setVat(resultSet.getInt("VAT"));
        salary.setSalary(resultSet.getFloat("salary"));
    }
}
