select COUNT(*) from good_type
 /*IF param != null*/
where 
0=0
	/*IF param.type_id != null*/
	   and type_id = /*param.type_id*/
	/*END*/
	/*IF param.tp_nm != null*/
	   and tp_nm = /*param.tp_nm*/
	/*END*/
/*END*/