package com.ll.exam;


import com.ll.exam.article.ArticleController;
import com.ll.exam.member.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * DispatchServlet class 역할
 * 1. usr에 대한 내용은 다 모이게함
 * 2. Rq, ArticleController, MemberController class 사용한다.
 */
@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

//        resp.getWriter().append("is it working?");

        Rq rq = new Rq(req, resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();


        /**
         * getRequestURI는
         * Http://localhost:8081/usr/article/list/free?page=1 에서
         * /usr/article/list/free 부분만 가져온다.
         */
//        String url = req.getRequestURI();
        System.out.println("rq.getRouteMethod():" + rq.getRouteMethod());


        switch (rq.getRouteMethod()) {
            case "GET":
                switch(rq.getActionPath()) {
                    case "/usr/article/detail":
                        articleController.showDetail(rq);
                        break;
                    case "/usr/article/list":
                        articleController.showList(rq);
                        break;
                    case "/usr/article/write":
                        articleController.showWrite(rq);
                        break;

                    case "/usr/article/modify":
                        articleController.showModify(rq);
                        break;
                    case "/usr/member/login":
                        memberController.showLogin(rq);
                        break;
                }
                break;
            case "POST":
                switch(rq.getActionPath()) {
                    case "/usr/article/write":
                        articleController.doWrite(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.doModify(rq);
                        break;
                }
                break;
            case "DELETE":
                switch (rq.getActionPath()) {
                    case "/usr/article/delete":
                        articleController.deleteList(rq);
                        break;
                }
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }


}
