package org.sopt.artoo.model;


import lombok.*;

import java.util.Date;

@Data
public class PurchaseReq {

    //입력 받야아할 부분
    private boolean p_isPost;
    //주문 방법
    private String p_recipient;
    //수령인 - 택배시에만
    private String p_address;
    //수령 주소 - 택배시에만
    private String p_phone;
    //수령인 전화 번호 - 택배시에만
    private int p_payment;
    //상태

    //Service DTO
    private int p_state;
    //주문 상태
    private Date p_currentTime;
    //주문 시간
    private String p_comment;
    // 주문 후기
    private int a_idx;
    // 작품 고유 번호
    private int p_sellerIdx;
    // 판매 고유번호
    private int p_buyerIdx;
    // 구매 고유번호
    private int p_price;



//    public boolean checkPurchaseReq() {
//        String p_paymentS = String.valueOf(p_payment);
//        if(p_isPost == true){
//            return ( p_recipient != null && p_address != null && p_phone != null && (p_paymentS!=null && !p_paymentS.isEmpty()));
//        }
//        else {
//            return (p_paymentS!=null && !p_paymentS.isEmpty());
//        }
//    }

    public boolean checkPurchaseReq() {
        if(p_isPost){
            return ( p_recipient != null && p_address != null && p_phone != null );
        }
        else {
            return (true);
        }
    }
}
