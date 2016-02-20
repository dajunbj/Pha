select good_producer_id as value,good_producer_nm as label from good_producer
where del_flg='0'

/*IF param.type_id != null*/
    and type_id = /*param.type_id*/
/*END*/
order by good_producer_id
