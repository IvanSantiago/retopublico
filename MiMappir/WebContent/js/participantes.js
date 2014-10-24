Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';

    var PARTICIPANTE = Ext.data.Record.create([
	{	
		name: 'ICVEPARTICIPANTE_ID'
		,mapping: 'ICVEPARTICIPANTE_ID'
		,type:'string'
	},
	{	
		name: 'ICVEPARTICIPANTE'
		,mapping: 'ICVEPARTICIPANTE_PK.ICVEPARTICIPANTE'
		,type: 'int'
	}, {
        name: 'CPARTICIPANTE',
        type: 'string'
	}, {
        name: 'CFBCUENTA',
        type: 'string'
	}, {
        name: 'CCORREO',
        type: 'string'
	}, {
        name: 'CNOMBRE',
        type: 'string'
    }, {
        name: 'DTFECHAREGISTRO',
        type: 'date',
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'PARTICIPANTES_view.action',
//        	read : 'PARTICIPANTES_viewSpecificOperator',
            create : 'PARTICIPANTES_create.action',
            update : 'PARTICIPANTES_update.action',
            destroy : 'PARTICIPANTES_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVEPARTICIPANTE_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    PARTICIPANTE);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVEPARTICIPANTE_PK: {
    	    		ICVEPARTICIPANTE : rec.get('ICVEPARTICIPANTE')
				}
				, CPARTICIPANTE : rec.get('CPARTICIPANTE')
				, CFBCUENTA : rec.get('CFBCUENTA')				
				, CCORREO : rec.get('CCORREO')
				, CNOMBRE : rec.get('CNOMBRE')			
				, DTFECHAREGISTRO : rec.get('DTFECHAREGISTRO')
				, ICVEPARTICIPANTE_ID : rec.get('ICVEPARTICIPANTE')+"-"
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVEPARTICIPANTE',
        proxy: proxy,
        reader: reader,
        writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
        autoSave: false // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    });

    //read the data from simple array
    store.load();
    
    Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
    	Ext.Msg.show({
    		title: 'ERROR',
//    		msg: res.message,
    		msg:'Error de configuracion',
    		icon: Ext.MessageBox.ERROR,
    		buttons: Ext.Msg.OK
    	});
    });

    
    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Actualizar',
        cancelText: 'Cancelar'    
    });    
    
      
    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
			{header: "Clave",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVEPARTICIPANTE',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Participante",
             width: 170,
             sortable: true,
             dataIndex: 'CPARTICIPANTE',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "Cuenta de Facebook",
                width: 170,
                sortable: true,
                dataIndex: 'CFBCUENTA',
                editor: {
                   xtype: 'textfield',
                   allowBlank: false
             }},
             {header: "Correo",
                 width: 170,
                 sortable: true,
                 dataIndex: 'CCORREO',
                 editor: {
                    xtype: 'textfield',
                    vtype:'email',
                    allowBlank: false
             }},
             {header: "Nombre",
                 width: 170,
                 sortable: true,
                 dataIndex: 'CNOMBRE',
                 editor: {
                    xtype: 'textfield',
                    allowBlank: false
             }},
             {header: "Fecha de Registro",
             width: 160,
             sortable: true,
             dataIndex: 'DTFECHAREGISTRO', 
             editor: {
                 xtype: 'datefield',                
                 format:'Y-m-d',
                 allowBlank: false,
             }
            }
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'PARTICIPANTES',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new PARTICIPANTE({
                	ICVEPARTICIPANTE: '0'
                	, CPARTICIPANTE: 'XXX'
                	, CFBCUENTA: 'Facebook'
                    , CCORREO: 'email@domain.com'  
                    , CNOMBRE: 'XXX'
                    , DTFECHAREGISTRO: new Date()
                    , ICVEPARTICIPANTE_ID:'0-'
                });
                editor.stopEditing();
                store.insert(0, e);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        },{
            iconCls: 'icon-delete',
            text: 'Eliminar registro',
            handler: function(){
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                    store.remove(r);
                }
            }
        },{
            iconCls: 'icon-save',
            text: 'Guardar Cambios',
            handler: function(){
                store.save();
            }
        }]
    });

    //render to DIV
    grid.render('crud-grid'); 
    
});