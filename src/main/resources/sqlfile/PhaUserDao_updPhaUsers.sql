update pha_user  
set 
  password =/*user.password*/,
  user_nm =/*user.user_nm*/,
  smart_phone=/*user.smart_phone*/,
  phone=/*user.phone*/,
  mail=/*user.mail*/,
  address=/*user.address*/,
  postal_code =/*user.postal_code*/,
  exchange_rate  =/*user.exchange_rate*/,
  rate =/*user.rate*/,
  level=/*user.level*/,
  recommend_id =/*user.recommend_id*/,
  upd_date =/*user.user_id*/,
  upd_id=/*user.user_id*/,
  del_flg=/*user.del_flg*/,
  login_id=/*user.login_id*/
  where user_id =/*user.user_id*/ 