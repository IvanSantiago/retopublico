Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var COMENTARIOGEO = Ext.data.Record.create([
	{	
		name: 'icvecomentario'
		,type: 'int'
	}, 
	{
        name: 'icvecategoria',
        type: 'int'
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
        name: 'tsfechacomentario',
        type: 'date'
    },
	{
        name: 'ccomentario',
        type: 'string'
    }
	]);    
    
    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'COMENTARIOSGEO_view.action',
            create : 'COMENTARIOSGEO_create.action',
            update : 'COMENTARIOSGEO_update.action',
            destroy : 'COMENTARIOSGEO_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "icvecomentario",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    COMENTARIOGEO);
    
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	
    	    	icvecomentario : rec.get('icvecomentario')
				, icvecategoria : rec.get('icvecategoria')
				, dlatitud : rec.get('dlatitud')
				, dlongitud : rec.get('dlongitud')
				, tsfechacomentario : rec.get('tsfechacomentario')
				, ccomentario : rec.get('ccomentario')
    	    };
    	  }
    });
    
    
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'icvecomentario',
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
			 dataIndex: 'icvecomentario',
			 editor: {
			     xtype: 'textfield',
			     readOnly: true,
			     allowBlank: false
			 }
			},
            {header: "Cve Categoria",
             width: 170,
             sortable: true,
             dataIndex: 'icvecategoria',
             editor: {
            	 xtype: 'numberfield',
                 allowDecimals: false,
                 allowNegative: false, 
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
              {header: "Fecha del Comentario",
               width: 170,
               sortable: true,
               dataIndex: 'tsfechacomentario',
               editor: {
                  xtype: 'datefield',
                  format:'Y-m-d',
                  allowBlank: false         
              }},
               {header: "Comentario",
                width: 170,
                sortable: true,
                dataIndex: 'ccomentario',
                editor: {
                   xtype: 'textfield',
                   allowBlank: false         
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'COMENTARIOS',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new COMENTARIOGEO({
                	icvecomentario: '0'
                    , icvecategoria: '0'                   
                    , dlatitud:'0.0'
                    , dlongitud:'0.0'
                    , tsfechacomentario:new Date()
                    , ccomentario:'XXX'
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