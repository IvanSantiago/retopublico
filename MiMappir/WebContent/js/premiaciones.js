Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';

    var PREMIACION = Ext.data.Record.create([
	{	
		name: 'ICVEPREMIACION_ID'
		,mapping: 'ICVEPREMIACION_ID'
		,type:'string'
	},
	{	
		name: 'ICVEPREMIACION'
		,mapping: 'ICVEPREMIACION_PK.ICVEPREMIACION'
		,type: 'int'
	}, {
        name: 'IPUBLICADO',
        type: 'int'
	}, {
        name: 'DTFECHAPREMIACION',
        type: 'date'
	}, {
        name: 'ICVEPREMIO',
        type: 'int'
	}, {
        name: 'ICVEUSUARIOPREMIA',
        type: 'int'
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'PREMIACIONES_view.action',
            create : 'PREMIACIONES_create.action',
            update : 'PREMIACIONES_update.action',
            destroy : 'PREMIACIONES_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVEPREMIACION_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    PREMIACION);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVEPREMIACION_PK: {
    	    		ICVEPREMIACION : rec.get('ICVEPREMIACION')
				}
				, IPUBLICADO : rec.get('IPUBLICADO')
				, DTFECHAPREMIACION : rec.get('DTFECHAPREMIACION')				
				, ICVEPREMIO : rec.get('ICVEPREMIO')
				, ICVEUSUARIOPREMIA : rec.get('ICVEUSUARIOPREMIA')			
				, ICVEPREMIACION_ID : rec.get('ICVEPREMIACION')+"-"
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVEPREMIACION',
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
			    dataIndex: 'ICVEPREMIACION',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Publicado",
             width: 170,
             sortable: true,
             dataIndex: 'IPUBLICADO',
             editor: {
            	 xtype: 'numberfield',
                 allowDecimals: false,
                 allowNegative: false, 
                 allowBlank: false,
                 maxLength:1,
                 maxLengthText:"El campo requiere solo 1 digito"                          
            }},
            {header: "Fecha Premiacion",
                width: 170,
                sortable: true,
                dataIndex: 'DTFECHAPREMIACION',
                editor: {
                   xtype: 'datefield', 
                   format:'Y-m-d',
                   allowBlank: false                 
             }},
             {header: "Premio",
                 width: 170,
                 sortable: true,
                 dataIndex: 'ICVEPREMIO',
                 editor: {
                	 xtype: 'numberfield',
                     allowDecimals: false,
                     allowNegative: false, 
                     allowBlank: false,
             }},
             {header: "Clave usuario premiado",
                 width: 170,
                 sortable: true,
                 dataIndex: 'ICVEUSUARIOPREMIA',
                 editor: {
                	 xtype: 'numberfield',
                     allowDecimals: false,
                     allowNegative: false, 
                     allowBlank: false,
             }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'PREMIACIONES',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new PREMIACION({
                	ICVEPREMIACION: '0'
                	, IPUBLICADO: '0'
                	, DTFECHAPREMIACION: new Date()
                    , ICVEPREMIO: '0'  
                    , ICVEUSUARIOPREMIA: '0'
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