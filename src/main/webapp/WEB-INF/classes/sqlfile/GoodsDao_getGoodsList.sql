select 
*
FROM goods
/*IF param != null*/
where 
    0=0
	/*IF param.type_id != null*/
	and type_id = /*param.type_id*/
	/*END*/
	/*IF param.goods_nm != null*/
	and goods_nm = /*param.goods_nm*/
	/*END*/
    and del_flg='0'
/*END*/
order by goods_id