package com.example.tp0;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet de démonstration affichant un message de bienvenue.
 * 
 * @author Achraf Khairouni
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Message d'accueil initialisé au démarrage
    private String messageAccueil;

    @Override
    public void init() {
        this.messageAccueil = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><meta charset='UTF-8'><title>Hello Servlet</title></head><body>");
        writer.println("<h1>" + this.messageAccueil + "</h1>");
        writer.println("<p>Réponse générée par <code>HelloServlet</code>.</p>");
        writer.println("<p><a href='index.jsp'>Retour</a></p>");
        writer.println("</body></html>");
    }

    @Override
    public void destroy() {
        // Libération des ressources si nécessaire
    }
}
