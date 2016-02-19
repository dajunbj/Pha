update goods
set 
/*IF param.type_id != null*/
     type_id=/*param.type_id*/,
/*END*/
/*IF param.goods_nm != null*/
    goods_nm=/*param.goods_nm*/, 
/*END*/
/*IF param.unit != null*/
    unit=/*param.unit*/,
/*END*/
/*IF param.capacity != null*/
    capacity=/*param.capacity*/,
/*END*/
upd_date=/*param.upd_date*/,
upd_id=/*param.upd_id*/,
del_flg=/*param.del_flg*/
where goods_id =/*param.goods_id*/ 