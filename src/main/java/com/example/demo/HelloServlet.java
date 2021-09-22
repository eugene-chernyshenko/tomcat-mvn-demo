package com.example.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties mainProps = new Properties();
        InputStream inputMain = getClass().getClassLoader().getResourceAsStream("main.properties");
        mainProps.load(inputMain);
        if (inputMain != null) {
            inputMain.close();
        }

        Properties overrideProps = new Properties();
        InputStream inputOverride = getClass().getClassLoader().getResourceAsStream("override.properties");
        overrideProps.load(inputOverride);
        if (inputOverride != null) {
            inputOverride.close();
        }

        Properties mergedProps = new Properties();
        mergedProps.putAll(mainProps);
        mergedProps.putAll(overrideProps);

        response.setStatus(200);
        response.getWriter().write("foo: " + mergedProps.getProperty("foo") + ", bar: " + mergedProps.getProperty("bar"));
    }
}
