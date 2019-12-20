package database.topics;

public class TopicGather {

    public static void main(String[] args) {
        String rootPath = Thread.currentThread()
                .getContextClassLoader()
                .getResource("")
                .getPath();
        System.out.println(rootPath);
    }

    static {

    }


}
