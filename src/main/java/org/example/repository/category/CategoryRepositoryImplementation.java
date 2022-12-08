package org.example.repository.category;

import org.example.entity.Category;
import org.example.model.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImplementation implements CategoryRepository {

    private final SessionFactory factory;

    @Autowired
    public CategoryRepositoryImplementation(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response addCategory(Category category) {
        try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                category.setName(category.getName().toLowerCase());
                session.persist(category);
                tx.commit();
            } catch (Exception e) {
                System.out.println("in addCategory category repo impl e.getStackTrace() = " + e.getMessage());
                return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false);

    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> updateCategory(Category category) {
        int results;
        try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery(
                        "update Category c set c.name=:name, c.imagePath=:imagePath" +
                                " where c.id=:id"
                );
                query.setParameter("name", category.getName());
                query.setParameter("imagePath", category.getImagePath());
                query.setParameter("id", category.getId());
                results = query.executeUpdate();
                tx.commit();
            } catch (Exception e) {
                System.out.println("in update category category repo impl e.getStackTrace() = " + e.getMessage());
                return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, results == 1);

    }


    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeCategory(int categoryID) {
        int results;

        try (Session session = factory.openSession()) {

            Transaction tx = session.beginTransaction();
            Category category = session.get(Category.class, categoryID);
            session.delete(category);
            tx.commit();
            } catch (Exception e) {
                System.out.println("in remove category category repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, true);

    }


    /**
     * @inheritDoc
     */
    @Override
    public Response<List<Category>> getAllCategories() {
        List<Category> categories;
        try (Session session = factory.openSession()) {
                session.beginTransaction();
                categories = session.createQuery("from Category", Category.class).list();
            } catch (Exception e) {
                System.out.println("in getAll category repo impl e.getStackTrace() = " + e.toString());
                return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, categories);
    }


    /**
     * @inheritDoc
     */
    @Override
    public Response<Category> getCategoryByName(String name) {
        Category category;
        try (Session session = factory.openSession()) {

                category = session.createQuery("from Category c WHERE c.name=:name", Category.class)
                        .setParameter("name", name)
                        .getSingleResult();

            } catch (Exception e) {
                System.out.println("in get category by name category repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, category);
    }


    /**
     * @inheritDoc
     */
    @Override
    public Response<Category> getCategoryByID(int id) {
        Category category;
        try (Session session = factory.openSession()) {

                category = session.createQuery("from Category c WHERE c.id=:id", Category.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (Exception e) {
                System.out.println("in getCategoryByID category repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, category);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<List<String>> getCategoriesNames() {
        List<String> categoriesNames;
        try (Session session = factory.openSession()) {

                categoriesNames = session.createQuery("SELECT c.name from Category c", String.class).list();
            } catch (Exception e) {
                System.out.println("in get category names category repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }

        return new Response("Done", 200, false, categoriesNames);
    }


    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Category>> searchByCategoryName(String categoryName) {
        List<Category> categories;
        categoryName=categoryName.toLowerCase();
        try (Session session = factory.openSession()) {

            session.beginTransaction();
            categories = session.createQuery("from Category where name like :searchkey " +
                            "or name like :searchkey2" +
                            " or name like :searchkey3 or name= :categoryName ", Category.class).
                    setParameter("searchkey", "% " + categoryName + " %")
                    .setParameter("searchkey2",categoryName+" %")
                    .setParameter("searchkey3","% "+categoryName)
                    .setParameter("categoryName", categoryName)
                    .list();
        } catch (Exception e) {
            System.out.println("in searchByCategoryName category repo impl e.getStackTrace() = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);
        }

        return new Response("Done", 200, false, categories);
    }
    //
    //     session.getTransaction().commit();
}
