package com.daqi.controller;

import com.daqi.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @GetMapping("/book")
    public String input(Book book) {
        book.setPrice(200f);
        return "picture_page";
    }

    @PostMapping("/book")
    public String imageshangchuan(MultipartFile ufile, Model model, HttpServletRequest request, @Valid Book book, BindingResult bookResult) {
        if (bookResult.hasErrors()) {
            return "picture_page";
        }

        List<String> errors = new ArrayList<>();
        if (ufile.isEmpty()) {
            errors.add("文件为空错误");
        }
        if (ufile.getSize() > 1024 * 1024 * 5) {
            errors.add("文件超出大小，请重新选择~");
        }
        if (!ufile.getContentType().contains("image/")) {
            errors.add("只允许上传图片文件！");
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errs", errors);
            return "picture_page";
        }

        String basePath = request.getServletContext().getRealPath("/img");

        String relativePath;//图片保存的路劲
        try {
            relativePath = makeRelativePath(ufile.getOriginalFilename());
            File target = new File(basePath + relativePath);
            target.getParentFile().mkdir();
            ufile.transferTo(target);
        } catch (IOException e) {
            model.addAttribute("err", "文件上传失败,请重试~");
            return "picture_page";
        }

        book.setCover(relativePath);
        try {
            System.out.println("对" + book + "进行保存等操作~");
        } catch (Exception e) {
//            实际业务中，需要考虑异常的处理
            model.addAttribute("err", "something");
            return "picture_page";
        }
        return "book_home";
    }


    public String makeRelativePath(String fileName) {
        Date now = new Date();
        String[] fileNames = splistFileString(fileName);
        return String.format("%s%s%supload_%s_%s.%s",
                File.separator,
                new SimpleDateFormat("yyMMdd").format(now),
                File.separator,
                fileNames[0],
                new SimpleDateFormat("hhmmss").format(now),
                fileNames[1]
        );
    }

    public String[] splistFileString(String fileName) {
        int dotPos = fileName.lastIndexOf(".");
        String ext = fileName.substring(dotPos + 1);
        String name = fileName.substring(0, dotPos);
        return new String[]{name, ext};
    }
}
