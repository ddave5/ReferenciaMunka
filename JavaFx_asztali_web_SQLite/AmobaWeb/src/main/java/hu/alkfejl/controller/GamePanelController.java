package hu.alkfejl.controller;

import hu.alkfejl.Model.Game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GamePanelController")
public class GamePanelController extends HttpServlet {
    private Game g ;
    public GamePanelController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        g = new Game();
        try {
            g.setPve(req.getParameter("pve").equals("Player v CPU"));
            g.setTablesize(Integer.parseInt(req.getParameter("number")));
            if (req.getParameter("timelimit").equals("none")) {
                g.setTime(-1);
            } else {
                g.setTime(Integer.parseInt(req.getParameter("timelimit")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (req.getParameter("backToMainButton") != null) {
            System.out.println("kutya");
            resp.sendRedirect("pages/MainPagePanel.jsp");

        }
        System.out.println("Macska");
    }
}
