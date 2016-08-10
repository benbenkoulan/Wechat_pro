var jqueryTool = (function(jquery){
	var tool = {};
	tool.getFormJson = function(formId){
		var obj = {},
		objArr = jquery(formId).serializeArray();
		jquery.each(objArr , function(){
			if(this.name){
				obj[this.name] = this.value || '';
			}
		});
		return JSON.stringify(obj);
	}
	tool.ajaxRequest = function(url , method , data , succ_fun , err_fun){
		var request_data = {
				type : method,
				url : url,
				data : data,
				dataType : 'json',
				success : succ_fun	
			};
		if(err_fun){
			request_data['error'] = err_fun;
		} else {
			request_data['error'] = function(jqXHR ,textStatus, errorThrown){
				if(textStatus == 'timeout'){
					alert("网络不给力，请稍后再试！");
				}
			}
		}
		jquery.ajax(request_data);
	}
	tool.ajaxJSONRequest = function(url , method , data , succ_fun , err_fun){
		var request_data = {
				type : method,
				url : url,
				data : data,
				dataType : 'json',
				contentType: "application/json",
				success : succ_fun	
			};
		if(err_fun){
			request_data['error'] = err_fun;
		} else {
			request_data['error'] = function(jqXHR ,textStatus, errorThrown){
				if(textStatus == 'timeout'){
					alert("网络不给力，请稍后再试！");
				}
			}
		}
		jquery.ajax(request_data);
	}
	return tool;
}($));