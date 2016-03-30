 <%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
 <%@ page 
 import="com.controller.GetScanRecord" 
 import="java.text.SimpleDateFormat"
 import="java.sql.Date"
 %>
<!DOCTYPE html>
<html lang="zh-CN">
   <head>
 	    <%@include file="header.jsp"%>
 	    <!-- 与表格相关的框架 bootstrap table -->
 	    <link rel="stylesheet" href="bootstrap/css/bootstrap-table.css">
   	    <script src="bootstrap/js/bootstrap-table.js"></script>
 	    <script src="bootstrap/js/bootstrap-table-zh-CN.js"></script>
 	    <!-- 编辑表格元素的js 库 -->
 	    <link rel="stylesheet" href="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/css/bootstrap-editable.css">
 	    <script src="bootstrap/js/bootstrap-table-editable.js"></script>
    	<script src="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/js/bootstrap-editable.js"></script>
   </head>
   <body >
   	  <%request.setAttribute("MENU_INDEX", "index");%>
      <!-- 包含导航栏 -->
 	    <%@include file="navbar.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
				<table id="record" 
					   data-row-style="rowStyle"
                ></table>
				<script type="text/javascript">
					buildTable();
					function buildTable() {
						//向服务器获取父表格的数据
						var row, fdata=[];
						$.ajax({
			   			    type : "POST", 
			   			    contentType : "application/json", 
			   			    url : "/btlocationServer/GetAnchorNode", 
			   			    dataType : 'json',
			   			    success : function(data){
			   			       //将数据填充到表格中
			   			    	$.each(data, function(i, item) { 
			   			    		row = {};
			   			    		row['nodeid']=i+1;
			   			    		row['amac']=item.a_address;
			   			    		row['aname']=item.a_name;
			   			    		row['ax']=item.a_x;
			   			    		row['ay']=item.a_y;
					 				fdata.push(row);
			   			    	});
			   			   	    gengerateFtable();//异步接收数据后刷新表格
			   			    }
			   			});
						function gengerateFtable(){
				   			//生成父表格
					    	$('#record').bootstrapTable({
					    		search: true,
					    		showRefresh: true,
					    		detailView: true, 
					    		showToggle:true,     //是否显示详细视图和列表视图的切换按钮
					        	columns: [{
					        		class: 'col-md-1 ',
							        field: 'nodeid',
							        title: 'ID',
							        sortable:true
							    },{
					        		align: 'center',
							        field: 'amac',
							        title: '锚节点Mac地址'
							    },{
							        field: 'aname',
							        align: 'center',
							        title: '锚节点名称'
							    }, {
							        field: 'ax',
							        align: 'center',
							        title: '锚节点X坐标',
						        	editable: {
			                            type: 'text',
			                            validate: function (value) {
			                                value = $.trim(value);
			                                //正则匹配浮点数
			                                var Re =/^(-?\d+)(\.\d+)?$/;
			                                if (!value) {
			                                    return '不能为空！';
			                                }
			                                if (!Re.test(value)) {
			                                    return '请输入数字';
			                                }
			                                var data = $('#record').bootstrapTable('getData'),
			                                index = $(this).parents('tr').data('index');
			                                var updateMac = data[index]['amac'];
			                                var sql = "update anchornode set ax ="+value+" where aaddress='"+updateMac+"'";
			                                updateXY(sql);
			                                return '';
			                            }
			                        },
							    },{
							        field: 'ay',
							        align: 'center',
							        title: '锚节点Y坐标',
						        	editable: {
			                            type: 'text',
			                            validate: function (value) {
			                            	value = $.trim(value);
			                                //正则匹配浮点数
			                                var Re =/^(-?\d+)(\.\d+)?$/ ;
			                                if (!value) {
			                                    return '不能为空！';
			                                }
			                                if (!Re.test(value)) {
			                                    return '请输入数字';
			                                }
			                                var data = $('#record').bootstrapTable('getData'),
			                                index = $(this).parents('tr').data('index');
			                                var updateMac = data[index]['amac'];
			                                var sql = "update anchornode set ay ="+value+" where aaddress='"+updateMac+"'";
			                                updateXY(sql);
			                                return '';
			                            }
			                        },
							    }],
							    data: fdata,
					            onExpandRow: function (index, row , $detail) {
					            	var selected = fdata[index]['amac'];
					            	console.log(index+",,"+fdata[index]['amac']);
					                expandTable(selected, $detail);
					            }
					    	});
						}
					}
					function expandTable(selected, $detail) {
						//向服务器获取子表格的数据
			   			var c_row, c_data=[];
			   			$.ajax({
			   			    type : "POST",
			   			    contentType : "application/json", 
			   			    url : "/btlocationServer/GetScanRecord", 
			   			 	data: selected,
			   			    dataType : 'json',
			   			    success : function(data){
			   			       //将数据填充到表格中
			   			    	$.each(data, function(i, item) { 
			   			    		c_row = {};
			   			    		c_row['recordID'] =i+1;
			   			    		c_row['time']=item.time;
			   			    		c_row['imac']=item.i_addr;
			   			    		c_row['irssi']=item.rssi;	
					 				c_data.push(c_row);
			   			    	});
			   			       generateCtable(c_data,$detail);
			   			    }
			   			});
			   			
				    }
					function generateCtable(c_data, $detail){
		   				//生成子表格
				        var subtable = $detail.html('<table></table>').find('table');
				        $(subtable).bootstrapTable({
			        	    height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数调整表格高度、
			        	    showRefresh: true,
				        	detailView: false,
				        	search: true,
				            striped: true,
				    		showToggle:true,     //是否显示详细视图和列表视图的切换按钮
				    		formatLoadingMessage: function () {return "请稍等，正在努力加载中...";},
				        	columns: [{
				        		class: 'col-md-1 ',
						        field: 'recordID',
						        title: '扫描记录',
						        sortable:true
						    },{
				        		class: 'col-md-2 ',
				        		align: 'center',
						        field: 'time',
						        title: '时间',
						        sortable:true
						    },{
						    	class: 'col-md-4',
		                        align: 'center',
						        field: 'imac',
						        title: '目标Mac地址',
						        //target:'#modalTable',
						        //events:'operateEvents',
						    }, {
						    	align: 'center',
						        field: 'irssi',
						        title: '信号强度'
						    }, {
		                        field: 'operate',
		                        title: '查看详情',
		                        align: 'center',
		                        events: operateEvents,
		                        formatter: operateFormatter
		                    }],
						    data:c_data,
				    	});
		   			}
					function updateXY(sql){
						//向服务器获取子表格的数据
			   			$.ajax({
			   			    type : "POST",
			   			    url : "/btlocationServer/UpdateXY", 
			   			 	data: sql,
			   			    success : function(data){
			   			    	console.log("坐标更新成功");
			   			    }
			   			})
					}
				</script>
				</div>
			</div>
			
			<script>
				function operateFormatter(value, row, index) {
			        return [
			            '<a class="like"  title="Like" data-toggle="modal" data-target="#modalTable" >',
			            '<i class="glyphicon glyphicon-info-sign"></i>',
			            '</a>  ',
			        ].join('');
			    }
			    window.operateEvents = {
			            'click .like': function (e, value, row, index) {
			                console.log('You click like action, row: ' + row["imac"]+index);
			                var getBeaconDetailSql = "select * from ibeacon where iaddress ='"+row["imac"]+"'";
				        	var ibeaconrow;
				        	console.log("sql 语句为"+getBeaconDetailSql);
				        	//先取得数据
			                $.ajax({
				   			    type : "POST",
				   			    url : "/btlocationServer/GetBeaconInfo", 
				   			 	contentType : "application/json", 
				   			    dataType : 'json',
				   			 	data: getBeaconDetailSql,
				   			    success : function(data){
				   			    	$.each(data, function(i, item) { 
				   			    		ibeaconrow = {};
				   			    		ibeaconrow['iname'] =item.name;
				   			    		ibeaconrow['imac']=item.address;
				   			    		ibeaconrow['uuid']=item.uuid;	
				   			    		ibeaconrow['major']=item.major;	
				   			    		ibeaconrow['minor']=item.minor;	
				   			    		ibeaconrow['tx_power']=item.tx_power;	
				   			    		console.log(ibeaconrow);
				   			    	});
				   			        showModelTable(index,ibeaconrow);
				   			    }
				   			});
				   			function showModelTable(index,ibeaconrow){
			                		console.log("开始加载表格");
			                		$mtable =$('#mtable'); 
			                		$mtable.bootstrapTable('removeAll');
			                		$mtable.bootstrapTable('insertRow',{
			                			index:0,
			                			row:ibeaconrow
			                		});
					        		$('#modalTable').modal('show');
				   			}
			            }
			    };
			</script>
		
		    <div class="modal fade"  id="modalTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	            <div class="modal-dialog" style="width:80%" >
	                <div class="modal-content">
	                    <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                            <span aria-hidden="true">&times;</span></button>
	                        <h4 class="modal-title">目标节点详情</h4>
	                    </div>
	                    <div class="modal-body" style="width:auto">
	                        <table id="mtable" 
	                        	   data-toggle="table">
		                        <thead>
		                            <tr>
		                                <th data-field="iname" data-align="center">iBeacon name</th>
		                                <th data-field="imac" data-align="center">iBeacon Mac</th>
		                                <th data-field="uuid" data-align="center">uuid</th>
		                                <th data-field="major" data-align="center">major</th>
		                                <th data-field="minor" data-align="center">minor</th>
		                                <th data-field="tx_power" data-align="center">tx_power</th>
		                            </tr>
	                            </thead>
	                        </table>
	                    </div>
	                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
	                </div><!-- /.modal-content -->
	            </div><!-- /.modal-dialog -->
	        </div><!-- /.modal -->
		</div>
   </body>
</html>