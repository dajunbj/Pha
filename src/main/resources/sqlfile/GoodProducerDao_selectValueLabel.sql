select type_id as value,tp_nm as label from good_type
where del_flg='0'

/*IF param.type_id != null*/
    and type_id = /*param.type_id*/
/*END*/
order by type_id
