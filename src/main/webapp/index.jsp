<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Accueil JSP - TP0</title>
    <link rel="stylesheet" href="resources/css/mycsslayout.css">
</head>
<body>
    <div class="main-container" style="max-width: 600px; margin-top: 50px;">
        <div class="app-card" style="text-align: center;">
            <div class="app-header" style="justify-content: center;">
                <h2 class="app-title">Accueil TP0 (Jakarta Server Pages)</h2>
            </div>
            <p style="font-size: 16px; margin: 20px 0;"><%= "Bienvenue sur l'application Jakarta EE !" %></p>
            <div style="margin: 25px 0;">
                <a href="hello-servlet" class="btn-primary" style="display: inline-block; text-decoration: none;">Accéder à la Servlet Hello</a>
            </div>
            <div style="border-top: 1px solid #e2e8f0; padding-top: 15px; margin-top: 20px;">
                <a href="index.xhtml" style="color: #2563eb; text-decoration: none; font-weight: 500;">➔ Aller vers l'interface Conversation (index.xhtml)</a>
            </div>
        </div>
    </div>
</body>
</html>
