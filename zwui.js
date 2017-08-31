function searchZList(list, input){
    var str = input.val();
    alert(str);
    list.children("label").each(function(){
       if($(this).html().indexOf(str) > -1){
           $(this).css("display", "");
       }else {
           $(this).css("display", "none");
       }
    });
}