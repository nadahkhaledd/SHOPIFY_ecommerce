package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.model.Response;
import org.example.repository.category.CategoryRepository;
import org.example.utility.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
            System.out.println("in AdminRepositoryImplementation.addAdmin  e.getStackTrace() = " + Arrays.toString(e.getStackTrace()));
            return new Response("error occurred while processing your request", 500, true);
        }
        System.out.println("magetsh fel error");
        return new Response("Done", 200, false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void createSuperAdmin() {
        Date date = dateUtils.convertStringToDate("1989-10-13");

        Admin superAdmin = new Admin("super", "admin",
                "superadmin@shop.com", "super@dm1n",
                Gender.male, date);
        addAdmin(superAdmin);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<List<Admin>> getAllAdmins() {
        List<Admin> admins;
        try (Session session = factory.openSession()) {

            session.beginTransaction();
            admins = session.createQuery("from User where email LIKE :key ", Admin.class)
                    .setString("key", "% "+"@shopify.com")
                    .list();
        } catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.getAllAdmins e.getStackTrace() = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, admins);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> updateAdmin(Admin admin) {
        int results;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "update User a set a.firstname=:firstname, a.lastname=:lastname," +
                            " a.email=:email, a.password=:password, a.gender=:gender, a.dateOfBirth=:dateOfBirth," +
                            " where a.id=:id and a.user_type:=user_type"
            );
            query.setParameter("firstname", admin.getFirstName());
            query.setParameter("lastname", admin.getLastName());
            query.setParameter("email", admin.getEmail());
            query.setParameter("password", admin.getPassword());
            query.setParameter("gender", admin.getGender());
            query.setParameter("dateOfBirth", admin.getDateOfBirth());
            query.setParameter("user_type", 0);
            query.setParameter("id", admin.getId());
            results = query.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.updateAdmin  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, results==1);

    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdmin(int adminID, String adminEmail) {
        int results;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "delete from User a where a.id=:id and a.email=:email"
            );
            query.setParameter("id", adminID);
            query.setParameter("email", adminEmail);
            results = query.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.removeAdmin  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, results==1);
    }


    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> deactivateCustomer(int customerID, String customerEmail) {
        int results;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "update User c set c.status=:status" +
                            " where c.id=:id and c.email:=email"
            );
            query.setParameter("status", CustomerStatus.DEACTIVATED);
            query.setParameter("id", customerID);
            query.setParameter("email", customerEmail);

            results = query.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in AdminRepositoryImplementation.deactivateCustomer  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200, false, results==1);
    }
}
