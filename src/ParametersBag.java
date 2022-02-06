import java.io.File;

public class ParametersBag {
    String EXAMPLE = "java -jar BiggestFolderFinder.jar -p D:\\Temp -l 50Mb";
    private long limit;
    private String path;

    public ParametersBag(String[] args) {

        String regexPath= "-p [A-Za-z]:\\\\[A-Za-z0-9!_-]+";
        String regexLimit = "-l \\d+[K,M,G,T]?b?";

        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments, must be four," +
                    " look at example" + "\n" + EXAMPLE);
        } else {
            String firstHalf = String.join(" ",args[0], args[1]);
            String secondHalf = String.join(" ",args[2], args[3]);
            if (firstHalf.matches(regexPath) & secondHalf.matches(regexLimit)) {
                setVariables(args[1],args[3]);
            } else if (firstHalf.matches(regexLimit) & secondHalf.matches(regexPath)) {
                setVariables(args[3],args[1]);
            } else {
                throw new IllegalArgumentException("Wrong command format, look at example" + "\n" + EXAMPLE);
            }
        }
    }

    private void setVariables (String checkedPath, String checkedLimit) {
        File folder = new File(checkedPath);
        if (!folder.isDirectory() || !folder.exists()) {
            throw new IllegalArgumentException("Path doesn't exist or not a directory," +
                    " look at example \\n" + EXAMPLE);
        } else {
            limit = SizeCalculator.getSizeFromHumanReadable(checkedLimit);
            setLimit(limit);
            path = checkedPath;
            setPath(path);
        }
    }

    public long getLimit(){
        return limit;
    }

    private void setLimit(long limit){
        this.limit = limit;
    }

    public String getPath(){
        return path;
    }

    private void setPath(String path){
        this.path = path;
    }

}
