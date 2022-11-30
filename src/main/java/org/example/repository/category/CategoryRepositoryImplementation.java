package org.example.repository.category;

import org.example.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImplementation implements CategoryRepository{

    private final SessionFactory factory;

    @Autowired
    public CategoryRepositoryImplementation(SessionFactory factory){
        this.factory = factory;
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
    public int removeCategory(int categoryID) {
        int results;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery(
                    "delete from Category c where c.id=:id",
                    Category.class
            );
            query.setParameter("id", categoryID);
            results = query.executeUpdate();
            tx.commit();
        }
        return results;
    }

}
