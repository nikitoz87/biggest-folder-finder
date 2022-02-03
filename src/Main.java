import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        String folderPath = "D:\\!ISO";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);

        //System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " milliseconds");


        /*
        MyThread thread = new MyThread(1);
        MyThread thread2 = new MyThread(2);
        thread.start();
        thread2.start();
        */
        //System.out.println(System.getProperties().get("user.dir"));

    }

    public static long getFolderSize (File folder)
    {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files ) {
            sum += getFolderSize(file);
        }

        return sum;

    }


}
