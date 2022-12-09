package com.thestorage.views;

import com.thestorage.DAO.ItemDAO;
import com.thestorage.domain.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemView {

    ItemDAO itemDAO;

    //private Connection connection;
    public ItemView() {
        this.itemDAO = new ItemDAO();
    }

    public void closeConnection() throws SQLException {

        //this.itemDao.closeConnection();
    }

    public void showAll(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Store ID</th>");
        out.println("<th>Item Name</th>");
        out.println("<th>Item Weight</th>");
        out.println("<th>Item Value</th>");
        out.println("</tr>");

        for (Item item : this.itemDAO.getAll()) {
            out.println("<tr>");
            out.println("<td>" + item.getStoreID().toString() + "</td>");
            out.println("<td>" + item.getId().toString() + "</td>");
            out.println("<td>" + item.getItemName() + "</td>");
            out.println("<td>" + item.getItemWeight() + "</td>");
            out.println("<td>" + item.getItemValue() + "</td>");
            out.println("</tr>");

        }
        out.println("</table>");
    }

    public void showItemRecord(HttpServletResponse response, Integer id) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>Store ID</th>");
        out.println("<th>Id.</th>");
        out.println("<th>Item Name</th>");
        out.println("<th>Item Weigh</th>");
        out.println("<th>Item Value</th>");
        out.println("</tr>");

        Item item = this.itemDAO.get(id);
        out.println("<tr>");
        out.println("<td>" + Integer.valueOf(item.getStoreID()).toString() + "</td>");
        out.println("<td>" + Integer.valueOf(item.getId()).toString() + "</td>");
        out.println("<td>" + item.getItemName() + "</td>");
        out.println("<td>" + item.getItemWeight() + "</td>");
        out.println("<td>" + item.getItemValue() + "</td>");
        out.println("</tr>");

        out.println("</table>");
        out.println("<a href = \"item.jsp\" target = \"_blank\"> New Store</a>");
    }

    public void showRecord(HttpServletResponse resp, Integer id) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>Store ID</th>");
        out.println("<th>Id</th>");
        out.println("<th>Item Name</th>");
        out.println("<th>Item Weight</th>");
        out.println("<th>Item Value</th>");
        out.println("</tr>");

        Item item = this.itemDAO.get(id);
        out.println("<tr>");
        out.println("<td>" + Integer.valueOf(item.getStoreID()).toString() + "</td>");
        out.println("<td>" + Integer.valueOf(item.getId()).toString() + "</td>");
        out.println("<td>" + item.getItemName() + "</td>");
        out.println("<td>" + item.getItemWeight() + "</td>");
        out.println("<td>" + item.getItemValue() + "</td>");
        out.println("</tr>");

        out.println("</table>");
        out.println("<a href = \"item.jsp\" target = \"_blank\"> New Store</a>");
    }

    public void showFilter(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<form action=\"item\" method=\"POST\">");
        out.println("<table class='tablafilter'>");
        out.println("<tr>");
        out.println("<td>Id: <input type=\"text\" minlength=\"3\" maxlength=\"50\" size=\"30\" name=\"txtItemId\"></td>");
        out.println("<td><input type=\"hidden\" name=\"action\" value=\"Filter\"><input type=\"submit\" value=\"Filter\"></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
    }

    public void showAllfilters(HttpServletResponse response, HttpServletRequest request) throws IOException {
        PrintWriter out = response.getWriter();
        Integer id = Integer.valueOf(0);
        if (!request.getParameter("txtItemId").equalsIgnoreCase("")) {
            id = Integer.valueOf(request.getParameter("txtItemId"));

            Integer storeID = Integer.valueOf(0);
            if (!request.getParameter("txtStoreID").equalsIgnoreCase("")) {
                storeID = Integer.valueOf(request.getParameter("txtStoreID"));
            }
            // Integer storeID = Integer.valueOf(request.getParameter("txtStoreID"));

            out.println("<table class='tabla'>");
            out.println("<tr>");
            out.println("<th>Store ID</th>");
            out.println("<th>Id.</th>");
            out.println("<th>Item Name</th>");
            out.println("<th>Item Weight</th>");
            out.println("<th>Item Value</th>");
            out.println("</tr>");

            for (Item item : this.itemDAO.getAllItemFilter(id)) {
                out.println("<tr>");
                out.println("<td>" + item.getStoreID().toString() + "</td>");
                out.println("<td>" + item.getId().toString() + "</td>");
                out.println("<td>" + item.getItemName() + "</td>");
                out.println("<td>" + item.getItemWeight() + "</td>");
                out.println("<td>" + item.getItemValue() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        }

    }
}
