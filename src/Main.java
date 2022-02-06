import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        //for (int i = 0; i < args.length; i++) {
        //    System.out.println(i + " => " + args[i]);
        //}

        ParametersBag bag = new ParametersBag(args);

        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();

        //String folderPath = "D:\\!ISO";
        //long sizeLimit = 50 * 1024 * 1024;

        File file = new File(folderPath);
        Node root = new Node(file,sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root.toString());

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " milliseconds");

    }


    public static long getFolderSize (File folder)
            // Deprecated method
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
