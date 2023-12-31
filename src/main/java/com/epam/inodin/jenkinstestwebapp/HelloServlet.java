package com.epam.inodin.jenkinstestwebapp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

@WebServlet(name = "/helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String postgresqlUrl;
    private String postgresqlUsername;
    private String postgresqlPassword;

    public void init() {
        postgresqlUrl = System.getenv("POSTGRESQL_URL");
        postgresqlUsername = System.getenv("POSTGRESQL_USERNAME");
        postgresqlPassword = System.getenv("POSTGRESQL_PASSWORD");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + getTextFromDb() + "</h1>");
        out.println("<h1>" + getInfoFromServlet() + "</h1>");
        out.println("</body></html>");
    }

    public String getTextFromDb() {

        String result;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(postgresqlUrl, postgresqlUsername, postgresqlPassword)) {
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT text FROM example WHERE id=1");
            rs.next();
            result = rs.getString("text");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String getInfoFromServlet(){
        return "Hello from servlet!";
    }

    public void destroy() {
    }

}