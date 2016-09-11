package com.cver.team.web;

import com.cver.team.model.externalresource.StaticFile;
import com.cver.team.services.StaticFileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

/**
 * Created by PC on 09/09/2016.
 */
@Controller
@RequestMapping(value = "/static")
public class StaticFileController {
    @Autowired
    StaticFileService staticFileService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void productPicture(HttpServletResponse response, @PathVariable String id) throws IOException {
        OutputStream out = response.getOutputStream();
        StaticFile staticFile = staticFileService.get(id);
        String contentDisposition = String.format("inline;filename=\"%s\"",
                staticFile.getIdentifier().getId() + staticFile.getName().substring(staticFile.getName().indexOf(".")));
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentType(staticFile.getContentType());
        response.setContentLength((int) staticFile.getValue().length);
        IOUtils.copy(new ByteArrayInputStream(staticFile.getValue()), out);
        out.flush();
        out.close();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String uploadImageForUser(@RequestParam("file") MultipartFile file) throws IOException {
        StaticFile staticFile = new StaticFile();
        staticFile.setValue(file.getBytes());
        staticFile.setContentType(file.getContentType());
        staticFile.setName(file.getOriginalFilename());
        staticFile = staticFileService.save(staticFile);

        return staticFile.getUrl();
    }
}
