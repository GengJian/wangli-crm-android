package com.jiuyi.model;

import java.util.List;

public class OperatorLoginBean {




    private OperatorBean operator;
    private String id_token;
    private String tim_signature;

    public OperatorBean getOperator() {
        return operator;
    }

    public void setOperator(OperatorBean operator) {
        this.operator = operator;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getTim_signature() {
        return tim_signature;
    }

    public void setTim_signature(String tim_signature) {
        this.tim_signature = tim_signature;
    }

    public static class OperatorBean {


        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String username;
        private boolean activated;
        private String name;
        private String telOne;
        private String telTwo;
        private Object telThree;
        private Object address;
        private Object email;
        private Object superiorOperator;
        private DepartmentBean department;
        private PositionBean position;
        private String lastLoginDate;
        private String previousLoginDate;
        private String timIdentifier;
        private Object dunningFailures;
        private Object shortWaybills;
        private List<RolesBean> roles;
        private List<?> members;

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isActivated() {
            return activated;
        }

        public void setActivated(boolean activated) {
            this.activated = activated;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelOne() {
            return telOne;
        }

        public void setTelOne(String telOne) {
            this.telOne = telOne;
        }

        public String getTelTwo() {
            return telTwo;
        }

        public void setTelTwo(String telTwo) {
            this.telTwo = telTwo;
        }

        public Object getTelThree() {
            return telThree;
        }

        public void setTelThree(Object telThree) {
            this.telThree = telThree;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getSuperiorOperator() {
            return superiorOperator;
        }

        public void setSuperiorOperator(Object superiorOperator) {
            this.superiorOperator = superiorOperator;
        }

        public DepartmentBean getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentBean department) {
            this.department = department;
        }

        public PositionBean getPosition() {
            return position;
        }

        public void setPosition(PositionBean position) {
            this.position = position;
        }

        public String getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(String lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public String getPreviousLoginDate() {
            return previousLoginDate;
        }

        public void setPreviousLoginDate(String previousLoginDate) {
            this.previousLoginDate = previousLoginDate;
        }

        public String getTimIdentifier() {
            return timIdentifier;
        }

        public void setTimIdentifier(String timIdentifier) {
            this.timIdentifier = timIdentifier;
        }

        public Object getDunningFailures() {
            return dunningFailures;
        }

        public void setDunningFailures(Object dunningFailures) {
            this.dunningFailures = dunningFailures;
        }

        public Object getShortWaybills() {
            return shortWaybills;
        }

        public void setShortWaybills(Object shortWaybills) {
            this.shortWaybills = shortWaybills;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public List<?> getMembers() {
            return members;
        }

        public void setMembers(List<?> members) {
            this.members = members;
        }

        public static class DepartmentBean {
            /**
             * createdBy : 13064793669
             * createdDate : 2018-05-17 20:04:05
             * lastModifiedBy : 13064793669
             * lastModifiedDate : 2018-05-17 20:41:41
             * id : 30
             * deleted : false
             * sort : 11
             * parent : {"createdBy":"admin","createdDate":"2018-04-06 18:13:38","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06 18:13:38","id":9,"deleted":false,"sort":10,"parent":null,"name":"东北大区","desp":null,"path":null,"totalCount":null,"marketTreands":null}
             * name : 辽宁办事处
             * desp : null
             * path : -9-
             * totalCount : null
             * marketTreands : null
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private ParentBean parent;
            private String name;
            private Object desp;
            private String path;
            private Object totalCount;
            private Object marketTreands;

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public ParentBean getParent() {
                return parent;
            }

            public void setParent(ParentBean parent) {
                this.parent = parent;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getDesp() {
                return desp;
            }

            public void setDesp(Object desp) {
                this.desp = desp;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public Object getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(Object totalCount) {
                this.totalCount = totalCount;
            }

            public Object getMarketTreands() {
                return marketTreands;
            }

            public void setMarketTreands(Object marketTreands) {
                this.marketTreands = marketTreands;
            }

            public static class ParentBean {
                /**
                 * createdBy : admin
                 * createdDate : 2018-04-06 18:13:38
                 * lastModifiedBy : admin
                 * lastModifiedDate : 2018-04-06 18:13:38
                 * id : 9
                 * deleted : false
                 * sort : 10
                 * parent : null
                 * name : 东北大区
                 * desp : null
                 * path : null
                 * totalCount : null
                 * marketTreands : null
                 */

                private String createdBy;
                private String createdDate;
                private String lastModifiedBy;
                private String lastModifiedDate;
                private long id;
                private boolean deleted;
                private int sort;
                private Object parent;
                private String name;
                private Object desp;
                private Object path;
                private Object totalCount;
                private Object marketTreands;

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public String getCreatedDate() {
                    return createdDate;
                }

                public void setCreatedDate(String createdDate) {
                    this.createdDate = createdDate;
                }

                public String getLastModifiedBy() {
                    return lastModifiedBy;
                }

                public void setLastModifiedBy(String lastModifiedBy) {
                    this.lastModifiedBy = lastModifiedBy;
                }

                public String getLastModifiedDate() {
                    return lastModifiedDate;
                }

                public void setLastModifiedDate(String lastModifiedDate) {
                    this.lastModifiedDate = lastModifiedDate;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public Object getParent() {
                    return parent;
                }

                public void setParent(Object parent) {
                    this.parent = parent;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getDesp() {
                    return desp;
                }

                public void setDesp(Object desp) {
                    this.desp = desp;
                }

                public Object getPath() {
                    return path;
                }

                public void setPath(Object path) {
                    this.path = path;
                }

                public Object getTotalCount() {
                    return totalCount;
                }

                public void setTotalCount(Object totalCount) {
                    this.totalCount = totalCount;
                }

                public Object getMarketTreands() {
                    return marketTreands;
                }

                public void setMarketTreands(Object marketTreands) {
                    this.marketTreands = marketTreands;
                }
            }
        }

        public static class PositionBean {
            /**
             * createdBy : 15058305380
             * createdDate : 2018-05-23 19:40:11
             * lastModifiedBy : 15058305380
             * lastModifiedDate : 2018-05-23 19:40:11
             * id : 2
             * deleted : false
             * sort : 10
             * name : MARKETING_DEPARTMENT_MINISTER
             * desp : 市场部部长
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private String name;
            private String desp;

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesp() {
                return desp;
            }

            public void setDesp(String desp) {
                this.desp = desp;
            }
        }

        public static class RolesBean {
            /**
             * createdBy : admin
             * createdDate : 2018-04-05 18:17:58
             * lastModifiedBy : admin
             * lastModifiedDate : 2018-04-12 17:45:42
             * id : 1
             * deleted : false
             * sort : 10
             * name : ROLE_ADMINISTRATOR
             * desp : 系统管理员
             * builtIn : true
             * operators : null
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private String name;
            private String desp;
            private boolean builtIn;
            private Object operators;

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesp() {
                return desp;
            }

            public void setDesp(String desp) {
                this.desp = desp;
            }

            public boolean isBuiltIn() {
                return builtIn;
            }

            public void setBuiltIn(boolean builtIn) {
                this.builtIn = builtIn;
            }

            public Object getOperators() {
                return operators;
            }

            public void setOperators(Object operators) {
                this.operators = operators;
            }
        }
    }
}
