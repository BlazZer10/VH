package com.bean;


import DB.Connect;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ManagedBean (name="log")
@SessionScoped
public class LogIn {

    private String name;
    private String password;
    private ResultSet result;
    private static int userID;
    private static String session_name = null;

    public String validate() {
        
        Connect db = new Connect();
        String email_query = null , pass_query = null;
        
        try {
            Statement stm = db.getConnection().createStatement();
            String query = "SELECT * FROM users WHERE email = '"+this.name+"'";
            this.result = stm.executeQuery(query);
            this.result.next();
            email_query = this.result.getString("Username");
            pass_query = this.result.getString("Password");
            } catch (Exception e) {
                System.err.println("Error is : "+e);
            }
        
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.name.equals(email_query) && this.password.equals(pass_query)){
            
            context.getExternalContext().getSessionMap().put("user", "user");
           
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            this.session_name = (String) session.getAttribute("user");
            session.setMaxInactiveInterval(60 * 10);
        }
        else{
            this.name = null;
            this.password = null;
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        String redirect = "/index.xhtml?faces-redirect=true";
        if(this.session_name.equals("admin")){
            redirect = "/admin.xhtml?faces-redirect=true";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        this.session_name = (String) session.getAttribute("user");
        return redirect;
    }
    
    public void setInfo(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getCurrentInstance().getExternalContext().getRequest();
        this.name = request.getParameter("username");
        this.password = request.getParameter("passw");
        if(this.session_name != null){
            logout();
        }
        validate();
}
    
    public String getSessionName(){
        return this.session_name;
    }
    
    public int getUserID(){
        return this.userID;
    }

}

