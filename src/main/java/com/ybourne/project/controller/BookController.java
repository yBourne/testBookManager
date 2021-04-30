package com.ybourne.project.controller;

import com.ybourne.project.model.User;
import com.ybourne.project.service.BookService;
import com.ybourne.project.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/index", method={RequestMethod.GET})
    public String bookList(Model model){
        User host = hostHolder.getUser();
        if (host != null){
            model.addAttribute("host", host);
        }
        loadAllBooksView(model);
        return "book/books";
    }

    public void loadAllBooksView(Model model){
        model.addAttribute("books", bookService.getAllBooks());
    }
}
