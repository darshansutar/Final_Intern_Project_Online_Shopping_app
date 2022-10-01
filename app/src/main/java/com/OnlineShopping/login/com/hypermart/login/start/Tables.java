package com.OnlineShopping.login.com.hypermart.login.start;


import android.provider.BaseColumns;

public final class Tables {

    private Tables(){}

    public static class Delivery implements BaseColumns{

        public static final String TABLE_NAME_DELIVERY = "delivery";
        public static final String COL_1_D = "orderId";
        public static final String COL_2_D = "name";
        public static final String COL_3_D = "deliveryAddress";
        public static final String COL_4_D = "postalCode";
        public static final String COL_5_D = "contactNo";
        public static final String COL_6_D = "userName";
        public static final String COL_7_D = "dateTime";



    }

    public static  class Bank implements  BaseColumns{


        public static final String TABLE_NAME_BANK = "bank";
        public static final String COL_1_B = "cardNumber";
        public static final String COL_2_B = "holderName";
        public static final String COL_3_B = "cardType";
        public static final String COL_4_B = "expiryDate";
        public static final String COL_5_B = "cvv";


    }

    public static class Payment implements BaseColumns{


        public static final String TABLE_NAME_PAYMENT = "payment";
        public static final String COL_1_P = "paymentId";
        public static final String COL_2_P = "userName";
        public static final String COL_3_P = "amount";


    }


}
