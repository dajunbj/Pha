update good_type
set 
/*IF param.type_id != null*/
 type_id=/*param.type_id*/,
/*END*/
/*IF param.tp_nm != null*/
tp_nm=/*param.tp_nm*/, 
/*END*/
upd_date=/*param.upd_date*/,
upd_id=/*param.upd_id*/,
del_flg=/*param.del_flg*/

where type_id =/*param.type_id*/ 