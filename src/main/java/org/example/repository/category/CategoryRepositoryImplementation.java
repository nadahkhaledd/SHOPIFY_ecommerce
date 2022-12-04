package org.example.repository.category;

import org.example.entity.Category;
import org.example.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            category.setName(category.getName().toLowerCase());
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
                            " where c.id=:id"
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
                    "delete from Category c where c.id=:id"
            );
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
    public List<Category> getAllCategories() {
        List<Category> categories;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            categories=session.createQuery("from Category", Category.class).list();
            //     session.getTransaction().commit();
        }
        return categories;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Category getCategoryByName(String name) {
        Category category;
        try (Session session = factory.openSession()) {
            category  = session.createQuery("from Category c WHERE c.name=:name", Category.class)
                    .setParameter("name", name)
                    .getSingleResult();

        }
        return category;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getCategoriesNames() {
        List<String> categoriesNames;
        try (Session session = factory.openSession()) {
            categoriesNames  = session.createQuery("SELECT c.name from Category c", String.class).list();

        }
        return categoriesNames;
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Category> searchByCategoryName(String categoryName) {
        List<Category> categories;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            categories=session.createQuery("from Category where name like :searchkey ", Category.class).
                    setParameter("searchkey", "%"+categoryName+"%").list();
            //     session.getTransaction().commit();
        }
        categories.forEach(System.out::println);
        return categories;
    }

}
