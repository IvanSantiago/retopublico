Ext
		.onReady(function() {

			Ext.QuickTips.init();

			var C_SI_record = Ext.data.Record.create([ {
				name : 'uid_SI'
			}, {
				name : 'categoria',
				type : 'string'
			}, {
				name : 'descripcion',
				type : 'string'
			}, {
				name : 'nombre',
				type : 'string'
			} ]);

			var proxy = new Ext.data.HttpProxy({
				api : {
					read : 'C_SI_externosView.action',
					create : 'create.action',
					update : 'update.action',
					destroy : 'delete.action'
				}
			});

			var reader = new Ext.data.JsonReader({
				totalProperty : 'total',
				successProperty : 'success',
				idProperty : 'id',
				root : 'data',
				messageProperty : 'message' // <-- New "messageProperty"
											// meta-data
			}, C_SI_record);

			// The new DataWriter component.
			var writer = new Ext.data.JsonWriter({
				encode : true,
				writeAllFields : true
			});

			// Typical Store collecting the Proxy, Reader and Writer together.
			var store = new Ext.data.Store({
				id : 'sistemasStore',
				proxy : proxy,
				reader : reader,
				writer : writer, // <-- plug a DataWriter into the store just
									// as you would a Reader
				autoSave : false
			// <-- false would delay executing create, update, destroy requests
			// until specifically told to do so with some [save] buton.
			});

			store.load();

			// Typical Store collecting the Proxy, Reader and Writer together.
			storePerfiles = loadPerfiles(0);

			function loadPerfiles(UID_SI) {
				var Perfil = Ext.data.Record.create([ {
					name : 'uid_PER'
				}, {
					name : 'orden',
					type : 'string'
				}, {
					name : 'desc_L',
					type : 'string'
				}, {
					name : 'perfil',
					type : 'string'
				} ]);

				var loaderProxy = new Ext.data.HttpProxy({
					api : {
						read : 'SOL_SI_PERF_viewForOneSystem.action?UID_SI='
								+ UID_SI,
						create : 'SOL_SI_PERF_create.action',
						update : 'SOL_SI_PERF_update.action',
						destroy : 'SOL_SI_PERF_delete.action'
					}
				});

				var reader = new Ext.data.JsonReader({
					totalProperty : 'total',
					successProperty : 'success',
					idProperty : 'id',
					root : 'data',
					messageProperty : 'message' // <-- New "messageProperty"
												// meta-data
				}, Perfil);

				var writer = new Ext.data.JsonWriter({
					encode : true,
					writeAllFields : true
				});

				var reloadedStore = new Ext.data.Store({
					id : 'reloadedPerfilStore',
					proxy : loaderProxy,
					reader : reader,
					writer : writer, // <-- plug a DataWriter into the store
										// just as you would a Reader
					autoSave : false
				// <-- false would delay executing create, update, destroy
				// requests until specifically told to do so with some [save]
				// buton.
				});
				reloadedStore.load();
				return reloadedStore;
			}
			
			var comboSistema = new Ext.form.ComboBox(
					{
						width : 500,
						fieldLabel : 'Sistema al cual solicita acceso',
						name:'UID_SI',
						id:'UID_SI',
						store : store,
						displayField : 'nombre',
						valueField : 'uid_SI',
						typeAhead : true,
						//mode : 'local',
						allowBlank: false,
						triggerAction : 'all',
						emptyText : 'Selecciona un opción...',
						selectOnFocus : true,
						tpl : '<tpl for="."><div ext:qtip="{nombre}" class="x-combo-list-item">{nombre}</div></tpl>',
						listeners : {
							select : function(combo, record, index) {
								//sistemaInfo.collapse();
								//usuarioInfo.collapse();
								creaCheckList(loadPerfiles(record.data.uid_SI));
								
								/*comboPerfil.clearValue();
								storePerfiles = loadPerfiles(record.data.uid_SI);
								comboPerfil
										.bindStore(storePerfiles);
								if(record.data.uid_SI>0)*/
							}
						}
					});

			/*comboPerfil = new Ext.form.ComboBox(
					{
						width : 500,
						//mode : 'local',
						emptyText : 'Selecciona un opción...',
						triggerAction : 'all',
						forceSelection : true,
						editable : false,
						name : 'perfil',
						hiddenName : 'perfil',
						fieldLabel : 'Perfil que solicita dentro del sistema',
						displayField : 'perfil',
						valueField : 'uid_PER',
						store : storePerfiles,
						typeAhead : true,
						selectOnFocus : true,
						tpl : '<tpl for="."><div ext:qtip="{perfil}" class="x-combo-list-item">{perfil}</div></tpl>',
						listeners : {
							select : function(combo, record, index) {
								
							}
						}
					});
*/
			var usuarioInfo = {
					xtype : 'fieldset',
					collapsible : true,
					title : 'Información del USUARIO que accederá la sistema',
					autoHeight : true,
					width : '100%',
					defaults : {
						width : '95%',
						allowBlank : false						
					},
					defaultType : 'textfield',
					collapsed : false,
					items : [ {
						fieldLabel : 'Empresa',
						name : 'NE',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Departamento ó Área',
						name : 'DS',
						vtype: 'LetNumEsp'
					}/*, {
						fieldLabel : 'Clave o denominaci&oacute;n en el sistema',
						name : 'CVE'
					}*/, {
						fieldLabel : 'Nombre(s)',
						name : 'NOM',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Apellido Paterno',
						name : 'AP',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Apellido Materno',
						name : 'AM',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Registro Federal de Contribuyente',
						name : 'RFC',
						vtype: 'RFC'						
					}, {
						fieldLabel : 'Calle y Número',
						name : 'DIR',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Estado',
						name : 'EDO',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Ciudad',
						name : 'CD',
						vtype: 'LetNumEsp'
					}, {
						fieldLabel : 'Colonia',
						name : 'COL',
						vtype: 'LetNumEsp',
						allowBlank : true
					}, {
						fieldLabel : 'Código Postal',
						name : 'CP',
						vtype: 'codigopostal'
					}, {
						fieldLabel : 'Teléfono',
						name : 'TEL',
						vtype: 'telefono',
						allowBlank : true
					}, {
						fieldLabel : 'Correo Electrónico',
						name : 'EM',
						vtype: 'email',
						allowBlank : true
					}]
				};
			
//			
//			/[a-z0-9_]/i  		
			// \d+
			Ext.apply(Ext.form.VTypes, {
				codigopostal:  function(v) {
			        return /^\d+$/.test(v);
			    },
			    codigopostalText: 'Solo debe contener numeros',
			    //codigopostalMask: /[\d\.]/i
			});
			Ext.apply(Ext.form.VTypes, {
				telefono:  function(v) {
			        return /^\d+$/.test(v);
			    },
			    telefonoText: 'Solo debe contener numeros',
			    //telefonoMask: /[\d\.]/i
			});
			Ext.apply(Ext.form.VTypes, {
				LetNumEsp:  function(v) {
			        return /^[a-zA-Z0-9 \.\,]+$/.test(v);
			    },
			    LetNumEspText: 'Solo se permite letras (A-Z), números (0-9), espacios( ), punto(.) y coma(,)',
			    //LetNumEspMask: /[a-zA-Z0-9 \.\,]/i
			});
			Ext.apply(Ext.form.VTypes, {
				LetNumEsp:  function(v) {
			        return /^[a-zA-Z0-9 \.\,]+$/.test(v);
			    },
			    LetNumEspText: 'Solo se permite letras (A-Z), números (0-9), espacios( ), punto(.) y coma(,)',
			    //LetNumEspMask: /[a-zA-Z0-9 \.\,]/i
			});
			Ext.apply(Ext.form.VTypes, {
				RFC:  function(v) {
			        return /^([A-Z|a-z|&amp;]{3}\d{2}((0[1-9]|1[012])(0[1-9]|1\d|2[0-8])|(0[13456789]|1[012])(29|30)|(0[13578]|1[02])31)|([02468][048]|[13579][26])0229)(\w{2})([A|a|0-9]{1})$|^([A-Z|a-z]{4}\d{2}((0[1-9]|1[012])(0[1-9]|1\d|2[0-8])|(0[13456789]|1[012])(29|30)|(0[13578]|1[02])31)|([02468][048]|[13579][26])0229)((\w{2})([A|a|0-9]{1})){0,3}$/.test(v);
			    },
			    RFCText: 'El RFC debe contener el algunos de los formatos correctos (ABCD790419) (ABC790419aa1) (ABCD790419AB1)',
			    //LetNumEspMask: /[a-zA-Z0-9 \.\,]/i
			});
			
			
			
			Ext.apply(Ext.form.VTypes, {
			    emailText: 'El formato del correo debe ser xxxxxxxxx@yyyyyy.zzz'
			});



			
			var sistemaInfo = {
					xtype : 'fieldset',
					collapsible : true,
					title : 'Información del SISTEMA al que desea acceder',
					autoHeight : true,
					monitorResize : true, // relay on browser resize
					autoWidth : true,
					layoutConfig : {
						align : 'stretch'
					},
					defaults : {
						flex : 1
					},
					defaultType : 'combobox',
					collapsed : false,
					items : [ comboSistema/*, comboPerfil */]
				};
			
			var simple = new Ext.FormPanel({
				labelWidth : 200, // label settings here cascade unless
									// overridden
				//url:'procesaSolicitudSistemas.action',
				// frame:true, tab-close.gif
				title : 'Formulario de datos personales',
				bodyStyle : 'padding:10px 10px 10px 10px',
				defaults : {
					align : 'right',
					width : '95%'
				},
				defaultType : 'textfield',

				items : [ usuarioInfo, sistemaInfo ],
				bbar : [ {
					text : 'Procesar Forma',
					iconCls : 'icon-save',
					enableToggle : true,
					handler: function(){
						var perfiles = new Array();
						if(Ext.getCmp('SOL_SI_PERF')==undefined){
							Ext.Msg.alert('Error', 'Valores necesarios no ingresados.');
							return false;
						}
						var perfilesCheckboxes = Ext.getCmp('SOL_SI_PERF').getValue();
						for(var x = 0;x<perfilesCheckboxes.length;x++){
							perfiles[x]= perfilesCheckboxes[x].getItemId();
						}						
						var JSONForm = Ext.encode(simple.getForm().getValues());
						simple.getForm().submit({							
							url:'solicitudAccesoSistemasExterno.action',
							method: 'POST',
							waitTitle : 'Enviando información al servidor',
					        params: {
					        	UID_PROCESO: 33,
					        	UID_SI: comboSistema.getValue(),
					        	SOL_SI_PERF: perfiles,
					        	FORM: JSONForm
					        },
					        success : function(form, action){
					        	simple.getForm().reset();
					        	creaCheckList(loadPerfiles(comboSistema.getValue()));
					        	Ext.Msg.alert('Exito', 'Su solicitud se a creado exitosamente.');
					        	window.location=action.result.url;
					        	
				        	},
					        failure : function(data){
					        	Ext.Msg.alert('Falla', 'Hubo un error al crear su solicitud intentelo nuevamente');
				        	}
						});						
					}						
				}, '	', '-', '	', {
					text : 'Limpiar Forma',
					iconCls : 'icon-clean-form',
					enableToggle : true,
					handler: function(){
						simple.getForm().reset();
						creaCheckList(loadPerfiles(comboSistema.getValue()));
					}
				} ]
			});
			
			function creaCheckList(mystore){
				var checkboxconfigs = []; //array of about to be checkboxes.   
				//var mystore = comboPerfil.getStore();
				mystore.load({
                    callback: function(rs, opts, success){
                    	if(rs.length>0){
                    		for(var x=0;x<rs.length;x++){
                        		checkboxconfigs.push({ //pushing into array
            				        id:rs[x].data.uid_SI,
            				        //itemId:rs[x].data.perfil,
            				        itemId:rs[x].data.perfil,
            				        boxLabel:rs[x].data.perfil,
            				        //any other checkbox properties, layout related or whatever
            				    });                    	
                        	}                    	
                        	var myCheckboxgroup = new Ext.form.CheckboxGroup({
            				    id:'SOL_SI_PERF',
            				    fieldLabel: 'Perfiles disponibles para este sistema',
            				    columns:2,
            				    items:checkboxconfigs,
            				    allowBlank : false//created in previous snippet.
            				    //any other checkbox group configuration
            				});
                        	var perfilesInfo = {
                					xtype : 'fieldset',
                					id:'perfilesInfo',
                					collapsible : true,
                					title : 'Información del PERFIL que desea tener dentro del sistema',
                					autoHeight : true,
                					monitorResize : true, // relay on browser resize
                					autoWidth : true,
                					layoutConfig : {
                						align : 'stretch'
                					},
                					defaults : {
                						flex : 1
                					},
                					defaultType : 'combobox',
                					collapsed : false,

                					items : [ myCheckboxgroup/*, comboPerfil */]
                				};
                        	simple.remove('perfilesInfo');
            				simple.add(perfilesInfo);
            				simple.doLayout();
                    	}else{
                    		simple.remove('perfilesInfo');
                    	}
                    },
                    scope: this
                });
			}			

			simple.render(document.body);
		});