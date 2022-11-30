package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;
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


    @Override
    public void addAdmin(Admin admin) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(admin);
            tx.commit();
        }
    }

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

    @Override
    public void suspendCustomer(int customerID) {

    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void updateCategory(int categoryID, String imgPath) {

    }

    @Override
    public void removeCategory(int categoryID) {

    }
}
