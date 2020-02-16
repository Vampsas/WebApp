package io.github.mat3e;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private static final String NAME_PARAM = "name";
    private HelloService service;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public HelloServlet() {
        this(new HelloService());
    }

    public HelloServlet(HelloService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var reqestMap = req.getParameterMap();
        logger.info("Request got" + req.getParameter("name"));
        resp.getWriter().write(service.prepareGreeting(req.getParameter(NAME_PARAM)));
        // var name = req.getParameter("name");
//         ((map != null) ? (resp.getWriter().write("Hello " + map)) : (resp.getWriter().write("Hello " ));
        //resp.getWriter().write("Hello " + (NAME_PARAM != null ? NAME_PARAM : "someone"));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(req.getParameter("name"));
    }
}