//1:奇数、偶数によって、色を設定する 
//2:モウスの移動により、行色が自動的に変更する
$(document).ready(function() {
	// 隔行表色
	$("tr[id=rowIndex]:even").addClass("even");
	$("tr[id=rowIndex]:odd").addClass("odd");

	// 滑动变色
	$("tr[id=rowIndex]").mouseover(function() {
		$(this).addClass("row_select");
	}).mouseout(function() {
		$(this).removeClass("row_select");
	});
});