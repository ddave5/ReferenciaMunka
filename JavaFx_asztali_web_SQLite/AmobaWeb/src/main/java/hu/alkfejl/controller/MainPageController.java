package hu.alkfejl.controller;

import hu.alkfejl.Model.Game;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MainPageController")
public class MainPageController extends HttpServlet {

    private Object Game;

    public MainPageController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("loadgame") != null){
            resp.sendRedirect("pages/ReplayPanel.jsp");
        }
        else if(req.getParameter("startgame") != null){
            req.setAttribute("game",Game);
            req.getRequestDispatcher("pages/GamePanel.jsp").forward(req,resp);
            //resp.sendRedirect("pages/GamePanel.jsp");
        }
    }
}
