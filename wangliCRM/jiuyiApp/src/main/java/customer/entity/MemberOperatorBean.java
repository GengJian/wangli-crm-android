package customer.entity;

/**
 * ****************************************************************
 * 文件名称:MemberOperatorBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/7 15:44
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/7 1.00 初始版本
 * ****************************************************************
 */
public class MemberOperatorBean {




    private long id;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    private String orgName;
    private ArOperatorBean arOperator;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArOperatorBean getArOperator() {
        return arOperator;
    }

    public void setArOperator(ArOperatorBean arOperator) {
        this.arOperator = arOperator;
    }

    public ArOperatorBean getFrOperator() {
        return frOperator;
    }

    public void setFrOperator(ArOperatorBean frOperator) {
        this.frOperator = frOperator;
    }

    public ArOperatorBean getSrOperator() {
        return srOperator;
    }

    public void setSrOperator(ArOperatorBean srOperator) {
        this.srOperator = srOperator;
    }

    private ArOperatorBean frOperator;
    private ArOperatorBean srOperator;



    public static class ArOperatorBean {
        /**
         * departmentName : null
         * lastModifiedDate : 2019-03-06 13:19:49
         * avatarUrl : null
         * lastModifiedBy : 徐超
         * sort : 10
         * title : 销售经理（Z）
         * createdDate : 2019-01-10 12:05:26
         * createdBy : 费婷
         * name : 徐超
         * id : 139
         * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-26 19:23:23","createdBy":"13901565517","lastModifiedBy":"管理员","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200}
         * oaId : 651
         * oaCode : 1004613
         * username : 1004613
         */

        private Object departmentName;
        private String lastModifiedDate;
        private Object avatarUrl;
        private String lastModifiedBy;
        private int sort;
        private String title;
        private String createdDate;
        private String createdBy;
        private String name;
        private int id;
        private DepartmentBean department;
        private String oaId;
        private String oaCode;
        private String username;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        private String role;


        public String getShowInfo() {
            return showInfo;
        }

        public void setShowInfo(String showInfo) {
            this.showInfo = showInfo;
        }

        private String showInfo ="";

        public Object getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(Object departmentName) {
            this.departmentName = departmentName;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public Object getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(Object avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public DepartmentBean getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentBean department) {
            this.department = department;
        }

        public String getOaId() {
            return oaId;
        }

        public void setOaId(String oaId) {
            this.oaId = oaId;
        }

        public String getOaCode() {
            return oaCode;
        }

        public void setOaCode(String oaCode) {
            this.oaCode = oaCode;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public static class DepartmentBean {
            /**
             * desp : null
             * createdDate : 2018-12-12 00:00:00
             * lastModifiedDate : 2019-01-26 19:23:23
             * createdBy : 13901565517
             * lastModifiedBy : 管理员
             * name : 国内销售部（Z）
             * oaDepartmentId : 411
             * id : 75
             * sort : 200
             */

            private Object desp;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String oaDepartmentId;
            private long id;
            private int sort;

            public Object getDesp() {
                return desp;
            }

            public void setDesp(Object desp) {
                this.desp = desp;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOaDepartmentId() {
                return oaDepartmentId;
            }

            public void setOaDepartmentId(String oaDepartmentId) {
                this.oaDepartmentId = oaDepartmentId;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }


}
