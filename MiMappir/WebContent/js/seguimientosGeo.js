Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var SEGUIMIENTOGEO = Ext.data.Record.create([
	{	
		name: 'icvesenal'
		,type: 'int'
	}, 
	{
        name: 'dlatitud',
        type: 'double'
    },
	{
        name: 'dlongitud',
        type: 'double'
    },
	{
        name: 'tsregistro',
        type: 'date'
    },
	{
        name: 'icveparticipante',
        type: 'int'
    }
	]);    
    
    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'SEGUIMIENTOSGEO_view.action',
            create : 'SEGUIMIENTOSGEO_create.action',
            update : 'SEGUIMIENTOSGEO_update.action',
            destroy : 'SEGUIMIENTOSGEO_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "icvesenal",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    SEGUIMIENTOGEO);
    
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	
    	    	icvesenal : rec.get('icvesenal')
				, dlatitud : rec.get('dlatitud')
				, dlongitud : rec.get('dlongitud')
				, tsregistro : rec.get('tsregistro')
				, icveparticipante : rec.get('icveparticipante')
    	    };
    	  }
    });
    
    
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'icvesenal',
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
    		msg: res.message,
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
			 dataIndex: 'icvesenal',
			 editor: {
			     xtype: 'textfield',
			     readOnly: true,
			     allowBlank: false
			 }
			},
            {header: "Latitud",
             width: 170,
             sortable: true,
             dataIndex: 'dlatitud',
             editor: {
            	 xtype: 'numberfield',
                 allowBlank: false         
            }},
             {header: "Longitud",
              width: 170,
              sortable: true,
              dataIndex: 'dlongitud',
              editor: {
                 xtype: 'numberfield',
                 allowBlank: false         
            }},
              {header: "Fecha de Registro",
               width: 170,
               sortable: true,
               dataIndex: 'tsregistro',
               editor: {
                  xtype: 'datefield',
                  format:'Y-m-d',
                  allowBlank: false         
              }},
               {header: "Cve Participante",
                width: 170,
                sortable: true,
                dataIndex: 'icveparticipante',
                editor: {
                	xtype: 'numberfield',
                    allowDecimals: false,
                    allowNegative: false, 
                    allowBlank: false           
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'SEGUIMIENTOS',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new SEGUIMIENTOGEO({
                	icvesenal: '0'                 
                    , dlatitud:'0.0'
                    , dlongitud:'0.0'
                    , tsregistro: new Date()
                    , icveparticipante:'0'
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