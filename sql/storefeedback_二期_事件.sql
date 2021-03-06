


CREATE procedure zmm_ds62_2_zmm_ds62_his()
BEGIN
SET @_v_sql = concat(
    'INSERT INTO zmm_ds62_his(REQUEST,DATAPAKID,RECORD,SALES_GRP,ZDEPTMNT,ZREGION,RPA_WGH2,ZMAT_CAT,PROD_HIER,ZARTICLE,CALDAY,ZINV_QTY,UNIT,ZKI01148,ZKI01153,ZPUR_TYPE,ZRATE,FLAG,ZINV_AMB,CURRENCY,RECORDMODE)
SELECT z.REQUEST,z.DATAPAKID,z.RECORD,z.SALES_GRP,z.ZDEPTMNT,z.ZREGION,z.RPA_WGH2,z.ZMAT_CAT,z.PROD_HIER,z.ZARTICLE,z.CALDAY,z.ZINV_QTY,z.UNIT,z.ZKI01148,z.ZKI01153,z.ZPUR_TYPE,z.ZRATE,z.FLAG,z.ZINV_AMB,z.CURRENCY,z.RECORDMODE FROM zmm_ds62 z
'
);
SELECT @_v_sql;
SET @v_sql = @_v_sql;
PREPARE stmt FROM @v_sql;
EXECUTE stmt;
COMMIT;
END

set global event_scheduler=1;

   CREATE EVENT if not exists zmm_ds62_2_zmm_ds62_his_event
          on schedule EVERY 1 DAY 
          STARTS '2015-04-05 22:00:00'
          on completion preserve
     do call zmm_ds62_2_zmm_ds62_his();