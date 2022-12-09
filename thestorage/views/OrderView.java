package com.thestorage.views;

import com.thestorage.DAO.OrderDAO;
import com.thestorage.domain.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderView {

    OrderDAO orderDAO;

    public OrderView() {
        this.orderDAO = new OrderDAO();
    }

    public void showAll(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>orderId</th>");
        out.println("<th>orderLineItem</th>");
        out.println("<th>billToName</th>");
        out.println("<th>billToAddress1</th>");
        out.println("<th>billToAddress2</th>");
        out.println("<th>zipcode</th>");
        out.println("<th>orderCreateDttm</th>");
        out.println("<th>billToStore</th>");
        out.println("<th>orderStatus</th>");

        out.println("</tr>");

        for (Order order : this.orderDAO.getAll()) {
            out.println("<tr>");
            out.println("<td>" + Long.valueOf(order.getOrderId()).toString() + "</td>");
            out.println("<td>" + order.getOrderLineItem() + "</td>");
            out.println("<td>" + order.getBillToName() + "</td>");
            out.println("<td>" + order.getBillToAddress1() + "</td>");
            out.println("<td>" + order.getBillToAddress2() + "</td>");
            out.println("<td>" + order.getZipcode() + "</td>");
            out.println("<td>" + order.getOrderCreateDttm() + "</td>");
            out.println("<td>" + order.getBillToStore() + "</td>");
            out.println("<td>" + order.getOrderStatus() + "</td>");
            
            out.println("</tr>");
        }
        out.println("</table>");
    }
    
    public void showRecord(HttpServletResponse resp, Long Id) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>orderId</th>");
        out.println("<th>orderLineItem</th>");
        out.println("<th>billToName</th>");
        out.println("<th>billToAddress1</th>");
        out.println("<th>billToAddress2</th>");
        out.println("<th>zipcode</th>");
        out.println("<th>orderCreateDttm</th>");
        out.println("<th>billToStore</th>");
        out.println("<th>orderStatus</th>");

        for (Order order : this.orderDAO.getAll()) {
            out.println("<tr>");
            out.println("<td>" + Long.valueOf(order.getOrderId()).toString() + "</td>");
            out.println("<td>" + order.getOrderLineItem() + "</td>");
            out.println("<td>" + order.getBillToName() + "</td>");
            out.println("<td>" + order.getBillToAddress1() + "</td>");
            out.println("<td>" + order.getBillToAddress2() + "</td>");
            out.println("<td>" + order.getZipcode() + "</td>");
            out.println("<td>" + order.getOrderCreateDttm() + "</td>");
            out.println("<td>" + order.getBillToStore() + "</td>");
            out.println("<td>" + order.getOrderStatus() + "</td>");
            
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href = \"order.html\" target = \"_blank\"> oredes</a>");
    }
    
    public void showFilter(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<form action=\"order\" method=\"POST\">");
        out.println("<table class='tablafilter'>");
        out.println("<tr>");
        out.println("<td>Id: <input type=\"text\" minlength=\"2\" maxlength=\"8\" size=\"30\" name=\"txtorderId\"></td>");
       // out.println("<td>Store Name: <input type=\"text\" minlength=\"5\" maxlength=\"50\" size=\"30\" name=\"txtStoreName\"></td>");
        out.println("<td><input type=\"hidden\" name=\"action\" value=\"Filter\"><input type=\"submit\" value=\"Filter\"></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
    }
    
     public void showAllfilters(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        PrintWriter out = resp.getWriter();
        Long orderId = Long.valueOf(0);
        if (!req.getParameter("txtorderId").equalsIgnoreCase("")) {
            orderId = Long.valueOf(req.getParameter("txtorderId"));
        }
        String storename = req.getParameter("txtStoreName");

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>orderId</th>");
        out.println("<th>orderLineItem</th>");
        out.println("<th>billToName</th>");
        out.println("<th>billToAddress1</th>");
        out.println("<th>billToAddress2</th>");
        out.println("<th>zipcode</th>");
        out.println("<th>orderCreateDttm</th>");
        out.println("<th>billToStore</th>");
        out.println("<th>orderStatus</th>");

        for (Order order : this.orderDAO.getAllFiltered(orderId)) {
            out.println("<tr>");
            out.println("<td>" + Long.valueOf(order.getOrderId()).toString() + "</td>");
            out.println("<td>" + order.getOrderLineItem() + "</td>");
            out.println("<td>" + order.getBillToName() + "</td>");
            out.println("<td>" + order.getBillToAddress1() + "</td>");
            out.println("<td>" + order.getBillToAddress2() + "</td>");
            out.println("<td>" + order.getZipcode() + "</td>");
            out.println("<td>" + order.getOrderCreateDttm() + "</td>");
            out.println("<td>" + order.getBillToStore() + "</td>");
            out.println("<td>" + order.getOrderStatus() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }

    public void closeConnection() throws SQLException {

        this.orderDAO.closeConnection();
    }

}
