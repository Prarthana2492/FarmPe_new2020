package com.SevenNine.essentialscode;


/**
 * Created by Rahul Hooda on 14/7/17.
 */
public enum AppEnvironment {

    SANDBOX {
        @Override
        public String merchant_Key() {
           // return "VqBU7SYz";
            return "9MW23LKL";
        }


        @Override
        public String merchant_ID() {
            return "6250075";
        }

        @Override
        public String furl() {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String surl() {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        public String salt() {
            return "u099G7u43W";
           // return "auJAYWqzMD";
        }

        @Override
        public boolean debug() {
            return true;
        }
    },
    PRODUCTION {
        @Override
        public String merchant_Key() {
            return "9MW23LKL";
        }  //O15vkB

        @Override
        public String merchant_ID() {
            return "6250075";
        }
        //4819816

        @Override
        public String furl() {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }


        @Override
        public String surl() {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }


        @Override
        public String salt() {
            return "u099G7u43W";
        }
        //LU1EhObh

        @Override
        public boolean debug() {
            return false;

        }
    };

    public abstract String merchant_Key();

    public abstract String merchant_ID();

    public abstract String furl();

    public abstract String surl();

    public abstract String salt();

    public abstract boolean debug();


}
