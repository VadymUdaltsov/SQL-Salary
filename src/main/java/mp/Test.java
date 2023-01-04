package mp;

import mp.dao.FullModelServiceMySQL;
import mp.tables.FullModel;

public class Test {
    public static void main(String[] args) {
        FullModelServiceMySQL fullModelServiceMySQL = new FullModelServiceMySQL();
        final FullModel uk1UV = fullModelServiceMySQL.getAllWorkerData("UK1UV");
        System.out.println(uk1UV);
    }
}
