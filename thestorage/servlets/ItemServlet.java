package com.thestorage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.thestorage.DAO.ItemDAO;
import com.thestorage.domain.Item;
import com.thestorage.views.ItemView;
import static java.lang.String.valueOf;
import java.sql.SQLException;

@WebServlet(name = "item", urlPatterns = {"/item"})
public class ItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ItemServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequest(action, request, response);

        //request.getRequestDispatcher("items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequest(action, request, response);

        //request.getRequestDispatcher("items.jsp").forward(request, response);
    }

    /*@Override
public String getServletInfo() {
        return "Short description";
    }*/
    protected void processRequest(String action, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            switch (action) {
                case "Creat":
                    addItem(request, response);
                    break;
                case "Search":
                    showItem(response);
                    break;
                case "Update":
                    //  updateStore(response);
                    break;
                case "Delete":
                //DeleteStore(response);
                case "Filter":
                    filterItem(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        ItemDAO itemDAO = new ItemDAO();
        Item item = new Item();
        
        item.setStoreID(Integer.valueOf(request.getParameter("txtStoreID")));
        item.setId(Integer.valueOf(request.getParameter("txtItemId")));
        item.setItemName(request.getParameter("txtItemName"));
        item.setItemWeight(Double.parseDouble(request.getParameter("txtItemWeight")));
        item.setItemValue(Float.parseFloat(request.getParameter("txtItemValue")));

        itemDAO.save(item);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");

        if (itemDAO.getLastException() == null) {
            out.println("<h1>");
            out.println("Item Has Been Created Succesfully");
            out.println("</h1>");

            ItemView itemView = new ItemView();
            itemDAO.closeConnection();
            itemView.closeConnection();

        } else {
            out.println("<h1>");
            out.println("Error, Item Number is not unique");
            out.println("</h1>");

        }
        out.println("<a href = \"items.jsp\" target = \"_self\" > Go Back</a>");
        out.println("</body>");
        out.println("</html>");

    }

    private void showItem(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        ItemView itemView = new ItemView();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        itemView.showFilter(response);
        itemView.showAll(response);
        //itemView.closeConnection();

        out.println("</body>");
        out.println("</html>");
    }

    private void filterItem(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        ItemView itemView = new ItemView();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        itemView.showFilter(response);
        itemView.showAllfilters(response, request);
        itemView.closeConnection();

        out.println("</body>");
        out.println("</html>");

    }

}
