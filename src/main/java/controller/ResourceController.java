package controller;

import domain.HttpRequest;
import domain.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;

import java.io.IOException;

public class ResourceController extends AbstractController {
    private static final Logger log =  LoggerFactory.getLogger(ResourceController.class);

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        byte[] body = new byte[0];
        try {
            body = HttpRequestUtils.readFile(request.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String contentType = request.getHeader("Accept").split(" ")[0];
        log.debug("Content Type : {}", contentType);
        response.response200Header(body.length, contentType).responseBody(body);
    }
}
