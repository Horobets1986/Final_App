package com.horobets.entity;

import com.horobets.Book;
import com.horobets.BookEntityMF;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
public class BookDAO {
    public List<Book> getAllBooks() {
        BookEntityMF bookEntityMF = new BookEntityMF();
        EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Book e");
        List<Book> book = query.getResultList();
        System.out.println("Данні записались в bookList.");
        entityManager.close();
        bookEntityMF.close();
        return book;
    }
    public void findData(int id) {
        try {
            BookEntityMF bookEntityMF = new BookEntityMF();
            EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
            Book book = entityManager.find(Book.class, id);
            System.out.println(book.toString());
            entityManager.close();
            bookEntityMF.close();
            System.out.println("Виконано.");
        }catch (NullPointerException e){
            throw new RuntimeException("Такого id не існує.");
        }
    }
    public void addData(Book book) {
        try {
            BookEntityMF bookEntityMF =new BookEntityMF();
            EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            entityManager.close();
            bookEntityMF.close();
            System.out.println("Объект збережено.");
        }catch (PersistenceException e){
            throw new RuntimeException("Перевірте корректність вхідних данних обєкта.");
        }
    }
    public void updateData(Book book, int id) {
        try {
            BookEntityMF bookEntityMF = new BookEntityMF();
            EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
            Book findBook = entityManager.find(Book.class, id);
            System.out.println(findBook.toString());
            entityManager.getTransaction().begin();
            findBook.setName(book.getName());
            findBook.setAuthor(book.getAuthor());
            findBook.setYear(book.getYear());
            findBook.setLanguage(book.getLanguage());
            findBook.setGenre(book.getGenre());
            findBook.setPrice(book.getPrice());
            findBook.setDescription(book.getDescription());
            findBook.setLink(book.getLink());
            findBook.setEncryptedImage(book.getEncryptedImage());
            entityManager.getTransaction().commit();
            findBook = entityManager.find(Book.class, id);
            entityManager.close();
            bookEntityMF.close();
            System.out.println(findBook.toString());
            System.out.println("Обєкт оногвлено.");
        } catch (NullPointerException e){
            throw new RuntimeException("Такого id не існує.");
        }
    }
    public void deleteData(int id) {
        try {
            BookEntityMF bookEntityMF = new BookEntityMF();
            EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
            Book findBook = entityManager.find(Book.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(findBook);
            entityManager.getTransaction().commit();
            entityManager.close();
            bookEntityMF.close();
            System.out.println("Объект видалено.");
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Такого id не існує.");
        }
    }
    public String findDataById(int id) {
        String bookName;
        try {
            BookEntityMF bookEntityMF = new BookEntityMF();
            EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
            Book book = entityManager.find(Book.class, id);
            bookName=book.getName();
            entityManager.close();
            bookEntityMF.close();
            System.out.println("Виконано.");
        }catch (NullPointerException e){
            throw new RuntimeException("Такого id не існує.");
        }
        return bookName;
    }
    public Book findBookById(int id) {
        Book findBook;
        try {
            BookEntityMF bookEntityMF = new BookEntityMF();
            EntityManager entityManager = (EntityManager) bookEntityMF.getEntityManager();
            findBook = entityManager.find(Book.class, id);
            entityManager.close();
            bookEntityMF.close();
            System.out.println("Виконано.");
        }catch (NullPointerException e){
            throw new RuntimeException("Такої книги не існує.");
        }
        return findBook;
    }
}
