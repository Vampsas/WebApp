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
    private static final String LANG_PARAM = "lang";
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
        logger.info("Request got" + req.getParameter("name"));
        String name = req.getParameter(NAME_PARAM);
        String lang = req.getParameter(LANG_PARAM);
        resp.getWriter().write(service.prepareGreeting(name, lang));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(req.getParameter("name"));
    }
}