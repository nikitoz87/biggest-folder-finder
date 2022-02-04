public class SizeCalculator {

    public static String getHumanReadableSize(long size) {
        //TODO
        //from bytes to
        //24B, 245Kb, 31Mb, 33Gb,4Tb

        String regKb = "\\d{4,6}";
        String regMb = "\\d{7,9}";
        String regGb = "\\d{10,12}";
        String regTb = "\\d{13,15}";
        String stringSize = Long.toString(size);

        if (size < 1024) {
            return size + " B";
        } else if (stringSize.matches(regKb)) {
            double dividedSize = (double) Math.round(( size / Math.pow(2, 10)) * 100) / 100 ;
            return String.format("%.2fKb",dividedSize);
        } else if (stringSize.matches(regMb)) {
            double dividedSize = (double) Math.round(( size / Math.pow(2, 20)) * 100) / 100 ;
            return String.format("%.2fMb", dividedSize);
        } else if (stringSize.matches(regGb)) {
            double dividedSize = (double) Math.round(( size / Math.pow(2, 30)) * 100) / 100 ;
            return String.format("%.2fGb", dividedSize);
        } else if (stringSize.matches(regTb)) {
            double dividedSize = (double) Math.round(( size / Math.pow(2, 40)) * 100) / 100 ;
            return String.format("%.2fTb", dividedSize);
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

