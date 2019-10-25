package aggregate_db;

import java.io.File;

public class SomeOtherTest {

    public static void main(String[] args) {
        String pathToAllDB = "resources/ALL_ELEM.db";
        System.out.println(new File(pathToAllDB).exists());
    }

}
