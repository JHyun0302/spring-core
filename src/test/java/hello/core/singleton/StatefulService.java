package hello.core.singleton;

public class StatefulService { //test 만들기: command + shift + T
//    private int price; //상태를 유지하는 필드

//    public void order(String name, int price)
    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제!
        return price;
        /**
         * 사용자A가 1만원 주문하고 사용자B가 2만원 주문했을 때
         * price가 싱글톤에 의해 공유 변수라서 2만원으로 바뀜
         * 사용자A가 주문금액 확인할 때 2만원으로 확인함!!
         */
    }

/*    public int getPrice(){
        return price;
    }*/
}
