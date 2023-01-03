package mp.dao;

import mp.tables.Salary;

public interface SalaryDAO {
    Salary getSalaryByPassport(String passport);
}
