package customer.entity;

import java.util.List;

public class DeptNewBean {

    /**
     * content : [{"id":29,"name":"重庆一办财务室","desp":null,"sort":11,"parent":{"id":26,"name":"重庆一办","desp":null,"sort":11,"parent":null,"path":"-24-8-","totalCount":4,"subDepartmentCount":1,"sapVkbur":null},"path":"-26-24-8-","totalCount":1,"subDepartmentCount":0,"sapVkbur":null},{"id":30,"name":"辽宁办事处","desp":null,"sort":11,"parent":{"id":9,"name":"东北大区","desp":null,"sort":10,"parent":null,"path":null,"totalCount":5,"subDepartmentCount":1,"sapVkbur":null},"path":"-9-","totalCount":3,"subDepartmentCount":0,"sapVkbur":null},{"id":16,"name":"实习生","desp":null,"sort":10,"parent":{"id":10,"name":"综合办","desp":"内勤、财务等","sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":5,"sapVkbur":null},"path":"-10-","totalCount":1,"subDepartmentCount":0,"sapVkbur":null},{"id":22,"name":"汶川办事处","desp":null,"sort":2,"parent":{"id":8,"name":"西南大区","desp":"重庆、成都等","sort":10,"parent":null,"path":null,"totalCount":13,"subDepartmentCount":4,"sapVkbur":null},"path":"-8-","totalCount":3,"subDepartmentCount":0,"sapVkbur":null},{"id":23,"name":"绵阳办事处","desp":null,"sort":3,"parent":{"id":8,"name":"西南大区","desp":"重庆、成都等","sort":10,"parent":null,"path":null,"totalCount":13,"subDepartmentCount":4,"sapVkbur":null},"path":"-8-","totalCount":1,"subDepartmentCount":0,"sapVkbur":null},{"id":24,"name":"重庆办事处","desp":null,"sort":1,"parent":{"id":8,"name":"西南大区","desp":"重庆、成都等","sort":10,"parent":null,"path":null,"totalCount":13,"subDepartmentCount":4,"sapVkbur":null},"path":"-8-","totalCount":6,"subDepartmentCount":2,"sapVkbur":null},{"id":25,"name":"成都办事处","desp":null,"sort":11,"parent":{"id":8,"name":"西南大区","desp":"重庆、成都等","sort":10,"parent":null,"path":null,"totalCount":13,"subDepartmentCount":4,"sapVkbur":null},"path":"-8-","totalCount":1,"subDepartmentCount":0,"sapVkbur":null},{"id":26,"name":"重庆一办","desp":null,"sort":11,"parent":{"id":24,"name":"重庆办事处","desp":null,"sort":1,"parent":null,"path":"-8-","totalCount":6,"subDepartmentCount":2,"sapVkbur":null},"path":"-24-8-","totalCount":4,"subDepartmentCount":1,"sapVkbur":null},{"id":27,"name":"重庆二办","desp":null,"sort":11,"parent":{"id":24,"name":"重庆办事处","desp":null,"sort":1,"parent":null,"path":"-8-","totalCount":6,"subDepartmentCount":2,"sapVkbur":null},"path":"-24-8-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":28,"name":"浙江大区","desp":null,"sort":11,"parent":null,"path":null,"totalCount":2,"subDepartmentCount":2,"sapVkbur":null},{"id":7,"name":"北京办","desp":null,"sort":10,"parent":null,"path":null,"totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":8,"name":"西南大区","desp":"重庆、成都等","sort":10,"parent":null,"path":null,"totalCount":13,"subDepartmentCount":4,"sapVkbur":null},{"id":9,"name":"东北大区","desp":null,"sort":10,"parent":null,"path":null,"totalCount":5,"subDepartmentCount":1,"sapVkbur":null},{"id":10,"name":"综合办","desp":"内勤、财务等","sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":5,"sapVkbur":null},{"id":11,"name":"内勤组","desp":"销售内勤","sort":10,"parent":{"id":10,"name":"综合办","desp":"内勤、财务等","sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":5,"sapVkbur":null},"path":"-10-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":12,"name":"财务组","desp":null,"sort":10,"parent":{"id":10,"name":"综合办","desp":"内勤、财务等","sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":5,"sapVkbur":null},"path":"-10-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":13,"name":"人事","desp":null,"sort":10,"parent":{"id":10,"name":"综合办","desp":"内勤、财务等","sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":5,"sapVkbur":null},"path":"-10-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":14,"name":"前台","desp":null,"sort":10,"parent":{"id":10,"name":"综合办","desp":"内勤、财务等","sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":5,"sapVkbur":null},"path":"-10-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":2,"name":"总裁办","desp":null,"sort":10,"parent":null,"path":null,"totalCount":1,"subDepartmentCount":0,"sapVkbur":null},{"id":3,"name":"温州办","desp":null,"sort":10,"parent":{"id":28,"name":"浙江大区","desp":null,"sort":11,"parent":null,"path":null,"totalCount":2,"subDepartmentCount":2,"sapVkbur":null},"path":"-28-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":4,"name":"漳州办","desp":null,"sort":10,"parent":null,"path":null,"totalCount":0,"subDepartmentCount":0,"sapVkbur":null},{"id":5,"name":"杭州办","desp":null,"sort":10,"parent":{"id":28,"name":"浙江大区","desp":null,"sort":11,"parent":null,"path":null,"totalCount":2,"subDepartmentCount":2,"sapVkbur":null},"path":"-28-","totalCount":2,"subDepartmentCount":0,"sapVkbur":null},{"id":6,"name":"上海办","desp":null,"sort":10,"parent":null,"path":null,"totalCount":0,"subDepartmentCount":0,"sapVkbur":null}]
     * last : true
     * totalElements : 23
     * totalPages : 1
     * number : 0
     * size : 0
     * sort : null
     * first : true
     * numberOfElements : 23
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private Object sort;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 29
         * name : 重庆一办财务室
         * desp : null
         * sort : 11
         * parent : {"id":26,"name":"重庆一办","desp":null,"sort":11,"parent":null,"path":"-24-8-","totalCount":4,"subDepartmentCount":1,"sapVkbur":null}
         * path : -26-24-8-
         * totalCount : 1
         * subDepartmentCount : 0
         * sapVkbur : null
         */

        private long id;
        private String name;
        private Object desp;
        private int sort;
        private ParentBean parent;
        private String path;
        private int totalCount;
        private int subDepartmentCount;
        private Object sapVkbur;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getSubDepartmentCount() {
            return subDepartmentCount;
        }

        public void setSubDepartmentCount(int subDepartmentCount) {
            this.subDepartmentCount = subDepartmentCount;
        }

        public Object getSapVkbur() {
            return sapVkbur;
        }

        public void setSapVkbur(Object sapVkbur) {
            this.sapVkbur = sapVkbur;
        }

        public static class ParentBean {
            /**
             * id : 26
             * name : 重庆一办
             * desp : null
             * sort : 11
             * parent : null
             * path : -24-8-
             * totalCount : 4
             * subDepartmentCount : 1
             * sapVkbur : null
             */

            private long id;
            private String name;
            private Object desp;
            private int sort;
            private Object parent;
            private String path;
            private int totalCount;
            private int subDepartmentCount;
            private Object sapVkbur;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
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

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getSubDepartmentCount() {
                return subDepartmentCount;
            }

            public void setSubDepartmentCount(int subDepartmentCount) {
                this.subDepartmentCount = subDepartmentCount;
            }

            public Object getSapVkbur() {
                return sapVkbur;
            }

            public void setSapVkbur(Object sapVkbur) {
                this.sapVkbur = sapVkbur;
            }
        }
    }
}
