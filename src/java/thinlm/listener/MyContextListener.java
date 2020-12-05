package thinlm.listener;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String,String> siteMap = new HashMap<>();
        ServletContext context = sce.getServletContext();
        siteMap.put("", "PagingController");
        siteMap.put("Home", "LoadPageController");
        siteMap.put("Logout", "LogoutController");
        siteMap.put("ViewCart", "ViewCartController");
        siteMap.put("Login", "LoginController");
        siteMap.put("Update", "UpdateCakeController");
        siteMap.put("AddToCart", "AddToCartController");
        siteMap.put("ApplyChange", "ApplyChangeAndChooseImageController");
        siteMap.put("Search", "SearchController");
        siteMap.put("Paging", "PagingController");
        siteMap.put("Create", "CreateAndChooseImageController");
        siteMap.put("Paging", "PagingForSearchAndHomeController");
        siteMap.put("CheckOutAll", "CheckOutAllController");
        siteMap.put("SubCheckOutAll", "SubCheckOutAllController");
        siteMap.put("Edit", "EditCakeInCart");
        siteMap.put("Confirm", "ConfirmRemoveController");
        siteMap.put("OrderTracking", "orderTracking.jsp");
        siteMap.put("SearchOrder", "SearchOrderController");
        siteMap.put("editQuantity", "EditQuantityController");
        siteMap.put("SeeDetail", "ViewOrderDetail");
        siteMap.put("GmailLogin", "GoogleLoginController");
        siteMap.put("CreateGGAccount", "CreateAccountForGGController");
                
        context.setAttribute("SITE_MAP", siteMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
