//定义service
app.service("taskService", function ($http) {
    //查询所有品牌列表；this表示brandService
    this.findAll = function () {
        return $http.get("../brand/findAll.do");
    };

    //分页查询
    this.findPage = function (page, rows) {
        return $http.get("../brand/findPage.do?page=" + page + "&rows=" + rows);

    };

    //保存
    this.add = function (entity) {
        return $http.post("http://localhost:8762/job/insert", entity);
    };

    //更新
    this.update = function (entity) {
        return $http.post("http://localhost:8762/job/update", entity);
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get("../brand/findOne.do?id=" + id);
    };

    //批量删除
    this.delete = function (selectedIds) {
        return $http.get("http://localhost:8762/job/delete?ids=" + selectedIds);

    };

    //分页条件查询
    this.search = function () {
        return $http.get("http://localhost:8762/job/list");
    };

    //获取符合select2组件的品牌列表数据
    this.selectOptionList = function () {
        return $http.get("http://localhost:8762/job/list");
    };

    //启动定时任务
    this.startTask = function(taskId){
        return $http.get("http://localhost:8762/job/start?taskId=" + taskId);
    }
});