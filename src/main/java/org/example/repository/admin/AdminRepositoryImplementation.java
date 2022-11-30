package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.Customer;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.utility.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
    public void addAdmin(Admin admin) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(admin);
            tx.commit();
        }
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

    @Override
    public void updateAdmin(Admin admin) {

    }

    /**
     * @inheritDoc
     */
    @Override
    public int removeAdmin(int adminID) {
        int results;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "delete from Admin a where a.id=:id",
                    Admin.class
            );
            query.setParameter("id", adminID);
            results = query.executeUpdate();
            tx.commit();
        }
        return results;
    }


    /**
     * @inheritDoc
     */
    @Override
    public int deactivateCustomer(int customerID) {
        int results;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "update Customer c set c.status=:status" +
                            " where c.id=:id",
                    Customer.class
            );
            query.setParameter("status", CustomerStatus.DEACTIVATED);
            query.setParameter("id", customerID);
            results = query.executeUpdate();
            tx.commit();
        }
        return results;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addCategory(Category category) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(category);
            tx.commit();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public int updateCategory(int categoryID, String imgPath) {
        int results;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "update Category c set c.imagePath=:imagePath" +
                            " where c.id=:id",
                    Category.class
            );
            query.setParameter("imagePath", imgPath);
            query.setParameter("id", categoryID);
            results = query.executeUpdate();
            tx.commit();
        }
        return results;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeCategory(int categoryID) {

    }
}
