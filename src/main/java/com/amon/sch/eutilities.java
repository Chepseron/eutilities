package com.amon.sch;

import com.amon.db.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.xml.ws.WebServiceRef;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.TreeNode;
import ws.Eutilities_Service;

@javax.faces.bean.ManagedBean(name = "eutilities")
@SessionScoped
public class eutilities implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ugkm5199lt201_8080/E-Utilities/eutilities.wsdl")
    private Eutilities_Service service;

    private Logger logger = Logger.getLogger("errorLog");
    @PersistenceContext(unitName = "eutilitiesPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Audit audit = new Audit();
    private Boolean remember = false;
    private List<Audit> auditList = new ArrayList<Audit>();
    private Status status = new Status();
    private List<Status> statusList = new ArrayList<Status>();
    private Payments payment = new Payments();
    private List<Payments> paymentList = new ArrayList<Payments>();
    private Usergroups usergroup = new Usergroups();
    private List<Usergroups> usergroupList = new ArrayList<Usergroups>();
    private Users user = new Users();
    private List<Users> usersList = new ArrayList<>();
    private String username = new String();
    private String password = new String();
    private UploadedFile file;
    private TreeNode root;
    private boolean skip;
    private String filename;

    public eutilities() {
    }

    @PostConstruct
    public void init() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    private String orientation = "horizontal";

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    private LineChartModel projectModel;

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getAttributes().clear();
        return "/index.xhtml?faces-redirect=true";
    }

    public String loginPage() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String onFlowProcess(FlowEvent event) {
        if (isSkip()) {
            setSkip(false);   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String login() {
        try {
            try {
                setUser((Users) getEm().createQuery("select u from Users u where u.username = '" + getUsername() + "' and u.password = '" + getPassword() + "'").getSingleResult());
                return "utilities.xhtml?faces-redirect=true";
            } catch (Exception ex) {
                ex.printStackTrace();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Please provide correct credentials");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginInfoMessages", message);
                return "index.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!ERROR!", "Internal server error");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginInfoMessages", message);
            getLogger().info(e.getMessage());
        }
        return null;
    }

    public String pay() {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "!SUCCESS!", "Payment for your utility bill has been made successfully : transaction reference RRXX123654");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("loginInfoMessages", message);

        return null;
    }

    public String payUmeme() {
        try {
            ws.Eutilities port = service.getEutilitiesPort();
            java.lang.String prn = "";
            java.lang.String result = port.umeme(prn);
            System.out.println("Result = " + result);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", result));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String payNwsc() {
        try { // Call Web Service Operation
            ws.Eutilities port = service.getEutilitiesPort();
            java.lang.String prn = "";
            java.lang.String result = port.nwsc(prn);
            System.out.println("Result = " + result);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", result));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String payNssf() {
        try { // Call Web Service Operation
            ws.Eutilities port = service.getEutilitiesPort();
            // TODO initialize WS operation arguments here
            java.lang.String prn = "";
            // TODO process result here
            java.lang.String result = port.nssf(prn);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", result));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String payUra() {
        try { // Call Web Service Operation
            ws.Eutilities port = service.getEutilitiesPort();
            java.lang.String prn = "";
            java.lang.String result = port.ura(prn);
            System.out.println("Result = " + result);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", result));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String createUsers() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUser().setDatecreated(new java.util.Date());
            getUser().setCreatedBy(1);
            Random rand = new Random();
            getUser().setPassword("1234");
            getUser().setTimeloggedin(new java.util.Date());

            getUtx().begin();
            getAudit().setActionperformed("created a user");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().persist(getUser());
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUser().getFullnames() + " saved successfully."));
            setUser(new Users());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String updateUsers() {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Users user = getEm().find(Users.class, getUser().getIdusers());
            user = user;
            getUtx().begin();
            getAudit().setActionperformed("Updated a user");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(user);
            getEm().persist(getAudit());
            getEm().merge(user);
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", user.getUsername() + " Updated successfully."));
            user = new Users();
        } catch (Exception ex) {
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }
        return null;
    }

    public String deleteUsers(Users user) {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("Updated a user");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(user);
            getEm().persist(getAudit());
            Users toBeRemoved = (Users) getEm().merge(user);
            getEm().remove(toBeRemoved);
            getUtx().commit();
            user = new Users();
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Users deleted", "Users deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Users", success);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Users", success);
        }

        return null;
    }

    public String createStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("created a status");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().persist(getStatus());
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getStatus().getName() + " saved successfully."));
            setStatus(new Status());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String updateStatus() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Status stats = getEm().find(Status.class, getStatus().getIdstatus());
            stats = getStatus();
            getUtx().begin();
            getAudit().setActionperformed("Updated a status");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().merge(stats);
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getStatus().getName() + " Updated successfully."));
            setStatus(new Status());
        } catch (Exception ex) {
            getLogger().info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }
        return null;
    }

    public String deleteStatus(Status status) {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("Deleted a status");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            Status toBeRemoved = (Status) getEm().merge(getStatus());
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Status deleted", "status deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("status", success);
            status = new Status();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("status", success);
        }

        return null;
    }

    public String createUserGroup() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("created a group");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().persist(getStatus());
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUsergroup().getName() + " saved successfully."));
            setStatus(new Status());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String updateUserGroup() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Usergroups usergroups = getEm().find(Usergroups.class, getUsergroup().getIdusergroups());
            usergroups = getUsergroup();
            getUtx().begin();
            getAudit().setActionperformed("Updated a user group");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().merge(usergroups);
            getUtx().commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getUsergroup().getName() + " Updated successfully."));
            usergroups = new Usergroups();
        } catch (Exception ex) {
            getLogger().info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }
        return null;
    }

    public String deleteUserGroup(Usergroups usergroup) {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("Deleted a user group");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            Usergroups toBeRemoved = (Usergroups) getEm().merge(getUsergroup());
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User group deleted", "User group deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Usergroup", success);
            usergroup = new Usergroups();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Usergroup", success);
        }

        return null;
    }

    public String createPayment() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("created a status");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().persist(getStatus());
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getStatus().getName() + " saved successfully."));
            setStatus(new Status());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            ex.printStackTrace();
            getLogger().info(ex.getMessage());
        }
        return null;
    }

    public String updatePayment() {
        try {
            if (StringUtils.isEmpty(getUsername())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            Status stats = getEm().find(Status.class, getStatus().getIdstatus());
            stats = getStatus();
            getUtx().begin();
            getAudit().setActionperformed("Updated a status");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            getEm().merge(stats);
            getUtx().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", getStatus().getName() + " Updated successfully."));
            setStatus(new Status());
        } catch (Exception ex) {
            getLogger().info(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Could not update a user."));
        }
        return null;
    }

    public String deletePayment(Status status) {
        try {
            if (StringUtils.isEmpty(getUsername())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please login to the system"));
                return "/index.xhtml";
            }
            getUtx().begin();
            getAudit().setActionperformed("Deleted a status");
            getAudit().setDateperformed(new java.util.Date());
            getAudit().setUser(getUser());
            getEm().persist(getAudit());
            Status toBeRemoved = (Status) getEm().merge(getStatus());
            getEm().remove(toBeRemoved);
            getUtx().commit();

            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Status deleted", "status deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("status", success);
            status = new Status();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().info(e.getMessage());
            FacesMessage success = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("status", success);
        }

        return null;
    }

    /**
     * @return the remember
     */
    public Boolean getRemember() {
        return remember;
    }

    /**
     * @param remember the remember to set
     */
    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    /**
     * @return the root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the utx
     */
    public UserTransaction getUtx() {
        return utx;
    }

    /**
     * @param utx the utx to set
     */
    public void setUtx(UserTransaction utx) {
        this.utx = utx;
    }

    /**
     * @return the audit
     */
    public Audit getAudit() {
        return audit;
    }

    /**
     * @param audit the audit to set
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    /**
     * @return the auditList
     */
    public List<Audit> getAuditList() {
        auditList = em.createQuery("select a from Audit a").getResultList();
        return auditList;
    }

    /**
     * @param auditList the auditList to set
     */
    public void setAuditList(List<Audit> auditList) {
        this.auditList = auditList;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the statusList
     */
    public List<Status> getStatusList() {
        statusList = getEm().createQuery("select s from Status s").getResultList();
        return statusList;
    }

    /**
     * @param statusList the statusList to set
     */
    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * @return the usersList
     */
    public List<Users> getUsersList() {
        usersList = em.createQuery("select u from Users u").getResultList();
        return usersList;
    }

    /**
     * @param usersList the usersList to set
     */
    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the projectModel
     */
    public LineChartModel getProjectModel() {
        return projectModel;
    }

    /**
     * @param projectModel the projectModel to set
     */
    public void setProjectModel(LineChartModel projectModel) {
        this.projectModel = projectModel;
    }

    /**
     * @return the usergroup
     */
    public Usergroups getUsergroup() {
        return usergroup;
    }

    /**
     * @param usergroup the usergroup to set
     */
    public void setUsergroup(Usergroups usergroup) {
        this.usergroup = usergroup;
    }

    /**
     * @return the usergroupList
     */
    public List<Usergroups> getUsergroupList() {
        usergroupList = getEm().createQuery("select u from Usergroups u").getResultList();
        return usergroupList;
    }

    /**
     * @param usergroupList the usergroupList to set
     */
    public void setUsergroupList(List<Usergroups> usergroupList) {
        this.usergroupList = usergroupList;
    }

    /**
     * @return the payment
     */
    public Payments getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(Payments payment) {
        this.payment = payment;
    }

    /**
     * @return the paymentList
     */
    public List<Payments> getPaymentList() {
        paymentList = em.createQuery("select p from Payments p").getResultList();
        return paymentList;
    }

    /**
     * @param paymentList the paymentList to set
     */
    public void setPaymentList(List<Payments> paymentList) {
        this.paymentList = paymentList;
    }

}
