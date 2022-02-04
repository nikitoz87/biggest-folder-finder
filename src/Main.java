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

        int number = (int) Math.ceil(Math.log10(Math.abs(size) + 0.5));
        System.out.println(number);

        //System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " milliseconds");


        String a = "235412413Gb";
        long b = getSizeFromHumanReadable(a);
        System.out.println("Long value of String: " + a + " is: " + b);

        long l = 102030405060708L;
        String s = getHumanReadableSize(l);
        System.out.println("String value of Long: " + l + " is: " + s);

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


    public static String getHumanReadableSize(long size) {
        //TODO
        //from bytes to
        //24B, 245Kb, 31Mb, 33Gb,4Tb

        String regKb = "\\d{4,6}";
        String regMb = "\\d{7,9}";
        String regGb = "\\d{10,12}";
        String regTb = "\\d{13,15}";
        String stringSize = Long.toString(size);
        System.out.println(stringSize);

        if (size < 1024) {
            return size + " B";
        } else if (stringSize.matches(regKb)) {
            double dividedSize = size / Math.pow(2,10);
            return String.format("%.0fKb",dividedSize);
        } else if (stringSize.matches(regMb)) {
            double dividedSize = size / Math.pow(2, 20);
            return String.format("%.0fMb", dividedSize);
        } else if (stringSize.matches(regGb)) {
            double dividedSize = size / Math.pow(2, 30);
            return String.format("%.0fGb", dividedSize);
        } else if (stringSize.matches(regTb)) {
            double dividedSize = size / Math.pow(2, 40);
            return String.format("%.0fTb", dividedSize);
        }

        return "";

    }

    public static long getSizeFromHumanReadable(String size) {
        //TODO
        //from
        //24B, 245Kb, 31Mb, 33Gb,4Tb
        //24B, 245K, 31M, 33G,4T
        //to bytes
        //235K => 240640
        String reg = "\\d+[M,G,T]b?";

        if (size.endsWith("B")) {
            String longSize = size.replace("B","");
            return Long.valueOf(longSize).longValue();
        } else if (size.endsWith("Kb") || (size.endsWith("K"))) {
            String regex = "Kb?";
            String longSize = size.replaceFirst(regex,"");
            return Long.valueOf(longSize).longValue() * (long) Math.pow(2,10);
        } else if (size.endsWith("Mb") || (size.endsWith("M"))) {
            String regex = "Mb?";
            String longSize = size.replaceFirst(regex,"");
            return Long.valueOf(longSize).longValue() * (long) Math.pow(2,20);
        } else if (size.endsWith("Gb") || (size.endsWith("G"))) {
            String regex = "Gb?";
            String longSize = size.replaceFirst(regex,"");
            return Long.valueOf(longSize).longValue() * (long) Math.pow(2,30);
        } else if (size.endsWith("Tb") || (size.endsWith("T"))) {
            String regex = "Tb?";
            String longSize = size.replaceFirst(regex,"");
            return Long.valueOf(longSize).longValue() * (long) Math.pow(2,40);
        }

        return 0;
    }

}
