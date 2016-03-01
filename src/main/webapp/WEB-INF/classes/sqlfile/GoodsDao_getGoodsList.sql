select 
*
FROM goods
/*IF param != null*/
where 
    0=0
	/*IF param.type_id != null*/
	and type_id = /*param.type_id*/
	/*END*/
	/*IF param.good_producer_id != null*/
	and good_producer_id = /*param.good_producer_id*/
	/*END*/
    and del_flg='0'
/*END*/
order by goods_id