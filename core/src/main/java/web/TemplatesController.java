package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/template")
public class TemplatesController {

    @Autowired
    ServletContext servletContext;

    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map getDefaultTemplate() {

        File defaultTemplate = new File( servletContext.getRealPath("/WEB-INF/cv_templates/defaultTemplate.html"));
        try {
            BufferedReader br = new BufferedReader( new FileReader(defaultTemplate));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            return Collections.singletonMap("response",sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }
}
