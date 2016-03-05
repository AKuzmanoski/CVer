package persistence.jena.helper.namespaces;

/**
 * Created by User on 3/1/2016.
 */
public class CVG {
    private static final String URI = "http://www.cver.com/graph/";

    public static String getURI() {
        return URI;
    }

    public static String getUri(String URI) {
        return CVG.URI + URI;
    }
}
