package com.horobets;

import com.horobets.entity.BookDAO;

import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();
    public List<Book> getAllData() {
        return bookDAO.getAllBooks();
    }
    public void findData(int id) {
        bookDAO.findData(id);
    }
    public void addData(Book book) {
        bookDAO.addData(book);
    }
    public void updateData(Book book, int id) {
        bookDAO.updateData(book,id);
    }
    public void deleteData(int id,String name) {
        String pathname="d:/uploads/";
        bookDAO.deleteData(id);
    }
    public String findDataById(int id) {
        return bookDAO.findDataById(id);
    }
    public Book findBookById(int id) {
        return bookDAO.findBookById(id);
    }
    public boolean bookIsExist(String bookName){
        for (Book book:getAllData()){
            if (bookName.equals(book.getName())){
                return true;
            }
        }
        return false;
    }
}