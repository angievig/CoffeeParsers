model Facturacion_Serv_Publicos
variables:
boolean Tipo_de_Servicio variants:[selected unselected]
boolean Energia variants:[selected unselected]
boolean Agua variants:[selected unselected]
boolean Alcantarillado variants:[selected unselected]
boolean Aseo variants:[selected unselected]
boolean Gas variants:[selected unselected]
boolean Alumbrado_Publico variants:[selected unselected]
boolean Registro_Consumo variants:[selected unselected]
boolean RegAutomatico variants:[selected unselected]
boolean RegManual variants:[selected unselected]
boolean RegPrepago variants:[selected unselected]
boolean Liquidacion variants:[selected unselected]
boolean Liq_Manual variants:[selected unselected]
boolean Generar_Doc_Factura variants:[selected unselected]
boolean Subir_cargos_Pendientes variants:[selected unselected]
boolean Liq_Automatica variants:[selected unselected]
boolean Liq_aut_Individual variants:[selected unselected]
boolean Liq_Ind_aut_Saldos variants:[selected unselected]
boolean Liq_Ind_aut_Consumos variants:[selected unselected]
boolean Liq_Ind_aut_Servicios variants:[selected unselected]
boolean Liq_Ind_aut_Cargos_Externos variants:[selected unselected]
boolean Liq_aut_Masiva variants:[selected unselected]
boolean Liq_mas_aut_Saldos variants:[selected unselected]
boolean Liq_mas_aut_Consumos variants:[selected unselected]
boolean Liq_mas_aut_Servicios variants:[selected unselected]
boolean Liq_mas_aut_Cargos_Externos variants:[selected unselected]
boolean Motor_liquidacion variants:[selected unselected]
boolean Motor_secuencial variants:[selected unselected]
boolean Motor_paralelo variants:[selected unselected]
boolean Gestion_Tarifaria variants:[selected unselected]
boolean Gest_Tar_Impuestos variants:[selected unselected]
boolean Gest_Tar_Consumos_Horarios variants:[selected unselected]
boolean Gest_Tar_Consumos_Horarios variants:[selected unselected]
boolean Otros_Cobros variants:[selected unselected]
boolean Ges_tar_Reglas_Dinamicas variants:[selected unselected]
boolean Ges_tar_reglas_codigo variants:[selected unselected]
boolean Emision_factura variants:[selected unselected]
boolean Emis_fact_Regulatoria variants:[selected unselected]
boolean Emis_fact_Digital_Individual_Web variants:[selected unselected]
boolean Ticket variants:[selected unselected]
boolean CopiaFiel variants:[selected unselected]
boolean EstadoCuenta variants:[selected unselected]
boolean Conf_Factura variants:[selected unselected]
boolean Conf_fact_Modulo_extraccion variants:[selected unselected]
boolean Conf_fact_estatico variants:[selected unselected]
boolean Analisis_Consumos variants:[selected unselected]
boolean An_cons_sencillos variants:[selected unselected]
boolean An_sen_Mineria variants:[selected unselected]
boolean An_sen_reglas variants:[selected unselected]
boolean An_cons_horarios variants:[selected unselected]
boolean An_hor_Mineria variants:[selected unselected]
boolean An_hor_Reglas variants:[selected unselected]
boolean An_Perdidas variants:[selected unselected]
boolean An_perdidas_Sectores variants:[selected unselected]
boolean An_perdidas_Agregado variants:[selected unselected]

constraints:C1: Tipo_de_Servicio is mandatory
C3: Registro_Consumo is mandatory
C5: Liquidacion is mandatory
C9: Liq_aut_Individual mandatory Liq_Ind_aut_Saldos
C10: Liq_aut_Individual mandatory Liq_Ind_aut_Consumos
C13: Liq_aut_Masiva mandatory Liq_mas_aut_Saldos
C14: Liq_aut_Masiva mandatory Liq_mas_aut_Consumos
C17: Liquidacion mandatory Motor_liquidacion
C19: Gestion_Tarifaria is mandatory
C25: Emision_factura mandatory Conf_Factura
C11: Liq_aut_Individual optional Liq_Ind_aut_Servicios
C12: Liq_aut_Individual optional Liq_Ind_aut_Cargos_Externos
C15: Liq_aut_Masiva optional Liq_mas_aut_Servicios
C16: Liq_aut_Masiva optional Liq_mas_aut_Cargos_Externos
C22: Emision_factura is optional
C27: Analisis_Consumos is optional
C32: Emis_fact_Regulatoria excludes Conf_fact_estatico
C33: Energia requires Alumbrado_Publico
C34: Liq_Automatica requires Gest_Tar_Impuestos
C35: Liq_aut_Masiva excludes Motor_secuencial
C36: Liq_Ind_aut_Servicios requires Subir_cargos_Pendientes
C37: Liq_Ind_aut_Cargos_Externos requires Subir_cargos_Pendientes
C38: Liq_mas_aut_Servicios requires Subir_cargos_Pendientes
C39: Liq_mas_aut_Cargos_Externos requires Subir_cargos_Pendientes
C40: Gest_Tar_Consumos_Horarios excludes Ges_tar_reglas_codigo
C41: Agua requires Alcantarillado
C42: RegPrepago requires Liq_aut_Individual
C2: parent:Tipo_de_Servicio group:Energia Agua Alcantarillado Aseo Gas Alumbrado_Publico  card:[1,6]
C4: parent:Registro_Consumo group:RegAutomatico RegManual RegPrepago  card:[1,3]
C6: parent:Liquidacion group:Liq_Manual Liq_Automatica  card:[1,2]
C7: parent:Liq_Manual group:Generar_Doc_Factura Subir_cargos_Pendientes  card:[1,2]
C8: parent:Liq_Automatica group:Liq_aut_Individual Liq_aut_Masiva  card:[1,2]
C18: parent:Motor_liquidacion group:Motor_secuencial Motor_paralelo  card:[1,2]
C20: parent:Gestion_Tarifaria group:Gest_Tar_Impuestos Gest_Tar_Consumos_Horarios Gest_Tar_Consumos_Horarios Otros_Cobros  card:[1,4]
C21: parent:Gestion_Tarifaria group:Ges_tar_Reglas_Dinamicas Ges_tar_reglas_codigo  card:[1,1]
C23: parent:Emision_factura group:Emis_fact_Regulatoria Emis_fact_Digital_Individual_Web  card:[1,2]
C24: parent:Emis_fact_Digital_Individual_Web group:Ticket CopiaFiel EstadoCuenta  card:[1,3]
C26: parent:Conf_Factura group:Conf_fact_Modulo_extraccion Conf_fact_estatico  card:[1,1]
C28: parent:Analisis_Consumos group:An_cons_sencillos An_cons_horarios An_Perdidas  card:[1,3]
C29: parent:An_cons_sencillos group:An_sen_Mineria An_sen_reglas  card:[1,1]
C30: parent:An_cons_horarios group:An_hor_Mineria An_hor_Reglas  card:[1,1]
C31: parent:An_Perdidas group:An_perdidas_Sectores An_perdidas_Agregado  card:[1,1]

