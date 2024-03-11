package com.horobets.controller;

import com.horobets.AdminController;
import com.horobets.Book;
import com.horobets.BookService;
import com.horobets.User;
import com.horobets.UserService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;
@ManagedBean
public class UserController {
    private double y_e;
    private int bookId;
    private UserService userService=new UserService();
    private BookService bookService=new BookService();
    private FacesContext context = FacesContext.getCurrentInstance();
    private HttpServletResponse response = (HttpServletResponse)
            context.getExternalContext().getResponse();
    private HttpServletRequest request = (HttpServletRequest)
            FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private String userName=request.getRemoteUser(); // Отримуємо імя поточного користувача
    private User user=userService.findUserByName(userName); //Отримуємо поточного за іменем
    private String filePath="d:/uploads/";
    public double getY_e() {
        return y_e;
    }
    public void setY_e(double y_e) {
        this.y_e = y_e;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void refill(){
        User user = userService.findUserByName(userName);
        user.setY_e(user.getY_e()+getY_e());
        userService.updateData(user,user.getUsername());
    }
    public double getBalance(){
        com.horobets.AdminController adminController = new AdminController();
        String userName = adminController.getCurrentUserName();
        return userService.findUserByName(userName).getY_e();
    }
    public void checkForDownloadWithValue(String to,String value) throws IOException {
        Book book = bookService.findBookById(getBookId()); //Отримуємо книгу по id
        if(book == null) { //Перевіряємо чи є книга за таким  id
            response.sendRedirect(request.getContextPath() +
                    "/user_pages/searchBookError.xhtml");
        } else {

            if (user.getY_e() - book.getPrice() < 0) {//Перевіряємо чи є кошти і користувача
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "You need to top up your balance.");
            } else {
                user.setY_e(user.getY_e() - book.getPrice());
                userService.updateData(user, user.getUsername());
                File file = new File("");
                if (!file.exists()) {
                    user.setY_e(user.getY_e() + book.getPrice());
                    userService.updateData(user, user.getUsername());
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
    }

    private void downloadWithValue(File file, String value) throws IOException {
        int readingData;
        final int myAppBuffSize = 209715200;//200 MB
        byte[] creatingBuffer;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.reset();
        response.setContentType("application/pdf");
        response.setContentLength((int) file.length());
        response.setHeader("Content-disposition", value+"; filename=" + file.getName());
        try{
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file),
                    myAppBuffSize);
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream(),
                    myAppBuffSize);
            creatingBuffer = new byte[myAppBuffSize];
            while ((readingData = bufferedInputStream.read(creatingBuffer)) > 0) {
                bufferedOutputStream.write(creatingBuffer, 0, readingData);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Objects.requireNonNull(bufferedInputStream).close();
            Objects.requireNonNull(bufferedOutputStream).close();
        }
        context.responseComplete();
    }
    private boolean searchConvertedFile(String filePath, File file){
        for (File searchConvertTXTFile : Objects.requireNonNull(new File(filePath).listFiles())){
            if (searchConvertTXTFile.equals(file)){
                return true;
            }
        }
        return false;
    }
}