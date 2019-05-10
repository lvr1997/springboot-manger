<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">


</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>用户管理</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
                            <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                        	<#--<@shiro.hasPermission name="system:user:add">

                        	</@shiro.hasPermission>-->
                        </p>
                        <hr>
                        <div class="row row-lg">
		                    <div class="col-sm-12">
								<div class="table-responsive">
									<table class="table table-hover text-nowrap" data-toggle="table">
										<thead>
											<tr>
												<th>ID</th>
												<th>用户名</th>
												<th>所属角色</th>
												<th>账号</th>
												<th>电话</th>
												<th>邮箱</th>
												<th>创建时间</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<#list user.list as userinfo>
                                            <tr>
                                                <td>${(userinfo.userId)!}</td>
                                                <td>${(userinfo.userName)!}</td>
                                                <td>${(userinfo.roles.roleName)!}</td>
                                                <td>${(userinfo.userAccount)!}</td>
                                                <td>${(userinfo.userPhone)!}</td>
                                                <td>${(userinfo.userEmail)!}</td>
                                                <td>${(userinfo.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                            <td>
												<#if userinfo.userStatus = '1'>
												 <span class="label label-info">可用 </span>
												 <#else>
												 <span class="label label-danger">不可用</span>
												</#if>
											</td>
											<td>
												<#if userinfo.userStatus = '1' >
                                                    <button class="btn btn-primary btn-xs" type="button" onclick="edit(${(userinfo.userId)!})"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;
												    <button class="btn btn-danger btn-xs" type="button" onclick="del(${(userinfo.userId)!})"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;
												</#if>
											</td>
										</tr>
											</#list>
										</tbody>
									</table>
                                </div>

		                        <!-- End Example Card View -->

                                当前是第 ${(user.pageNum)!}/${(user.pages)!}页，共${(user.total)!}条记录
                                <nav>
                                    <ul class="pager">
										<#if user.hasPreviousPage>
										  <li><a href="/user/index?pageNumber=1">第一页</a></li>
										  <li><a aria-label="Previous" href="/user/index?pageNumber=${(user.pageNum-1)!}"> <span aria-hidden="true">上一页</span> </a> </li>
										<#else>
                                          <li class="disabled"><a aria-label="Previous"> <span aria-hidden="true">上一页</span> </a> </li>
										  <li class="disabled"><a aria-label="First"> <span aria-hidden="true">第一页</span> </a> </li>
										</#if>
										<#if user.hasNextPage>
											<li><a href="/user/index?pageNumber=${(user.pageNum+1)!}" aria-label="Next"> <span aria-hidden="true">下一页</span> </a> </li>
											<li><a href="/user/index?pageNumber=${(user.lastPage)}">最末页</a></li>
										<#else>
											<li class="disabled"><a aria-label="Next"> <span aria-hidden="true">下一页</span> </a> </li>
											<li class="disabled"><a aria-label="Last"> <span aria-hidden="true">最末页</span> </a> </li>
										</#if>
									</ul>
                                </nav>
		                    </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>
        function edit(id){
        	layer.open({
        	      type: 2,
        	      title: '用户修改',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '600px'],
        	      content: '${ctx!}/user/edit/' + id,
        	      end: function(index){
                      $.ajax({
                          type:"get",
                          dataType:"json",
                          data:{pageNumber:1},
                          url:'${ctx!}/user/index'

                      })
       	    	  }
        	    });
        }
        function add(){
        	layer.open({
        	      type: 2,
        	      title: '用户添加',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '600px'],
        	      content: '${ctx!}/user/add',
        	      end: function(index){
        	    	  /*layer.msg("添加成功");*/
       	    	  }
        	    });
        }

        function del(id){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
    	    		   type: "POST",
    	    		   dataType: "json",
    	    		   url: "${ctx!}/user/delete/" + id,
    	    		   success: function(data){
    	    		       parent.layer.msg(data.msg, {time:2000});
						   window.location.href = "${ctx!}/user/index?pageNumber=1";
                           // alert(msg.msg);
                       }
    	    	});
       		});
        }

    </script>
    

</body>

</html>
