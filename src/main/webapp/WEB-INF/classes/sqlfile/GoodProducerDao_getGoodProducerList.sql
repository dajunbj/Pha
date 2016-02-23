select 
  T1.good_producer_id,
  T1.type_id,
  T1.good_producer_nm,
  T1.founder,
  T1.address,
  T1.phone,
  T1.registe_date,
  T1.registe_id,
  T1.upd_date,
  T1.upd_id,
  T1.del_flg,
  T2.tp_nm  
FROM good_producer T1
left join good_type T2 on T1.type_id = T2.type_id
/*IF param != null*/
where 
    0=0	
    /*IF param.type_id != null*/
	and T1.type_id = /*param.type_id*/
	/*END*/
	/*IF param.good_producer_nm != null*/
	and T1.good_producer_nm like /*param.good_producer_nm*/ 
	/*END*/

    and T1.del_flg='0'
/*END*/
order by T2.type_id,T1.good_producer_id;