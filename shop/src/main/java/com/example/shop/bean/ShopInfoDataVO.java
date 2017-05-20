package com.example.shop.bean;

/**
 * Created by lizhiqiang on 2017/5/16.
 */

public class ShopInfoDataVO {
    private String id;
    private String sn;
    private String name;
    private String phone;
    private String area_id;
    private String address;
    private String logo;
    private String start_time;
    private String end_time;
    private String instruction;
    private String deliver_limit;
    private String deliver_fee;
    private String deliver_free;
    private String active;
    private Area area;
    private Config config;


    public class Area{
        private String id;
        private String parentid;
        private String shortname;
        private Parent parent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public Parent getParent() {
            return parent;
        }

        public void setParent(Parent parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "id='" + id + '\'' +
                    ", parentid='" + parentid + '\'' +
                    ", shortname='" + shortname + '\'' +
                    ", parent=" + parent +
                    '}';
        }
    }

    public class Parent{
        private String id;
        private String parentid;
        private String shortname;
        private Parent parent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public Parent getParent() {
            return parent;
        }

        public void setParent(Parent parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Parent{" +
                    "id='" + id + '\'' +
                    ", parentid='" + parentid + '\'' +
                    ", shortname='" + shortname + '\'' +
                    ", parent=" + parent +
                    '}';
        }
    }

    public class Config{
        private String notification;
        private String marquee;
        private String jiugg;

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }

        public String getMarquee() {
            return marquee;
        }

        public void setMarquee(String marquee) {
            this.marquee = marquee;
        }

        public String getJiugg() {
            return jiugg;
        }

        public void setJiugg(String jiugg) {
            this.jiugg = jiugg;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "notification='" + notification + '\'' +
                    ", marquee='" + marquee + '\'' +
                    ", jiugg='" + jiugg + '\'' +
                    '}';
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDeliver_limit() {
        return deliver_limit;
    }

    public void setDeliver_limit(String deliver_limit) {
        this.deliver_limit = deliver_limit;
    }

    public String getDeliver_fee() {
        return deliver_fee;
    }

    public void setDeliver_fee(String deliver_fee) {
        this.deliver_fee = deliver_fee;
    }

    public String getDeliver_free() {
        return deliver_free;
    }

    public void setDeliver_free(String deliver_free) {
        this.deliver_free = deliver_free;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "ShopInfoDataVO{" +
                "id='" + id + '\'' +
                ", sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", area_id='" + area_id + '\'' +
                ", address='" + address + '\'' +
                ", logo='" + logo + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", instruction='" + instruction + '\'' +
                ", deliver_limit='" + deliver_limit + '\'' +
                ", deliver_fee='" + deliver_fee + '\'' +
                ", deliver_free='" + deliver_free + '\'' +
                ", active='" + active + '\'' +
                ", area=" + area +
                ", config=" + config +
                '}';
    }
}
