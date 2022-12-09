package com.thestorage.views;

import com.thestorage.DAO.StoreDAO;
import com.thestorage.domain.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreView {

    StoreDAO storeDao;

    public StoreView() {
        this.storeDao = new StoreDAO();
    }

    public void showAll(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>Id.</th>");
        out.println("<th>Store Name</th>");
        out.println("<th>District</th>");
        out.println("<th>Pilot Name</th>");
        out.println("</tr>");

        for (Store store : this.storeDao.getAll()) {
            out.println("<tr>");
            out.println("<td>" + Long.valueOf(store.getId()).toString() + "</td>");
            out.println("<td>" + store.getStoreName() + "</td>");
            out.println("<td>" + store.getStoreDistrict() + "</td>");
            out.println("<td>" + store.getPilotNameId() + "</td>");
            out.println("</tr>");

        }
        out.println("</table>");
    }

    public void showRecord(HttpServletResponse resp, Long Id) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>Id.</th>");
        out.println("<th>Store Name</th>");
        out.println("<th>District</th>");
        out.println("<th>Pilot Name</th>");
        out.println("</tr>");

        Store store = this.storeDao.get(Id);
        out.println("<tr>");
        out.println("<td>" + Long.valueOf(store.getId()).toString() + "</td>");
        out.println("<td>" + store.getStoreName() + "</td>");
        out.println("<td>" + store.getStoreDistrict() + "</td>");
        out.println("<td>" + store.getPilotNameId() + "</td>");
        out.println("</tr>");

        out.println("</table>");
        out.println("<a href = \"newStore.html\" target = \"_blank\"> New Store</a>");
    }

    public void showFilter(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<form action=\"store\" method=\"POST\">");
        out.println("<table class='tablafilter'>");
        out.println("<tr>");
        out.println("<td>Id: <input type=\"text\" minlength=\"5\" maxlength=\"50\" size=\"30\" name=\"txtStoreID\"></td>");
        out.println("<td>Store Name: <input type=\"text\" minlength=\"5\" maxlength=\"50\" size=\"30\" name=\"txtStoreName\"></td>");
        out.println("<td><input type=\"hidden\" name=\"action\" value=\"Filter\"><input type=\"submit\" value=\"Filter\"></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        

    }

    public void showAllfilters(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        PrintWriter out = resp.getWriter();
        Long id = Long.valueOf(0);
        if (!req.getParameter("txtStoreID").equalsIgnoreCase("")) {
            id = Long.valueOf(req.getParameter("txtStoreID"));
        }
        String storename = req.getParameter("txtStoreName");

        out.println("<table class='tabla'>");
        out.println("<tr>");
        out.println("<th>Id.</th>");
        out.println("<th>Store Name</th>");
        out.println("<th>District</th>");
        out.println("<th>Pilot Name</th>");
        out.println("</tr>");

        for (Store store : this.storeDao.getAllFiltered(id, storename)) {
            out.println("<tr>");
            out.println("<td>" + Long.valueOf(store.getId()).toString() + "</td>");
            out.println("<td>" + store.getStoreName() + "</td>");
            out.println("<td>" + store.getStoreDistrict() + "</td>");
            out.println("<td>" + store.getPilotNameId() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }

    public void closeConnection() throws SQLException {

        this.storeDao.closeConnection();
    }
}
