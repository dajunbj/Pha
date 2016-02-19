update good_producer
set 
/*IF param.type_id != null*/
    type_id=/*param.type_id*/,
/*END*/
/*IF param.good_producer_nm != null*/
    good_producer_nm=/*param.good_producer_nm*/,
/*END*/
/*IF param.founder != null*/
    founder=/*param.founder*/,
/*END*/
/*IF param.address != null*/
    address=/*param.address*/,
/*END*/
/*IF param.phone != null*/
    phone=/*param.phone*/,
/*END*/
upd_date=/*param.upd_date*/,
upd_id=/*param.upd_id*/,
del_flg=/*param.del_flg*/

where good_producer_id =/*param.good_producer_id*/ 