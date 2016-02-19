select 
*
FROM good_type
/*IF param != null*/
where 
    0=0
	/*IF param.tp_nm != null*/
	and tp_nm like '/*param.tp_nm*/%'
	/*END*/

    and del_flg='0'
/*END*/
order by type_id;