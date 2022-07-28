package com.ll.exam.member;

import com.ll.exam.Rq;

/**
 * MemberController class 역할
 * 1. 로그인 한 것을 보여준다.
 */
public class MemberController {


    public void showLogin(Rq rq) {
        rq.println("로그인");
    }

}
