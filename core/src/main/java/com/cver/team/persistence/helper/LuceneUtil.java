package com.cver.team.persistence.helper;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 03/09/2016.
 */
public final class LuceneUtil {

    private LuceneUtil() {}

    public static List<String> tokenizeString(Analyzer analyzer, String string) {
        List<String> result = new ArrayList<>();
        try {
            TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
        } catch (IOException e) {
            // not thrown b/c we're using a string reader...
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String analyzeString(Analyzer analyzer, String string) {
        List<String> result = tokenizeString(analyzer, string);
        StringBuilder stringBuilder = new StringBuilder();
        if (result.size() == 0)
            stringBuilder.append("*");
        for (int i = 0; i < result.size(); i++) {
            String token = result.get(i);
            stringBuilder.append(token);
            if (i < result.size() - 1)
                stringBuilder.append("* ");
            else
                stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }
}
