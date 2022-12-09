package com.thestorage.servlets;

import com.thestorage.DAO.OrderDAO;
import com.thestorage.domain.Order;
import com.thestorage.views.OrderView;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "orders", urlPatterns = {"/orders"})
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequest(action, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequest(action, request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void processRequest(String action, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            switch (action) {
                case "Create":
                    createData(response, request);
                    break;
                case "Search":
                    showData(response);
                    break;
                case "Filter":
                    filters(response, request);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createData(HttpServletResponse response, HttpServletRequest request) throws ParseException, IOException, SQLException {
       OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       
       order.setOrderId(Long.valueOf(request.getParameter("txtorderId")));
       order.setOrderLineItem(request.getParameter("txtorderLineItem"));
       order.setBillToName(request.getParameter("txtbillToName"));
       order.setBillToAddress1(request.getParameter("billToAddress1"));
       order.setBillToAddress2(request.getParameter("billToAddress2"));
       order.setZipcode(Integer.valueOf(request.getParameter("txtzipcode")));
       Date orderDate = (Date) formatter.parse(request.getParameter("txtorderCreateDttm"));
       order.setOrderCreateDttm(orderDate);
       order.setBillToStore(request.getParameter("txtbillToStore"));
       order.setOrderStatus(request.getParameter("txtorderStatus"));
       
       
       orderDAO.save(order);
       
       PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        
        if (orderDAO.getLastException() == null) {
            out.println("<h1>");
            out.println("Order Has Been Created Succesfully");
            out.println("</h1>");

            OrderView orderView = new OrderView();
            orderView.showRecord(response, order.getOrderId());
            orderView.closeConnection();
        } else {
            out.println("<h1>");
            out.println("Error, Could not been created");
            out.println("</h1>");

        }
        out.println("<a href = \"orders.html\" target = \"_self\" > Go Back</a>");
        out.println("</body>");
        out.println("</html>");
        
    }
           
 

    private void showData(HttpServletResponse response) throws SQLException, IOException {
          OrderView orderView = new OrderView();

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        orderView.showFilter(response);
        orderView.showAll(response);
        orderView.closeConnection();

        out.println("</body>");
        out.println("</html>");

}

    private void filters(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        OrderView orderView = new OrderView();
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><link rel='stylesheet' href='styles/stylesStore.css'></head>");
        out.println("<body>");
        orderView.showFilter(response);
        orderView.showAllfilters(response, request);
        orderView.closeConnection();

        out.println("</body>");
        out.println("</html>");
    }
}
