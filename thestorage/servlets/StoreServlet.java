package com.thestorage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.thestorage.DAO.StoreDAO;
import com.thestorage.domain.Store;
import com.thestorage.views.StoreView;
import java.sql.SQLException;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public StoreServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequests(action, response, request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequests(action, response, request);
    }

    private void showData(HttpServletResponse resp) throws ClassNotFoundException, IOException, SQLException {

        StoreView storeView = new StoreView();

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        storeView.showFilter(resp);
        storeView.showAll(resp);
        storeView.closeConnection();

        out.println("</body>");
        out.println("</html>");
    }

    private void processRequests(String action, HttpServletResponse response, HttpServletRequest req) {
        try {
            switch (action) {
                case "Create":
                    createData(response, req);
                    break;
                case "Search":
                    showData(response);
                    break;
                case "Filter":
                    filters(response, req);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createData(HttpServletResponse resp, HttpServletRequest req) throws IOException, SQLException {
        StoreDAO storeDAO = new StoreDAO();
        Store store = new Store();
        store.setId(Long.valueOf(req.getParameter("txtStoreID")));
        store.setStoreName(req.getParameter("txtStoreName"));
        store.setStoreDistrict(req.getParameter("txtStoreDistrict"));
        store.setPilotNameId(req.getParameter("txtPilotNameID"));

        storeDAO.save(store);

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");

        if (storeDAO.getLastException() == null) {
            out.println("<h1>");
            out.println("Store Has Been Created Succesfully");
            out.println("</h1>");

            StoreView storeView = new StoreView();
            storeView.showRecord(resp, store.getId());
            storeDAO.closeConnection();
            storeView.closeConnection();
        } else if (storeDAO.getLastException().getSQLState().equalsIgnoreCase("23000")) {
            out.println("<h1>");
            out.println("Error, Store Number is not unique");
            out.println("</h1>");

        }
        out.println("<a href = \"newStore.html\" target = \"_self\" > Go Back</a>");
        out.println("</body>");
        out.println("</html>");
        
    }

    private void filters(HttpServletResponse resp, HttpServletRequest req) throws IOException, SQLException {
         StoreView storeView = new StoreView();

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        storeView.showFilter(resp);
        storeView.showAllfilters(resp, req);
        storeView.closeConnection();

        out.println("</body>");
        out.println("</html>");
    }
  

}
