//定义处理器
app.controller("taskController", function ($scope, $http, $controller, taskService) {

    //继承controller，可以使用baseController中的所有方法
    $controller("baseController", {$scope:$scope});

    //查询所有品牌列表
    $scope.findAll = function () {
        taskService.findAll().success(function (response) {
            $scope.list = response;
        }).error(function () {
            alert("查询列表失败");
        });

    };

    //分页查询
    $scope.findPage = function (page, rows) {
        taskService.findPage(page, rows).success(function (response) {//response是分页对象PageResult
            //设置列表
            $scope.list = response.rows;
            //更新分页导航条
            $scope.paginationConf.totalItems = response.total;//总记录数
        });

    };

    //保存
    $scope.save = function () {
        var obj;
        if($scope.entity.id != null){
            //更新
            obj = taskService.update($scope.entity);
        } else {
            obj = taskService.add($scope.entity);
        }
        //$scope.entity 弹出层里面双向绑定的那些表单项
        obj.success(function (response) {
            if(response.success){
                //刷新列表
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        });

    };

    //根据id查询
    $scope.findOne = function (id) {
        taskService.findOne(id).success(function (response) {
            $scope.entity = response;
        });
    };

    //批量删除
    $scope.delete = function () {
        if($scope.selectedIds.length == 0){
            alert("请选择要删除的记录");
            return ;
        }
        //confirm如果点击 确认 则返回true,否则false
        if(confirm("确认要删除选中的记录吗？")){
            taskService.delete($scope.selectedIds).success(function (response) {
                if(response.success){
                    //刷新列表
                    $scope.reloadList();
                    $scope.selectedIds = [];
                } else {
                    alert(response.message);
                }
            });
        }

    };

    //分页条件查询
    // $scope.searchEntity = {};
    $scope.search = function () {
        taskService.search().success(function (response) {
            //设置列表
            $scope.list = response;
        });
    };

    //启动定时任务
    $scope.startTask = function(taskId){
        taskService.startTask(taskId).success(function (response) {
            alert(response.message);
            //刷新列表
            $scope.reloadList();
        });
    }

});