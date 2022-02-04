import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        String folderPath = "D:\\!ISO";
        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root.toString());

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " milliseconds");

        System.exit(0);

        // Trials
        /*
        String a = "235Kb";
        long b = getSizeFromHumanReadable(a);
        System.out.println("Long value of String: " + a + " is: " + b);

        long l = 240640L;
        String s = getHumanReadableSize(l);
        System.out.println("String value of Long: " + l + " is: " + s);
        */

        String stingSize = "10000100Gb";
        char sizeFactor = stingSize.replaceAll("[0-9\\s+]+", "").charAt(0);
        System.out.println("char is: " + sizeFactor);

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
