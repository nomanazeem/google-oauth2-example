package org.example.servlets;

import com.google.api.services.oauth2.model.Userinfo;
import org.example.OAuthUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html;");
    response.getWriter().println("<h1>OAuth 2.0 Example</h1>");

    String sessionId = request.getSession().getId();
    boolean isUserLoggedIn =
        OAuthUtils.isUserLoggedIn(sessionId);
    
    if (isUserLoggedIn) {
      Userinfo userInfo = OAuthUtils.getUserInfo(sessionId);

      response.getWriter().println("<p>ID: " + userInfo.getId() + "</p>");
      response.getWriter().println("<p>Email: " + userInfo.getEmail() + "</p>");
      response.getWriter().println("<p>First name: " + userInfo.getGivenName() + "</p>");
      response.getWriter().println("<p>Last name: " + userInfo.getFamilyName() + "</p>");
      response.getWriter().println("<p>Full name: " + userInfo.getName() + "</p>");
      response.getWriter().println("<img src=\"" + userInfo.getPicture() + "\" />");
      
      response.getWriter().println("<p><a href=\"/logout\">Logout</a></p>");
    } else {
      response.getWriter().println("<a href=\"/login\">Login with Google</a>");	
    }
  }
}