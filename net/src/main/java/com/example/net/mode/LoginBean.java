package com.example.net.mode;

public class LoginBean {


    /**
     * code : 200
     * message : 登录成功
     * result : {"id":"123","name":"123","password":"123","email":"1234","phone":"17627923461","point":"11.0","address":"?????????","money":"1.0","avatar":"/123/1577188145237.jpg","token":"cfe6bce0-2a0e-4ac5-b626-08c74d05d4d4AND1597947892868"}
     */

    private String code;
    private String message;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 123
         * name : 123
         * password : 123
         * email : 1234
         * phone : 17627923461
         * point : 11.0
         * address : ?????????
         * money : 1.0
         * avatar : /123/1577188145237.jpg
         * token : cfe6bce0-2a0e-4ac5-b626-08c74d05d4d4AND1597947892868
         */

        private String id;
        private String name;
        private String password;
        private String email;
        private String phone;
        private String point;
        private String address;
        private String money;
        private String avatar;
        private String token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
