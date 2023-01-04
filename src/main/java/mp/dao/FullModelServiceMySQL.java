package mp.dao;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import mp.data.Month;
import mp.tables.Expenses;
import mp.tables.FullModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static mp.data.Const.*;

public class FullModelServiceMySQL implements FullModelDAO {
    @Override
    public FullModel getAllWorkerData(String passport) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            String monthlyData = "monthly_expenses.food, monthly_expenses.accountant, monthly_expenses.number_account, " +
                    "monthly_expenses.internet, monthly_expenses.house, monthly_expenses.cat";

            String filter = "job_salary.worker_passport = monthly_expenses.worker_passport";

            CONNECTION = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            PREPARED_STATEMENT = CONNECTION.prepareStatement("SELECT job_salary.*, " + monthlyData +
                    " FROM job_salary" + " INNER JOIN monthly_expenses ON " + filter);
            final ResultSet resultSet = PREPARED_STATEMENT.executeQuery();
            FullModel model = new FullModel();

            while (resultSet.next()) {
                initModel(resultSet, model);
            }
            return model;
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

    private void initModel(ResultSet resultSet, FullModel model) {
        final java.lang.reflect.Field[] modelFields = model.getClass().getDeclaredFields();
        final com.mysql.cj.result.Field[] resultFields = ((ResultSetImpl) resultSet).getMetadata().getFields();
        int i = 0;
        for (java.lang.reflect.Field modelField : modelFields) {
            modelField.setAccessible(true);
            Object instance;
            try {
                instance = modelField.getType().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            final Field[] instanceFields = instance.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : instanceFields) {
                field.setAccessible(true);

                final boolean exPassport = field.getDeclaringClass().equals(Expenses.class) && field.getName().equals("worker_passport");
                final boolean exYear = field.getDeclaringClass().equals(Expenses.class) && field.getName().equals("year");
                final boolean exMonth_ID = field.getDeclaringClass().equals(Expenses.class) && field.getName().equals("month_ID");
                final boolean exMonth = field.getDeclaringClass().equals(Expenses.class) && field.getName().equals("month");

                if (exPassport || exYear || exMonth_ID || exMonth){
                    try {
                        if (field.getType().equals(String.class) || field.getType().equals(Month.class)) {
                            field.set(instance, null);
                        } else {
                            field.set(instance, 0);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else if (field.getType().equals(String.class)) {
                    try {
                        field.set(instance, resultSet.getString(resultFields[i].getName()));
                    } catch (IllegalAccessException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                    i++;
                } else if (field.getType().equals(Month.class)) {
                    final Month month;
                    try {
                        month = Month.valueOf(resultSet.getString("month").toUpperCase());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        field.set(instance, month);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    i++;
                } else {
                    if (field.getType().getName().equals("int")) {
                        try {
                            field.set(instance, resultSet.getInt(resultFields[i].getName()));
                        } catch (IllegalAccessException | SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            field.set(instance, resultSet.getFloat(resultFields[i].getName()));
                        } catch (IllegalAccessException | SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    i++;
                }
            }
            try {
                modelField.set(model, instance);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
