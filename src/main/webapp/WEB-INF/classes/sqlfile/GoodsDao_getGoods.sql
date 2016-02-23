select * from goods
 /*IF param != null*/
where 
0=0
	/*IF param.goods_id != null*/
	   and goods_id = /*param.goods_id*/
	/*END*/
	/*IF param.type_id != null*/
	   and type_id=/*param.type_id*/
	/*END*/
	 	/*IF param.goods_nm != null*/
	   and goods_nm=/*param.goods_nm*/
	/*END*/
/*END*/