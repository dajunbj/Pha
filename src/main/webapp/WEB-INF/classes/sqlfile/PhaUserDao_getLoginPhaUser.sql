select 
user_id ,
  password,
  user_nm ,
  smart_phone ,
  phone ,
  mail ,
  address ,
  postal_code ,
  exchange_rate ,
  rate ,
  level ,
  recommend_id ,
  registe_date ,
  registe_id ,
  upd_date ,
  upd_id ,
  del_flg 
 FROM pha_user
 /*IF user != null*/
where 
    0=0
	/*IF user.user_id != null*/
   user_id = /*user.user_id*/
	/*END*/
	/*IF user.user_id != null*/
   and password=/*user.password*/
	/*END*/
 
	/*END*/