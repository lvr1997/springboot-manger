<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>资源列表</title>
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
                        <h5>资源管理</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
                        	<#--<@shiro.hasPermission name="system:resource:add">

                        	</@shiro.hasPermission>-->
                            <button class="btn btn-success" type="button" onclick="add();"><i class="fa fa-upload"></i>&nbsp;上传文件</button>
                        </p>
                        <hr>
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table class="table table-hover text-nowrap" data-toggle="table">
											<thead>
												<tr>
													<th>ID</th>
													<th>资源名称</th>
													<th>存储路径</th>
													<th>上传人</th>
													<th>上传时间</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
											<#list  resourceFileList as resource>
											  <tr>
												<td>${(resource.resourceId)!}</td>
												<td>${(resource.resourceName)!}</td>
												<td>${(resource.resourcePath)!}</td>
												<td>${(resource.onloadUser)!}</td>
												<td>${(resource.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
												<td>
                                                  <#if resource.resourceStatus ==1  >
                                                      <span class="label label-info">可用 </span>
													  <#else >
													  <span class="label label-danger">不可用 </span>
												  </#if>
												</td>
                                                <td>
                                                    <#if resource.resourceStatus == 1>
                                                        <button class="btn btn-danger btn-xs" type="button" onclick="del(${(resource.resourceId)!},'${(resource.resourcePath)!}','${(resource.resourceName)!}')"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                                        <button class="btn btn-file btn-xs" type="button" onclick="download('${(resource.resourcePath)!}','${(resource.resourceName)!}')"><i class="fa fa-download"></i>&nbsp;下载</button>
                                                    </#if>
                                                </td>
                                              </tr>
											</#list>
											</tbody>
										</table>
		                            </div>
		                        </div>
		                        <!-- End Example Card View -->
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


	<!-- Bootstrap table -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>

		//打开上传弹框
        function add(){
        	layer.open({
        	      type: 2,
        	      title: '资源文件上传',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '300px'],
        	      content: '${ctx!}/resourceFile/add',
        	      end: function(index){

       	    	  }
        	    });
        }
        function del(id,url,name){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
    	    		   type: "POST",
    	    		   dataType: "json",
                       data:{url:url,name:name},
    	    		   url: "${ctx!}/resourceFile/delete/" + id,
    	    		   success: function(data){
                           layer.msg(data.msg,{time:2000},function () {
                               window.location.href = "${ctx!}/resourceFile/index";
                               layer.close(index);
                           });

    	    		   }
    	    	});
       		});
        }
        function download(url,fileName){

            window.location.href="${ctx!}/resourceFile/download?downloadUrl="+url+"&fileName="+fileName;

		}

    </script>

    
    

</body>

</html>
