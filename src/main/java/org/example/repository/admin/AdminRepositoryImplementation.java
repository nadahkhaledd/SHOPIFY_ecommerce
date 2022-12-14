package org.example.repository.admin;
import org.example.entity.Admin;
import org.example.entity.User;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.model.Response;
import org.example.service.security.EncryptionService;
import org.example.utility.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AdminRepositoryImplementation implements AdminRepository{

    private final SessionFactory factory;
    private DateUtils dateUtils = new DateUtils();


    @Autowired
    public AdminRepositoryImplementation(SessionFactory factory){
        this.factory = factory;
    }


    /**
     * @inheritDoc
     */
    @Override
    public Response addAdmin(User admin) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(admin);
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.addAdmin  e.getMessage() = " + e.getMessage());
            return new Response("error occurred while processing your request", 500, true);
        }
        return new Response("Done", 200, false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void createSuperAdmin() {
        Date date = dateUtils.convertStringToDate("1989-10-13");

        Admin superAdmin = new Admin("super", "admin",
                "superadmin@shopify.com", "super@dm1n",
                Gender.male, date);
        addAdmin(superAdmin);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<List<Admin>> getAllAdmins() {
        List<User> admins;
        try (Session session = factory.openSession()) {

            session.beginTransaction();
            admins = session.createQuery("from User where email LIKE :key ", User.class)
                    .setString("key", "%"+"@shopify.com")
                    .list();
        } catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.getAllAdmins = " + e.getMessage());
            return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, admins);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> updateAdmin(User admin) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(admin);
            session.getTransaction().commit();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.updateAdmin  e.getMessage() = " + e.getMessage());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, false, true);

    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdminByEmail(String email) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Admin admin = session.get(Admin.class, email);
            session.delete(admin);
            session.getTransaction().commit();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.removeAdminID  e.getMessage() = " + e.getMessage());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, false, true);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdminByID(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            session.delete(admin);
            session.getTransaction().commit();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.removeAdminID  e.getMessage() = " + e.getMessage());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, false, true);
    }


    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> deactivateCustomer(String customerEmail) {
        int results;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "update User c set c.status=:status" +
                            " where c.email=:email"
            );
            query.setParameter("status", CustomerStatus.DEACTIVATED);
            query.setParameter("email", customerEmail);

            results = query.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.deactivateCustomer  e.getMessage() = " + e.getMessage());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, results==1);
    }
}
