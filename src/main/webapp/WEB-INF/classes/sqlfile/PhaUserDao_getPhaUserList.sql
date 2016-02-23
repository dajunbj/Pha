select 
*
FROM pha_user
/*IF user != null*/
where 
    0=0
	/*IF user.user_id != null*/
	and user_id = /*user.user_id*/
	/*END*/
	/*IF user.password != null*/
	and password = /*user.password*/
	/*END*/
	 /*IF user.user_nm != null*/
	 and user_nm = /*user.user_nm*/
	/*END*/
	 /*IF user.login_id != null*/
	 and login_id = /*user.login_id*/
	/*END*/
	 
	 
	 
	 /*IF user.level == 9*/
	 and recommend_id = /*user.recommend_id*/
	 and level = '1'
	/*END*/
	 /*IF user.level == 1*/
	 and recommend_id = /*user.recommend_id*/
	 and level = '0'
	/*END*/
	 
/*END*/