function upload(){
    var type = "file";          //后台接收时需要的参数名称，自定义即可
    var id = "file";            //即input的id，用来寻找值
    var formData = new FormData();
    var filePath =  false;
    formData.append(type, $("#"+id)[0].files[0]);    //生成一对表单属性
    $.ajax({
        type: "POST",           //因为是传输文件，所以必须是post
        url: '/upload',         //对应的后台处理类的地址
        data: formData,
        dataType: "json",
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            //alert("上传成功")
            if(data.code == 0){
                alert("上传成功");
                document.getElementById('filePath').value = data.data;
            }
        }
    });
    return filePath;
}