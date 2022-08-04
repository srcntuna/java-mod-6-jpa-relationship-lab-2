package org.example;

import org.example.models.Game;
import org.example.models.Review;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        // create EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // access transaction object
        EntityTransaction transaction = entityManager.getTransaction();

        Game game1 = new Game();
        game1.setTitle("Max Payne");
        game1.setGenre("RPG");
        game1.setPlatform("PC");
        game1.setPrice(100);

        Review review1 = new Review();
        review1.setComment("THIS WAS THE BEST RPG BACK IN TIME!");
        review1.setScore(10);
        review1.setGame(game1);

        Review review2 = new Review();
        review2.setComment("I wish 3rd version was released");
        review2.setScore(8);
        review2.setGame(game1);

        game1.getReviewList().add(review1);
        game1.getReviewList().add(review2);

        transaction.begin();

        entityManager.persist(game1);

        entityManager.persist(review1);
        entityManager.persist(review2);



        transaction.commit();

        // close entity manager
        entityManager.close();
        entityManagerFactory.close();
    }
}