import java.io.File;

public class ParametersBag {
    String EXAMPLE = "java -jar BiggestFolderFinder.jar -p D:\\Temp -l 50Gb";
    private long limit;
    private String path;

    public ParametersBag(String args[]) {
        File folder = new File(args[1]);
        String regexB= "-p [A-Za-z]:\\\\[A-Za-z0-9!_-]+ -l \\d+[K,M,G,T]?b?";

        if (args.length > 4) {
            throw new IllegalArgumentException("Too many arguments, look at example" + "\n" + EXAMPLE);
        } else if (!folder.isDirectory() || !folder.exists()) {
            throw new IllegalArgumentException("Path doesn't exist or not a directory," +
                    " look at example \\n" + EXAMPLE);
        } else {
            String entireCommand = String.join(" ",args);
            //System.out.println(entireCommand);
            if (!entireCommand.matches(regexB)) {
                throw new IllegalArgumentException("Wrong command format, look at example" + "\n" + EXAMPLE);
            } else {
                limit = SizeCalculator.getSizeFromHumanReadable(args[3]);
                setLimit(limit);
                path = args[1];
                setPath(path);
            }

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
