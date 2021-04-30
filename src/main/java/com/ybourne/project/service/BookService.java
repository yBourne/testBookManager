package com.ybourne.project.service;

import com.ybourne.project.dao.BookDao;
import com.ybourne.project.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        try{
            return bookDao.selectAll();
        }catch(Exception e){
//            logger.error(e);
            System.out.println("Exception throw:" + e.getMessage());
            return null;
        }
    }

    public Book selectBook(int id){
        return bookDao.selectBookById(id);
    }

    public Book selectBook(String name){
        return bookDao.selectBookByName(name);
    }

    public int addBooks(Book book){
        return bookDao.addBook(book);
    }

    public void deleteBooks(int id){}

    public void recoverBooks(int id){}
}
