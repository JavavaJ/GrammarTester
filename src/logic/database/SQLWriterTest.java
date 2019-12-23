package logic.database;

class SQLWriterTest {
    public static void main(String[] args) {
        int id = 4;
        String body = "My father _____ early retirement.";
        String a = "was taken";
        String b = "has taken";
        String c = "has been taken";
        String d = "take";
        
        /*
        SQLWriter writer = new SQLWriter();
        writer.storeValues(id, body, a, b, c, d); // SUCCESS!!!
        */
        
        String dDName = "TEST4";
        String tableName = "test4";        
        DataBaseMaker.createDB(dDName, tableName);
        System.out.println("DB is created!");
        
        SQLWriter writer = new SQLWriter(dDName, tableName);
        writer.storeValues(id, tableName, a, b, c, d);
        
        System.out.println("Values are stored!"); // SUCCUSS!!
        
    }
}