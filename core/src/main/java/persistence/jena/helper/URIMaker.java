package persistence.jena.helper;

/**
 * Created by User on 2/14/2016.
 */
public class URIMaker {
    public static final String cvr = "http://cver.com/resource/";
    public static final String cvo = "http://cver.com/ontology/";

    public static final String getCvr(String account) {
        return addBraces(cvr + account);
    }

    public static final String getCvo(String account) {
        return addBraces(cvo + account);
    }

    private static final String addBraces(String uri) {
        return " <" + uri + "> ";
    }
}
