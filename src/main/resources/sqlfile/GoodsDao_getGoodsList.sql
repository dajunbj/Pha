select 
T1.*,
T2.good_producer_nm,
T3.tp_nm
FROM goods T1
left join good_producer T2 on 
T1.good_producer_id = T2.good_producer_id
left join good_type T3 on 
T1.type_id = T3.type_id
/*IF param != null*/
where 
    0=0
	/*IF param.type_id != null*/
	and type_id = /*param.type_id*/
	/*END*/
	/*IF param.good_producer_id != null*/
	and good_producer_id = /*param.good_producer_id*/
	/*END*/
    and T1.del_flg='0'
/*END*/
order by goods_id